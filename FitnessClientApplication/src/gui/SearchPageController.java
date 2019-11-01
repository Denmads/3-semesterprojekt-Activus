package gui;

import gui.cellsControllers.cellAllExerciseControler;
import Models.Exercise;
import gui.ContentPageController;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

/**
 * FXML Controller class
 * 
 * This is class is meant to search for buddies.
 *
 * @author Sebas
 * @auther Stped
 * @author Victor
 */
public class SearchPageController extends ContentPageController {

    private ListView<Exercise> searchFiled;
    private ObservableList<Exercise> test;

    @FXML
    private ComboBox<String> genderBox;
    @FXML
    private TextField cityTextfield;
    @FXML
    private ComboBox<Integer> ageBox;
    @FXML
    private ListView<?> searchField;
    @FXML
    private Button requestBuddyButton;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //test = FXCollections.observableArrayList();

        //Exercise testEx = new Exercise(112, "Push up", 0);
        //test.add(testEx);       
        //Exercise testEx1 = new Exercise(1, "Bench Press", 0);  
        //test.add(testEx1);
        
        //Making the requestBuddyButton invisible until it's needed.
        requestBuddyButton.setVisible(false);
        if (!searchField.getSelectionModel().isEmpty()) {
            requestBuddyButton.setVisible(true);
        }
        
        //Initializing ComboBoxes
        genderBox.getItems().add("Male");
        genderBox.getItems().add("Female");

        for (int i = 15; i < 100; i++) {
            ageBox.getItems().add(i);
        }
    }

    private void searchAction(KeyEvent event) {
        searchFiled.setItems(test);
        searchFiled.setCellFactory((ListView<Exercise> view) -> {
            return new cellAllExerciseControler(null);
        });
        searchFiled.refresh();

    }

    @FXML
    private void handleSearchButtonAction(ActionEvent event) {
    }

    @FXML
    private void handleRequestBuddyAction(ActionEvent event) {
        //Look at verifyLogin for inspiration.
    }

}