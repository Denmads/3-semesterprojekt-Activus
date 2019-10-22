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
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author steff
 */
public class BuffyListPageController extends PageHandler implements Initializable {

    @FXML
    private TextField searchfiels;
    @FXML
    private ImageView menuBtn;
    @FXML
    private ImageView searchBtn;
    @FXML
    private ListView<?> resultList;
    @FXML
    private AnchorPane pane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void ActionOnMouseClick(MouseEvent event) {
        if(event.getSource()==menuBtn){
            changeFxml(pane, "FXML/Menu.fxml");
        }else if (event.getSource()==searchBtn){
            
        }
    }
    
}
