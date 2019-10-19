package GUI;

import Communication.CommunicationFacade;
import Communication.Request;
import Exceptions.ConfigFileNotFound;
import LayerInterfaces.Enums.RequestArguementName;
import LayerInterfaces.Enums.RequestType;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;
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
        
        try {
            CommunicationFacade cf = new CommunicationFacade();
            
            cf.openConnection();
            
            Request r = new Request(RequestType.LOGIN, UUID.randomUUID(), 1);
            r.AddArgument(RequestArguementName.USERNAME, "Denmads");
            r.AddArgument(RequestArguementName.PASSWORD, "test");
            cf.sendRequest(r);
            
            cf.closeConnection();
            
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