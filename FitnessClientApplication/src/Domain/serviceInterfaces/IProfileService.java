package Domain.serviceInterfaces;

import Models.Exercise;
import Models.Profile;
import Enums.SearchType;
import Enums.ServiceType;
import LayerInterfaces.ICommunicationFacade;
import LayerInterfaces.IDomainFacade;
import LayerInterfaces.IService;
import java.util.List;


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

    public abstract Profile getProfile(int profileID);

    public abstract boolean updateProfile(Profile newProfileInfo);

    public abstract boolean updateProfilePassWord(String newPassword);

    public abstract boolean deleteAccount();

    public abstract boolean followProfile(int profileID);

    public abstract boolean followTrainingProgram(int programID);
    
    public abstract boolean sendBuddyRequest(int buddyID);

    public abstract boolean acceptBuddyRequest(int requestingProfileID);

    public abstract boolean setGoal(String Goal);

    public abstract int setStats(Exercise exercise);

    public abstract boolean removeStats(int statsID);

}
