package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import javafx.event.ActionEvent;

import java.io.IOException;

public class UserController {
    @FXML
    private TableView<Users> userTable;
    @FXML
    private TableColumn<Users,String> userColumn;
    @FXML
    private Label username;
    @FXML
    private Label password;
    @FXML
    private Label quantity;
    @FXML
    private Label last_item;
    @FXML
    private Label spent;
    @FXML
    private Label discount;

 public static ObservableList<Users> userData =FXCollections.observableArrayList();

    public static ObservableList<Users> getUserData() {
        return userData;
    }
    @FXML
    private void initialize() {
        userColumn.setCellValueFactory(cellData -> cellData.getValue().usernameProperty());
        showUsers(null);
        userTable.getSelectionModel().selectedItemProperty().addListener((observarble,oldValue,newValue)->showUsers(newValue));
        userTable.setItems(getUserData());

    }


    private void showUsers(Users user) {
        if (user != null) {
            //namelabel.setText(student.getName());
            username.setText(user.getUsername());
            password.setText(user.getPassword());
            quantity.setText(Integer.toString(user.getQuantity()));
            last_item.setText(user.getLast_item());
            spent.setText(Integer.toString(user.getSpent()));
            discount.setText(Integer.toString(user.getDiscount()));


        } else {
            username.setText("");
            password.setText("");
            quantity.setText("");
            last_item.setText("");
            spent.setText("");
            discount.setText("");

        }
    }

    @FXML
    private void handleDiscount(){
        Users selectedPerson = userTable.getSelectionModel().getSelectedItem();
        if (selectedPerson != null) {
            boolean okClicked = Main.showDiscountEditDialog(selectedPerson);
            if (okClicked) {
                showUsers(selectedPerson);
            }
        }

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
