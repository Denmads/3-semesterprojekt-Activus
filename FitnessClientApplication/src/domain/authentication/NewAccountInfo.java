/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Domain.authentication;

/**
 * Contains minimum info to create a new account
 * A model class
 *
 * @author madsh
 */
public class NewAccountInfo {
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    
    public NewAccountInfo(String firstName, String lastName, String username, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
    
    
}
