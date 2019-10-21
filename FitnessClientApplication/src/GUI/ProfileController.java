package GUI;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;

/**
 * FXML Controller class
 *
 * @author Sebas
 */
public class ProfileController implements Initializable {

    @FXML
    private ImageView CoverPic;
    @FXML
    private Circle ProfilePic;
    @FXML
    private TextArea nameTextField;
    @FXML
    private TextArea CuntryTextField;
    @FXML
    private TextArea SexTextField;
    @FXML
    private TextArea GymTextField;
    @FXML
    private TextArea CityTextField;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}