package lab.entities;

import javax.persistence.*;
import java.util.List;

/**
 * @Author Paul Bakker - paul.bakker@luminis.eu
 */
@Entity
public class WebOrder {
    @Id @GeneratedValue
    private long id;

    @ManyToMany
    private List<Product> products;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private Customer customer;

    public WebOrder() {
    }

    public WebOrder(List<Product> products, Customer customer) {
        this.products = products;
        this.customer = customer;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
