/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Domain;

import bank.domain.Account;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import util.DatabaseCleaner;

/**
 *
 * @author Thijn
 */
public class domainTest {
    
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("bankPU");
    EntityManager em = emf.createEntityManager();
    DatabaseCleaner dc = new DatabaseCleaner(em);   
    
    public domainTest() {
    }
    
    @Before
    public void setUp() {
        try{
            dc.clean();
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void opdracht1() {
        Account account = new Account(111L);
        em.getTransaction().begin();
        em.persist(account);
        //TODO: verklaar en pas eventueel aan
        assertNull(account.getId());
        em.getTransaction().commit();
        System.out.println("AccountId: " + account.getId());
        //TODO: verklaar en pas eventueel aan
        assertTrue(account.getId() > 0L);
    }
}
