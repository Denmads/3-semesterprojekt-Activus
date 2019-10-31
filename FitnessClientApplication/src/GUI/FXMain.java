package GUI;

import Communication.CommunicationFacade;
import Models.Request;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author Victor
 */
public class FXMain extends Application {
  
    @Override
    public void start(Stage stage) throws Exception {
            CommunicationFacade facade = new CommunicationFacade();
            Request
        
//        Parent root = FXMLLoader.load(getClass().getResource("FXML/Login.fxml"));  
//        Scene scene = new Scene(root);       
//        stage.setScene(scene);
//        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}