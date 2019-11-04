/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import Communication.CommunicationFacade;
import Enums.ServiceType;
import Exceptions.ConfigFileNotFound;
import Exceptions.ServiceNotFoundException;
import Models.Exercise;
import domain.DomainFacade;
import domain.serviceInterfaces.IProfileService;
import domain.serviceInterfaces.ITrainingSchemeService;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;

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
    private ChoiceBox<String> choseBoks;
    private ObservableList<Exercise> exerciseList;
    
    /**
     * Initializes the controller class.
     * @param url
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
//        choseBoks.getItems().add(new Exercise(1, "pusups",0 ).getName());
//        choseBoks.getItems().add(new Exercise(2, "pullups",0 ).getName());
        // creating choisboks with users exercises
        try {
            domainFacade = new DomainFacade(new CommunicationFacade());
           // exerciseList = FXCollections.observableArrayList(domainFacade.<IProfileService>getService(ServiceType.TRAININGSCHEME).getCurrentStats(domainFacade.<IProfileService>getService(ServiceType.PROFILE).getCurrentProfile().getProfileId()));
           exerciseList = FXCollections.observableArrayList(domainFacade.<ITrainingSchemeService>getService(ServiceType.TRAININGSCHEME).loadAllExercise());
            if (!exerciseList.isEmpty()) {
                exerciseList.forEach((e) -> {
                    choseBoks.getItems().add(e.getName());
                });
                
            }

        } catch (ServiceNotFoundException ex) {
            Logger.getLogger(StatsPageController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassCastException ex) {
            Logger.getLogger(StatsPageController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ConfigFileNotFound ex) {
            Logger.getLogger(StatsPageController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(StatsPageController.class.getName()).log(Level.SEVERE, null, ex);
        }


    }

}
