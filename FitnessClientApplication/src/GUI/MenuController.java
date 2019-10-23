/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author steff
 */
public class MenuController extends PageHandler implements Initializable {

    @FXML
    private AnchorPane pane;
    @FXML
    private ImageView calenderBtn;
    @FXML
    private ImageView buddylistBtn;
    @FXML
    private ImageView profileBtn;
    @FXML
    private ImageView statsBtn;
    @FXML
    private ImageView exiciseBtn;
    @FXML
    private ImageView workoutBtn;
    @FXML
    private ImageView InboksBtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

    @FXML
    private void OnMousclickHandler(MouseEvent event) {
        if(event.getSource()==calenderBtn){
            System.out.println("calender");
        }else if(event.getSource()== buddylistBtn){
            changeFxml(pane, "FXML/BuddyListPage.fxml");
        }else if(event.getSource()==profileBtn){        
            changeFxml(pane, "FXML/Profile.fxml");
        }else if(event.getSource()== statsBtn){
            changeFxml(pane, "FXML/StatePage.fxml");
        }else if (event.getSource()==exiciseBtn){
            changeFxml(pane, "FXML/ExiciesePage.fxml");
        }else if(event.getSource()==workoutBtn){
            changeFxml(pane, "FXML/WorkoutPage.fxml");
        }else if (event.getSource()==InboksBtn){
            changeFxml(pane, "FXML/InboksPage.fxml");
        }else
                System.out.println("?");
    }
    
}
