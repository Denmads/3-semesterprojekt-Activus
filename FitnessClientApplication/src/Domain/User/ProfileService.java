package Domain.User;

import Domain.serviceInterfaces.IProfileService;
import Domain.DomainFacade;
import Domain.TraningScheme.Exercise;
import Domain.serviceInterfaces.IAuthenticationService;
import Enums.RequestArguementName;
import Enums.RequestType;
import Enums.ResponseArguementName;
import Enums.SearchType;
import Exceptions.ServiceNotFoundException;
import LayerInterfaces.Enums.ServiceType;
import LayerInterfaces.ICommunicationFacade;
import LayerInterfaces.IDomainFacade;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import Exceptions.*;
import Models.Request;
import Models.Response;

/**
 *
 * @author steff
 */
public class ProfileService extends IProfileService {

    private Profile currentProfile;

    public ProfileService(Profile currentProfile, ICommunicationFacade communication, IDomainFacade domainFacade) {
        super(communication, domainFacade);
        this.currentProfile = currentProfile;
    }

    @Override
    public List<Profile> search(String searchString, SearchType searchType) {
        List<Profile> profiles = null;
        try {
            
            Request request = getAuthenticationService().createRequest(RequestType.SEARCH);
            request.AddArgument(RequestArguementName.SEARCH_TYPE, searchType);
            request.AddArgument(RequestArguementName.TEXT, searchType);
            Response response = communicationLayer.sendRequest(request);
            profiles= (List < Profile >) response.getArguement(ResponseArguementName.PROFILE);
        } catch (ArguementNotFoundException ex) {
            Logger.getLogger(ProfileService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ServiceNotFoundException ex) {
            Logger.getLogger(ProfileService.class.getName()).log(Level.SEVERE, null, ex);
        }

        return profiles;
    }

    @Override
    public Profile getProfile(int profileID) {
        Profile profile=null;
        try {
            Request request = getAuthenticationService().createRequest(RequestType.GET_PROFILE);
            request.AddArgument(RequestArguementName.PROFILE_ID, profileID);
            Response response = communicationLayer.sendRequest(request);
            profile= (Profile) response.getArguement(ResponseArguementName.PROFILE);
        } catch (ServiceNotFoundException ex) {
            Logger.getLogger(ProfileService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassCastException ex) {
            Logger.getLogger(ProfileService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ArguementNotFoundException ex) {
            Logger.getLogger(ProfileService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return profile;
    }

    @Override
    public boolean updateProfile(Profile newProfileInfo) {
        
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean updateProfilePassWord(String newPassword) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean deleteAccount() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean FollowProfile(int profileID) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean FolloTraniningProgram(int programID) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean SendBuddyRequest(int buddyID) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean AcceptBuddyRequest(int requestingProfileID) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean setGoal(String Goal) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int setStats(Exercise exercise) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean removeStats(int statsID) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private IAuthenticationService getAuthenticationService() throws ServiceNotFoundException{
        return  domainFacade.<IAuthenticationService>getService(ServiceType.AUTHENTICATION);
    }
}