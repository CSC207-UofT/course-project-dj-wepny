import java.util.ArrayList;
import java.util.HashMap;

/**
 *  Below is the User class which stores user's information.
 */
public class User {
    private final int id;
    private String username;
    private final String gender;
    private HashMap<FoodFilterCriterion, Boolean> foodPreference;
    private HashMap<String, Object> exercisePreference;
    private ArrayList<String> riskFactor;
    private HashMap<String, Object> personalData;
    private String activityRating;


    /**
     * Construct a User, with a username, user's gender and personal data
     *
     * @param username Username of the user
     * @param gender  Gender of the user
     * @param personalData contains user's height, weight, age, daily activity level, BMI, and EER
     */

    public User(int id, String username, String gender, HashMap<String, Object> personalData) {
        this.id = id;
        this.username = username;
        this.gender = gender;
        this.foodPreference = new HashMap<>();
        this.exercisePreference = new HashMap<String, Object>();
        this.riskFactor = new ArrayList<String>();
        this.personalData = personalData;
    }

    /*
       An overloaded constructor, but why do we need this? - David
     * @param id
     * @param name
     * @param gender
     * @param personalData
     * @param food
     * @param exercise
     * @param disease
     */
    public User(int id, String name, String gender, HashMap<String, Object> personalData, HashMap<FoodFilterCriterion, Boolean> food,
                HashMap<String, Object> exercise, ArrayList<String> disease){
        this.id = id;
        this.username= name;
        this.gender = gender;
        this.foodPreference = food;
        this.exercisePreference = exercise;
        this.riskFactor = disease;
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

    public HashMap<FoodFilterCriterion, Boolean> getFoodPreference() {
        return foodPreference;
    }

    public HashMap<String, Object> getExercisePreference() {
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

    public void setExercisePreference(String key, Object value) {
        exercisePreference.put(key, value);
    }

    public void setRiskFactor(ArrayList<String> newSymptoms) {
        riskFactor = newSymptoms;
    }

    public void setPersonalData(String key, Object value) {
        personalData.put(key, value);
    }

    public void setUserName(String newName) {this.username = newName;}
    // do we need this?


//    public void setActivityRating(String activityRating) {this.activityRating = activityRating;}

//    public String getActivityRating() {return activityRating;}
}

