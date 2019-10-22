/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import sun.plugin.javascript.navig.Anchor;

/**
 *
 * @author steff
 */
public abstract class PageLoader {
    
    public void pageChanger(AnchorPane subpane, String fxmlpath) throws IOException{
        AnchorPane pane;
        pane = FXMLLoader.load(getClass().getResource(fxmlpath));
        subpane.getChildren().setAll(pane);
        
    }
}