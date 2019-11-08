/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import gui.cellsControllers.cellAllExerciseControler;
import models.Exercise;
import gui.ContentPageController;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

/**
 * FXML Controller class
 *
 * @author Sebas
 * @auther Stped
 */
public class SearchPageController extends ContentPageController{
    @FXML
    private TextField search;
    @FXML
    private ListView<Exercise> searchFiled;
    private ObservableList<Exercise> test;
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         test = FXCollections.observableArrayList();
         
        Exercise testEx = new Exercise(112, "Push up", 0);
        
        
        test.add(testEx);       
        Exercise testEx1 = new Exercise(1, "Bench Press", 0);  
        
        test.add(testEx1);
        
       
    }    

    @FXML
    private void searchAction(KeyEvent event) { 
       searchFiled.setItems(test);
       searchFiled.setCellFactory((ListView<Exercise> view) -> {
           return new cellAllExerciseControler(null);
       });
        searchFiled.refresh();
        
        
        
    }
    
}
