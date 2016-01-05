package auction.service;

import ServiceConsumers.RegistrationConsumer;
import ServiceConsumers.TestConsumer;
import static org.junit.Assert.*;
import org.junit.*;
import webservice.User;

public class WebServiceRegistrationMgrTest {

    @Before
    public void setUp() throws Exception {      
        TestConsumer.databaseCleaner();
    }

    @Test
    public void registerUser() {
        User user1 = RegistrationConsumer.registerUser("xxx1@yyy");
        assertTrue(user1.getEmail().equals("xxx1@yyy"));
        User user2 = RegistrationConsumer.registerUser("xxx2@yyy2");
        assertTrue(user2.getEmail().equals("xxx2@yyy2"));
        User user2bis = RegistrationConsumer.registerUser("xxx2@yyy2");
        assertEquals(user2bis.getId(), user2.getId());
        //geen @ in het adres
        assertNull(RegistrationConsumer.registerUser("abc"));
    }

    @Test
    public void getUser() {
        User user1 = RegistrationConsumer.registerUser("xxx5@yyy55");
        User userGet = RegistrationConsumer.getUser("xxx5@yyy55");
        assertEquals(userGet.getId(), user1.getId());
        assertNull(RegistrationConsumer.getUser("aaa4@bb5"));
        RegistrationConsumer.registerUser("abc");
        assertNull(RegistrationConsumer.getUser("abc"));
    }
}
