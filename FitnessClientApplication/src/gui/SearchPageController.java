package gui;

import Enums.SearchType;
import Enums.ServiceType;
import Exceptions.ServiceNotFoundException;
import Models.Exercise;
import Models.Profile;
import domain.serviceInterfaces.IProfileService;
import gui.cellsControllers.ProfileCellController;
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
import javafx.scene.control.TextField;

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
    private ListView<Profile> searchField;
    @FXML
    private Button requestBuddyButton;
    @FXML
    private Label returnLabel;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Making the requestBuddyButton & returnSearchLabel invisible until they're needed.
        requestBuddyButton.setVisible(false);
        returnLabel.setVisible(false);

        //Initializing ComboBoxes
        genderBox.getItems().add("Male");
        genderBox.getItems().add("Female");

        for (int i = 15; i < 100; i++) {
            ageBox.getItems().add(i);
        }
    }

    @FXML
    private void handleSearchButtonAction(ActionEvent event) {
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

        //Getting the selected users id.
        int userid = searchField.getSelectionModel().getSelectedItem().getProfileId();

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

    }

}