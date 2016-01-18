/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package KeyGen;

import java.io.*;
import java.security.*;

/**
 *
 * @author Thijn
 */
public class KeyGenerator {
    
    private static PublicKey pubKey;
    private static PrivateKey privKey;
    
    public static void main(String[] args) {
        try{
            KeyPair pair = KeyPairGenerator.getInstance("rsa").generateKeyPair();
            pubKey = pair.getPublic();
            privKey = pair.getPrivate();
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        
        writeToFile("private");
        writeToFile("public");
    }
    
    private static void writeToFile(String privPub){
        
        String filePath = "";
        
        switch(privPub){
            case "private":
                filePath = "D:/Users/Thijn/Desktop/privKey.keyFile";
                try (
                    OutputStream file = new FileOutputStream(filePath);
                    OutputStream buffer = new BufferedOutputStream(file);
                    ObjectOutput output = new ObjectOutputStream(buffer);
                ){
                    output.writeObject(privKey);
                }  
                catch(IOException ex){
                    System.err.println(ex.getMessage());
                }
                break;
            case "public":
                filePath = "D:/Users/Thijn/Desktop/pubKey.keyFile";
                
                try (
                    OutputStream file = new FileOutputStream(filePath);
                    OutputStream buffer = new BufferedOutputStream(file);
                    ObjectOutput output = new ObjectOutputStream(buffer);
                ){
                    output.writeObject(pubKey);
                }  
                catch(IOException ex){
                    System.err.println(ex.getMessage());
                }
                break;
        }
    }
}

