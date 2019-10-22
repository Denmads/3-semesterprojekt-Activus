package GUI;

import Domain.Authentication.AuthenticationService;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;


/**
 * FXML Controller class
 *
 * @author Victor
 */
public class LoginController implements Initializable {

    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Label loginLabel;
    @FXML
    private Label createAccountLabel;
    @FXML
    private ImageView loginImageButton;
    private AuthenticationService authentication;
    @FXML
    private AnchorPane apane;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    //All of the following methods are meant to do the same thing.
    //Get the text from the two textfields and then verify the login.
    //The easiest thing to do to avoid code repetition is to make a new method
    //that verifies the login and then call it in the following methods.


    @FXML
    private void handleCreateAccountAction(MouseEvent event) {
        //This is the only method that is not supposed to verify the login.
    }

    //Create Login method here;

    @FXML
    private void handleLoginAction(MouseEvent event) {
        verifyLogin();
    }

    private boolean verifyLogin() {
        //Do the getText here:
        String username = usernameField.getText();
        String password = passwordField.getText();
        //TO do conection to db
        if(authentication.login(username, password)){
           
            try { 
                PageLoader pageLoader = null;
                pageLoader.pageChanger(apane, "../FXML/Menu.fxml");
            } catch (IOException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        

        return false;
    }
}