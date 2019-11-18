package gui;

import Enums.SearchType;
import Enums.ServiceType;
import Exceptions.ServiceNotFoundException;
import models.Profile;
import domain.serviceInterfaces.IProfileService;
import domain.serviceInterfaces.ITrainingSchemeService;
import gui.cellsControllers.ProfileCellController;
import gui.cellsControllers.cellAllExerciseControler;
import models.Exercise;
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
    @FXML
    private ComboBox<String> cityBox;

    private ObservableList<Exercise> allExercisesList = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     *
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Search Profile--------------------------------------------------------
        //Making the requestBuddyButton & returnSearchLabel invisible until they're needed.
        requestBuddyButton.setVisible(false);
        returnLabel.setVisible(false);

        //Search Exercises------------------------------------------------------
        //Making resultLabel invisible until it's needed.
        resultLabel.setVisible(false);

        //Shall only be editable when radiobutton is selected.
        exerciseNameField.setEditable(false);
    }

    @Override
    public void onContentInitialize() {
        //Loading all exercises to the listview
        loadAllExercises();
        //Initializing comboBoxes
        loadBoxes();
    }

    @FXML
    private void handleSearchProfileButtonAction(ActionEvent event) {
        //Clearing Listview
        searchField.getItems().clear();

        //Ensuring the label & requestBuddyButton is invisible.
        returnLabel.setVisible(false);
        requestBuddyButton.setVisible(false);

        String gender = genderBox.getValue();
        String city = cityBox.getValue();
        //Needs to be done this way. Age can't be null.
        int age = 0;
        if (!ageBox.getSelectionModel().isEmpty()) {
            age = ageBox.getValue();
        }

        System.out.println("Searching with the chosen values/parameters");

        ObservableList<Profile> returnList = FXCollections.observableArrayList();

        //Clearing list to make sure there aren't duplicates. In case the user keeps searching.
        returnList.clear();

        //If all boxes are empty
        if (genderBox.getSelectionModel().isEmpty() && cityBox.getSelectionModel().isEmpty() && ageBox.getSelectionModel().isEmpty()) {
            returnList.addAll(searchByAge(15));

            //If only gender is empty
        } else if (genderBox.getSelectionModel().isEmpty() && !cityBox.getSelectionModel().isEmpty() && ageBox.getSelectionModel().isEmpty()) {
            returnList.addAll(searchByCity(city));
            for (int i = returnList.size() - 1; i > -1; i--) {
                if (returnList.get(i).getAge() < age) {
                    returnList.remove(i);
                }
            }
            //If only age is empty    
        } else if (!genderBox.getSelectionModel().isEmpty() && !cityBox.getSelectionModel().isEmpty() && ageBox.getSelectionModel().isEmpty()) {
            returnList.addAll(searchByCity(city));
            for (int i = returnList.size() - 1; i > -1; i--) {
                if (!returnList.get(i).getGender().equals(gender)) {
                    returnList.remove(i);
                }
            }
            //If only city is empty    
        } else if (!genderBox.getSelectionModel().isEmpty() && cityBox.getSelectionModel().isEmpty() && !ageBox.getSelectionModel().isEmpty()) {
            returnList.addAll(searchByGender(gender));

            for (int i = returnList.size() - 1; i > -1; i--) {
                if (returnList.get(i).getAge() < age) {
                    returnList.remove(i);
                }
            }
            //If only city is chosen    
        } else if (genderBox.getSelectionModel().isEmpty() && !cityBox.getSelectionModel().isEmpty() && ageBox.getSelectionModel().isEmpty()) {
            returnList.addAll(searchByCity(city));
            //If only age is chosen
        } else if (genderBox.getSelectionModel().isEmpty() && cityBox.getSelectionModel().isEmpty() && !ageBox.getSelectionModel().isEmpty()) {
            returnList.addAll(searchByAge(age));
            //If only gender is chosen
        } else if (!genderBox.getSelectionModel().isEmpty() && cityBox.getSelectionModel().isEmpty() && ageBox.getSelectionModel().isEmpty()) {
            returnList.addAll(searchByGender(gender));
            //If all are chosen
        } else if (!genderBox.getSelectionModel().isEmpty() && !cityBox.getSelectionModel().isEmpty() && !ageBox.getSelectionModel().isEmpty()) {
            returnList.addAll(searchByCity(city));

            for (int i = returnList.size() - 1; i > -1; i--) {
                if (returnList.get(i).getAge() < age || !gender.equals(returnList.get(i).getGender())) {
                    returnList.remove(i);
                }
            }
        }

        //Removing current user from the search
        for (int i = returnList.size() - 1; i > -1; i--) {
            try {
                if (returnList.get(i).getProfileId() == domainFacade.<IProfileService>getService(ServiceType.PROFILE).getCurrentProfile().getProfileId()) {
                    returnList.remove(i);
                }
            } catch (ServiceNotFoundException | ClassCastException ex) {
                Logger.getLogger(SearchPageController.class.getName()).log(Level.SEVERE, null, ex);
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

        //If the returned profiles match the search parameters they are put in the list.
    }

    //This is used if the user only searches for gender.
    private List<Profile> searchByGender(String gender) {

        List<Profile> getList = new ArrayList();

        try {
            getList = domainFacade.<IProfileService>getService(ServiceType.PROFILE).search(gender, SearchType.GENDER);

        } catch (ServiceNotFoundException | ClassCastException ex) {
            Logger.getLogger(SearchPageController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (getList.isEmpty()) {
                returnLabel.setVisible(true);
                returnLabel.setText("Search returned no results");
            }
        }
        return getList;
    }

    //This is used if the user only searches for age.
    private List<Profile> searchByAge(int age) {

        List<Profile> getList = new ArrayList();

        try {
            getList = domainFacade.<IProfileService>getService(ServiceType.PROFILE).search(Integer.toString(age), SearchType.AGE);

            //Filtering by age.
            for (Profile p : getList) {
                if (p.getAge() < age) {
                    getList.remove(p);
                }
            }

        } catch (ClassCastException | ServiceNotFoundException ex) {
            Logger.getLogger(SearchPageController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (getList.isEmpty()) {
                returnLabel.setVisible(true);
                returnLabel.setText("Search returned no results");
            }
        }
        return getList;
    }

    //This is used if the user only searches for city.
    private List<Profile> searchByCity(String city) {

        List<Profile> getList = new ArrayList();

        try {
            getList = domainFacade.<IProfileService>getService(ServiceType.PROFILE).search(city, SearchType.CITY);

        } catch (ServiceNotFoundException | ClassCastException ex) {
            Logger.getLogger(SearchPageController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (getList.isEmpty()) {
                returnLabel.setVisible(true);
                returnLabel.setText("Search returned no results");
            }
        }
        return getList;
    }

    @FXML
    private void handleRequestBuddyAction(ActionEvent event) {

        //Getting the selected users/buddy's id.
        int buddyID = searchField.getSelectionModel().getSelectedItem().getProfileId();

        //Getting the current users buddies
        int[] currentUserBuddies = null;
        try {
            currentUserBuddies = domainFacade.<IProfileService>getService(ServiceType.PROFILE).getCurrentProfile().getBuddyIds();
        } catch (ServiceNotFoundException | ClassCastException ex) {
            Logger.getLogger(SearchPageController.class.getName()).log(Level.SEVERE, null, ex);
        }

        boolean buddyCheck = true;

        //Going through the current users buddies to see if the user already has the selected profile as buddy.
        for (int i = 0; i < currentUserBuddies.length; i++) {
            if (buddyID == currentUserBuddies[i]) {
                returnLabel.setText("This user is already your buddy");
                buddyCheck = false;
            }

            //If the user doesn't have the selected profile as a buddy, a buddy request is sent.
            if (buddyCheck = true) {
                boolean request = false;

                try {
                    //Sending request.
                    request = domainFacade.<IProfileService>getService(ServiceType.PROFILE).sendBuddyRequest(buddyID);

                } catch (ServiceNotFoundException | ClassCastException ex) {
                    Logger.getLogger(SearchPageController.class.getName()).log(Level.SEVERE, null, ex);
                }

                //Showing the user if the request is sent or if it failed.
                if (request == false) {
                    returnLabel.setText("Buddy request failed");
                } else if (request == true) {
                    returnLabel.setText("Buddy request sent");
                }
            }

        }

    }

    private void loadBoxes() {
        //Search Profile-----------------------------
        genderBox.getItems().add("Male");
        genderBox.getItems().add("Female");

        //Filling ageBox
        for (int i = 15; i < 100; i++) {
            ageBox.getItems().add(i);
        }

        //Filling cityBox
        ArrayList<Profile> checkList = new ArrayList();
        ArrayList<String> cityList = new ArrayList();
        checkList.addAll(searchByAge(15));
        for (Profile p : checkList) {
            if (!cityList.contains(p.getCity())) {
                cityList.add(p.getCity());
            }
        }
        for (String s : cityList) {
            cityBox.getItems().add(s);
        }
        checkList.clear();
        cityList.clear();

        //Search Exercise----------------------------
        exerciseTypeBox.getItems().add("All");
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

        //Ensuring that the label is invisible.
        resultLabel.setVisible(false);

        String type = exerciseTypeBox.getValue();
        String name = exerciseNameField.getText();

        ObservableList<Exercise> returnList = FXCollections.observableArrayList();

        System.out.println("Searching for exercises with the chosen values/parameters");

        //Searching for exercises with the given parameters.
        if (searchNameRadioButton.isSelected()) {
            for (Exercise e : allExercisesList) {
                if (type.equals("All") && e.getName().equals(name)) {
                    returnList.add(e);
                } else if (e.getName().equals(name) && e.getType().equals(type)) {
                    returnList.add(e);
                }
            }
        } else if (!searchNameRadioButton.isSelected()) {
            if (type.equals("All")) {
                returnList.addAll(allExercisesList);
            } else {

                for (Exercise e : allExercisesList) {
                    if (e.getType().equals(type)) {
                        returnList.add(e);
                    }
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

    @FXML
    private void handleClearSearchButtonAction(ActionEvent event) {
        searchField.getItems().clear();
        //Boxes
        ageBox.getSelectionModel().clearSelection();
        cityBox.getSelectionModel().clearSelection();
        genderBox.getSelectionModel().clearSelection();
    }

}