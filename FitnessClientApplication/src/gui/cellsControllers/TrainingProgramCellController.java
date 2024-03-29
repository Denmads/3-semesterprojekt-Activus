/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.cellsControllers;

import Enums.ServiceType;
import Exceptions.ServiceNotFoundException;
import domain.DomainFacade;
import domain.serviceInterfaces.ITrainingSchemeService;
import models.TrainingProgram;
import gui.TrainingProgramsPageController;
import java.io.IOException;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

/**
 *
 * @author Denmads
 */
public class TrainingProgramCellController extends ListCell<TrainingProgram>{

    @FXML
    private Label programNameLabel;
    @FXML
    private Label numExerciseLabel;
    
    private TrainingProgramsPageController controller;
    private DomainFacade domainFacade;
    
    private TrainingProgram program;
    
    public TrainingProgramCellController (TrainingProgramsPageController controller, DomainFacade facade) {
        this.controller = controller;
        domainFacade = facade;
    }
    
    
    @Override
    protected void updateItem(TrainingProgram item, boolean empty) {
        super.updateItem(item, empty); 
        
        program = item;
        
        if (empty || item == null) {
            setText(null);
            setGraphic(null);

        } else {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/FXML/listViewCells/TrainingProgramCell.fxml"));
        
            loader.setController(this);
            GridPane parent = null;
            try{
               parent = loader.load();
               
            }catch(IOException ex){
                System.out.println(ex);
                return; 
            }
            
            
            programNameLabel.setText(item.getName());
            numExerciseLabel.setText(item.getExercises().size() + "");
            
            
            setText(null);
            setGraphic(parent);
        }
    }
    
    @FXML
    private void deleteTrainingProgram (MouseEvent event) {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Delete Training Program?");
        alert.setHeaderText(null);
        alert.setContentText("Do you really want to delete " + program.getName() + "?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            controller.deleteTrainingProgram(program);
        }
    }
    
    @FXML
    private void editTrainingProgram (MouseEvent event) {
        if (controller.openDialog(program.getName(), program.getDescription(), true, program)) {
            try {
                domainFacade.<ITrainingSchemeService>getService(ServiceType.TRAININGSCHEME).updateTrainingProgramInfo(program.getId(), program.getName(), program.getDescription());
                controller.showProgramDetails(program);
            } catch (ServiceNotFoundException | ClassCastException ex) {
                Logger.getLogger(TrainingProgramCellController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    
}
