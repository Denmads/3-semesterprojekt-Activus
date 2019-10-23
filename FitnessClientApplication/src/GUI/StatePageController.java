/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Domain.TrainingScheme.Exercise;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.ChoiceBox;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author steff
 */
public class StatePageController extends PageHandler implements Initializable {

    @FXML
    private AnchorPane pane;
    @FXML
    private NumberAxis timeakse;
    @FXML
    private CategoryAxis dataakse;
    @FXML
    private ChoiceBox<Exercise> dataBoks;
    @FXML
    private ImageView menuBtn;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }

    @FXML
    private void onMouseClick(MouseEvent event) {
    }

}
