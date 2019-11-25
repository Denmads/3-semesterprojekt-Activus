/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.cellsControllers;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import models.SetInfo;

/**
 *
 * @author Denmads
 */
public class SetInfoCellController extends ListCell<SetInfo>{

    @FXML
    private Label repsLabel;
    @FXML
    private Label weightLabel;
    
    private TrainingProgramExerciseCellController parent;
    
    public SetInfoCellController (TrainingProgramExerciseCellController exercise) {
        this.parent = exercise;
    }
    
    
    @Override
    protected void updateItem(SetInfo item, boolean empty) {
        super.updateItem(item, empty); 
        
        if (empty || item == null) {
            setText(null);
            setGraphic(null);

        } else {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/FXML/listViewCells/SetInfoCell.fxml"));
        
            loader.setController(this);
            HBox parent = null;
            try{
               parent = loader.load();
               
            }catch(IOException ex){
                System.out.println(ex);
                return; 
            }
            
            item.setSetIndex(getIndex());
            repsLabel.setText(item.getReps() + "");
            weightLabel.setText(item.getWeight() + " Kg");
            
            
            setText(null);
            setGraphic(parent);
        }
    }
    
    @FXML
    private void deleteSet (MouseEvent event) {
        parent.deleteSet(getItem());
    }
    
    @FXML
    private void moveSetUp (MouseEvent event) {
        parent.moveSetUp(getIndex());
    }
    
    @FXML
    private void moveSetDown (MouseEvent event) {
        parent.moveSetDown(getIndex());
    }
}
