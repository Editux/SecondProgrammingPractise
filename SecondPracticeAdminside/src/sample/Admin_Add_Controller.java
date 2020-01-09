package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Admin_Add_Controller {
    private Stage dialogStage;
    private Admins admin;
    private boolean okClicked = false;

    @FXML
    private TextField Username;
    @FXML
    private  TextField Password;

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
    }

    /**
     * Sets the stage of this dialog.
     *
     * @param dialogStage
     */
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    /**
     * Sets the person to be edited in the dialog.
     *
     * @param admin
     */
    public void setPerson(Admins admin) {
        this.admin = admin;

        Username.setText(admin.getUsername());
        Password.setText(admin.getPassword());

    }

    /**
     * Returns true if the user clicked OK, false otherwise.
     *
     * @return
     */
    public boolean isOkClicked() {
        return okClicked;
    }


    @FXML
    private void handleOk() {
        if (isInputValid()) {

            admin.setUsername(Username.getText());
            admin.setPassword(Password.getText());
            Connection conn;
            Statement stmt = null;

            try{
                Class.forName("org.sqlite.JDBC");
                System.out.print("\nConnecting to database...");
                conn = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\edita\\user.db");
                System.out.println(" SUCCESS!\n");
                stmt = conn.createStatement();
                stmt.executeUpdate("INSERT INTO Admins ('User_name','User_password') VALUES('" + Username.getText() + "','" +Password.getText() + "')");
                //conn.commit();
                System.out.println(" SUCCESS!\n");
                conn.close();
            }catch ( Exception e ) {
                System.err.println( e.getClass().getName() + ": " + e.getMessage() );

            }

            okClicked = true;
            dialogStage.close();
        }
    }

    /**
     * Called when the user clicks cancel.
     */
    @FXML
    private void handleCancel() {
        dialogStage.close();
    }

    /**
     * Validates the user input in the text fields.
     *
     * @return true if the input is valid
     */
    private boolean isInputValid() {
        String errorMessage = "";

        if (Username.getText() == null || Username.getText().length() == 0) {
            errorMessage += "No valid first name!\n";
        }
        if (Password.getText() == null || Password.getText().length() == 0) {
            errorMessage += "No valid last name!\n";
        }



        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Show the error message.
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText(errorMessage);

            alert.showAndWait();

            return false;
        }
    }
}



