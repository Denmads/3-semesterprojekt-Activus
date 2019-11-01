/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import GUI.FXML.SideMenu.SidebarMenuController;
import domain.DomainFacade;
import gui.CustomMenuButtonController;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;

/**
 * FXML Controller class
 *
 * @author steff
 */
public class RootPageController implements Initializable {

    @FXML
    private StackPane rootPane;
    @FXML
    private BorderPane contentPane;
    
    private SidebarMenuController menuContoller;
    private DomainFacade domainFacade = null;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }
    
    public void setDomainFacade (DomainFacade facade) {
        domainFacade = facade;
        addMenu();
        loadContent("HomePage");
    }
    
    private void addMenu () {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXML/SideMenu/SidebarMenu.fxml"));
            Parent menuRoot = loader.load();
            menuContoller = loader.getController();
            menuRoot.setTranslateX(-300);
            rootPane.getChildren().add(menuRoot);
            populateMenu();
        } catch (IOException ex) {
            Logger.getLogger(RootPageController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void populateMenu () {
        addMenuButton("Home", "HomePage");
        addMenuButton("Profile", "ProfilePage");
        addMenuButton("Stats", "StatsPage");
        addMenuButton("Search", "SearchPage");
        addCustomMenuButton("Buddies", "buddiesButton", "BuddyPage");
        addMenuButton("Training programs", "TrainingProgramsPage");
        addCustomMenuButtonWithCustomAction("Logout", "logoutButton");
    }
    
    private void addMenuButton (String buttonText, String pageName) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXML/SideMenu/buttons/defaultButton.fxml"));
            Parent menuButtonNode = loader.load();
            Button menuButton = (Button)menuButtonNode;
            menuButton.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    loadContent(pageName);
                }
            });
            menuButton.setText(buttonText);
            menuContoller.addButton(menuButtonNode);
        } catch (IOException ex) {
            Logger.getLogger(RootPageController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void addCustomMenuButton (String buttonText, String buttonNameFxml, String pageName) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXML/SideMenu/buttons/" + buttonNameFxml + ".fxml"));
            Parent menuButtonNode = loader.load();
            CustomMenuButtonController customController = loader.getController();
            customController.setDomainFacade(domainFacade);
            Button menuButton = customController.getButtonNode();
            menuButton.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    loadContent(pageName);
                }
            });
            menuButton.setText(buttonText);
            menuContoller.addButton(menuButtonNode);
        } catch (IOException ex) {
            Logger.getLogger(RootPageController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void addCustomMenuButtonWithCustomAction (String buttonText, String buttonNameFxml) {
         try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXML/SideMenu/buttons/" + buttonNameFxml + ".fxml"));
            Parent menuButtonNode = loader.load();
            CustomMenuButtonController customController = loader.getController();
            customController.setDomainFacade(domainFacade);
            Button menuButton = customController.getButtonNode();
            menuButton.setText(buttonText);
            menuContoller.addButton(menuButtonNode);
        } catch (IOException ex) {
            Logger.getLogger(RootPageController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void loadContent (String fxml) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXML/" + fxml + ".fxml"));
            Parent contentRoot = loader.load();
            ContentPageController contentController = loader.getController();
            contentController.setDomainFacade(domainFacade);
            contentPane.setCenter(contentRoot);
        } catch (IOException ex) {
            Logger.getLogger(RootPageController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
