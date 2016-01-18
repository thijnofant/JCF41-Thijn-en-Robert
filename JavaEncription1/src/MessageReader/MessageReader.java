/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MessageReader;

import java.io.*;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;

/**
 *
 * @author Thijn
 */
public class MessageReader {
    
    private static PublicKey pubKey;
    private static String path;
    private static String message;
    private static String name;
    private static String destPath;
    
    public static void main(String[] args) {
        path = "D:/Users/Thijn/Desktop/pubKey.keyFile";
        name = "Thijn van Dijk";
        destPath = "D:/Users/Thijn/Desktop/";
        pubKey = loadPubKey(path);
        message = decodeMessage(name, destPath);
        System.out.println("MessageOut: " + message);
    }
    
    public static String decodeMessage(String name, String destinationPath){
        int signLenght = 0;
        int messageLenght = 0;
        File filetoread = new File(destinationPath + "Input(SignedBy"+name+").ext");
        
        try (
            InputStream file = new FileInputStream(filetoread);
            InputStream buffer = new BufferedInputStream(file);
            ObjectInput input = new ObjectInputStream(buffer);
        ){
            signLenght = input.readInt();
            messageLenght = (int)filetoread.length();
            byte[] signature = new byte[signLenght];
            byte[] readMessage = new byte[messageLenght];
            input.read(signature, 0, signLenght);
            input.read(readMessage);
            Signature sig = Signature.getInstance("SHA1withRSA");
            sig.initVerify(pubKey);
            if (!sig.verify(signature)){
                return "Wrong signature";
            }
            System.out.println(new String(readMessage));
            return new String(readMessage);
        }  
        catch(IOException | NoSuchAlgorithmException | InvalidKeyException | SignatureException ex){
            System.err.println(ex.getMessage());
            return null;
        }   
        
    }
    
    public static PublicKey loadPubKey(String pubKeyPath){
        PublicKey recoveredKey = null;
        
        try(InputStream file = new FileInputStream(path);
            InputStream buffer = new BufferedInputStream(file);
            ObjectInput input = new ObjectInputStream (buffer);
         ){
            recoveredKey = (PublicKey)input.readObject();
        }
        catch(ClassNotFoundException | IOException ex){
            System.err.println(ex.getMessage());
        }
        return recoveredKey;
    }
}
