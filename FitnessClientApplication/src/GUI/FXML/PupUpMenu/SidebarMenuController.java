/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.FXML.PupUpMenu;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author steff
 */
public class SidebarMenuController implements Initializable {

    @FXML
    private Button profileBtn;
    @FXML
    private Button statsBtn;
    @FXML
    private Button BuddiesBtn;
    @FXML
    private Label messegetLb;
    @FXML
    private Button findBuddiesBtn;
    @FXML
    private Button exercicesBtn;
    @FXML
    private Button trainingProgramBtn;
    @FXML
    private Button loguotBtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void ViewProfile(ActionEvent event) {
        System.out.println("profile");
    }

    @FXML
    private void ViewStats(ActionEvent event) {
    }

    @FXML
    private void ViewBuddies(ActionEvent event) {
    }

    @FXML
    private void ViewFindBuddy(ActionEvent event) {
    }

    @FXML
    private void ViewExercise(ActionEvent event) {
    }

    @FXML
    private void ViewTrainingPrograms(ActionEvent event) {
    }

    @FXML
    private void LogoutBtn(ActionEvent event) {
    }
    
}
