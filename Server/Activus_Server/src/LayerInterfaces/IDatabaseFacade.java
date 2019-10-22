/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LayerInterfaces;

import Persistence.IDatabaseAction;

/**
 *
 * @author madsh
 */
public interface IDatabaseFacade {
    public void execute(IDatabaseAction query);
}
