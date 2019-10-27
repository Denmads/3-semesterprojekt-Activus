package GUI;

import Domain.DomainFacade;
import Domain.authentication.AuthenticationService;
import Domain.serviceInterfaces.IAuthenticationService;
import Enums.RequestArgumentName;
import Enums.ServiceType;
import Exceptions.ServiceNotFoundException;
import GUI.PageHandler;
import LayerInterfaces.IDomainFacade;
import Models.Request;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Victor
 */
public class LoginController extends PageHandler implements Initializable {

   
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private ImageView loginImageButton;
    @FXML
    private Label loginLabel;
    private DomainFacade domain;
    @FXML
    private Pane aPene;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void handleLoginAction(MouseEvent event) {
        String username = usernameField.getText();
        String password = passwordField.getText();
        
        
        System.out.println("login ");
        
        try {
            boolean login = domain.<AuthenticationService>getService(ServiceType.AUTHENTICATION).login(username, password);
            if(login){
            changeFxml(aPene, "FXML/RootPage.fxml");
            
            }
            else{
                infoBox("Worng username or password", "Login Erro!", "Login not validated!");
            }
            
            
        } catch (ServiceNotFoundException | ClassCastException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    
}