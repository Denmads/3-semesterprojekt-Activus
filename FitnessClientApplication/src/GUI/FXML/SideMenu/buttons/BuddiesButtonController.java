/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.FXML.SideMenu.buttons;

import gui.CustomMenuButtonController;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 *
 * @author madsh
 */
public class BuddiesButtonController extends CustomMenuButtonController {

    @FXML
    private Button buddiesBtn;
    @FXML
    private Label messageLbl;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }
    
    @Override
    public Button getButtonNode() {
        return buddiesBtn;
    }
}
