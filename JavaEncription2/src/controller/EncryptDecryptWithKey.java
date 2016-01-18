/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.*;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;

/**
 *
 * @author Thijn
 */
public class EncryptDecryptWithKey {
    public static SecretKey createKeyFromPassword(char[] pass){
        
        try {
            PBEKeySpec keySpec = new PBEKeySpec(pass);
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBEWithMD5AndDES");
            SecretKey key = factory.generateSecret(keySpec);
            keySpec.clearPassword();
            
            pass = new char[]{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
            
            return key;
        } catch (NoSuchAlgorithmException | InvalidKeySpecException ex) {
            Logger.getLogger(EncryptDecryptWithKey.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public static void encrypt(SecretKey key ,String message, File filetowrite){      
        try{
        int iterations = 1000;
        byte[] salt = new byte[8];
        SecureRandom random = new SecureRandom();
        random.nextBytes(salt);
        
        PBEParameterSpec PBESpec = new PBEParameterSpec(salt, iterations);
        Cipher cipher = Cipher.getInstance("PBEWithMD5AndDES");
        cipher.init(Cipher.ENCRYPT_MODE, key, PBESpec);
        
        byte[] ciphertext = cipher.doFinal(message.getBytes());
            try (FileOutputStream fileStream = new FileOutputStream(filetowrite)) {
                ByteArrayOutputStream outStream = new ByteArrayOutputStream();
                outStream.write(salt);
                outStream.write(ciphertext);
                outStream.writeTo(fileStream);
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        System.out.println("file was written");
    }
    
    public static String decrypt(SecretKey key, File filetoread){
               
        try {
            int iterations = 1000;
            byte[] salt = new byte[8];
            byte[] readFile = Files.readAllBytes(filetoread.toPath());
            FileInputStream inputStream = new FileInputStream(filetoread);
            inputStream.read(salt, 0 , 8);
            
            PBEParameterSpec paramSpec = new PBEParameterSpec(salt, iterations);
            Cipher cipher = Cipher.getInstance("PBEWithMD5AndDES");
            cipher.init(Cipher.DECRYPT_MODE, key, paramSpec);
            
            byte[] filteredByteArray = Arrays.copyOfRange(readFile, 8,readFile.length);
            byte[] filteredFixedSize = new byte[16];
            System.arraycopy(filteredByteArray, 0,filteredFixedSize, 0, filteredByteArray.length);
            
            byte[] ciphertext = cipher.doFinal(filteredByteArray);
            
            System.out.println("retrieved message:" + new String(ciphertext));
            return new String(ciphertext);
        } catch (IOException | NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException ex) {
            ex.printStackTrace();
            return null;
        }
        
        
    }
}
