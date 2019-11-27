package gui;

import Enums.ServiceType;
import Exceptions.ServiceNotFoundException;
import domain.DomainFacade;
import domain.authentication.NewAccountInfo;
import domain.serviceInterfaces.IAuthenticationService;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author madsh
 */
public class CreateUserPageController implements Initializable {

    private DomainFacade domainFacade;
    private Stage stage;

    @FXML
    private Label errorTxt;
    @FXML
    private TextField firstNameField;
    @FXML
    private TextField lastNameField;
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private PasswordField passwordReField;
    @FXML
    private VBox inputBox;
    @FXML
    private Label successLbl;
    @FXML
    private ComboBox<Integer> ageBox;
    @FXML
    private TextField cityField;
    @FXML
    private ComboBox<String> genderBox;
    
    private ArrayList<TextField> fields;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        fields = new ArrayList<>();
        fields.add(firstNameField);
        fields.add(lastNameField);
        fields.add(usernameField);
        fields.add(passwordField);
        fields.add(passwordReField);
        fields.add(cityField);

        //Filling ageBox
        for (int i = 15; i < 100; i++) {
            ageBox.getItems().add(i);
        }
        //Filling genderBox
        genderBox.getItems().add("Male");
        genderBox.getItems().add("Female");  
    }

    public void setFacade(DomainFacade facade) {
        domainFacade = facade;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    private void createNewUser(ActionEvent event) {
        for (TextField tf : fields) {
            if (tf.getText().isEmpty()) {
                setErrorText("All fields must be entered!");
                return;
            }
        }

        if (ageBox.getSelectionModel().isEmpty() || genderBox.getSelectionModel().isEmpty()) {
            setErrorText("All fields must be entered!");
            return;
        }

        if (passwordField.getText().equals(passwordReField.getText())) {
            NewAccountInfo newInfo = new NewAccountInfo(firstNameField.getText(), lastNameField.getText(), usernameField.getText(), passwordField.getText(), cityField.getText(), ageBox.getValue(), genderBox.getValue());

            try {
                String errors = domainFacade.<IAuthenticationService>getService(ServiceType.AUTHENTICATION).createAccount(newInfo);

                if (errors.equals("")) {
                    inputBox.setEffect(new GaussianBlur(5));
                    inputBox.setDisable(true);
                    successLbl.setVisible(true);

                    Timer timer = new Timer(true);
                    timer.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            Platform.runLater(() -> {
                                stage.close();
                            });
                        }
                    }, 2000);
                } else {
                    setErrorText(errors);
                }
            } catch (ServiceNotFoundException | ClassCastException ex) {
                setErrorText(ex.getMessage());
                Logger.getLogger(CreateUserPageController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            setErrorText("Passwords do not match!");
        }
    }

    private void setErrorText(String text) {
        errorTxt.setText(text);
        errorTxt.setVisible(true);
    }
}