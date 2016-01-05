package auction.service;

import auction.dao.UserDAO;
import nl.fontys.util.Money;
import auction.domain.Bid;
import auction.domain.Item;
import auction.domain.User;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import auction.dao.*;

public class AuctionMgr  {

    private ItemDAO itemDAO;
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("auctionPU");
    EntityManager em;
    
   /**
     * @param id
     * @return het item met deze id; als dit item niet bekend is wordt er null
     *         geretourneerd
     */
    public Item getItem(Long id) {
        em = emf.createEntityManager();
        itemDAO = new ItemDAOJPAImpl(em);
        em.getTransaction().begin();
        Item foundItem = itemDAO.find(id);
        em.getTransaction().commit();
        return foundItem;
    }

  
   /**
     * @param description
     * @return een lijst met items met @desciption. Eventueel lege lijst.
     */
    public List<Item> findItemByDescription(String description) {
        em = emf.createEntityManager();
        itemDAO = new ItemDAOJPAImpl(em);
        em.getTransaction().begin();
        List<Item> foundItems = itemDAO.findByDescription(description);
        em.getTransaction().commit();
        return foundItems;
    }

    /**
     * @param item
     * @param buyer
     * @param amount
     * @return het nieuwe bod ter hoogte van amount op item door buyer, tenzij
     *         amount niet hoger was dan het laatste bod, dan null
     */
    public Bid newBid(Item item, User buyer, Money amount) {
        em = emf.createEntityManager();
        itemDAO = new ItemDAOJPAImpl(em);
        em.getTransaction().begin();
        Bid temp = item.newBid(buyer, amount);
        itemDAO.edit(item);
        em.getTransaction().commit();
        return temp;
    }
}
