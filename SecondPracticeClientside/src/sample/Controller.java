package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class Controller {

    @FXML
    private TableView<Shop> shopTable;
    @FXML
    private TableColumn<Shop, String> NameColumn;
    @FXML
    private TableView<Basket> orderTable;
    @FXML
    private TableColumn<Basket,String> OrderColumn;
    @FXML
    private Label namelabel;
    @FXML
    private Label descriptionlabel;
    @FXML
    private Label pricelabel;
    @FXML
    private Label totallabel;
    @FXML
    private Label totalVatlabel;

    private Main mainapp;

    public static ObservableList<Shop> shopData = FXCollections.observableArrayList();
    public static ObservableList<Basket> basketData = FXCollections.observableArrayList();
    public static ObservableList<Shop> getShopData() {
        return shopData;}

    public static ObservableList<Basket> getBasketData() {
        return basketData;
    }



    @FXML
    private void initialize() {

        NameColumn.setCellValueFactory(cellData -> cellData.getValue().itemnameProperty());
        OrderColumn.setCellValueFactory(cellData->cellData.getValue().itemnameProperty());



        showShop(null);
        showOrder(null);


        shopTable.getSelectionModel().selectedItemProperty().addListener((observarble,oldValue,newValue)->showShop(newValue));
        orderTable.getSelectionModel().selectedItemProperty().addListener((observarble,oldValue,newValue)->showOrder(newValue));
        shopTable.setItems(getShopData());
        orderTable.setItems(getBasketData());
    }

    private void showShop(Shop shop){
        if(shop != null){
            //namelabel.setText(student.getName());
            namelabel.setText(shop.getShopname());
            descriptionlabel.setText(shop.getDescription());
            pricelabel.setText(Double.toString(shop.getPrice()));
        } else {

            namelabel.setText("");
            descriptionlabel.setText("");
            pricelabel.setText("");
        }

    }
    private void showOrder(Basket basket) {
        if (basket != null) {
            totallabel.setText(Double.toString(basket.getTotal()));
            totalVatlabel.setText(Double.toString(basket.getTotalVat()));
        }else {
            totallabel.setText("");
            totalVatlabel.setText("");
        }
    }

    @FXML
    private void AddtotheBasket(){
        Shop selection = shopTable.getSelectionModel().getSelectedItem();

        if (selection != null) {


            int quantity= selection.getQuantity() -1;
            selection.setQuantity(quantity);
            orderTable.getItems().add(new Basket(selection.getShopname(),selection.getDescription(),selection.getPrice(),selection.getQuantity()));

            Connection conn;
            Statement stmt = null;
            PreparedStatement ps =null;

            try{
                Class.forName("org.sqlite.JDBC");
                System.out.print("\nConnecting to database...");
                conn = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\edita\\user.db");
                System.out.println(" SUCCESS!\n");
                conn.setAutoCommit(false);
                stmt = conn.createStatement();
                ps=conn.prepareStatement("UPDATE Items set Quantity ='"+selection.getQuantity()+"' where Item_name ='"+selection.getShopname()+"'");
                ps.execute();
                conn.commit();
                System.out.println(" SUCCESS!\n");

                conn.close();
            }catch ( Exception e ) {
                System.err.println( e.getClass().getName() + ": " + e.getMessage() );

            }
            System.out.println("Item added successfully");
        }
    }


    @FXML
    private void handleDelete(){
        Basket selectedItem = orderTable.getSelectionModel().getSelectedItem();
        orderTable.getItems().remove(selectedItem);

        Connection conn;
        Statement stmt = null;
        PreparedStatement ps =null;
        int quantity= selectedItem.getQuantity() +1;
        selectedItem.setQuantity(quantity);
        try{
            Class.forName("org.sqlite.JDBC");
            System.out.print("\nConnecting to database...");
            conn = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\edita\\user.db");
            System.out.println(" SUCCESS!\n");
            conn.setAutoCommit(false);
            stmt = conn.createStatement();
            ps=conn.prepareStatement("UPDATE Items set Quantity ='"+selectedItem.getQuantity()+"' where Item_name ='"+selectedItem.getDishname()+"'");
            ps.execute();
            conn.commit();
            System.out.println(" SUCCESS!\n");

            conn.close();
        }catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );

        }
        System.out.println("Item deleted successfully");

    }
    @FXML
    private void handleRefresh(){
        orderTable.refresh();

    }
    @FXML
    private void handleOrder(){

        Basket basket = new Basket();
        Connection conn;
        Statement stmt = null;
        PreparedStatement ps =null;
        int quantity=LoginController.clientData.get(0).getQuantity();
        String last_item= LoginController.clientData.get(0).getLast_item();
        String username = LoginController.clientData.get(0).getUser();
        String password= LoginController.clientData.get(0).getPassword();
        double total = basket.getTotalVat();
        try{
            Class.forName("org.sqlite.JDBC");
            System.out.print("\nConnecting to database...");
            conn = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\edita\\user.db");

            conn.setAutoCommit(false);
            stmt = conn.createStatement();
            for (int i = 0; i <basketData.size() ; i++) {
                stmt.executeUpdate("INSERT INTO Orders ('username','item_name') VALUES('" +LoginController.clientData.get(0).getUser() + "','" + basketData.get(i).getDishname()+"')");
            }

            ps=conn.prepareStatement("UPDATE Users set quantity ='"+quantity+"', last_item='"+last_item+"',  price='"+total+"' where User_name = '"+username+"' and User_password ='"+password+"' ");
            ps.execute();
            conn.commit();
            conn.close();
        }catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );

        }
        System.out.println("Order is inserted in database successfully");
    }
   /* private void setPrice(){
        //set price labels
        //formats to string

        String price = String.format("%.2f",basketData.get() );
        subtotalPriceLabel.setText(price);
        //with VAT

        price = String.format("%.2f", shoppingCart.getFinalPriceVAT());
        totalPriceLabel.setText(price);
    }
*/


}
