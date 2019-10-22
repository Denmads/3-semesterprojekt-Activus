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
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
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
    private void handleCreateAccountAction(MouseEvent event) {
        //This is the only method that is not supposed to verify the login.
    }

    //Create Login method here;
    @FXML
    private void handleLoginAction(MouseEvent event) {
        verifyLogin();
    }

    @FXML
    private void handleLoginKeyAction(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            verifyLogin();
        }
    }

    private boolean verifyLogin() {
        //Do the getText here:
        String username = usernameField.getText();
        String password = passwordField.getText();

        return false;
    }
}
