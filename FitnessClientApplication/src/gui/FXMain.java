package GUI;

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

        instance = this;
        this.stage = stage;
        loadLoginPage();
        stage.show();

    }
    private DomainFacade domainFacade;
    

    private void loadLoginPage() throws IOException, ConfigFileNotFound {

        CommunicationFacade communicationFacade = new CommunicationFacade();
        domainFacade = new DomainFacade(communicationFacade);

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