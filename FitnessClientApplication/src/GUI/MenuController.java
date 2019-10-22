package GUI;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Victor
 */
public class MenuController implements Initializable {

    @FXML
    private ImageView todayImageButton;
    @FXML
    private ImageView todayTextboxImage;
    @FXML
    private ImageView statsImageButton;
    @FXML
    private ImageView programImageButton;
    @FXML
    private ImageView profileImageButton;
    @FXML
    private ImageView emptyImageButton2;
    @FXML
    private ImageView buddyImageButton;
    @FXML
    private Label todayLabel;
    @FXML
    private Label statsLabel;
    @FXML
    private Label profileLabel;
    @FXML
    private Label scheduleLabel;
    @FXML
    private Label buddyLabel;
    @FXML
    private Label trainingPrgLabel;
    private PageLoader pageLoader=null;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }


    @FXML
    private void handleAction(MouseEvent event) {
       if(event.getSource()==todayImageButton){
           
       }       
       
        }

}
private void changepage(string path){
PageL
}