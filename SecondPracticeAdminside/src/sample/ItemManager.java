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


public class ItemManager {
    @FXML
    private TableView<Items> itemTable;
    @FXML
    private TableColumn<Items,String> itemColumn;
    @FXML
    private Label name;
    @FXML
    private Label description;
    @FXML
    private Label quantity;
    @FXML
    private Label  price;


    @FXML
    private void initialize() {

        itemColumn.setCellValueFactory(cellData ->cellData.getValue().itemnameProperty());
        //showUsers(null);
        itemTable.getSelectionModel().selectedItemProperty().addListener((observarble,oldValue,newValue)->showItems(newValue));
        itemTable.setItems(getItemData());

    }


    public static ObservableList<Items> itemData = FXCollections.observableArrayList();

    public ObservableList<Items> getItemData() {
        return itemData;
    }

    private void showItems(Items items) {
        if (items != null) {
            //namelabel.setText(student.getName());
            name.setText(items.getShopname());
            description.setText(items.getDescription());
            price.setText(Double.toString(items.getPrice()));
            quantity.setText(Integer.toString(items.getQuantity()));
        } else {

            name.setText("");
            description.setText("");
            price.setText("");
            quantity.setText("");
        }
    }

    @FXML
    private void handleAddItem(){
        Items tempPerson = new Items();
        boolean okClicked = Main.showNewEdit(tempPerson);
        if (okClicked) {
            getItemData().add(tempPerson);
            Connection conn;
            Statement stmt = null;

            try{
                Class.forName("org.sqlite.JDBC");
                System.out.print("\nConnecting to database...");
                conn = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\edita\\user.db");
                System.out.println(" SUCCESS!\n");
                stmt = conn.createStatement();
                stmt.executeUpdate("INSERT INTO Items ('Item_name','description','price','Quantity') VALUES('" +tempPerson.getShopname() + "','" + tempPerson.getDescription() + "','"+tempPerson.getPrice()+"','"+tempPerson.getQuantity()+"')");
                //conn.commit();
                System.out.println(" SUCCESS!\n");
                conn.close();
            }catch ( Exception e ) {
                System.err.println( e.getClass().getName() + ": " + e.getMessage() );

            }

        }
    }
    @FXML
    private void handleEditItem() {
        Items selectedPerson = itemTable.getSelectionModel().getSelectedItem();
        if (selectedPerson != null) {
            boolean okClicked = Main.showNewEdit(selectedPerson);
            if (okClicked) {
                showItems(selectedPerson);
            }
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
                ps=conn.prepareStatement("UPDATE Items set Item_name ='"+selectedPerson.getShopname()+"',description='"+selectedPerson.getDescription()+"', price='"+selectedPerson.getPrice()+"', Quantity='"+selectedPerson.getQuantity()+"'  where Item_name ='"+selectedPerson.getShopname()+"'");
                ps.execute();
                conn.commit();
                System.out.println(" SUCCESS!\n");

                conn.close();
            }catch ( Exception e ) {
                System.err.println( e.getClass().getName() + ": " + e.getMessage() );

            }

        }
    }
    @FXML
    private void handleDeleteItem(){
        Items selectedItem = itemTable.getSelectionModel().getSelectedItem();
        itemTable.getItems().remove(selectedItem);

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
            ps=conn.prepareStatement("DELETE FROM Items where Item_name ='"+selectedItem+"' and item_name='"+selectedItem.getShopname()+"'");
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
