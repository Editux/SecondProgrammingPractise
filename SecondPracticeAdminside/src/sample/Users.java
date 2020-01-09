package sample;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Users {
    private StringProperty username;
    private StringProperty password;
    private IntegerProperty quantity;
    private StringProperty last_item;
    private IntegerProperty spent;
    private IntegerProperty discount;

    public Users(){this(null,null,0,null,0,0);}

    public Users(String username,String password,int quantity,String last_item,int spent,int discount){
        this.username = new SimpleStringProperty( username);
        this.password= new SimpleStringProperty(password);
        this.quantity= new SimpleIntegerProperty(quantity);
        this.last_item=new SimpleStringProperty(last_item);
        this.spent=new SimpleIntegerProperty(spent);
        this.discount=new SimpleIntegerProperty(discount);

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

    public void setPassword(String password) {
        this.password.set(password);
    }

    public String getPassword() {
        return password.get();
    }

    public void setQuantity(int quantity) {
        this.quantity.set(quantity);
    }

    public int getQuantity() {
        return quantity.get();
    }

    public void setLast_item(String last_item) {
        this.last_item.set(last_item);
    }

    public String getLast_item() {
        return last_item.get();
    }

    public void setSpent(int spent) {
        this.spent.set(spent);
    }

    public int getSpent() {
        return spent.get();
    }

    public void setDiscount(int discount) {
        this.discount.set(discount);
    }

    public int getDiscount() {
        return discount.get();
    }
}

