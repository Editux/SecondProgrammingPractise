package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.event.ActionEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class MainController {




        @FXML
        private TableView<Admins> AdminTable;
        @FXML
        private TableColumn<Admins, String> NameColumn;

public static ObservableList<Admins> adminData=FXCollections.observableArrayList();

    public static ObservableList<Admins> getAdminData() {
        return adminData;
    }
    @FXML
    private void initialize() {

       NameColumn.setCellValueFactory(cellData->cellData.getValue().usernameProperty());


        AdminTable.setItems(getAdminData());
    }

    @FXML
        private void handleAddAdmin(){
            Admins tempPerson = new Admins();
            boolean okClicked = Main.showPersonEditDialog(tempPerson);
            if (okClicked) {
                getAdminData().add(tempPerson);
            }
        }

        @FXML
        private void handleDeleteAdmin(){
            Admins selectedItem =AdminTable.getSelectionModel().getSelectedItem();
            Connection conn;
            Statement stmt = null;
        try{
            Class.forName("org.sqlite.JDBC");
            System.out.print("\nConnecting to database...");
            conn = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\edita\\user.db");
            System.out.println(" SUCCESS!\n");
            stmt = conn.createStatement();
            PreparedStatement ps = conn.prepareStatement("DELETE FROM Admins WHERE User_name = ?");
            ps.setString(1, selectedItem.getUsername());
            AdminTable.getItems().remove(selectedItem);
            ps.execute();
            conn.close();


        }catch( Exception e ) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }

        }

        @FXML
        private void handleManageUsers(ActionEvent event) throws IOException {
            FXMLLoader loader = new FXMLLoader();
            System.out.println("DO IT");
            Parent home_page_parent =  FXMLLoader.load(getClass().getResource("UserManager.fxml"));
            Scene home_page_scene = new Scene(home_page_parent);
            Stage app_stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            app_stage.hide(); //optional
            app_stage.setScene(home_page_scene);
            app_stage.show();

        }
        @FXML
        private void handleManageOrders(ActionEvent event) throws IOException {
            FXMLLoader loader = new FXMLLoader();
            System.out.println("DO IT");
            Parent home_page_parent =  FXMLLoader.load(getClass().getResource("OrderManager.fxml"));
            Scene home_page_scene = new Scene(home_page_parent);
            Stage app_stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            app_stage.hide(); //optional
            app_stage.setScene(home_page_scene);
            app_stage.show();

        }
        @FXML
        private void handleManageItems(ActionEvent event) throws IOException {
            FXMLLoader loader = new FXMLLoader();
            System.out.println("DO IT");
            Parent home_page_parent = FXMLLoader.load(getClass().getResource("ItemManager.fxml"));
            Scene home_page_scene = new Scene(home_page_parent);
            Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            app_stage.hide(); //optional
            app_stage.setScene(home_page_scene);
            app_stage.show();
        }
    }


