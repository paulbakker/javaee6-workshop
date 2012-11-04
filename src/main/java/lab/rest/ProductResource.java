package lab.rest;

import lab.ejb.ProductDao;
import lab.entities.Product;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * @Author Paul Bakker - paul.bakker@luminis.eu
 */
@Path("products")
public class ProductResource {
    @Inject
    ProductDao productDao;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Product> listProducts() {
        return productDao.listProducts();
    }
}
