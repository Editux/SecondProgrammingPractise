package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class DiscountController {
    private Stage dialogStage;
    private Users user;
    private boolean okClicked = false;
    //public static int number=4;
    @FXML
    private TextField discount;
    @FXML
    private Label username;

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
     * @param user
     */
    public void setPerson(Users user) {
        this.user = user;

        discount.setText(Integer.toString(user.getDiscount()));
        username.setText(user.getUsername());


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

            user.setDiscount(Integer.parseInt(discount.getText()));
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
                ps=conn.prepareStatement("UPDATE Users set discount ='"+discount.getText()+"' where User_name ='"+user.getUsername()+"'");
                ps.execute();
                conn.commit();
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

        if (discount.getText() == null || discount.getText().length() == 0) {
            errorMessage += "No valid first name!\n";
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


