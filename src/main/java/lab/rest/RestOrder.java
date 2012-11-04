package lab.rest;

import java.util.List;

/**
 * @Author Paul Bakker - paul.bakker@luminis.eu
 */
public class RestOrder {
    private String customerName;
    private String customerEmail;
    private List<Long> productIds;

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public List<Long> getProductIds() {
        return productIds;
    }

    public void setProductIds(List<Long> productIds) {
        this.productIds = productIds;
    }
}
