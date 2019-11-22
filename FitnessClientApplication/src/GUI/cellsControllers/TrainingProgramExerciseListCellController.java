/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.cellsControllers;

import gui.TrainingProgramsPageController;
import models.Exercise;
import static gui.TrainingProgramsPageController.PROJECT_DATA_FORMAT;
import java.io.IOException;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.GridPane;

/**
 *
 * @author Denmads
 */
public class TrainingProgramExerciseListCellController extends ListCell<Exercise>{

    @FXML
    private Label exerciseNameLabel;
    @FXML
    private Label exerciseTypeLabel;
    
    private TrainingProgramsPageController controller;
    
    private Exercise exercise;
    
    private EventHandler<MouseEvent> dragDetectEvent;
    
    public TrainingProgramExerciseListCellController (TrainingProgramsPageController controller) {
        this.controller = controller;
        
        dragDetectEvent = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Dragboard db = startDragAndDrop(TransferMode.COPY);
                         ClipboardContent cc = new ClipboardContent();
                         cc.put(PROJECT_DATA_FORMAT, getItem());
                         db.setContent(cc);
                         event.consume();
            }
        };
    }
    
    
    @Override
    protected void updateItem(Exercise item, boolean empty) {
        super.updateItem(item, empty); 
        
        exercise = item;
        
        if (empty || item == null) {
            setText(null);
            setGraphic(null);
            setOnDragDetected(null);

        } else {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/FXML/listViewCells/TrainingProgramExerciseListCell.fxml"));
        
            loader.setController(this);
            GridPane parent = null;
            try{
               parent = loader.load();
               
            }catch(IOException ex){
                System.out.println(ex);
                return; 
            }
            
            
            exerciseNameLabel.setText(item.getName());
            exerciseTypeLabel.setText(item.getType());
            
            
            setText(null);
            setGraphic(parent);
            setOnDragDetected(dragDetectEvent);
        }
    }
    
    
}
