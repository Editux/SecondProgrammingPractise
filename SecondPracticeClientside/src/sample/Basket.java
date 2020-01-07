package sample;

import javafx.beans.property.*;

import java.io.FileInputStream;

public class Basket {
    private StringProperty itemname;
    private StringProperty description;
    private DoubleProperty price;
    private FileInputStream picture;
    private IntegerProperty quantity;

    private double total;


    public Basket(){
        this(null,null,0,0);
    }
    public Basket(String itemname,String description,double price, int quantity){
        this.itemname = new SimpleStringProperty(itemname);
        this.description= new SimpleStringProperty(description);
        this.price = new SimpleDoubleProperty(price);
        this.quantity=new SimpleIntegerProperty(quantity);
        /*try {
            this.picture = new FileInputStream(String.valueOf(picture));
        } catch (FileNotFoundException e) {
            System.out.println("This method of picture doesn't work");
        }
        */

    }

    public void setDishname(String dishname) {
        this.itemname.set(dishname);
    }

    public String getDishname() {
        return itemname.get();
    }

    public StringProperty itemnameProperty() {
        return itemname;
    }

    public void setDescription(String description) {
        this.description.set(description);
    }

    public String getDescription() {
        return description.get();
    }

    public void setPrice(double price) {
        this.price.set(price);
    }

    public double getPrice() {
        return price.get();
    }

    public DoubleProperty priceProperty() {
        return price;
    }

    public void setPicture(FileInputStream picture) {
        this.picture = picture;
    }

    public FileInputStream getPicture() {
        return picture;
    }

    public void setQuantity(int quantity) {
        this.quantity.set(quantity);
    }

    public int getQuantity() {
        return quantity.get();
    }

    public double getTotal() {

        for (int i = 0; i <Controller.getBasketData().size() ; i++) {

            total=total + Controller.getBasketData().get(i).getPrice();
        }
        return total;
    }
    public double getTotalVat(){
        final double  tax = 1.21;

        return total *tax;
    }
}
