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
import gui.TrainingProgramsPageController;
import java.io.IOException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import models.TrainingProgram;

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
    private DomainFacade domainFacade;
    
    public TrainingProgramExerciseCellController (TrainingProgramsPageController controller, DomainFacade facade) {
        this.controller = controller;
        domainFacade = facade;
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
            
            
            if (getIndex() != item.getIndexInProgram()) {
                TrainingProgram program = controller.getCurrentProgram();
                try {
                    domainFacade.<ITrainingSchemeService>getService(ServiceType.TRAININGSCHEME).updateExerciseIndex(item.getID(), program.getId(), getIndex());
                } catch (ServiceNotFoundException | ClassCastException ex) {
                    Logger.getLogger(TrainingProgramExerciseCellController.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
            item.setIndexInProgram(getIndex());
            
            sets = FXCollections.observableArrayList();
            sets.addAll(item.getSetInfo());
            setsList.setItems(sets);
            setsList.setCellFactory(view -> new SetInfoCellController(this, domainFacade));
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
    private void moveExerciseUp (MouseEvent event) {
        controller.moveExerciseUp(getIndex());
    }
    
    @FXML
    private void moveExerciseDown (MouseEvent event) {
        controller.moveExerciseDown(getIndex());
    }
    
    @FXML
    private void addSet (MouseEvent event) {
        try {
            SetInfo info = new SetInfo(new Random().nextInt(20), 10);
            info.setSetIndex(sets.size());
            domainFacade.<ITrainingSchemeService>getService(ServiceType.TRAININGSCHEME).addSetToExercise(getItem().getID(), controller.getCurrentProgram().getId(), info);
            sets.add(info);
            getItem().addSetInfo(info);
            setsList.setPrefHeight(33.5 * sets.size());
        } catch (ServiceNotFoundException ex) {
            Logger.getLogger(TrainingProgramExerciseCellController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassCastException ex) {
            Logger.getLogger(TrainingProgramExerciseCellController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void deleteSet (SetInfo info) {
        try {
            domainFacade.<ITrainingSchemeService>getService(ServiceType.TRAININGSCHEME).removeSet(info.getId());
            sets.remove(info);
            getItem().removeSetInfo(info);
            setsList.refresh();
            setsList.setPrefHeight(33.5 * sets.size());
        } catch (ServiceNotFoundException | ClassCastException ex) {
            Logger.getLogger(TrainingProgramExerciseCellController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void moveSetUp (int indexOfSet) {
        if (indexOfSet == 0) {
            return;
        }
        
        SetInfo info = sets.get(indexOfSet);
        sets.remove(indexOfSet);
        sets.add(indexOfSet-1, info);
        setsList.refresh();
        
        //Update set index of indexOfSet - 1 and indexOfSet in DB
    }
    
    public void moveSetDown (int indexOfSet) {
        if (indexOfSet == sets.size()-1) {
            return;
        }
        
        SetInfo info = sets.get(indexOfSet);
        sets.remove(indexOfSet);
        sets.add(indexOfSet+1, info);
        setsList.refresh();
        
        //Update set index of indexOfSet + 1 and indexOfSet in DB
    }
}
