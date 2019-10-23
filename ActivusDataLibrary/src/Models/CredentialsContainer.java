package Models;

import java.io.Serializable;
import java.util.UUID;

/**
 * Contains login information
 * Used in authentication
 *
 * @author madsh
 */
public class CredentialsContainer implements Serializable {

    private String username;
    private int userId;
    private UUID authenticationToken;

    public CredentialsContainer(String username, int userId, UUID authenticationToken) {
        this.username = username;
        this.userId = userId;
        this.authenticationToken = authenticationToken;
    }

    public String getUsername() {
        return username;
    }

    public int getUserId() {
        return userId;
    }

    public UUID getAuthenticationToken() {
        return authenticationToken;
    }

}