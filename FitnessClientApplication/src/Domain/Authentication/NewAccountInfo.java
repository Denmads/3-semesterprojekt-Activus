/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Domain.Authentication;

/**
 *
 * @author madsh
 */
public class NewAccountInfo {
    public String firstName;
    public String lastName;
    public String username;
    public String password;

    public NewAccountInfo(String firstName, String lastName, String username, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
    }
    
    
}
