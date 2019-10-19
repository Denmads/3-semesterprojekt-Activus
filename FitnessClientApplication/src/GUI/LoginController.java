package GUI;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

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
    private void handleUserFieldAction(ActionEvent event) {
    }

    @FXML
    private void handlePasswordFieldAction(ActionEvent event) {
    }

    @FXML
    private void handleLoginImageAction(MouseEvent event) {
    }

    @FXML
    private void handleLoginLabelAction(MouseEvent event) {
    }
    
    @FXML
    private void handleCreateAccountAction(MouseEvent event) {
    //This is the only method that is not supposed to verify the login.
    }

}