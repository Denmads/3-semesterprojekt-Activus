/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import Models.Exercise;
import Models.TrainingProgram;
import gui.ContentPageController;
import gui.cellsControllers.TrainingProgramCellController;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
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
    @FXML
    private HBox trainingProgramView;
    @FXML
    private ListView<Exercise> exerciseInProgramList;
    
    private ObservableList<Exercise> exercises;
    @FXML
    private Label trainingProgramLabel;
    @FXML
    private Label trainingProgramDesc;
    @FXML
    private Label hintTextLabel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        exercises = FXCollections.observableArrayList();
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
        
    }

    @FXML
    private void addNewtrainingProgram(ActionEvent event) {
        TrainingProgram program = new TrainingProgram();
        if (openDialog("", "", false, program)) {
            trainingPrograms.add(program);
            showProgramDetails(program);
        }
    }
    
    public void deleteTrainingProgram (TrainingProgram program) {
        trainingPrograms.remove(program);
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
        
        trainingProgramList.refresh();
        return result.isPresent();
    }
    
    private void showProgramDetails (TrainingProgram program) {
        trainingProgramView.setDisable(false);
        trainingProgramView.setVisible(true);
        
        trainingProgramLabel.setText(program.getName());
        trainingProgramDesc.setText(program.getDescription());
        
        hintTextLabel.setVisible(program.getExercises().isEmpty());
    }
    
    private void hideProgramDetails () {
        trainingProgramView.setDisable(true);
        trainingProgramView.setVisible(false);
    }
}
