/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author steff
 */
public class RootPageController extends PageHandler implements Initializable {

    @FXML
    private Pane menuPane;
    @FXML
    private Pane subPane;
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
        double width;
    @FXML
    private Pane Rootpane;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        changeFxml(subPane, "FXML/Menu.fxml");
        menuPane.setPrefWidth(300);
        width = Rootpane.getWidth();
    }    

    @FXML
    private void MouseExited(MouseEvent event) {
        menuPane.setPrefWidth(1);
        
        
        
    }

    @FXML
    private void MouseEntered(MouseEvent event) {
        menuPane.setPrefWidth(300);
        
        
    }

    @FXML
    private void ViewProfile(ActionEvent event) {
        changeFxml(subPane, "FXML/Profile.fxml");
       
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
