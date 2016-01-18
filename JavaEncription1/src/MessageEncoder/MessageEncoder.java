/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MessageEncoder;

import java.io.BufferedOutputStream;
import java.io.*;
import java.security.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Thijn
 */
public class MessageEncoder {
    
    private static PrivateKey privKey;
    private static String path;
    private static String message;
    private static String name;
    private static String destPath;

    public static void main(String[] args) {
        path = "D:/Users/Thijn/Desktop/privKey.keyFile";
        message = "MESSAGE LOLOLO";
        name = "Thijn van Dijk";
        destPath = "D:/Users/Thijn/Desktop/";
        privKey = loadPrivKey(path);
        encodeMessage(message, name, destPath);
    }
    
    public static PrivateKey loadPrivKey(String path){
        PrivateKey recoveredKey = null;
        
        try(InputStream file = new FileInputStream(path);
            InputStream buffer = new BufferedInputStream(file);
            ObjectInput input = new ObjectInputStream (buffer);
         ){
            recoveredKey = (PrivateKey)input.readObject();
        }
        catch(ClassNotFoundException | IOException ex){
            System.err.println(ex.getMessage());
        }
        return recoveredKey;
    }
    
    
    public static void encodeMessage(String message, String name, String destinationPath){
        int signLenght = 0;
        byte[] signBytes;
        
        
        try {
            Signature sig = Signature.getInstance("SHA1withRSA");
            sig.initSign(privKey);
            signBytes = sig.sign();
            signLenght = signBytes.length;   
        } catch (NoSuchAlgorithmException | InvalidKeyException | SignatureException ex) {
            System.err.println(ex.getMessage());
            return;
        }
        
        try (
            OutputStream file = new FileOutputStream(destinationPath + "Input(SignedBy"+name+").ext");
            OutputStream buffer = new BufferedOutputStream(file);
            ObjectOutput output = new ObjectOutputStream(buffer);
        ){
            output.writeInt(signLenght);
            output.write(signBytes);
            output.writeBytes(message);
        }  
        catch(IOException ex){
            System.err.println(ex.getMessage());
        }   
    }   
}
