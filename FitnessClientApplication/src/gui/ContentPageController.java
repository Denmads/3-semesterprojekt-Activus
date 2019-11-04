/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import domain.DomainFacade;
import javafx.fxml.Initializable;

/**
 *
 * @author madsh
 */
public abstract class ContentPageController implements Initializable {
    protected DomainFacade domainFacade;
    
    public void setDomainFacade (DomainFacade facade) {
        domainFacade = facade;
        onContentInitialize();
    }
    
    public abstract void onContentInitialize ();
}
