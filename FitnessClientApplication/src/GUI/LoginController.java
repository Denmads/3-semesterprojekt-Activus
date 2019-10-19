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
    private ImageView loginImageBtn;
    @FXML
    private Label loginLabel;
    @FXML
    private Label createAccountLabel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

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
    }

}