/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.cellsControllers;

import LayerInterfaces.IDomainFacade;
import Models.Exercise;
import java.io.IOException;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
/**
 *
 * @author Sebas
 */
public class cellAllExerciseControler extends ListCell<Exercise>{
    
    
    
    @FXML
    private Label ExerciseName;
    
    @FXML
    private Label ExerciseDescription;
    
    
   
    private Exercise exercise;
    
    private final IDomainFacade domainFacade;

    public cellAllExerciseControler( IDomainFacade domainFacade) {
        
        this.domainFacade = domainFacade;
       
        
        
        
    }
    
    
    
    @Override
    protected void updateItem(Exercise item, boolean empty) {
        super.updateItem(item, empty);
          
        if (empty || item == null) {
            setText(null);
            setGraphic(null);

        } else {
            exercise = item;
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/FXML/listViewCells/ExerciseCell.fxml"));  
            
            loader.setController(this);
            HBox parrent= null;
           
            try{
                parrent = loader.load();
                
            }catch(IOException ex){
                System.out.println(ex);
                return; 
            }
            ExerciseName.setText(exercise.getName());
            ExerciseDescription.setText(exercise.getDescription());
            setText(null);
            setGraphic(parrent);
    }
    }
}
