package auction.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import javax.persistence.*;

@Entity(name="Gebruiker") @Table(name="Gebruiker")
@NamedQueries({
    @NamedQuery(name = "Gebruikers.count", query = "select count(a) from Gebruiker as a"),
    @NamedQuery(name = "Gebruikers.findByEmail", query = "select a from Gebruiker as a where a.email = :email")
})
public class User {
    
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String email;
    @OneToMany(mappedBy = "seller")
    private Set<Item> offeredItems = new HashSet<>();
    
    public User(){
    }
    
    public User(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof User)) {
            return false;
        }
        User other = (User) obj;
        if (this.email.equals(other.getEmail())) {
            return true;
        }
        return false;
    }
    
    public Iterator<Item> getOfferedItems(){
        return this.offeredItems.iterator();
    }
    
    protected void addItem(Item item){
        this.offeredItems.add(item);
    }
    
    public int numberOfOfferdItems(){
        return this.offeredItems.size();
    }
}
