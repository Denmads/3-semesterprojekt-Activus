package domain.serviceInterfaces;

import models.Exercise;
import models.Profile;
import Enums.SearchType;
import Enums.ServiceType;
import layerInterfaces.ICommunicationFacade;
import layerInterfaces.IDomainFacade;
import layerInterfaces.IService;
import java.util.List;
import models.Stats;


/**
 * The service provinding methods related to the profile and buddies
 * 
 * @author madsh
 */
public abstract class IProfileService extends IService {

    protected Profile currentProfile;
    
    public IProfileService(ICommunicationFacade communication, IDomainFacade domainFacade, Profile currentProfile) {
        super(communication, domainFacade);
        this.currentProfile = currentProfile;
        type = ServiceType.PROFILE;
    }

    public Profile getCurrentProfile () {
    	return currentProfile;
    }

    public abstract List<Profile> search(String searchString, SearchType searchType);

    public abstract boolean updateProfile(Profile newProfileInfo);

    public abstract boolean updateProfilePassWord(String newPassword);

    public abstract boolean followProfile(int profileID);

    public abstract boolean followTrainingProgram(int programID);
    
    public abstract boolean sendBuddyRequest(int buddyID);

    public abstract boolean acceptBuddyRequest(int requestingProfileID);

    public abstract boolean setGoal(String Goal);
    
    public abstract boolean deleteAccount(String profileUsername);

    public abstract int setStats(Exercise exercise);

    public abstract boolean removeStats(int statsID);
    
    /**
     *
     * @param ProfileID
     * @return
     */
    public abstract Stats getCurrentStats(int ProfileID);
    
   

}
