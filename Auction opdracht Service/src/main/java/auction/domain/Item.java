package auction.domain;

import java.util.Objects;
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
    @OneToOne(mappedBy = "item", cascade=CascadeType.REMOVE)
    private Bid highest;

    public Item(User seller, Category category, String description) {
        this.seller = seller;
        this.category = category;
        this.description = description;
        seller.addItem(this);
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
        if (highest != null && highest.getAmount().compareTo(amount) >= 0) {
            return null;
        }
        highest = new Bid(buyer, amount, this);
        return highest;
    }
    
    @Override
    public int compareTo(Object arg0) {
        //TODO
        return -1;
    }

    @Override
    public boolean equals(Object o) {
        Item i = (Item) o;
        return this.id.equals(i.getId())
                && this.description.equals(i.description)
                && this.seller.equals(i.seller)
                && this.category.getDiscription().equals(i.getCategory().getDiscription());
        
    }

    @Override
    public int hashCode() {
        int hash = 1;
        hash = hash * 17 + (this.id == null ? 0 : this.id.intValue());
        hash = hash * 31 + this.seller.hashCode();
        hash = hash * 13 + this.description.hashCode();
        hash = hash * 19 + this.category.hashCode();
        return hash;
    }
}
