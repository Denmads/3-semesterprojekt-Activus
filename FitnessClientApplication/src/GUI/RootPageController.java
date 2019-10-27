/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import GUI.FXML.SideMenu.SidebarMenuController;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

/**
 * FXML Controller class
 *
 * @author steff
 */
public class RootPageController extends PageHandler implements Initializable {

    @FXML
    private StackPane rootPane;
    @FXML
    private BorderPane contentPane;
    
    private SidebarMenuController menuController;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        rootPane.setPrefWidth(900);
        addMenu();
    }
    
    private void addMenu () {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXML/SideMenu/SidebarMenu.fxml"));
            Parent menuRoot = loader.load();
            menuRoot.setTranslateX(-300);
            menuController = loader.getController();
            rootPane.getChildren().add(menuRoot);
        } catch (IOException ex) {
            Logger.getLogger(RootPageController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void addMenuButton (String buttonText, String contentFxml) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXML/SideMenu/defaultButton.fxml"));
            Button menuButton = loader.load();
            menuButton.onActionProperty().addListener((observable, oldVal, newVal) -> {
                loadContent(contentFxml);
            });
            menuButton.setText(buttonText);
            rootPane.getChildren().add(menuButton);
        } catch (IOException ex) {
            Logger.getLogger(RootPageController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void addCustomMenuButton () {
        
    }
    
    private void loadContent (String fxml) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXML/" + fxml + ".fxml"));
            Parent contentRoot = loader.load();
            contentPane.setCenter(contentRoot);
            
        } catch (IOException ex) {
            Logger.getLogger(RootPageController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
