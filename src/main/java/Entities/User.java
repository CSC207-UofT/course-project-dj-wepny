package Entities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

/**
 *  This class stores user's information.
 */

public class User {
    private final int id;
    private String username;
    private String gender;
    private int numFood;
    private HashMap<FoodFilterCriterion, Boolean> foodPreference;
    private HashMap<String, String> exercisePreference;
    private ArrayList<String> riskFactor;
    private HashMap<String, Object> personalData;

    /**
     * Construct a User, with a username, user's gender and personal data
     *
     * @param username Username of the user
     * @param gender  Gender of the user
     * @param personalData contains user's height, weight, age, daily activity level
     */

    public User(int id, String username, String gender, HashMap<String, Object> personalData) {
        this.id = id;
        this.username = username;
        this.gender = gender;
        this.foodPreference = new HashMap<>();
        this.exercisePreference = new HashMap<String, String>();
        this.riskFactor = new ArrayList<String>();
        this.personalData = personalData;
   }

    public int getId() {
        return this.id;
    }

    public String getUsername() {
        return username;
    }

    public String getGender() {
        return gender;
    }

    public int getNumFood() {
        return numFood;
    }

    public HashMap<FoodFilterCriterion, Boolean> getFoodPreference() {
        return foodPreference;
    }

    public HashMap<String, String> getExercisePreference() {
        return exercisePreference;
    }

    public ArrayList<String> getRiskFactor() {
        return riskFactor;
    }

    public HashMap<String, Object> getPersonalData() {
        return personalData;
    }

    public void setFoodPreference(FoodFilterCriterion key, Boolean value) {
        foodPreference.put(key, value);
    }

    public void setNumFoods(int numFoods) {
        this.numFood = numFoods;
    }

    public void setExercisePreference(String key, String value) {
        exercisePreference.put(key, value);
    }

    public void addRiskFactor(String addedSymptoms) {
        riskFactor.add(addedSymptoms);
    }

    public void resetRiskFactor() {riskFactor = new ArrayList<String>();}

    public void setPersonalData(String key, Object value) {
        personalData.put(key, value);
    }

    public void resetFoodPreference() {
        foodPreference = new HashMap<>();
    }

    public void setUserName(String newName) {this.username = newName;}
    // do we need this?

    public void setUserGender(String newGender) {this.gender = newGender;}

}



