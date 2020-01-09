package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {


    private static Stage primaryStage;
    @Override
    public void start(Stage stage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("AdminLogin.fxml"));
        Scene scene_login = new Scene(root);
        stage.setScene(scene_login);
        stage.show();

        Driver.OpenDB();
        Driver.getUsers();
        Driver.getOrders();
        Driver.getItems();
    }
    public static boolean showPersonEditDialog(Admins admin) {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("Admin add.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit Person");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the person into the controller.
            Admin_Add_Controller controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setPerson(admin);

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    public static boolean showDiscountEditDialog(Users user) {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("Discount.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit Person");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the person into the controller.
            DiscountController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setPerson(user);

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
        public static boolean showNewEdit(Items item) {
            try {
                // Load the fxml file and create a new stage for the popup dialog.
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(Main.class.getResource("NewEdit.fxml"));
                AnchorPane page = (AnchorPane) loader.load();

                // Create the dialog Stage.
                Stage dialogStage = new Stage();
                dialogStage.setTitle("Edit Person");
                dialogStage.initModality(Modality.WINDOW_MODAL);
                dialogStage.initOwner(primaryStage);
                Scene scene = new Scene(page);
                dialogStage.setScene(scene);

                // Set the person into the controller.
                NewEditdialog controller = loader.getController();
                controller.setDialogStage(dialogStage);
                controller.setPerson(item);

                // Show the dialog and wait until the user closes it
                dialogStage.showAndWait();

                return controller.isOkClicked();
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
    }




    public static void main(String[] args) {
        launch(args);
    }
}
