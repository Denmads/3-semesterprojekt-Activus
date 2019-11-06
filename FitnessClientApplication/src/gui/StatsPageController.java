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
import Models.Profile;
import Models.SetInfo;
import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import domain.DomainFacade;
import domain.serviceInterfaces.IProfileService;
import domain.serviceInterfaces.ITrainingSchemeService;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.Observable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.util.Callback;
import models.Stats;

/**
 * FXML Controller class
 *
 * @author steff
 */
public class StatsPageController extends ContentPageController {

    @FXML
    private Label exerciseName;
    @FXML
    private LineChart<SetInfo, Date> charid;
    @FXML
    private ChoiceBox<String> choseBoks;
    private ObservableList<Exercise> exerciseList;
   
    /**
     * Initializes the controller class.
     * @param url
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         try {
           Stats stats =(Stats) domainFacade.<IProfileService>getService(ServiceType.PROFILE).getCurrentStats(domainFacade.<IProfileService>getService(ServiceType.PROFILE).getCurrentProfile().getProfileId());
        for (int i = 0; i < 10; i++) {
            Exercise e = new Exercise(i, ("pus"+i), i);
            for (int j = 0; j < 3; j++) {
                SetInfo s = new SetInfo(j, i);
                e.addSetInfo(s);
            }
            
            Date s= new Date();
            String t=i+"tyoe";
            stats.addExercises(t, s, e);
        }

        // creating choisboks with users exercises
        
        for (Map.Entry<String, HashMap> entry : stats.getStatsMap().entrySet()) {
                choseBoks.getItems().add(entry.getKey());
                
            }
           
            choseBoks.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
               @Override
               public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                   
                   
                   
                   exerciseName.setText(choseBoks.getItems());
                   
                   
               }
           });

        } catch (ServiceNotFoundException ex) {
            Logger.getLogger(StatsPageController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassCastException ex) {
            Logger.getLogger(StatsPageController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        
        
    }
    

}
