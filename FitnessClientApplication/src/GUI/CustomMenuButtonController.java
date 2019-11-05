/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import domain.DomainFacade;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

/**
 *
 * @author madsh
 */
public abstract class CustomMenuButtonController implements Initializable {
    public abstract Button getButtonNode ();
    
    protected DomainFacade domainFacade;
    
    public void setDomainFacade (DomainFacade facade) {
        domainFacade = facade;
    }
}
