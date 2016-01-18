/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaencription2;

import controller.EncryptDecryptWithKey;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javax.crypto.SecretKey;

/**
 *
 * @author Thijn
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private ComboBox cbbActions;
    @FXML
    private PasswordField tbPassword;
    @FXML
    private TextArea tbMessage;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cbbActions.getItems().add("Encrypt");
        cbbActions.getItems().add("Decrypt");
        cbbActions.setValue("Encrypt");
    }    
    
    @FXML
    private void handleGoButtonClick(ActionEvent event){
        char[] pass = tbPassword.getText().toCharArray();
        SecretKey key = EncryptDecryptWithKey.createKeyFromPassword(pass);
        FileChooser choose = new FileChooser();
        
        switch (cbbActions.getValue().toString()) {
            case "Encrypt":
                File saveFile = choose.showSaveDialog(null);
                EncryptDecryptWithKey.encrypt(key, tbMessage.getText(), saveFile);
                break;
            case "Decrypt":
                File openFile = choose.showOpenDialog(null);
               tbMessage.setText(EncryptDecryptWithKey.decrypt(key, openFile));
                break;
        }
    } 
}
