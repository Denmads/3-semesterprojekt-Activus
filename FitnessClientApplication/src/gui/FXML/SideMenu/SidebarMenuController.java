/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.FXML.SideMenu;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import javafx.animation.AnimationTimer;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author steff
 */
public class SidebarMenuController implements Initializable {

    
    @FXML
    private HBox root;
    @FXML
    private VBox buttonBox;
    
    private boolean open = false;
    
    //Animations
    private Timeline menuFocusTimeline;
    private Timeline menuLostFocusTimeline;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        createAnimationMenu();
    }

    private void createAnimationMenu () {
        menuFocusTimeline = new Timeline();
        KeyValue kv = new KeyValue(root.translateXProperty(), 0, Interpolator.LINEAR);
        KeyFrame kf = new KeyFrame(Duration.millis(100), kv);
        menuFocusTimeline.getKeyFrames().add(kf);
        
        menuLostFocusTimeline = new Timeline();
        KeyValue kv1 = new KeyValue(root.translateXProperty(), -300, Interpolator.LINEAR);
        KeyFrame kf1 = new KeyFrame(Duration.millis(100), kv1);
        menuLostFocusTimeline.getKeyFrames().add(kf1);
    }
    
    @FXML
    private void hideMenu(MouseEvent event) {
        menuLostFocusTimeline.play();
        Timer timer = new Timer(true);
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                open = false;
            }
        }, 100);
    }

    @FXML
    private void showMenu(MouseEvent event) {
        if (!open) {
            menuFocusTimeline.play();
            open = true;
        }
        
    }
    
    public void addButton (Node button) {
        buttonBox.getChildren().add(button);
    }
    
}
