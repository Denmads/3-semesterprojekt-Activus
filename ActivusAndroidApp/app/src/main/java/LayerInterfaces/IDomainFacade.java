package LayerInterfaces;

import Exceptions.ServiceNotFoundException;
import Enums.ServiceType;
import java.util.HashMap;

/**
 *
 * @author madsh
 */
public interface IDomainFacade {

    <T extends IService> T getService(ServiceType type) throws ServiceNotFoundException, ClassCastException;

    public void addService(ServiceType type, IService service);

    public void removeAllServices();
}