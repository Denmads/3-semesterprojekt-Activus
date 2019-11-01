/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.FXML.SideMenu.buttons;

import Domain.serviceInterfaces.IAuthenticationService;
import Enums.ServiceType;
import Exceptions.ServiceNotFoundException;
import GUI.CustomMenuButtonController;
import GUI.FXMain;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

/**
 * FXML Controller class
 *
 * @author madsh
 */
public class LogoutButtonController extends CustomMenuButtonController {

    @FXML
    private Button logoutBtn;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @Override
    public Button getButtonNode() {
        return logoutBtn;
    }
    
    @FXML
    private void logout (ActionEvent event) {
        try {
            domainFacade.<IAuthenticationService>getService(ServiceType.AUTHENTICATION).logout();
            FXMain.showLoginPage();
        } catch (ServiceNotFoundException ex) {
            Logger.getLogger(LogoutButtonController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassCastException ex) {
            Logger.getLogger(LogoutButtonController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(LogoutButtonController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
