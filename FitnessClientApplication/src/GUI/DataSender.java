/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Models.Profile;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 *
 * @author steff
 */
public class DataSender {
    Profile profile;

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }
        
    
       
  
    
    private DataSender() {
    }
    
    public static DataSender getInstance() {
        return DateSenderHolder.INSTANCE;
    }
    
    private static class DateSenderHolder {

        private static final DataSender INSTANCE = new DataSender();
    }
    

    
    
    
    
}
