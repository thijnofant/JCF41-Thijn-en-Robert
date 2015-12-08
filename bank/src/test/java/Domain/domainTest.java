/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Domain;

import bank.dao.AccountDAOJPAImpl;
import bank.domain.Account;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import util.DatabaseCleaner;

/**
 *
 * @author Thijn
 */
public class domainTest {
    
    /**
     * Voor elke test moet je in ieder geval de volgende vragen beantwoorden:
     *  1.	Wat is de waarde van asserties en printstatements? Corrigeer verkeerde asserties zodat de test ‘groen’ wordt.
     *  2.	Welke SQL statements worden gegenereerd?
     *  3.	Wat is het eindresultaat in de database?
     *  4.	Verklaring van bovenstaande drie observaties.
     */
    
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
        em = emf.createEntityManager();
    }

    /**
     * Hoe werken persist en commit in samenhang met de database.
     * 
     * 1. assertNull(account.getId()); --> Slaagt omdat de ID nog niet bekend is voor het invoegen van de account in de database.
     *    System.out.println("AccountId: " + account.getId()); -->  Een cijfer dat elke run een hoger wordt.
     *    assertTrue(account.getId() > 0L); --> Slaagt omdat de ID die wordt toegekent door de db hoger is dan 0
     * 2. INSERT INTO ACCOUNT (ACCOUNTNR, BALANCE, THRESHOLD) VALUES (?, ?, ?)	bind => [111, 0, 0];
     *    SELECT LAST_INSERT_ID();
     * 3. Account: 111, 0, 0
     * 4. 
     */
    @Test
    public void opdracht1() {
        System.out.println("Opdracht1 Start");
        Account account = new Account(111L);
        em.persist(account);
        
        em.getTransaction().begin();
        //TODO: verklaar en pas eventueel aan
        //Id is null opmdat je hem niet zelf toevoegd
        assertNull(account.getId());
        em.getTransaction().commit();
        System.out.println("AccountId: " + account.getId());
        //TODO: verklaar en pas eventueel aan
        //Id moet positief zijn
        assertTrue(account.getId() > 0L);
        System.out.println("Opdracht1 End");
    }
    
    /**
     * Rollback
     * 1.	Wat is de waarde van asserties en printstatements? Corrigeer verkeerde asserties zodat de test ‘groen’ wordt.
     * 1.       assertNull(account.getId()); --> Slaagt omdat transactie nog niet gecommit is.
     *          assertEquals("Table Account is niet leeg.", 0, accDAO.count()); --> Slaagt omdat de transactie gerollbacked is.
     * 2.	Welke SQL statements worden gegenereerd?
     * 2.       DELETE FROM ACCOUNT
     *          SELECT COUNT(ID) FROM ACCOUNT
     * 3.	Wat is het eindresultaat in de database?
     * 3.       Een lege account tabel.
     * 4.	Verklaring van bovenstaande drie observaties.
     * 4.       Het account object wordt gepersisteerd. Maar de transactie wordt niet uitgevoerd
     *          omdat rollback wordt aangeroepen. Hierdoor geeft de assertEquals() true terug omdat
     *          er geen records zijn toegevoegd aan de database.
     */
    @Test
    public void opdracht2(){
        System.out.println("Opdracht2 Start");
        Account account = new Account(111L);
        em.getTransaction().begin();
        em.persist(account);
        assertNull(account.getId());
        em.getTransaction().rollback();
        
        // TODO code om te testen dat table account geen records bevat. 
        // Hint: bestudeer/gebruik AccountDAOJPAImpl
        AccountDAOJPAImpl accDAO = new AccountDAOJPAImpl(em);
        assertEquals("Table Account is niet leeg.", 0, accDAO.count());
        System.out.println("Opdracht2 End");
    }
    
    /**
     * Flushen maar
     * 1.	Wat is de waarde van asserties en printstatements? Corrigeer verkeerde asserties zodat de test ‘groen’ wordt.
     * 1.       assertEquals(expected, account.getId()); = true
     *          assertNotEquals(expected, account.getId()); = true
     * 2.	Welke SQL statements worden gegenereerd?
     * 2.       INSERT INTO ACCOUNT (ACCOUNTNR, BALANCE, THRESHOLD) VALUES (?, ?, ?) bind => [111, 0, 0]
     * 3.	Wat is het eindresultaat in de database?
     * 3.       Account: 111, 0, 0
     * 4.	Verklaring van bovenstaande drie observaties.
     * 4.       
     */
    @Test
    public void opdracht3(){
        System.out.println("Opdracht3 Start");
        Long expected = -100L;
        Account account = new Account(111L);
        account.setId(expected);
        em.getTransaction().begin();
        em.persist(account);
        
        //TODO: verklaar en pas eventueel aan
        assertEquals(expected, account.getId());
        em.flush();
        assertNotEquals(expected, account.getId());
        //TODO: verklaar en pas eventueel aan
        //assertEquals(expected, account.getId();
        em.getTransaction().commit();
        //TODO: verklaar en pas eventueel aan
        System.out.println("Opdracht3 End");
    }
    
    /**
     * Veranderingen na de persist
     * 1.	Wat is de waarde van asserties en printstatements? Corrigeer verkeerde asserties zodat de test ‘groen’ wordt.
     * 1.       assertEquals(expectedBalance, account.getBalance()); = true
     *          assertEquals(expectedBalance, found.getBalance()); = true
     * 2.	Welke SQL statements worden gegenereerd?
     * 2.       INSERT INTO ACCOUNT (ACCOUNTNR, BALANCE, THRESHOLD) VALUES (?, ?, ?) bind => [114, 400, 0]
                SELECT LAST_INSERT_ID(); result =  [93]
                SELECT ID, ACCOUNTNR, BALANCE, THRESHOLD FROM ACCOUNT WHERE (ID = ?) bind => [93]
     * 3.	Wat is het eindresultaat in de database?
     * 3.       Account: 93, 114, 400, 0
     * 4.	Verklaring van bovenstaande drie observaties.
     * 4.       
     */
    @Test
    public void opdracht4(){
        System.out.println("Opdracht4 Start");
        Long expectedBalance = 400L;
        Account account = new Account(114L);
        em.getTransaction().begin();
        em.persist(account);
        account.setBalance(expectedBalance);
        em.getTransaction().commit();
        assertEquals(expectedBalance, account.getBalance());
        //TODO: verklaar de waarde van account.getBalance
        Long acId = account.getId();
        account = null;
        EntityManager em2 = emf.createEntityManager();
        em2.getTransaction().begin();
        Account found = em2.find(Account.class, acId);
        //TODO: verklaar de waarde van found.getBalance
        assertEquals(expectedBalance, found.getBalance());
        System.out.println("Opdracht4 End");
    }
    
    /**
     * Refresh
     * In de vorige opdracht verwijzen de objecten account en found naar dezelfde rij in de database. 
       Pas een van de objecten aan, persisteer naar de database. 
       Refresh vervolgens het andere object om de veranderde state uit de database te halen. 
       Test met asserties dat dit gelukt is.
     * 1.	Wat is de waarde van asserties en printstatements? Corrigeer verkeerde asserties zodat de test ‘groen’ wordt.
     * 1.       assertEquals(newBalance, account.getBalance()); = true -> de balans van het object is aangepast
     *          assertEquals(newBalance, found.getBalance()); = true -> door het aanroepen van de refreshmethode is ook de balans van dit object aangepast
     * 2.	Welke SQL statements worden gegenereerd?
     * 2.       
     * 3.	Wat is het eindresultaat in de database?
     * 3.       
     * 4.	Verklaring van bovenstaande drie observaties.
     * 4.       
     */
    //TODO
    @Test
    public void opdracht5(){
        System.out.println("Opdracht5 Start");
        Long expectedBalance = 400L;
        Account account = new Account(114L);
        em.getTransaction().begin();
        em.persist(account);
        account.setBalance(expectedBalance);
        em.getTransaction().commit();
        assertEquals(expectedBalance, account.getBalance());

        Long acId = account.getId();
        EntityManager em2 = emf.createEntityManager();
        em2.getTransaction().begin();
        Account found = em2.find(Account.class, acId);
        assertEquals(expectedBalance, found.getBalance());
        
        // Begin opdracht 5
        // Balans eerste object aanpassen.
        Long newBalance = 222L;
        em.getTransaction().begin();
        account.setBalance(newBalance);
        em.getTransaction().commit();
        assertEquals(newBalance, account.getBalance());
        
        // Tweede object refreshen.
        em2.refresh(found);
        assertEquals(newBalance, found.getBalance());
        System.out.println("Opdracht5 End");
    }
    
    /**
     * Merge
     * 1.	Wat is de waarde van asserties en printstatements? Corrigeer verkeerde asserties zodat de test ‘groen’ wordt.
     * 1.       
     * 2.       Welke SQL statements worden gegenereerd?
     * 2.       
     * 3.	Wat is het eindresultaat in de database?
     * 3.       
     * 4.	Verklaring van bovenstaande drie observaties.
     * 4.       
     */
    @Test
    public void opdracht6(){
        
        // <editor-fold desc="scenario 2:>
        // </editor-fold>
        
        System.out.println("Opdracht6 Start");
        Account acc = new Account(1L);
        Account acc2 = new Account(2L);
        Account acc9 = new Account(9L);
     
        // <editor-fold desc="scenario 1">
        Long balance1 = 100L;
        em.getTransaction().begin();
        assertNotEquals(balance1, acc.getBalance());
        em.persist(acc);
        acc.setBalance(balance1);
        assertEquals(balance1, acc.getBalance());
        em.getTransaction().commit();
        assertEquals(balance1, acc.getBalance());
        //TODO: voeg asserties toe om je verwachte waarde van de attributen te verifieren.
        //TODO: doe dit zowel voor de bovenstaande java objecten als voor opnieuw bij de entitymanager opgevraagde objecten met overeenkomstig Id.
        // </editor-fold>

        // <editor-fold desc="scenario 2">
        Long balance2a = 211L;
        acc = new Account(2L);
        em.getTransaction().begin();
        acc9 = em.merge(acc);
        assertEquals(acc.getBalance(), acc9.getBalance());
        acc.setBalance(balance2a);
        acc9.setBalance(balance2a+balance2a);
        assertEquals((Long)(acc.getBalance()*2L), acc9.getBalance());
        assertNotEquals(acc.getBalance(), acc9.getBalance());
        em.getTransaction().commit();
        //TODO: voeg asserties toe om je verwachte waarde van de attributen te verifiëren.
        //TODO: doe dit zowel voor de bovenstaande java objecten als voor opnieuw bij de entitymanager opgevraagde objecten met overeenkomstig Id. 
        // HINT: gebruik acccountDAO.findByAccountNr
        // </editor-fold>

        // <editor-fold desc="scenario 3">        
        em = emf.createEntityManager();
        
        Long balance3b = 322L;
        Long balance3c = 333L;
        acc = new Account(3L);
        em.persist(acc);
        em.getTransaction().begin();
        assertTrue(em.contains(acc)); // verklaar
        assertFalse(em.contains(acc2)); // verklaar
        acc2 = em.merge(acc);
        assertTrue(em.contains(acc2)); // verklaar
        assertEquals(acc,acc2);  //verklaar
        acc2.setBalance(balance3b);
        acc.setBalance(balance3c);
        assertEquals(acc,acc2);
        em.getTransaction().commit();
        assertEquals(acc,acc2);
        //TODO: voeg asserties toe om je verwachte waarde van de attributen te verifiëren.
        //TODO: doe dit zowel voor de bovenstaande java objecten als voor opnieuw bij de entitymanager opgevraagde objecten met overeenkomstig Id.
        // </editor-fold>
        
        // <editor-fold desc="scenario 4 kan het mondeling verklaren">
        Account account = new Account(114L);
        account.setBalance(450L);
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(account);
        em.getTransaction().commit();

        Account account2 = new Account(114L);
        Account tweedeAccountObject = account2;
        tweedeAccountObject.setBalance(650l);
        assertEquals((Long)650L,account2.getBalance());  //verklaar
        account2.setId(account.getId());
        em.getTransaction().begin();
        account2 = em.merge(account2);
        assertSame(account,account2);  //verklaar
        assertTrue(em.contains(account2));  //verklaar
        assertFalse(em.contains(tweedeAccountObject));  //verklaar
        tweedeAccountObject.setBalance(850l);
        assertEquals((Long)650L,account.getBalance());  //verklaar
        assertEquals((Long)650L,account2.getBalance());  //verklaar
        em.getTransaction().commit();
        em.close();
        // </editor-fold>
        
        System.out.println("Opdracht6 End");
    }
    
    /**
     * Find en clear
     * 1.	Wat is de waarde van asserties en printstatements? Corrigeer verkeerde asserties zodat de test ‘groen’ wordt.
     * 1.       assertSame(accF1, accF2); = true
     *          assertNotSame(accF1, accF2); = true
     * 2.	Welke SQL statements worden gegenereerd?
     * 2.       
     * 3.	Wat is het eindresultaat in de database?
     * 3.       
     * 4.	Verklaring van bovenstaande drie observaties.
     * 4.       
     */
    //TODO
    @Test
    public void opdracht7(){
        System.out.println("Opdracht7 Start");
        Account acc1 = new Account(77L);
        em.getTransaction().begin();
        em.persist(acc1);
        em.getTransaction().commit();
        //Database bevat nu een account.

        // scenario 1        
        Account accF1;
        Account accF2;
        accF1 = em.find(Account.class, acc1.getId());
        accF2 = em.find(Account.class, acc1.getId());
        assertSame(accF1, accF2);

        // scenario 2        
        accF1 = em.find(Account.class, acc1.getId());
        em.clear();
        accF2 = em.find(Account.class, acc1.getId());
        assertNotSame(accF1, accF2);
        //TODO verklaar verschil tussen beide scenario's
        System.out.println("Opdracht7 End");
    }
    
    /**
     * Remove
     * 1.	Wat is de waarde van asserties en printstatements? Corrigeer verkeerde asserties zodat de test ‘groen’ wordt.
     * 1.       
     * 2.	Welke SQL statements worden gegenereerd?
     * 2.       
     * 3.	Wat is het eindresultaat in de database?
     * 3.       
     * 4.	Verklaring van bovenstaande drie observaties.
     * 4.       
     */
    @Test
    public void opdracht8(){
        System.out.println("Opdracht8 Start");
        Account acc1 = new Account(88L);
        em.getTransaction().begin();
        em.persist(acc1);
        em.getTransaction().commit();
        Long id = acc1.getId();
        //Database bevat nu een account.
        
        em.remove(acc1);
        assertEquals(id, acc1.getId());        
        Account accFound = em.find(Account.class, id);
        assertNull(accFound);
        //Je verwijderd de account en daardoor is deze null
        System.out.println("Opdracht8 End");
    }
    
    /**
     * Generation type
     * Opgave 1 heb je uitgevoerd met @GeneratedValue(strategy = GenerationType.IDENTITY)
        Voer dezelfde opdracht nu uit met GenerationType SEQUENCE en TABLE.
        Verklaar zowel de verschillen in testresultaat als verschillen van de database structuur.

     * 1.	Wat is de waarde van asserties en printstatements? Corrigeer verkeerde asserties zodat de test ‘groen’ wordt.
     * 1.       SEQUENCE = Objecten wordt een ID toegekend voordat ze aan de database toegevoegd worden. Hierdoor kunnen
     *                      gaten ontstaan tussen IDs in de db wanneer objecten aangemaakt worden maar niet gepersisteerd worden.
     *          TABLE = Bijna hetzelfde als sequence maar table kijkt naar de laatst gebruikte ID en sequence naar de volgende.
     */
   
}
