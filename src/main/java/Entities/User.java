package Entities;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * This class stores an User Object
 * describing the information and preferences the user has
 */

public class User {
    // an unique id used to identify the user
    private final int id;
    // the user's name
    private String username;
    private String gender;
    private final HashMap<String, Object> personalData;
    // stores the number of food the user want the mealPlanGenerator to output
    private int numFood;
    // stores the user's preference on low carbs, low sugar, low fat and whether they are vegetarian
    private HashMap<FoodFilterCriterion, Boolean> foodPreference;
    // stores the part of the muscle the user want to exercise and the equipments they have
    private final HashMap<String, String> exercisePreference;
    // stores the symptoms the user have
    private ArrayList<String> riskFactor;

    /**
     * Construct a User, with a username, user's gender and personal data
     *
     * @param username     Username of the user
     * @param gender       Gender of the user
     * @param personalData hashmap containing user's height, weight, age, and activity level
     */
    public User(int id, String username, String gender, HashMap<String, Object> personalData) {
        this.id = id;
        this.username = username;
        this.gender = gender;
        this.foodPreference = new HashMap<>();
        this.exercisePreference = new HashMap<>();
        this.riskFactor = new ArrayList<>();
        this.personalData = personalData;
    }

    // getter functions
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

    // setter functions
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

    public void resetRiskFactor() {
        riskFactor = new ArrayList<>();
    }

    public void setPersonalData(String key, Object value) {
        personalData.put(key, value);
    }

    public void resetFoodPreference() {
        foodPreference = new HashMap<>();
    }

    public void setUserName(String newName) {
        this.username = newName;
    }

    public void setUserGender(String newGender) {
        this.gender = newGender;
    }

}



