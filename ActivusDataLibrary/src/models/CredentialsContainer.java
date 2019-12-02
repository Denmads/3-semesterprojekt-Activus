package models;

import java.io.Serializable;
import java.util.UUID;

/**
 * Contains login information
 * Used in authentication
 *
 * @author madsh
 */
public class CredentialsContainer implements Serializable {

    private final String username;
    private final int loginId;
    private final int profileId;
    private final UUID authenticationToken;

    public CredentialsContainer(String username, int loginId, int profileId, UUID authenticationToken) {
        this.username = username;
        this.loginId = loginId;
        this.profileId = profileId;
        this.authenticationToken = authenticationToken;
    }

    public String getUsername() {
        return username;
    }

    public int getLoginId() {
        return loginId;
    }

    public int getProfileId() {
        return profileId;
    }
    
    public UUID getAuthenticationToken() {
        return authenticationToken;
    }

}