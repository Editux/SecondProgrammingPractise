package sample;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Client {
    StringProperty user;
    StringProperty password;
    IntegerProperty quantity;
    StringProperty last_item;
    IntegerProperty total;

    public Client(){this(null,null,0,null,0);};
    public Client(String user,String password,int quantity,String last_item,int total){
        this.user= new SimpleStringProperty(user);
        this.password=new SimpleStringProperty(password);
        this.quantity= new SimpleIntegerProperty(quantity);
        this.last_item=new SimpleStringProperty(last_item);
        this.total= new SimpleIntegerProperty(total);

    }

    public void setUser(String user) {
        this.user.set(user);
    }

    public void setPassword(String password) {
        this.password.set(password);
    }

    public void setLast_item(String last_item) {
        this.last_item.set(last_item);
    }

    public void setQuantity(int quantity) {
        this.quantity.set(quantity);
    }

    public void setTotal(int total) {
        this.total.set(total);
    }

    public String getUser() {
        return user.get();
    }

    public String getPassword() {
        return password.get();
    }

    public int getQuantity()
    {
        return Controller.basketData.size();
    }

    public String getLast_item() {
        int n =Controller.basketData.size();

        return Controller.basketData.get(n-1).getDishname();
    }

    public int getTotal() {
        return total.get();
    }
}
