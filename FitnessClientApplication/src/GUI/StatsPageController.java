/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Domain.serviceInterfaces.IProfileService;
import Domain.serviceInterfaces.ITrainingSchemeService;
import Models.Exercise;
import Models.TrainingScheme;
import Domain.trainingScheme.TrainingSchemeService;
import Enums.ServiceType;
import Exceptions.ServiceNotFoundException;
import LayerInterfaces.IDomainFacade;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author steff
 */
public class StatsPageController extends ContentPageController {

    @FXML
    private Label exerciseName;
    @FXML
    private LineChart<Integer, Date> charid;
    @FXML
    private ChoiceBox<Exercise> choseBoks;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            IProfileService profileService = domainFacade.<IProfileService>getService(ServiceType.PROFILE);
            
        } catch (ServiceNotFoundException ex) {
            Logger.getLogger(StatsPageController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassCastException ex) {
            Logger.getLogger(StatsPageController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

}
