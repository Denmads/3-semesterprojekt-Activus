package gui;

import Enums.SearchType;
import Enums.ServiceType;
import Exceptions.ServiceNotFoundException;
import Models.Exercise;
import Models.Profile;
import domain.profile.ProfileService;
import domain.serviceInterfaces.IProfileService;
import domain.serviceInterfaces.ITrainingSchemeService;
import gui.cellsControllers.ProfileCellController;
import gui.cellsControllers.cellAllExerciseControler;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * This is class is meant to search for buddies & Exercises.
 *
 * @author Victor
 */
public class SearchPageController extends ContentPageController {

    @FXML
    private ComboBox<String> genderBox;
    @FXML
    private TextField cityTextfield;
    @FXML
    private ComboBox<Integer> ageBox;
    @FXML
    private ListView<Profile> searchField;
    @FXML
    private Button requestBuddyButton;
    @FXML
    private Label returnLabel;
    @FXML
    private ComboBox<String> exerciseTypeBox;
    @FXML
    private TextField exerciseNameField;
    @FXML
    private ListView<Exercise> exerciseListField;
    @FXML
    private Label resultLabel;
    @FXML
    private RadioButton searchNameRadioButton;

    private ObservableList<Exercise> allExercisesList = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     *
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Initializing comboBoxes
        loadBoxes();

        //Search Profile--------------------------------------------------------
        //Making the requestBuddyButton & returnSearchLabel invisible until they're needed.
        requestBuddyButton.setVisible(false);
        returnLabel.setVisible(false);

        //Search Exercises------------------------------------------------------
        //Making resultLabel invisible until it's needed.
        resultLabel.setVisible(false);

        //Shall only be editable when radiobutton is selected.
        exerciseNameField.setEditable(false);

        //Loading all exercises to the listview
        //This shit doesn't work. Loading domainFacade into initialize.
        loadAllExercises();
    }
    
    

    @FXML
    private void handleSearchProfileButtonAction(ActionEvent event) {
        //Ensuring the label & requestBuddyButton is invisible.
        returnLabel.setVisible(false);
        requestBuddyButton.setVisible(false);

        String gender = genderBox.getValue();
        String city = cityTextfield.getText();
        int age = ageBox.getValue();

        System.out.println("Searching with the chosen values/parameters");

        List<Profile> cityList = new ArrayList();
        ObservableList<Profile> returnList = FXCollections.observableArrayList();

        //Clearing list to make sure there aren't duplicates. In case the user keeps searching.
        cityList.clear();
        returnList.clear();

        try {
            cityList = domainFacade.<IProfileService>getService(ServiceType.PROFILE).search(city, SearchType.CITY);

        } catch (ServiceNotFoundException | ClassCastException ex) {
            Logger.getLogger(SearchPageController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (cityList.isEmpty()) {
                returnLabel.setVisible(true);
                returnLabel.setText("Search returned no results");
            }
        }

        //Checking if the returned results match the search parameters.
        for (Profile p : cityList) {
            if (p.getGender().equals(gender) && p.getAge() >= age) {
                returnList.add(p);
            }
        }

        //If the returned profiles match the search parameters they are put in the list.
        if (!returnList.isEmpty()) {
            searchField.setItems(returnList);
            searchField.setCellFactory((view) -> {
                return new ProfileCellController(domainFacade);
            });

            searchField.refresh();

            //Adding request button.
            if (!searchField.getItems().isEmpty()) {
                requestBuddyButton.setVisible(true);
            }
        }
    }

    @FXML
    private void handleRequestBuddyAction(ActionEvent event) {

        //Getting the selected users/buddy's id.
        int userid = searchField.getSelectionModel().getSelectedItem().getProfileId();

        //Getting the current users buddies
        int[] currentUserBuddies = null;
        try {
            currentUserBuddies = domainFacade.<IProfileService>getService(ServiceType.PROFILE).getCurrentProfile().getBuddyIds();
        } catch (ServiceNotFoundException | ClassCastException ex) {
            Logger.getLogger(SearchPageController.class.getName()).log(Level.SEVERE, null, ex);
        }

        //Going through the current users buddies to see if the user already has the selected profile as buddy.
        for (int i = 0; i < currentUserBuddies.length; i++) {
            if (userid != currentUserBuddies[i]) {

                boolean request = false;

                try {
                    //Sending request.
                    request = domainFacade.<IProfileService>getService(ServiceType.PROFILE).sendBuddyRequest(userid);

                } catch (ServiceNotFoundException | ClassCastException ex) {
                    Logger.getLogger(SearchPageController.class.getName()).log(Level.SEVERE, null, ex);
                }

                //Showing the user if the request is sent or if it failed.
                if (request == false) {
                    returnLabel.setText("Buddy request failed");
                } else if (request == true) {
                    returnLabel.setText("Buddy request sent");
                }

            } else if (userid == currentUserBuddies[i]) {
                returnLabel.setText("This users is already your buddy");
            }

        }

    }

    private void loadBoxes() {
        //Search Profile
        genderBox.getItems().add("Male");
        genderBox.getItems().add("Female");

        for (int i = 15; i < 100; i++) {
            ageBox.getItems().add(i);
        }
        //Search Exercise
        exerciseTypeBox.getItems().add("Chest");
        exerciseTypeBox.getItems().add("Bicep");
        exerciseTypeBox.getItems().add("Tricep");
        exerciseTypeBox.getItems().add("Core");
        exerciseTypeBox.getItems().add("Shoulder");
        exerciseTypeBox.getItems().add("Leg");
        exerciseTypeBox.getItems().add("Buttocks");
        exerciseTypeBox.getItems().add("Back");
    }

    @FXML
    private void handleSearchExerciseButtonAction(ActionEvent event) {
        //Removing all exercises from initialization.
        exerciseListField.getItems().clear();

        //Ensuring the label &  is invisible.
        resultLabel.setVisible(false);

        String type = exerciseTypeBox.getValue();
        String name = exerciseNameField.getText();

        ObservableList<Exercise> returnList = FXCollections.observableArrayList();

        System.out.println("Searching for exercises with the chosen values/parameters");

        //Searching for exercises with the given parameters.
        if (searchNameRadioButton.isSelected()) {
            for (Exercise e : allExercisesList) {
                if (e.getName().equals(name) && e.getType().equals(type)) {
                    returnList.add(e);
                }
            }
        } else if (!searchNameRadioButton.isSelected()) {
            for (Exercise e : allExercisesList) {
                if (e.getType().equals(type)) {
                    returnList.add(e);
                }
            }
        }

        //Adding the exercises to the ListView
        exerciseListField.setItems(returnList);
        exerciseListField.setCellFactory((ListView<Exercise> view) -> {
            return new cellAllExerciseControler(domainFacade);
        });

        exerciseListField.refresh();
    }

    private void loadAllExercises() {
        //Removing all exercises from initialization.
        exerciseListField.getItems().clear();

        System.out.println("Loading all exercises");

        //Clearing list to make sure there aren't duplicates. In case the user keeps searching.
        allExercisesList.clear();

        try {

            allExercisesList.addAll(domainFacade.<ITrainingSchemeService>getService(ServiceType.TRAININGSCHEME).getAllExercises());

        } catch (ServiceNotFoundException | ClassCastException ex) {
            Logger.getLogger(SearchPageController.class.getName()).log(Level.SEVERE, null, ex);
        }

        exerciseListField.setItems(allExercisesList);
        exerciseListField.setCellFactory((ListView<Exercise> view) -> {
            return new cellAllExerciseControler(domainFacade);
        });

        exerciseListField.refresh();
    }

    @FXML
    private void handleSearchNameRadioButtonAction(ActionEvent event) {
        if (searchNameRadioButton.isSelected()) {
            exerciseNameField.setEditable(true);
        } else if (!searchNameRadioButton.isSelected()) {
            exerciseNameField.setEditable(false);
            exerciseNameField.clear();
        }

    }

}