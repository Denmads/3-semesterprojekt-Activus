/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Domain.authentication.AuthenticationService;
import GUI.PageHandler;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author steff
 */
public class LoginController extends PageHandler implements Initializable {

    @FXML
    private AnchorPane pane;
    @FXML
    private ImageView loginBtn;
    @FXML
    private ImageView createAccountbtn;
    @FXML
    private TextField username;
    @FXML
    private TextField password;
    AuthenticationService authenticationService;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void loginMouseClick(MouseEvent event) {
        changeFxml(pane, "FXML/Menu.fxml");
//        if(authenticationService.login(username.getText().toString(), password.getText().toString())){
//            changeFxml(pane, "FXML/Menu.fxml");
//        }
    }

    @FXML
    private void createAccountOnMouse(MouseEvent event) {
    }
    
}
