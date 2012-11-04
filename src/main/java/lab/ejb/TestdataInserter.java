package lab.ejb;

import lab.entities.Product;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

/**
 * @Author Paul Bakker - paul.bakker@luminis.eu
 */
@Singleton
@Startup
public class TestdataInserter {

    @Inject
    ProductDao productDao;

    @PostConstruct
    public void insert() {
        for(int i = 1; i <= 5; i++) {
            productDao.saveProduct(new Product("Product " + i, i * 10));
        }

    }
}
