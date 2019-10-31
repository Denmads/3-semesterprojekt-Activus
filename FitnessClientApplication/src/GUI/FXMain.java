package GUI;

import Communication.CommunicationFacade;
import Domain.DomainFacade;
import Exceptions.ConfigFileNotFound;
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

    private static FXMain instance;
    private Stage stage;

    @Override
    public void start(Stage stage) throws Exception {

        instance = this;
        this.stage = stage;
        loadLoginPage();
        stage.show();

    }

    private void loadLoginPage() throws IOException, ConfigFileNotFound {

        CommunicationFacade communicationFacade = new CommunicationFacade();
        DomainFacade domainFacade = new DomainFacade(communicationFacade);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXML/LoginPage.fxml"));
        Parent root = loader.load();
        LoginPageController controller = loader.getController();
        controller.setObjects(stage, domainFacade);
        Scene scene = new Scene(root, 1280, 720);
        stage.setScene(scene);

    }

    public static void showLoginPage() throws Exception{
        instance.loadLoginPage();
        
    }
            
            /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
