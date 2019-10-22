package GUI;

import Communication.CommunicationFacade;
import Exceptions.ConfigFileNotFound;
import Enums.*;
import Exceptions.ArgumentNotFoundException;
import Models.Request;
import Models.Response;
import java.io.IOException;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Victor
 */
public class FXMain extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {


        Parent root = FXMLLoader.load(getClass().getResource("/GUI/FXML/Login.fxml"));

        Scene scene = new Scene(root);

        Stage stage = new Stage();
        stage.setTitle("Activius");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}