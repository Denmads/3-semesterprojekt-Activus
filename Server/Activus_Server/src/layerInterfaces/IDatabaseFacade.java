/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package layerInterfaces;

import persistence.IDatabaseAction;

/**
 * Defines the database facade, which should be able to execute a databaseAction
 *
 * @author madsh
 */
public interface IDatabaseFacade {
    public void execute(IDatabaseAction query);
}
