package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class AdminController {
    @FXML
    private Label label;
    @FXML
    private AnchorPane home_page;
    @FXML
    private TextField username_box;
    @FXML
    private TextField password_box;
    @FXML
    private Button enterButton;

    Window window;


    @FXML
    private void handleButtonAction(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader();
        System.out.println("DO IT");
        Parent home_page_parent =  FXMLLoader.load(getClass().getResource("sample.fxml"));
        Scene home_page_scene = new Scene(home_page_parent);
        Stage app_stage = (Stage) ((Node)event.getSource()).getScene().getWindow();



        if (isValidCredentials())
        {
            app_stage.hide(); //optional
            app_stage.setScene(home_page_scene);
            app_stage.show();




        } else
        {
            username_box.clear();
            password_box.clear();

        }

    }

    private boolean isValidCredentials()
    {

        boolean let_in = false;
        System.out.println( "SELECT * FROM Users WHERE User_name " + "'" + username_box.getText() + "'"
                + " AND User_password= " + "'" + password_box.getText() + "'" );

        Connection c = null;
        Statement stmt = null;
        try {
            c = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\edita\\user.db");
            c.setAutoCommit(false);

            System.out.println("Opened database successfully");
            stmt = c.createStatement();

            ResultSet rs = stmt.executeQuery( "SELECT * FROM Admins WHERE User_name= " + "'" + username_box.getText() + "'"
                    + " AND User_password= " + "'" + password_box.getText() + "'");


            while ( rs.next() ) {
                if (rs.getString("User_name") != null && rs.getString("User_password") != null) {

                    //clientData.add(new Client(username_box.getText(),password_box.getText(),quantity,last_item,total));
                    let_in = true;
                }
            }
            rs.close();
            stmt.close();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        System.out.println("Operation done successfully");
        return let_in;

    }
}
