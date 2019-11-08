/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import Enums.ServiceType;
import Exceptions.ServiceNotFoundException;
import models.Exercise;
import models.SetInfo;
import domain.serviceInterfaces.IProfileService;
import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
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
   
   
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         try {
           Stats stats =(Stats) domainFacade.<IProfileService>getService(ServiceType.PROFILE).getCurrentStats(domainFacade.<IProfileService>getService(ServiceType.PROFILE).getCurrentProfile().getProfileId());
        
           stats.getStatsMap().entrySet().forEach((entry) -> {
               String key = entry.getKey();
               HashMap value = entry.getValue();
               choseBoks.getItems().add(key);
             });

            choseBoks.getSelectionModel().selectedIndexProperty().addListener((ObservableValue<? extends Number> observable, Number oldValue, Number newValue) -> {
                exerciseName.setText(choseBoks.getItems().toString());
                charid.setTitle(choseBoks.getItems().toString());
                stats.getStatsMap().entrySet().forEach((entry) -> {
                    Object key = entry.getKey();
                    HashMap<Date,Exercise> value = entry.getValue();
                    XYChart.Series series = new XYChart.Series();
                   if (choseBoks.getItems()==key) {
                       value.entrySet().forEach((entry1) -> {
                           Date key1 = entry1.getKey();
                           Exercise value1 = entry1.getValue();
                           series.getData().add(new XYChart.Data(value1.getSetInfo(), key1));
                       });
                       charid.getData().addAll(series);
                   }
               });
           });

        } catch (ServiceNotFoundException | ClassCastException ex) {
            Logger.getLogger(StatsPageController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        
        
    }
    

}
