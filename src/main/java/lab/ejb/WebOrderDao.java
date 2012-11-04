package lab.ejb;

import lab.entities.WebOrder;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * @Author Paul Bakker - paul.bakker@luminis.eu
 */
@Stateless
public class WebOrderDao {
    @PersistenceContext
    EntityManager em;

    public void save(WebOrder webOrder) {
        em.persist(webOrder);
    }

    public List<WebOrder> listOrders() {
        return em.createQuery("select o from WebOrder o", WebOrder.class).getResultList();
    }
}
