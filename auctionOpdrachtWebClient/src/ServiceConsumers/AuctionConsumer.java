/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ServiceConsumers;

import java.util.List;
import webservice.*;

/**
 *
 * @author Thijn
 */
public class AuctionConsumer {

    public static java.util.List<webservice.Item> findItemByDescription(java.lang.String arg0) {
        webservice.AuctionService service = new webservice.AuctionService();
        webservice.Auction port = service.getAuctionPort();
        return port.findItemByDescription(arg0);
    }

    public static Item getItem(java.lang.Long arg0) {
        webservice.AuctionService service = new webservice.AuctionService();
        webservice.Auction port = service.getAuctionPort();
        return port.getItem(arg0);
    }

    public static Bid newBid(webservice.Item arg0, webservice.User arg1, webservice.Money arg2) {
        webservice.AuctionService service = new webservice.AuctionService();
        webservice.Auction port = service.getAuctionPort();
        return port.newBid(arg0, arg1, arg2);
    }

    public static Item offerItem(webservice.User arg0, webservice.Category arg1, java.lang.String arg2) {
        webservice.AuctionService service = new webservice.AuctionService();
        webservice.Auction port = service.getAuctionPort();
        return port.offerItem(arg0, arg1, arg2);
    }

    public static boolean revokeItem(webservice.Item arg0) {
        webservice.AuctionService service = new webservice.AuctionService();
        webservice.Auction port = service.getAuctionPort();
        return port.revokeItem(arg0);
    }
    
}
