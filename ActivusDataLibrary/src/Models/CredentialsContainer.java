/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.util.UUID;

/**
 *
 * @author madsh
 */
public class CredentialsContainer {
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
