/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LayerInterfaces;

import Exceptions.ServiceNotFoundException;
import LayerInterfaces.Enums.ServiceType;
import java.util.HashMap;

/**
 *
 * @author madsh
 */
public interface IDomainFacade {
    
    <T extends IService> T GetService (ServiceType type) throws ServiceNotFoundException, ClassCastException;
}
