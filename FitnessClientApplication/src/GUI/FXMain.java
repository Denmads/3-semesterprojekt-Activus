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

        Parent root = FXMLLoader.load(getClass().getResource("FXML/Profile.fxml"));  
        Scene scene = new Scene(root);       
        stage.setScene(scene);
        stage.show();
    }
//hej med dig
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}