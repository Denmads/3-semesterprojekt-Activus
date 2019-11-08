/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.Date;
import java.util.HashMap; 

/**
 *
 * @author steff
 */
public class Stats {

    private final Profile currentProfile;
    private HashMap<Date, Exercise> exerHashMap;
    private final HashMap<String, HashMap> statsMap;

    public Stats(Profile currentProfile) {
        this.currentProfile = currentProfile;
        this.statsMap = new HashMap<>();
    }

    public void addExercises(String type, Date date, Exercise exercise) {
        if (statsMap.containsKey(type)) {
            statsMap.get(type).put(date, exercise);
        } else {
            exerHashMap = new HashMap<Date, Exercise>();
            statsMap.put(type, exerHashMap);
            statsMap.get(type).put(date, exercise);
        }

    }

    public HashMap<String, HashMap> getStatsMap() {
        return this.statsMap;
    }

    public HashMap<String, Exercise> getExerHashMap(String type) {
        if (statsMap.containsKey(type)) {
            return statsMap.get(type);
        }
        return null;
    }

}
