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
        return (Profile) returnObject(RequestType.GET_PROFILE, RequestArguementName.PROFILE_ID, ResponseArguementName.PROFILE, profileID);
        
    }

    @Override
    public boolean updateProfile(Profile newProfileInfo) {
        boolean isUpdate;
        currentProfile = (Profile) returnObject(RequestType.UPDATE_PROFILE, RequestArguementName.PROFILE_ID, ResponseArguementName.PROFILE, newProfileInfo);
        //To do OPdate the profile in db;
        if(currentProfile!=newProfileInfo){
            isUpdate= false;
        }else{
            isUpdate=true;
        }
        return isUpdate;
    }

    @Override
    public boolean updateProfilePassWord(String newPassword) {
        return (boolean) returnObject(RequestType.UPDATE_PASSWORD, RequestArguementName.PASSWORD, ResponseArguementName.SUCCESS, newPassword);
    }

    @Override
    public boolean deleteAccount() {
        return (boolean) returnObject(RequestType.DELETE_ACCOUNT, RequestArguementName.PROFILE_ID, ResponseArguementName.SUCCESS, currentProfile);
    }

    @Override
    public boolean followProfile(int profileID) {
        return (boolean) returnObject(RequestType.FOLLOW_PROFILE, RequestArguementName.PROFILE_ID, ResponseArguementName.SUCCESS, profileID);
    }

    @Override
    public boolean folloTraniningProgram(int programID) {
        return (boolean) returnObject(RequestType.FOLLOW_TRAINING_PROGRAM, RequestArguementName.PROGRAM_ID, ResponseArguementName.SUCCESS, programID);
        
    }

    @Override
    public boolean sendBuddyRequest(int buddyID) {
        return (boolean) returnObject(RequestType.SEND_BUDDY_REQUEST, RequestArguementName.PROFILE_ID, ResponseArguementName.SUCCESS, buddyID);
        
    }

    @Override
    public boolean acceptBuddyRequest(int requestingProfileID) {
        return (boolean) returnObject(RequestType.ACCEPT_BUDDY_REQUEST, RequestArguementName.PROFILE_ID, ResponseArguementName.SUCCESS, requestingProfileID);
    }

    @Override
    public boolean setGoal(String Goal) {
        return (boolean) returnObject(RequestType.SET_GOAL, RequestArguementName.TEXT, ResponseArguementName.SUCCESS, Goal);
    }

    @Override
    public int setStats(Exercise exercise) {
        return (int) returnObject(RequestType.SET_STAT, RequestArguementName.STAT_ID, ResponseArguementName.STATS, exercise);
    }

    @Override
    public boolean removeStats(int statsID) {
       return (boolean) returnObject(RequestType.REMOVE_STAT, RequestArguementName.STAT_ID, ResponseArguementName.STATS, statsID);
    }

    private IAuthenticationService getAuthenticationService() throws ServiceNotFoundException{
        return  domainFacade.<IAuthenticationService>getService(ServiceType.AUTHENTICATION);
    }
    private Object returnObject(RequestType requestType, RequestArguementName requestArguementName,ResponseArguementName responseArguementName, Object o){
        Object object=null;
        try {
            Request request = getAuthenticationService().createRequest(requestType);
            request.AddArgument(requestArguementName, o);
            Response response = communicationLayer.sendRequest(request);
            object= response.getArguement(responseArguementName);
        } catch (ServiceNotFoundException ex) {
            Logger.getLogger(ProfileService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassCastException ex) {
            Logger.getLogger(ProfileService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ArguementNotFoundException ex) {
            Logger.getLogger(ProfileService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return object;
    }
}