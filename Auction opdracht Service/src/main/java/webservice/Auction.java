/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webservice;

import javax.jws.WebService;
import auction.domain.*;
import auction.service.*;
import java.util.List;
import nl.fontys.util.Money;

/**
 *
 * @author Thijn
 */
@WebService
public class Auction {
    private static final AuctionMgr AucMgr = new AuctionMgr();
    private static final SellerMgr SellMgr = new SellerMgr();
    
    public Item getItem(Long id){
        return AucMgr.getItem(id);
    }
    
    public List<Item> findItemByDescription(String description){
        return AucMgr.findItemByDescription(description);
    }
    
    public Bid newBid(Item item, User buyer, Money amount){
        return AucMgr.newBid(item, buyer, amount);
    }
    
    public Item offerItem(User seller, Category cat, String description){
        return SellMgr.offerItem(seller, cat, description);
    }
    
    public boolean revokeItem(Item item){
        return SellMgr.revokeItem(item);
    }
}
