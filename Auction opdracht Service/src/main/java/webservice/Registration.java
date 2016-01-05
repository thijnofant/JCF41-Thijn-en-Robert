/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webservice;

import auction.domain.*;
import auction.service.RegistrationMgr;
import javax.jws.WebService;

/**
 *
 * @author Thijn
 */
@WebService
public class Registration {
    private static final RegistrationMgr regMgr = new RegistrationMgr();
    
    public User registerUser(String email){
        return regMgr.registerUser(email);
    }
    
    public User getUser(String email){
        return regMgr.getUser(email);
    };
}
