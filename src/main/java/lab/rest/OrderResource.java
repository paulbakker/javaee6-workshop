package lab.rest;

import lab.ejb.ProductDao;
import lab.entities.Customer;
import lab.entities.Product;
import lab.entities.WebOrder;
import lab.events.WebOrderCreated;

import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author Paul Bakker - paul.bakker@luminis.eu
 */
@Path("order")
public class OrderResource {

    @Inject
    @WebOrderCreated
    Event<WebOrder> webOrderEvent;

    @Inject
    ProductDao productDao;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void placeOrder(RestOrder restOrder) {
        List<Product> products = new ArrayList<Product>();

        for (long id : restOrder.getProductIds()) {
            products.add(productDao.findById(id));
        }

        Customer customer = new Customer(restOrder.getCustomerName(), restOrder.getCustomerEmail());
        webOrderEvent.fire(new WebOrder(products, customer));
    }
}
