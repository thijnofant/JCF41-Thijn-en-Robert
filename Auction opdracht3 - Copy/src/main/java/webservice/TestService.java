/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webservice;

import javax.jws.WebService;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Thijn
 */
@WebService
public class TestService {
    
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("auctionPU");
    EntityManager em = emf.createEntityManager();
    DatabaseCleaner dc = new DatabaseCleaner(em);

    public void databaseCleaner(){
        em = emf.createEntityManager();
        dc = new DatabaseCleaner(em);
        try{
            dc.clean();
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}
