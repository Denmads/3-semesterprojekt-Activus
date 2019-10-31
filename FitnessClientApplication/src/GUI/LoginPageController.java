package GUI;

import Domain.DomainFacade;
import Domain.serviceInterfaces.IAuthenticationService;
import Enums.ServiceType;
import Exceptions.ServiceNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Victor
 */
public class LoginPageController implements Initializable {

   
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;

    private Stage window;
    private DomainFacade domainFacade;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    public void setObjects (Stage window, DomainFacade facade) {
        domainFacade = facade;
        this.window = window;
    }
    
    @FXML
    private void handleLoginAction(MouseEvent event) {
//        try {
            String username = usernameField.getText();
            String password = passwordField.getText();
            
            //boolean login = domainFacade.<IAuthenticationService>getService(ServiceType.AUTHENTICATION).login(username, password);
            if((username.equalsIgnoreCase("name ")&& password.equalsIgnoreCase("pass"))){
                changeToRootPage();
            }
            else{
                showAlert("Login Error!", "Wrong username or password");
            }
//        } catch (ServiceNotFoundException | ClassCastException ex) {
//            Logger.getLogger(LoginPageController.class.getName()).log(Level.SEVERE, null, ex);
//        }
        
    }

    private void showAlert (String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    
    private void changeToRootPage () {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXML/RootPage.fxml"));
            Parent root = loader.load();
            RootPageController controller = loader.getController();
            controller.setDomainFacade(domainFacade);
            Scene scene = new Scene(root, 1280, 720);
            window.setScene(scene);
        } catch (IOException ex) {
            showAlert("Error!", "An Error occured: " + ex.getMessage());
        }
    }
}