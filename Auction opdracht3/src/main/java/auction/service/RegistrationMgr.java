package auction.service;

import java.util.*;
import auction.domain.User;
import auction.dao.UserDAOJPAImpl;
import auction.dao.UserDAO;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class RegistrationMgr {

    private UserDAO userDAO;
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("auctionPU");
    EntityManager em;
    
    public RegistrationMgr() {       
    }

    /**
     * Registreert een gebruiker met het als parameter gegeven e-mailadres, mits
     * zo'n gebruiker nog niet bestaat.
     * @param email
     * @return Een Userobject dat geïdentificeerd wordt door het gegeven
     * e-mailadres (nieuw aangemaakt of reeds bestaand). Als het e-mailadres
     * onjuist is ( het bevat geen '@'-teken) wordt null teruggegeven.
     */
    public User registerUser(String email) {
        em = emf.createEntityManager();
        userDAO = new UserDAOJPAImpl(em);
        em.getTransaction().begin();
        if (!email.contains("@")) {
            em.close();
            return null;
        }
        User user = userDAO.findByEmail(email);
        if (user != null) {
            em.close();
            return user;
        }
        user = new User(email);
        userDAO.create(user);
        em.getTransaction().commit();
        return user;
    }

    /**
     *
     * @param email een e-mailadres
     * @return Het Userobject dat geïdentificeerd wordt door het gegeven
     * e-mailadres of null als zo'n User niet bestaat.
     */
    public User getUser(String email) {
        em = emf.createEntityManager();
        userDAO = new UserDAOJPAImpl(em);
        em.getTransaction().begin();
        User foundUser = userDAO.findByEmail(email);
        em.getTransaction().commit();
        return foundUser;
    }

    /**
     * @return Een iterator over alle geregistreerde gebruikers
     */
    public List<User> getUsers() {
        em = emf.createEntityManager();
        userDAO = new UserDAOJPAImpl(em);
        em.getTransaction().begin();
        List<User> users = userDAO.findAll();
        em.getTransaction().commit();
        return users;
    }
}
