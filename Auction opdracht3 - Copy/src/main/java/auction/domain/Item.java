package auction.domain;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import nl.fontys.util.Money;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Entity(name="AuctionItem") @Table(name="AuctionItem")
@NamedQueries({
    @NamedQuery(name = "Items.count", query = "select count(a) from AuctionItem as a"),
    @NamedQuery(name = "Items.FindByDesc", query = "select a from AuctionItem as a where a.description = :description")
})
public class Item implements Comparable {

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private User seller;
    
    @Embedded
    @AttributeOverrides({
     @AttributeOverride(name = "description", column = @Column(name = "c_description"))
    })
    private Category category;
    
    private String description;
    @OneToOne
    private Bid highest;

    public Item(User seller, Category category, String description) {
        this.seller = seller;
        this.category = category;
        this.description = description;
    }
    
    public Item(){
    }

    public Long getId() {
        return id;
    }

    public User getSeller() {
        return seller;
    }

    public Category getCategory() {
        return category;
    }

    public String getDescription() {
        return description;
    }

    public Bid getHighestBid() {
        return highest;
    }

    public Bid newBid(User buyer, Money amount) {
        if (highest != null && highest.getAmount().getCents() >= amount.getCents()) {
            return null;
        }
        this.highest = new Bid(buyer, amount);
        return highest;
    }
    
    @Override
    public int compareTo(Object arg0) {
        //TODO
        return -1;
    }

    @Override
    public boolean equals(Object o) {
        //TODO
        return false;
    }

    @Override
    public int hashCode() {
        //TODO
        return 0;
    }
}
