/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author steff
 */
public abstract class PageHandler {
    
    public void changeFxml(AnchorPane subPane, String fxml) {
        AnchorPane pane;
        try {
            pane = FXMLLoader.load(getClass().getResource(fxml));
            subPane.getChildren().setAll(pane);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
  public static Object getController(Node node) {
    Object controller = null;
    do {
        controller = node.getProperties().get("foo");
        node = node.getParent();
    } while (controller == null && node != null);
    return controller;
}
  
  
  public static Object getControllerObject(Node node) {
    Object controller = null;
    do {
        controller = node.getUserData();
        node = node.getParent();
    } while (controller == null && node != null);
    return controller;
}
}
