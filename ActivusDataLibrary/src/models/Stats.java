/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import Models.*;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


/**
 *
 * @author steff
 */
public class Stats {
    
    private final Profile currentProfile;
    private HashMap<String,Exercise> exerHashMap;
    private HashMap<String,HashMap> statsMap;
    

    public Stats(Profile currentProfile) {
        this.currentProfile=currentProfile;
        this.statsMap = new HashMap<>();
    }
    
    


   public void addExercises(String Type, Date date, Exercise exercise){
     if(statsMap.containsKey(Type)){
         statsMap.get(Type).put(date, exercise);   
     }else{
         exerHashMap = new HashMap<String, Exercise>();
         statsMap.put(Type, exerHashMap);
         statsMap.get(Type).put(date, exercise);
     }
     
   }
    
    
    
    
}
