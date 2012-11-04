package lab.ejb;

import lab.entities.Customer;
import lab.entities.Product;
import lab.entities.WebOrder;
import lab.events.OrderDBProcessor;
import lab.events.WebOrderCreated;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.enterprise.event.Event;
import javax.inject.Inject;
import java.util.Arrays;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

@RunWith(Arquillian.class)
public class WebOrderEventTest {
    @Inject
    private WebOrderDao weborderdao;

    @Inject
    private ProductDao productDao;



    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class, "test.jar")
                .addClasses(WebOrderDao.class, OrderDBProcessor.class, ProductDao.class, WebOrderCreated.class)
                .addPackage(WebOrder.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @Inject @WebOrderCreated
    Event<WebOrder> webOrderEvent;

    @Test
    public void testIsEventHandled() {
        assertThat(weborderdao.listOrders().size(), is(0));

        Product product = new Product("p1", 10);
        productDao.saveProduct(product);

        webOrderEvent.fire(new WebOrder(Arrays.asList(product), new Customer("test", "test@email.com")));

        assertThat(weborderdao.listOrders().size(), is(1));
    }




}
