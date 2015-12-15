/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package auction.dao;

import java.util.List;
import javax.persistence.*;
import auction.domain.*;
import javax.persistence.criteria.CriteriaQuery;

/**
 *
 * @author Thijn
 */
public class ItemDAOJPAImpl implements ItemDAO {
    private final EntityManager em;
    
    public ItemDAOJPAImpl(EntityManager em) {
        this.em = em;
    }
    
    @Override
    public int count() {
        Query q = em.createNamedQuery("Items.count", Item.class);
        return ((Long) q.getSingleResult()).intValue();
    }

    @Override
    public void create(Item item) {
        em.persist(item);
    }

    @Override
    public void edit(Item item) {
        em.merge(item);
    }

    @Override
    public Item find(Long id) {
        return em.find(Item.class, id);
    }

    @Override
    public List<Item> findAll() {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(Item.class));
        return em.createQuery(cq).getResultList();
    }

    @Override
    public List<Item> findByDescription(String description) {
        try {
            Query q = em.createNamedQuery("Items.FindByDesc", Item.class);
            q.setParameter("description", description);
            return q.getResultList();
        }
        catch (Exception e) {
            return null;
        }
    }

    @Override
    public void remove(Item item) {
        em.remove(em.merge(item));
    }
    
}
