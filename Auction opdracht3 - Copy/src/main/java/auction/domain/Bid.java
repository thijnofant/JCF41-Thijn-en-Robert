package auction.domain;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import nl.fontys.util.FontysTime;
import nl.fontys.util.Money;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
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

    public Bid(User buyer, Money amount) {
        this.buyer = buyer;
        this.amount = amount;
    }

    public Bid(){}
    
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
