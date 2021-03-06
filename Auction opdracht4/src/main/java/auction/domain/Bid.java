package auction.domain;

import javax.persistence.*;
import nl.fontys.util.FontysTime;
import nl.fontys.util.Money;

@Entity(name="Bid") @Table(name="Bid")
public class Bid {

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Embedded
    private FontysTime time;
    @ManyToOne
    private User buyer;
    @Embedded
    private Money amount;
    
    @OneToOne
    private Item item;

    public Bid(User buyer, Money amount, Item item) {
        this.buyer = buyer;
        this.amount = amount;
        this.item = item;
    }

    public Bid(){}
    
    public Item getItem(){
        return this.item;
    }
    public FontysTime getTime() {
        return time;
    }

    public User getBuyer() {
        return buyer;
    }

    public Money getAmount() {
        return amount;
    }
}
