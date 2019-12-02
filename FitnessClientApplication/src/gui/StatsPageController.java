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
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import models.Profile;
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
    private LineChart<Integer, Date> charid;
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
//            Stats s = new Stats(domainFacade.<IProfileService>getService(ServiceType.PROFILE).getCurrentProfile());
            Stats s = new Stats(new Profile(1));
            for(int j=0; j<5;j++){
            
        
            Exercise e = new Exercise(j, "bænk", j, "bryst");
            for(int i = 0; i<3;i++){
                SetInfo setinfo = new SetInfo((i+j), (i+10+j));
                e.addSetInfo(setinfo);
                
            }
            Date date = new Date(j,j,j,j,j);
            s.addExercises(e.getType(), date, e);
            s.addExercises(e.getType(), date, e);
            XYChart.Series reps= new XYChart.Series<>();
            XYChart.Series weight= new XYChart.Series<>();
            
            for (Map.Entry<String, HashMap> en : s.getStatsMap().entrySet()) {
                Object key = en.getKey();
                HashMap<Date,Exercise> value = en.getValue();
                for (Map.Entry<Date, Exercise> entry : value.entrySet()) {
                    Date key1 = entry.getKey();
                    Exercise value1 = entry.getValue();
                    for(SetInfo setInfo: value1.getSetInfo()){
                        reps.getData().add(new XYChart.Data<>(key1.toString(),setInfo.getReps()));
                        weight.getData().add(new XYChart.Data<>(key1.toString(),setInfo.getWeight()));
                        
                    }
                    
                    
                }
                
            }
            charid.getData().addAll(reps);
            charid.getData().addAll(weight);
            }
            
            
        } catch (ClassCastException ex) {
            Logger.getLogger(StatsPageController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void onContentInitialize() {
        
        
        
         try {
            Stats s = new Stats(domainFacade.<IProfileService>getService(ServiceType.PROFILE).getCurrentProfile());
            Exercise e = new Exercise(0, "bænk", 0, "bryst");
            for(int i = 0; i<3;i++){
                SetInfo setinfo = new SetInfo((i+1), (i+10));
                e.addSetInfo(setinfo);
                
            }
            s.addExercises(e.getType(), new Date(), e);
           //Stats stats =(Stats) domainFacade.<IProfileService>getService(ServiceType.PROFILE).getCurrentStats(domainFacade.<IProfileService>getService(ServiceType.PROFILE).getCurrentProfile().getProfileId());
            Stats stats = s;
            if(stats!=null){
                
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
                            for(SetInfo si : value1.getSetInfo()){
                              series.getData().add(new XYChart.Data<>(si.getReps(), key1));  
                            }
                           
                           
                       });
                       charid.getData().addAll(series);
                   }
               });
           });
                
            }else{
               showAlert("No Stats!", "There are not stats to your profile");
               
            }
            
            
            
//           Stats stats = new Stats(domainFacade.<IProfileService>getService(ServiceType.PROFILE).getCurrentProfile());
//            
//           Exercise exercise= new Exercise(0, "fitness", 0, "noget");
//           for(int i=0; i<2;i++){
//            SetInfo set = new SetInfo(i, i); 
//            exercise.addSetInfo(set);
//           }
//           
//           Date date = new Date();
//           
//           stats.addExercises(exercise.getType(),date , exercise);
           
           
           
           
          

        } catch (ServiceNotFoundException | ClassCastException ex) {
            Logger.getLogger(StatsPageController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
<<<<<<< HEAD:FitnessClientApplication/src/gui/StatsPageController.java
    private void showAlert (String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
=======

    @Override
    public void onContentInitialize() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
>>>>>>> master:FitnessClientApplication/src/GUI/StatsPageController.java
