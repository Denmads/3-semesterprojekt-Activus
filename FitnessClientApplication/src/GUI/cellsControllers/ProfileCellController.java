/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.cellsControllers;

import Models.Profile;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.HBox;
import layerInterfaces.IDomainFacade;

/**
 *
 * @author Sebas
 */
public class ProfileCellController extends ListCell<Profile> {

    @FXML
    private Label userName;

    @FXML
    private Label gymName;

    private Profile profile;

    private IDomainFacade domainFacade;

    public ProfileCellController(IDomainFacade domainFacade) {

        this.domainFacade = domainFacade;

    }

    
    @Override
    protected void updateItem(Profile item, boolean empty) {
        super.updateItem(item, empty);

        if (empty || item == null) {
            setText(null);
            setGraphic(null);

        } else {
            profile = item;
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/FXML/listViewCells/ProfileCell.fxml"));

            loader.setController(this);
            HBox parrent = null;

            try {
                parrent = loader.load();

            } catch (IOException ex) {
                System.out.println(ex);
                return;
            }
            userName.setText(profile.getUsername());
            gymName.setText(profile.getGym());
            setText(null);
            setGraphic(parrent);
        }
    }

}
