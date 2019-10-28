package GUI.cellsControllers;

import Domain.serviceInterfaces.ITrainingSchemeService;
import Enums.ServiceType;
import Exceptions.ServiceNotFoundException;
import LayerInterfaces.IDomainFacade;
import Models.Exercise;
import Models.SetInfo;
import java.io.IOException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

/**
 *
 * @author Sebas
 */
public class TodaysCellController extends ListCell<Exercise> {

    @FXML
    private Label ExerciseNameLabel;

    @FXML
    private VBox setsList;

    private ObservableList<Exercise> exerciseList;
    private Exercise exercise;
    
    private IDomainFacade domainFacade;

    public TodaysCellController(ObservableList<Exercise> exerciseList, IDomainFacade domainFacade) {

        this.exerciseList = exerciseList;
        this.domainFacade = domainFacade;
        

    }

    @FXML
    private void exerciseDone(ActionEvent event) {
        System.out.println("Done");
//        try {
//            domainFacade.<ITrainingSchemeService>getService(ServiceType.TRAININGSCHEME).exerciseForTodayDone(exercise);
//        } catch (ServiceNotFoundException ex) {
//            Logger.getLogger(TodaysCellController.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (ClassCastException ex) {
//            Logger.getLogger(TodaysCellController.class.getName()).log(Level.SEVERE, null, ex);
//        }

        exerciseList.remove(exercise);
        
    }

    @Override
    protected void updateItem(Exercise item, boolean empty) {
        super.updateItem(item, empty);

        if (empty || item == null) {
            setText(null);
            setGraphic(null);

        } else {
            exercise = item;
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/FXML/listViewCells/TodayCell.fxml"));
        
            loader.setController(this);
            VBox parent = null;
            try{
               parent = loader.load();
               
            }catch(IOException ex){
                System.out.println(ex);
                return; 
            }
            ExerciseNameLabel.setText(exercise.getName());
            for (SetInfo setInfo : exercise.getSetInfo()) {
                
                HBox root = new HBox();
                root.setPrefSize(Control.USE_COMPUTED_SIZE, Control.USE_COMPUTED_SIZE);
                root.setSpacing(25);
                root.setAlignment(Pos.CENTER);
                
                
                VBox repsBox = new VBox();
                repsBox.setPrefSize(75, Control.USE_COMPUTED_SIZE);
                repsBox.setAlignment(Pos.TOP_LEFT);
                HBox.setHgrow(repsBox, Priority.ALWAYS);
                root.getChildren().add(repsBox);
                
                Label reps = new Label("Reps: " + setInfo.getReps());
                
                repsBox.getChildren().add(reps);
                
                
                VBox weightBox = new VBox();
                weightBox.setPrefSize(Control.USE_COMPUTED_SIZE, Control.USE_COMPUTED_SIZE);
                weightBox.setAlignment(Pos.TOP_LEFT);
                HBox.setHgrow(weightBox, Priority.ALWAYS);
                root.getChildren().add(weightBox);
                
                Label weight = new Label("Weight: " + setInfo.getWeight());
                
                weightBox.getChildren().add(weight);
                
                setsList.getChildren().add(root);
                
                
                
                setText(null);
                setGraphic(parent);
            }
        }
    }
    
    

}
