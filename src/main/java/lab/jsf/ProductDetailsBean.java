package lab.jsf;

import lab.ejb.ProductDao;
import lab.entities.Product;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * @Author Paul Bakker - paul.bakker@luminis.eu
 */
@RequestScoped
@Named
public class ProductDetailsBean {
    private long productId;
    private Product product;
    @Inject
    private ProductDao productDao;

    public void loadProduct() {
        product = productDao.findById(productId);
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
