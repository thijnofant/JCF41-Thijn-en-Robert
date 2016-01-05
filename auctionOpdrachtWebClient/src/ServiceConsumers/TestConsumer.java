/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ServiceConsumers;

/**
 *
 * @author Thijn
 */
public class TestConsumer {

    public static void databaseCleaner() {
        webservice.TestServiceService service = new webservice.TestServiceService();
        webservice.TestService port = service.getTestServicePort();
        port.databaseCleaner();
    }
    
}
