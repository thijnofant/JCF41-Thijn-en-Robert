package auction.service;

import auction.dao.ItemDAO;
import auction.dao.ItemDAOJPAImpl;
import auction.domain.*;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class SellerMgr {
    
    private ItemDAO itemDAO;
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("auctionPU");
    EntityManager em;
    
    
    
    /**
     * @param seller
     * @param cat
     * @param description
     * @return het item aangeboden door seller, behorende tot de categorie cat
     *         en met de beschrijving description
     */
    public Item offerItem(User seller, Category cat, String description) {
        Item temp = new Item(seller, cat, description);
        em = emf.createEntityManager();
        itemDAO = new ItemDAOJPAImpl(em);
        em.getTransaction().begin();
        itemDAO.create(temp);
        em.getTransaction().commit();
        return temp;
    }
    
     /**
     * @param item
     * @return true als er nog niet geboden is op het item. Het item word verwijderd.
     *         false als er al geboden was op het item.
     */
    public boolean revokeItem(Item item) {
        em = emf.createEntityManager();
        itemDAO = new ItemDAOJPAImpl(em);
        em.getTransaction().begin();
        Item foundItem = itemDAO.find(item.getId());

        if (foundItem.getHighestBid() != null) {
            em.getTransaction().commit();
            return false;
        }
        else
        {
            itemDAO.remove(foundItem);
            em.getTransaction().commit();
            return true;
        }
    }
}
