package sample;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Admins {

    private StringProperty username;
    private StringProperty password;

public Admins(){this(null,null);}

public Admins(String username,String password){
    this.username= new SimpleStringProperty(username);
    this.password= new SimpleStringProperty(password);
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
}
