package com.dimdron.InternetProviders.dbconnection.model;

 import org.hibernate.annotations.GenericGenerator;

 import javax.persistence.*;
 import java.util.HashSet;
 import java.util.Set;


@Entity
 @Table(name = "t_connections")
public class ConnectionType {
    @Id
    @GeneratedValue(generator="increment")
    @GenericGenerator(name="increment", strategy = "increment")
    @Column(name="id")
     private Integer id;

     @Column(name = "type", nullable = false, length = 100)
     private String type;

     @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
     @JoinTable(name="PROVIDERS_TYPES",
             joinColumns = @JoinColumn(name="TYPE_ID"),
             inverseJoinColumns = @JoinColumn(name="PROVIDER_ID"))
     private Set<Provider> providers = new HashSet<Provider>();

    public ConnectionType() {}

    public ConnectionType(String type) {
        setType(type);
    }

    public ConnectionType(int id, String type) {
        setId(id);
        setType(type);
    }

     public Integer getId() {
         return id;
     }

     public void setId(Integer id) {
         this.id = id ;
     }

     public String getType() {
         return type;
     }

     public void setType(String type) {
         this.type = type;
     }

     public Set<Provider> getProviders() {
         return providers;
     }

     public void setProviders(Set<Provider> providers) {
         this.providers = providers;
     }

     @Override
    public String toString() {
         return type;
     }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += type != null ? type.hashCode() : 0;
        return hash;
    }
    @Override
    public boolean equals(Object object) {
        String otherType = "";
        if (object instanceof ConnectionType)
            otherType = ((ConnectionType)object).getType();
        else if (object instanceof String)
            otherType = (String)object;
        else
            return false;

        if ((this.type == null && otherType != null)
                || (this.type != null && !this.type.equals(otherType)))
            return false;
        return true;
    }
}
