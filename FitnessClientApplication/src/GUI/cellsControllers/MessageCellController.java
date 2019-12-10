/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.cellsControllers;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.HBox;
import layerInterfaces.IDomainFacade;
import models.Message;

/**
 * FXML Controller class
 *
 * @author terpen
 */
public class MessageCellController extends ListCell<Message> {

    @FXML
    private Label name;
    @FXML
    private Label dateTime;
    @FXML
    private Label message;
    
    private IDomainFacade domainFacade;

    private Message mes;
    
    public MessageCellController(IDomainFacade domainFacade) {
        this.domainFacade = domainFacade;
    }

    @Override
    public void updateItem(Message item, boolean empty){
        super.updateItem(item, empty);

        if (empty || item == null) {
            setText(null);
            setGraphic(null);
        } else {
            mes = item;
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/FXML/listViewCells/MessageCell.fxml"));
            loader.setController(this);
            HBox parrent = null;
            try {
                parrent = loader.load();

            } catch (IOException ex) {
                System.out.println(ex);
                return;
            }
            message.setText(mes.getMessage());
            dateTime.setText(mes.getTime().toString());
            //needs to make the sender id to 
            //name.setText(mes.getSenderId());
            setText(null);
            setGraphic(parrent);
        }
        
        
    }
    
   
    
}
