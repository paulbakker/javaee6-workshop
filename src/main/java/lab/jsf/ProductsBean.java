package lab.jsf;

import lab.ejb.ProductDao;
import lab.entities.Product;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

/**
 * @Author Paul Bakker - paul.bakker@luminis.eu
 */
@RequestScoped
@Named
public class ProductsBean {
    @Inject
    private ProductDao productDao;

    private List<Product> products;

    private String filter;

    public List<Product> getProducts() {
        if (products == null) {
            products = productDao.listProducts(filter);
        }

        return products;
    }

    public String getFilter() {
        return filter;
    }

    public void setFilter(String filter) {
        this.filter = filter;
        products = null;
    }
}
