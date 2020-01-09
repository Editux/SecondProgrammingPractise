package sample;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Orders {
    private StringProperty username;
    private StringProperty item;
    private StringProperty country;
    private StringProperty city;
    private StringProperty  address;
    private IntegerProperty cost;

   public Orders(){this(null,null,null,null,null,0);}

   public Orders(String username,String item, String country,String city,String address, int cost){
       this.username= new SimpleStringProperty(username);
       this.item=new SimpleStringProperty(item);
       this.country=new SimpleStringProperty(country);
       this.city=new SimpleStringProperty(city);
       this.address=new SimpleStringProperty(address);
       this.cost=new SimpleIntegerProperty(cost);
   }

    public void setUsername(String username) {
        this.username.set(username);
    }

    public String getUsername() {
        return username.get();
    }

    public StringProperty usernameProperty() {
        return username;
    }

    public void setItem(String item) {
        this.item.set(item);
    }

    public String getItem() {
        return item.get();
    }

    public StringProperty itemProperty() {
        return item;
    }

    public void setCountry(String country) {
        this.country.set(country);
    }

    public String getCountry() {
        return country.get();
    }

    public void setCity(String city) {
        this.city.set(city);
    }

    public String getCity() {
        return city.get();
    }

    public void setAddress(String address) {
        this.address.set(address);
    }

    public String getAddress() {
        return address.get();
    }

    public void setCost(int cost) {
        this.cost.set(cost);
    }

    public int getCost() {
        return cost.get();
    }
}
