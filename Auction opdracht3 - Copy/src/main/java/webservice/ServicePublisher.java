/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webservice;

import javax.xml.ws.Endpoint;

/**
 *
 * @author Thijn
 */
public class ServicePublisher {
    private static final String url = "http://localhost:8080/Auction";
    private static final String url2 = "http://localhost:8080/Registration";
    private static final String url3 = "http://localhost:8080/Test";

    public static void main(String[] args) {
        Endpoint.publish(url, new Auction());
        Endpoint.publish(url2, new Registration());
        Endpoint.publish(url3, new TestService());
    }
}
