package auction.service;

import ServiceConsumers.AuctionConsumer;
import ServiceConsumers.RegistrationConsumer;
import ServiceConsumers.TestConsumer;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.util.List;
import org.junit.Ignore;
import webservice.*;

public class AuctionMgrTest {
    
    @Before
    public void setUp() throws Exception {
        TestConsumer.databaseCleaner();
    }
    
    @Test
    public void getItem() {

        String email = "xx2@nl";
        String omsch = "omsch";

        User seller1 = RegistrationConsumer.registerUser(email);
        Category cat = new Category();
        cat.setDescription("cat2");
        Item item1 = AuctionConsumer.offerItem(seller1, cat, omsch);
        Item item2 = AuctionConsumer.getItem(item1.getId());
        assertEquals(omsch, item2.getDescription());
        assertEquals(email, item2.getSeller().getEmail());
    }

    @Test
    public void findItemByDescription() {
        String email3 = "xx3@nl";
        String omsch = "omschrijvinger";
        String email4 = "xx4@nl";
        String omsch2 = "omschrijvinger2";

        User seller3 = RegistrationConsumer.registerUser(email3);
        User seller4 = RegistrationConsumer.registerUser(email4);
        Category cat = new Category();
        cat.setDescription("cat3");
        Item item1 = AuctionConsumer.offerItem(seller3, cat, omsch);
        Item item2 = AuctionConsumer.offerItem(seller4, cat, omsch);

        List<Item> res = AuctionConsumer.findItemByDescription(omsch2);
        assertEquals(0, res.size());

        res = AuctionConsumer.findItemByDescription(omsch);
        assertEquals(2, res.size());

    }

    @Test
    public void newBid() {
        String email = "ss2@nl";
        String emailb = "bb@nl";
        String emailb2 = "bb2@nl";
        String omsch = "omsch_bb";

        User seller = RegistrationConsumer.registerUser(email);
        User buyer = RegistrationConsumer.registerUser(emailb);
        User buyer2 = RegistrationConsumer.registerUser(emailb2);
        // eerste bod
        Category cat = new Category();
        cat.setDescription("cat9");
        Item item1 = AuctionConsumer.offerItem(seller, cat, omsch);
        Money mon = new Money();
        mon.setCents(10);
        mon.setCurrency("eur");
        Bid new1 = AuctionConsumer.newBid(item1, buyer, mon);
        assertEquals(emailb, new1.getBuyer().getEmail());
        System.out.println(item1.getHighest());

        // lager bod
        item1 = AuctionConsumer.getItem(item1.getId());
        Money mon2 = new Money();
        mon2.setCents(9);
        mon2.setCurrency("eur");
        assertNull(AuctionConsumer.newBid(item1, buyer2, mon2));

        // hoger bod
        item1 = AuctionConsumer.getItem(item1.getId());
        Money mon3 = new Money();
        mon3.setCents(11);
        mon3.setCurrency("eur");
        Bid new3 = AuctionConsumer.newBid(item1, buyer2, mon3);
        assertEquals(emailb2, new3.getBuyer().getEmail());
    }
}
