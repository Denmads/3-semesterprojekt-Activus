package domain.profile;

import models.Profile;
import models.Exercise;
import Enums.RequestArgumentName;
import Enums.RequestType;
import Enums.ResponseArgumentName;
import Enums.SearchType;
import Exceptions.ServiceNotFoundException;
import Enums.ServiceType;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import Exceptions.*;
import models.Request;
import models.Response;
import domain.DomainFacade;
import models.Request;
import models.Response;
import domain.serviceInterfaces.IAuthenticationService;
import domain.serviceInterfaces.IProfileService;
import gui.FXMain;
import layerInterfaces.ICommunicationFacade;
import layerInterfaces.IDomainFacade;
import models.Stats;

/**
 *
 * @author steff
 */
public class ProfileService extends IProfileService {

    public ProfileService(Profile currentProfile, ICommunicationFacade communication, IDomainFacade domainFacade) {
        super(communication, domainFacade, currentProfile);
    }

    @Override
    public List<Profile> search(String searchString, SearchType searchType) {

        List<Profile> profiles = null;
        try {

            Request request = createRequest(RequestType.SEARCH);
            request.addArgument(RequestArgumentName.SEARCH_TYPE, searchType);
            request.addArgument(RequestArgumentName.TEXT, searchString);

            Response response = communicationLayer.sendRequest(request);
            profiles = (List<Profile>) response.getArgument(ResponseArgumentName.SUCCESS);
        } catch (ServiceNotFoundException | ArgumentNotFoundException ex) {
            Logger.getLogger(ProfileService.class.getName()).log(Level.SEVERE, null, ex);
        }

        return profiles;
    }

    @Override
    public boolean updateProfile(Profile newProfileInfo) {
        try {
            boolean isUpdated = false;

            Request req = createRequest(RequestType.UPDATE_PROFILE);
            req.addArgument(RequestArgumentName.PROFILE_ID, newProfileInfo.getProfileId());
            req.addArgument(RequestArgumentName.PROFILE_GYM, newProfileInfo.getGym());
            req.addArgument(RequestArgumentName.PROFILE_CITY, newProfileInfo.getCity());
            req.addArgument(RequestArgumentName.PROFILE_AGE, newProfileInfo.getAge());
            req.addArgument(RequestArgumentName.PROFILE_FIRST_NAME, newProfileInfo.getFirstName());
            req.addArgument(RequestArgumentName.PROFILE_LAST_NAME, newProfileInfo.getLastName());
            req.addArgument(RequestArgumentName.PROFILE_GENDER, newProfileInfo.getGender());
            req.addArgument(RequestArgumentName.PROFILE_COUNTRY, newProfileInfo.getCountry());
            req.addArgument(RequestArgumentName.PROFILE_ACTIVE_BUDDY, newProfileInfo.isActiveBuddy());

            Response res = communicationLayer.sendRequest(req);
            System.out.println("response: " + res);
            System.out.println("arg:" + res.getArgument(ResponseArgumentName.SUCCESS));
            isUpdated = (boolean) res.getArgument(ResponseArgumentName.SUCCESS);

            if (isUpdated) {
                currentProfile = newProfileInfo;
            }

            return isUpdated;
        } catch (ServiceNotFoundException | ArgumentNotFoundException ex) {
            Logger.getLogger(ProfileService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean updateProfilePassWord(String newPassword) {
        return (boolean) returnResponsObject(RequestType.UPDATE_PASSWORD, RequestArgumentName.PASSWORD, ResponseArgumentName.SUCCESS, newPassword);
    }

    @Override
    public boolean followProfile(int profileID) {
        return (boolean) returnResponsObject(RequestType.FOLLOW_PROFILE, RequestArgumentName.PROFILE_ID, ResponseArgumentName.SUCCESS, profileID);
    }

    @Override
    public boolean followTrainingProgram(int programID) {
        return (boolean) returnResponsObject(RequestType.FOLLOW_TRAINING_PROGRAM, RequestArgumentName.PROGRAM_ID, ResponseArgumentName.SUCCESS, programID);

    }

    @Override
    public boolean sendBuddyRequest(int buddyID, int profileId) {
        boolean isUpdated = false;
        try {
            Request req = createRequest(RequestType.SEND_BUDDY_REQUEST);
            req.addArgument(RequestArgumentName.PROFILE_ID, profileId);
            req.addArgument(RequestArgumentName.BUDDY_ID, buddyID);
            
            Response res = communicationLayer.sendRequest(req);
            System.out.println("response: " + res);
            System.out.println("arg:" + res.getArgument(ResponseArgumentName.SUCCESS));
            isUpdated = (boolean) res.getArgument(ResponseArgumentName.SUCCESS);
            
        } catch (ServiceNotFoundException | ArgumentNotFoundException ex) {
            Logger.getLogger(ProfileService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return isUpdated;
    }

    @Override
    public boolean acceptBuddyRequest(int profileId, int buddyID) {
        boolean isUpdated = false;
        try {
            Request req = createRequest(RequestType.ACCEPT_BUDDY_REQUEST);
            req.addArgument(RequestArgumentName.PROFILE_ID, profileId);
            req.addArgument(RequestArgumentName.BUDDY_ID, buddyID);
            
            Response res = communicationLayer.sendRequest(req);
            System.out.println("response: " + res);
            System.out.println("arg:" + res.getArgument(ResponseArgumentName.SUCCESS));
            isUpdated = (boolean) res.getArgument(ResponseArgumentName.SUCCESS);
            
        } catch (ServiceNotFoundException | ArgumentNotFoundException ex) {
            Logger.getLogger(ProfileService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return isUpdated;
    }
    
    @Override
    public boolean dennyBuddyRequest(int profileId, int buddyID) {
        boolean isUpdated = false;
        try {
            Request req = createRequest(RequestType.DENNY_BUDDY_REQUEST);
            req.addArgument(RequestArgumentName.PROFILE_ID, profileId);
            req.addArgument(RequestArgumentName.BUDDY_ID, buddyID);
            
            Response res = communicationLayer.sendRequest(req);
            System.out.println("response: " + res);
            System.out.println("arg:" + res.getArgument(ResponseArgumentName.SUCCESS));
            isUpdated = (boolean) res.getArgument(ResponseArgumentName.SUCCESS);
            
        } catch (ServiceNotFoundException | ArgumentNotFoundException ex) {
            Logger.getLogger(ProfileService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return isUpdated;
    }

    @Override
    public boolean setGoal(String Goal) {
        return (boolean) returnResponsObject(RequestType.SET_GOAL, RequestArgumentName.TEXT, ResponseArgumentName.SUCCESS, Goal);
    }

    @Override
    public int setStats(Exercise exercise) {
        return (int) returnResponsObject(RequestType.SET_STAT, RequestArgumentName.STAT_ID, ResponseArgumentName.STATS, exercise);
    }

    @Override
    public boolean removeStats(int statsID) {
        return (boolean) returnResponsObject(RequestType.REMOVE_STAT, RequestArgumentName.STAT_ID, ResponseArgumentName.STATS, statsID);
    }

    private Object returnResponsObject(RequestType requestType, RequestArgumentName requestArguementName, ResponseArgumentName responseArguementName, Object o) {
        Object object = null;
        try {
            Request request = createRequest(requestType);
            request.addArgument(requestArguementName, o);
            Response response = communicationLayer.sendRequest(request);
            object = response.getArgument(responseArguementName);
        } catch (ServiceNotFoundException ex) {
            Logger.getLogger(ProfileService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ArgumentNotFoundException ex) {
            Logger.getLogger(ProfileService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return object;
    }

    @Override
    public boolean deleteAccount(String profileUsername) {
        boolean isDeleted = false;
        try {
            try {
                Request req = createRequest(RequestType.DELETE_ACCOUNT);
                req.addArgument(RequestArgumentName.USERNAME, profileUsername);

                Response res = communicationLayer.sendRequest(req);
                isDeleted = (boolean) res.getArgument(ResponseArgumentName.SUCCESS);
            } catch (ServiceNotFoundException | ArgumentNotFoundException ex) {
                Logger.getLogger(ProfileService.class.getName()).log(Level.SEVERE, null, ex);
            }

            domainFacade.<IAuthenticationService>getService(ServiceType.AUTHENTICATION).logout();
            FXMain.showLoginPage();
        } catch (ServiceNotFoundException ex) {
            Logger.getLogger(ProfileService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassCastException ex) {
            Logger.getLogger(ProfileService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(ProfileService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return isDeleted;
    }

    /**
     *
     * @param ProfileID
     * @return
     */
    @Override
    public Stats getCurrentStats(int ProfileID) {
        return (Stats) returnResponsObject(RequestType.LOAD_ALL_STATS, RequestArgumentName.STAT_ID, ResponseArgumentName.STATS, ProfileID);

    }

    @Override
    public List<Profile> getAllBuddys(int ProfileID) {
        return (List<Profile>) returnResponsObject(RequestType.GET_ALL_BUDDYS, RequestArgumentName.PROFILE_ID, ResponseArgumentName.PROFILE, ProfileID);
    }

    @Override
    public boolean isBuddy(int buddyID) {
        return (boolean) returnResponsObject(RequestType.IS_BUDDY, RequestArgumentName.PROFILE_ID, ResponseArgumentName.SUCCESS, buddyID);
    }
    
 
}
