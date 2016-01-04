/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculatorws_client_application;

import java.util.logging.Level;
import java.util.logging.Logger;
import webservice.NegativeNumberException_Exception;
import webservice.WebCalculator;
import webservice.WebCalculatorService;

/**
 *
 * @author Thijn
 */
public class CalculatorWS_Client_Application {

    private static final WebCalculatorService service = new WebCalculatorService();
    
    private static int add(int x, int y){
        WebCalculator port = service.getWebCalculatorPort();
        try {
            return port.add(x,y);
        } catch (NegativeNumberException_Exception ex) {
            Logger.getLogger(CalculatorWS_Client_Application.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        System.out.println(CalculatorWS_Client_Application.add(500, 2));
        
    }
    
}
