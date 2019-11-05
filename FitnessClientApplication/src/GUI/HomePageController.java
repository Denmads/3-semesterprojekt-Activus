/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import gui.cellsControllers.TodaysCellController;
import Models.Exercise;
import Models.SetInfo;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

/**
 * FXML Controller class
 *
 * @author Sebas
 */
public class HomePageController extends ContentPageController {
    @FXML
    private ListView<Exercise> listViewTodaysTraining;
    
    private ObservableList<Exercise> test;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        test = FXCollections.observableArrayList();
        
        Exercise testEx = new Exercise(112, "Push up", 0, "Chest");
        testEx.addSetInfo(new SetInfo(10, 15));
        testEx.addSetInfo(new SetInfo(12, 25));
        testEx.addSetInfo(new SetInfo(1, 35));
        test.add(testEx);
        
        Exercise testEx1 = new Exercise(1, "Bench Press", 0, "Chest");
        testEx1.addSetInfo(new SetInfo(100, 150));
        testEx1.addSetInfo(new SetInfo(120, 250));
        testEx1.addSetInfo(new SetInfo(10, 350));
        test.add(testEx1);
        
        listViewTodaysTraining.setItems(test);
        listViewTodaysTraining.setCellFactory((ListView<Exercise> view) -> {
            return new TodaysCellController(test, null);
        });
        //listViewTodaysTraining.refresh();
    }
    
}
