package auction.service;

import ServiceConsumers.AuctionConsumer;
import ServiceConsumers.RegistrationConsumer;
import ServiceConsumers.TestConsumer;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import webservice.*;

public class SellerMgrTest {
    
    @Before
    public void setUp() throws Exception {
        TestConsumer.databaseCleaner();
    }

    /**
     * Test of offerItem method, of class SellerMgr.
     */
    @Test
    public void testOfferItem() {
        String omsch = "omsch";

        User user1 = RegistrationConsumer.registerUser("xx@nl");
        Category cat = new Category();
        cat.setDescription("cat1");
        Item item1 = AuctionConsumer.offerItem(user1, cat, omsch);
        assertEquals(omsch, item1.getDescription());
        assertNotNull(item1.getId());
        boolean res = AuctionConsumer.revokeItem(item1);
    }

    /**
     * Test of revokeItem method, of class SellerMgr.
     */
    @Test
    public void testRevokeItem() {
        String omsch = "omschrijv";
        String omsch2 = "omschrijv2";
        
    
        User seller = RegistrationConsumer.registerUser("sel@nl");
        User buyer = RegistrationConsumer.registerUser("buy@nl");
        Category cat = new Category();
        cat.setDescription("cat2");
        
        // revoke before bidding
        Item item1 = AuctionConsumer.offerItem(seller, cat, omsch);
        boolean res = AuctionConsumer.revokeItem(item1);
        assertTrue(res);
        int count = AuctionConsumer.findItemByDescription(omsch).size();
        assertEquals(0, count);
        
        // revoke after bid has been made
        Item item2 = AuctionConsumer.offerItem(seller, cat, omsch2);
        Money mon = new Money();
        mon.setCents(100);
        mon.setCurrency("Euro");
        AuctionConsumer.newBid(item2, buyer, mon);
        item2 = AuctionConsumer.getItem(item2.getId());
        boolean res2 = AuctionConsumer.revokeItem(item2);
        assertFalse(res2);
        int count2 = AuctionConsumer.findItemByDescription(omsch2).size();
        assertEquals(1, count2);
    }
}
