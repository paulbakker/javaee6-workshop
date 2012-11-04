package lab.events;

import lab.ejb.WebOrderDao;
import lab.entities.WebOrder;

import javax.enterprise.event.Observes;
import javax.inject.Inject;

/**
 * @Author Paul Bakker - paul.bakker@luminis.eu
 */
public class OrderDBProcessor {
    @Inject
    WebOrderDao webOrderDao;

    public void handleNewWebOrder(@Observes @WebOrderCreated WebOrder webOrder) {
        System.out.println("bla");
        webOrderDao.save(webOrder);
    }
}
