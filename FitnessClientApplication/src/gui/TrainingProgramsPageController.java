/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import Enums.ServiceType;
import Exceptions.ServiceNotFoundException;
import gui.cellsControllers.TrainingProgramExerciseListCellController;
import models.Exercise;
import models.TrainingProgram;
import domain.serviceInterfaces.ITrainingSchemeService;
import gui.cellsControllers.TrainingProgramCellController;
import gui.cellsControllers.TrainingProgramExerciseCellController;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.DataFormat;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.util.Pair;

/**
 * FXML Controller class
 *
 * @author steff
 */
public class TrainingProgramsPageController extends ContentPageController {

    @FXML
    private ListView<TrainingProgram> trainingProgramList;
    
    private ObservableList<TrainingProgram> trainingPrograms;
    @FXML
    private ListView<Exercise> exerciseList;
    private ObservableList<Exercise> exercises;
    private FilteredList<Exercise> filteredExercises;
    @FXML
    private HBox trainingProgramView;
    @FXML
    private ListView<Exercise> exerciseInProgramList;
    private ObservableList<Exercise> addedExercises;
            
    @FXML
    private Label trainingProgramLabel;
    @FXML
    private Label trainingProgramDesc;
    @FXML
    private Label hintTextLabel;
    @FXML
    private TextField exerciseSearchField;
    @FXML
    private ChoiceBox<String> exerciseTypeChb;

    
    public static final DataFormat PROJECT_DATA_FORMAT = new DataFormat("Exercise");
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        exercises = FXCollections.observableArrayList();
        addedExercises = FXCollections.observableArrayList();
        trainingPrograms = FXCollections.observableArrayList();
        
        trainingProgramList.setItems(trainingPrograms);
        trainingProgramList.setCellFactory(view -> new TrainingProgramCellController(this));
        
        
        trainingProgramList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<TrainingProgram>() {
            @Override
            public void changed(ObservableValue<? extends TrainingProgram> observable, TrainingProgram oldValue, TrainingProgram newValue) {
                if (newValue != null) {
                    showProgramDetails(newValue);
                }
                else {
                    hideProgramDetails();
                }
            }
        });
        
        
    }    

    @Override
    public void onContentInitialize() {
        try {
            exercises.addAll(domainFacade.<ITrainingSchemeService>getService(ServiceType.TRAININGSCHEME).getAllExercises());
            setupExerciseList();
            
            populateChoiceBox();
            setupExerciseSearch();
        } catch (ServiceNotFoundException ex) {
            Logger.getLogger(TrainingProgramsPageController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassCastException ex) {
            Logger.getLogger(TrainingProgramsPageController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void populateChoiceBox () {
        ObservableList<String> types = FXCollections.observableArrayList();
        types.add("All");
        types.add("Chest");
        types.add("Bicep");
        types.add("Tricep");
        types.add("Core");
        types.add("Shoulder");
        types.add("Leg");
        types.add("Buttocks");
        types.add("Back");
        
        exerciseTypeChb.setItems(types);
        exerciseTypeChb.getSelectionModel().selectFirst();
    }
    
    private void setupExerciseSearch () {
        Predicate<Exercise> textSearchPredicate = new Predicate<Exercise>() {
            @Override
            public boolean test(Exercise t) {
                if (exerciseSearchField.getText().length() == 0) {
                    return true;
                }
                
                return t.getName().toLowerCase().startsWith(exerciseSearchField.getText().toLowerCase());
            }
        };
        
        Predicate<Exercise> typeSearchPredicate = new Predicate<Exercise>() {
            @Override
            public boolean test(Exercise t) {
                if (exerciseTypeChb.getSelectionModel().getSelectedIndex() == 0) {
                    return true;
                }
                
                String type = exerciseTypeChb.getSelectionModel().getSelectedItem();
                return t.getType().equalsIgnoreCase(type);
            }
        };
        
        exerciseSearchField.textProperty().addListener((observable, oldVal, newVal) -> {
            filteredExercises.setPredicate(typeSearchPredicate.and(textSearchPredicate));
        });
        
        exerciseTypeChb.getSelectionModel().selectedItemProperty().addListener((observable, oldVal, newVal) -> {
            filteredExercises.setPredicate(typeSearchPredicate.and(textSearchPredicate));
        });
    }

    private void setupExerciseList () {
        filteredExercises = new FilteredList<>(exercises, p -> true);
        exerciseList.setItems(filteredExercises);
        exerciseList.setCellFactory(view -> {
            return new TrainingProgramExerciseListCellController(this);
        });
        exerciseList.refresh();
        
        exerciseInProgramList.setItems(addedExercises);
        exerciseInProgramList.setCellFactory(view -> {
            TrainingProgramExerciseCellController cell =  new TrainingProgramExerciseCellController(this);
            
            cell.setOnDragDropped(new EventHandler<DragEvent>() {
                @Override
                public void handle(DragEvent event) {
                    
                    
                    Dragboard db = event.getDragboard();
                    if (db.hasContent(PROJECT_DATA_FORMAT)) {
                        Exercise exercise = (Exercise) db.getContent(PROJECT_DATA_FORMAT);
                        Exercise addedExercise = new Exercise();
                        addedExercise.setID(exercise.getID());
                        addedExercise.setName(exercise.getName());
                        addedExercise.setDescription(exercise.getDescription());
                        addedExercise.setType(exercise.getType());
                        addedExercise.setSet(exercise.getSet());

                        
                        int index = addedExercises.size();
                        if (!cell.isEmpty()) {
                            index = cell.getIndex()+1;
                        }
                        addedExercise.setIndexInProgram(index);
                         
                        //Add exercise in database
                        System.out.println("Drop on cell");
                        addedExercises.add(index, addedExercise);
                        trainingProgramList.getSelectionModel().getSelectedItem().addExercise(addedExercise);
                    }
                }
            });
            
            return cell;
        });
        
        exerciseInProgramList.setOnDragOver(new EventHandler<DragEvent>() {
            @Override
            public void handle(DragEvent event) {
                if (event.getDragboard().hasContent(PROJECT_DATA_FORMAT)) {
                        event.acceptTransferModes(TransferMode.COPY);
                   }
            }
        });
        
        exerciseInProgramList.setOnDragDropped(new EventHandler<DragEvent>() {
            @Override
            public void handle(DragEvent event) {
                Dragboard db = event.getDragboard();
                if (db.hasContent(PROJECT_DATA_FORMAT)) {
                     Exercise exercise = (Exercise) db.getContent(PROJECT_DATA_FORMAT);
                     Exercise addedExercise = new Exercise();
                     addedExercise.setID(exercise.getID());
                     addedExercise.setName(exercise.getName());
                     addedExercise.setDescription(exercise.getDescription());
                     addedExercise.setType(exercise.getType());
                     addedExercise.setSet(exercise.getSet());
                     
                     //Add exercise in database
                     System.out.println("Drop on list");
                     addedExercises.add(addedExercise);
                     trainingProgramList.getSelectionModel().getSelectedItem().addExercise(addedExercise);
                     trainingProgramList.refresh();
                }
            }
        });
    }
    
    @FXML
    private void addNewtrainingProgram(ActionEvent event) {
        TrainingProgram program = new TrainingProgram();
        if (openDialog("", "", false, program)) {
            //Add program database
            trainingPrograms.add(program);
            showProgramDetails(program);
        }
    }
    
    public void deleteTrainingProgram (TrainingProgram program) {
        //delete program database
        trainingPrograms.remove(program);
        trainingProgramList.refresh();
    }
    
    public void deleteExerciseFromProgram (Exercise exercise) {
        //Delete exercise link in db
        addedExercises.remove(exercise);
        trainingProgramList.getSelectionModel().getSelectedItem().removeExercise(exercise);
        exerciseInProgramList.refresh();
        trainingProgramList.refresh();
    }
    
    public boolean openDialog (String name, String desc, boolean edit, TrainingProgram program) {
        // Create the custom dialog.
        Dialog<Pair<String, String>> dialog = new Dialog<>();
        dialog.setTitle((edit ? "Edit" : "New") + " Training program");
        dialog.setHeaderText(null);

        // Set the button types.
        ButtonType confirmButtonType = new ButtonType("Confirm", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(confirmButtonType, ButtonType.CANCEL);

        // Create the name and description labels and fields.
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        TextField nameField = new TextField();
        nameField.setPromptText("Name");
        nameField.setText(name);
        TextArea descriptionField = new TextArea();
        descriptionField.setPromptText("Description");
        descriptionField.setText(desc);

        grid.add(new Label("Name:"), 0, 0);
        grid.add(nameField, 1, 0);
        grid.add(new Label("Description:"), 0, 1);
        grid.add(descriptionField, 1, 1);

        // Enable/Disable confirm button depending on whether a name was entered.
        Node confirmButton = dialog.getDialogPane().lookupButton(confirmButtonType);
        confirmButton.setDisable(true);

        // Do some validation (using the Java 8 lambda syntax).
        nameField.textProperty().addListener((observable, oldValue, newValue) -> {
            confirmButton.setDisable(newValue.trim().isEmpty() || newValue.trim().length() > 20);
        });

        dialog.getDialogPane().setContent(grid);

        // Request focus on the name field by default.
        Platform.runLater(() -> nameField.requestFocus());

        // Convert the result to a name-description-pair when the confirm button is clicked.
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == confirmButtonType) {
                return new Pair<>(nameField.getText(), descriptionField.getText());
            }
            return null;
        });
        
        dialog.initModality(Modality.APPLICATION_MODAL);
        Optional<Pair<String, String>> result = dialog.showAndWait();

        result.ifPresent(info -> {
            program.setName(info.getKey());
            program.setDescription(info.getValue());
        });
        
        //if edit update program in database
        
        trainingProgramList.refresh();
        return result.isPresent();
    }
    
    private void showProgramDetails (TrainingProgram program) {
        trainingProgramView.setDisable(false);
        trainingProgramView.setVisible(true);
        
        trainingProgramLabel.setText(program.getName());
        trainingProgramDesc.setText(program.getDescription());
        
        hintTextLabel.setVisible(program.getExercises().isEmpty());
        
        addedExercises.clear();
        addedExercises.addAll(program.getExercises());
        exerciseInProgramList.refresh();
    }
    
    private void hideProgramDetails () {
        trainingProgramView.setDisable(true);
        trainingProgramView.setVisible(false);
    }
}
