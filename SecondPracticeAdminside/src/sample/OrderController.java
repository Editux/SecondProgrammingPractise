package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class OrderController {
    @FXML
    private TableView<Orders> orderTable;
    @FXML
    private TableColumn<Orders, String> userColumn;
    @FXML
    private TableColumn<Orders,String> itemColumn;
    @FXML
    private Label username;
    @FXML
    private Label item;
    @FXML
    private Label country;
    @FXML
    private Label city;
    @FXML
    private Label address;
    @FXML
    private Label cost;


    public static ObservableList<Orders> orderData= FXCollections.observableArrayList();

    public static ObservableList<Orders> getOrderData() {
        return orderData;
    }

    @FXML
    private void initialize() {
        userColumn.setCellValueFactory(cellData -> cellData.getValue().usernameProperty());
        itemColumn.setCellValueFactory(cellData ->cellData.getValue().itemProperty());
        //showUsers(null);
        orderTable.getSelectionModel().selectedItemProperty().addListener((observarble,oldValue,newValue)->showOrders(newValue));
        orderTable.setItems(getOrderData());

    }
    private void showOrders (Orders order){
        if (order != null) {
            //namelabel.setText(student.getName());
           username.setText(order.getUsername());
           item.setText(order.getItem());
           country.setText(order.getCountry());
           city.setText(order.getCity());
           address.setText(order.getAddress());
           cost.setText(Integer.toString(order.getCost()));


        } else {
            username.setText("");
            item.setText("");
            country.setText("");
            city.setText("");
            address.setText("");
            cost.setText("");

        }
    }

    @FXML
    private void handleDeleteOrder(){
        Orders selectedItem = orderTable.getSelectionModel().getSelectedItem();
        orderTable.getItems().remove(selectedItem);

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
            ps=conn.prepareStatement("DELETE FROM Orders where username ='"+selectedItem.getUsername()+"' and item_name='"+selectedItem.getItem()+"'");
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
    private void goBack(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        System.out.println("DO IT");
        Parent home_page_parent =  FXMLLoader.load(getClass().getResource("sample.fxml"));
        Scene home_page_scene = new Scene(home_page_parent);
        Stage app_stage = (Stage) ((Node)event.getSource()).getScene().getWindow();

        app_stage.hide(); //optional
        app_stage.setScene(home_page_scene);
        app_stage.show();
    }
}
