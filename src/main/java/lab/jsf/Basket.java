package lab.jsf;

import lab.entities.Customer;
import lab.entities.Product;
import lab.entities.WebOrder;
import lab.events.WebOrderCreated;

import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @Author Paul Bakker - paul.bakker@luminis.eu
 */
@Named
@ConversationScoped
public class Basket implements Serializable {
    private final List<Product> products = new CopyOnWriteArrayList<Product>();

    @Inject
    private Conversation conversation;

    private Customer customer = new Customer();


    public void addProduct(Product product) {
        if (conversation.isTransient()) {
            conversation.begin();
        }

        products.add(product);
    }

    public List<Product> getProducts() {
        return products;
    }

    public double getTotal() {
        double total = 0;
        for (Product product : products) {
            total += product.getPrice();
        }

        return total;
    }

    @Inject
    @WebOrderCreated
    Event<WebOrder> webOrderEvent;

    public String checkout() {
        if (!conversation.isTransient()) {
            conversation.end();
        }

        webOrderEvent.fire(new WebOrder(products, customer));

        return "index.xhtml?faced-redirect=true";
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
