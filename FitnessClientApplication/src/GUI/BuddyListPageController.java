/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Models.Profile;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author steff
 */
public class BuddyListPageController extends PageHandler implements Initializable {

    
    @FXML
    private AnchorPane pane;
    @FXML
    private ImageView Menubtn;
    @FXML
    private AnchorPane tilePane;
    @FXML
    private VBox Vboks;
    private List<Profile> buddyList;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         for (int i = 0; i < 5; i++) {
             Profile p=new Profile(i);
             ProfileItem(p);       
        }
        
            
 
           
        
        
        
        
       
        
        
        
    }    

    @FXML
    private void ActionOnMouseClick(MouseEvent event) {
        if(event.getSource()==Menubtn){
            changeFxml(pane, "FXML/Menu.fxml");
        }
    }
         private void ProfileItem(Profile profile){
          AnchorPane g = new AnchorPane();
          changeFxml(g, "FXML/UserShow.fxml");
          
           Vboks.getChildren().add(g);
           Label l = new Label();           
           Vboks.getChildren().add(l);
     }   
           
}
