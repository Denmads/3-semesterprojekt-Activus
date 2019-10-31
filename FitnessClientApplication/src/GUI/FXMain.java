package GUI;

import Communication.CommunicationFacade;
import Domain.DomainFacade;
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

        CommunicationFacade communicationFacade = new CommunicationFacade();
        DomainFacade domainFacade = new DomainFacade(communicationFacade);
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXML/LoginPage.fxml"));
        Parent root = loader.load();
        LoginPageController controller = loader.getController();
        controller.setObjects(stage, domainFacade);
        Scene scene = new Scene(root, 1280, 720);       
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