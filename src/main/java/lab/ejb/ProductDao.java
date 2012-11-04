package lab.ejb;

import lab.entities.Product;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * @Author Paul Bakker - paul.bakker@luminis.eu
 */
@Stateless
public class ProductDao {
    @PersistenceContext
    private EntityManager em;

    public void saveProduct(Product product) {
        if (product.getId() == 0) {
            em.persist(product);
        } else {
            em.merge(product);
        }
    }

    public List<Product> listProducts() {
        return listProducts(null);
    }

    public List<Product> listProducts(String filter) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Product> query = cb.createQuery(Product.class);
        Root<Product> product = query.from(Product.class);
        query.select(product);

        if(filter != null) {
            query.where(cb.like(product.<String>get("name"), filter +"%"));
        }

        return em.createQuery(query).getResultList();
    }

    public Product findById(long id) {
        return em.find(Product.class, id);
    }
}
