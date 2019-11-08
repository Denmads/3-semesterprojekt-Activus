/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.cellsControllers;

import gui.TrainingProgramsPageController;
import java.io.IOException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import models.Exercise;
import models.SetInfo;

/**
 *
 * @author Denmads
 */
public class TrainingProgramExerciseCellController extends ListCell<Exercise>{

    @FXML
    private Label nameLabel;
    @FXML
    private ListView<SetInfo> setsList;
    private ObservableList<SetInfo> sets;
    
    private TrainingProgramsPageController controller;
    
    public TrainingProgramExerciseCellController (TrainingProgramsPageController controller) {
        this.controller = controller;
        sets = FXCollections.observableArrayList();
    }
    
    
    @Override
    protected void updateItem(Exercise item, boolean empty) {
        super.updateItem(item, empty); 
        
        if (empty || item == null) {
            setText(null);
            setGraphic(null);

        } else {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/FXML/listViewCells/TrainingProgramExerciseCell.fxml"));
        
            loader.setController(this);
            GridPane parent = null;
            try{
               parent = loader.load();
               
            }catch(IOException ex){
                System.out.println(ex);
                return; 
            }
            
            
            nameLabel.setText(item.getName());
            
            sets.addAll(item.getSetInfo());
            setsList.setItems(sets);
            setsList.setCellFactory(view -> new SetInfoCellController(this));
            setsList.setPrefHeight(33.5 * sets.size());
            
            setText(null);
            setGraphic(parent);
        }
    }
    
    @FXML
    private void deleteExercise (MouseEvent event) {
        controller.deleteExerciseFromProgram(getItem());
    }
    
    
    @FXML
    private void addSet (MouseEvent event) {
        SetInfo info = new SetInfo(10, 10);
        sets.add(info);
        getItem().addSetInfo(info);
        setsList.setPrefHeight(33.5 * sets.size());
    }
    
    public void deleteSet (SetInfo info) {
        //Remove from database
        sets.remove(info);
        getItem().removeSetInfo(info);
        setsList.refresh();
        setsList.setPrefHeight(33.5 * sets.size());
    }
}
