package lab.ejb;

import lab.entities.Product;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

@RunWith(Arquillian.class)
public class ProductDaoTest {
    @Inject
    private ProductDao productdao;

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class, "test.jar")
                .addClass(ProductDao.class)
                .addPackage(Product.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @Test
    public void testIsDeployed() {
        Assert.assertNotNull(productdao);
    }

    @Test
    public void testSaveProduct() {
        assertThat(productdao.listProducts().size(), is(0));

        productdao.saveProduct(new Product("someproduct", 10));

        assertThat(productdao.listProducts().size(), is(1));

    }
}
