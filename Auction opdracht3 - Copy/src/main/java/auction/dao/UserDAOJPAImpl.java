package auction.dao;

import auction.domain.User;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;

public class UserDAOJPAImpl implements UserDAO {

    private final EntityManager em;

    public UserDAOJPAImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public int count() {
        Query q = em.createNamedQuery("Gebruikers.count", User.class);
        return ((Long) q.getSingleResult()).intValue();
    }

    @Override
    public void create(User user) {
        em.persist(user);
        
    }

    @Override
    public void edit(User user) {
        em.merge(user);
        
    }


    @Override
    public List<User> findAll() {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(User.class));
        return em.createQuery(cq).getResultList();
    }

    @Override
    public User findByEmail(String email) {
        try {
            Query q = em.createNamedQuery("Gebruikers.findByEmail", User.class);
            q.setParameter("email", email);
            return (User) q.getSingleResult();
        }
        catch (Exception e) {
            return null;
        }
    }

    @Override
    public void remove(User user) {
        em.remove(em.merge(user));
    }
}
