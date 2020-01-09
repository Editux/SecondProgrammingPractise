package sample;

import javafx.beans.property.IntegerProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class NewEditdialog {

        private Stage dialogStage;
        private Items item;
        private boolean okClicked = false;
        //public static int number=4;
        @FXML
        private TextField Product_name;

        @FXML
        private TextField description;
        @FXML
        private TextField price;
        @FXML
        private TextField quantity;
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
         * @param item
         */
        public void setPerson(Items item) {
            this.item = item;

            //discount.setText(Integer.toString(user.getDiscount()));
            Product_name.setText(item.getShopname());
            description.setText(item.getDescription());
            price.setText(Double.toString(item.getPrice()));
            quantity.setText(Integer.toString(item.getQuantity()));



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

                //user.setDiscount(Integer.parseInt(discount.getText()));
                item.setShopname(Product_name.getText());
                item.setDescription(description.getText());
                item.setPrice(Double.parseDouble(price.getText()));
                item.setQuantity(Integer.parseInt(quantity.getText()));

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

            if (Product_name.getText() == null || Product_name.getText().length() == 0) {
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


