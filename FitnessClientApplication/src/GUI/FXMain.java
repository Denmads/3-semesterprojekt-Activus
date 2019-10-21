package GUI;

import Communication.CommunicationFacade;
import Exceptions.ConfigFileNotFound;
import Enums.*;
import Models.Request;
import java.io.IOException;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;

/**
 *
 * @author Victor
 */
public class FXMain extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {

        try {
            CommunicationFacade cf = new CommunicationFacade();

            Request r = new Request(RequestType.LOGIN, UUID.randomUUID(), 1);
            r.addArgument(RequestArgumentName.USERNAME, "Denmads");
            r.addArgument(RequestArgumentName.PASSWORD, "test");
            cf.sendRequest(r);

            Platform.exit();

//        Parent root = FXMLLoader.load(getClass().getResource("/GUI/FXML/Login.fxml"));
//
//        Scene scene = new Scene(root);
//
//        Stage stage = new Stage();
//        stage.setTitle("Activius");
//        stage.setScene(scene);
//        stage.show();
        } catch (ConfigFileNotFound ex) {
            Logger.getLogger(FXMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}