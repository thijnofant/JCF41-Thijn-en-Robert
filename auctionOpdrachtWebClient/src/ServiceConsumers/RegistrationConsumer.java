/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ServiceConsumers;

import webservice.*;

/**
 *
 * @author Thijn
 */
public class RegistrationConsumer {

    public static User getUser(java.lang.String arg0) {
        webservice.RegistrationService service = new webservice.RegistrationService();
        webservice.Registration port = service.getRegistrationPort();
        return port.getUser(arg0);
    }

    public static User registerUser(java.lang.String arg0) {
        webservice.RegistrationService service = new webservice.RegistrationService();
        webservice.Registration port = service.getRegistrationPort();
        return port.registerUser(arg0);
    }
    
}
