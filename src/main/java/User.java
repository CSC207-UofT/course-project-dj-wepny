/* Below is the User class which stores user's information.
 */
import java.util.HashMap;

public class User {

    private final String username;
    private final String gender;
    private HashMap<String, Object> foodPreference;
    private HashMap<String, Object> exercisePreference;
    private HashMap<String, Object> riskFactor;
    private HashMap<String, Object> personalData;

    /**
     * Construct a User, with a username, user's gender, food preference, exercise preference, risk factors,
     * and personal data
     *
     * @param username Username of the user
     * @param gender  Gender of the user
     * @param foodPreference contains user's preference on nutritional score, salt, sugar, fat, food processing, and
     *                       whether the user is vegetarian
     * @param exercisePreference contains user's preference on the muscle they want to work on, the type of exercise
     *                           they want to do, and the equipment they have.
     * @param riskFactor contains the symptoms that the user has
     * @param personalData contains user's height, weight, age, daily activity level, BMI, and EER
     */

    public User(String username, String gender, HashMap<String, Object> foodPreference,
                HashMap<String, Object> exercisePreference, HashMap<String, Object> riskFactor,
                HashMap<String, Object> personalData) {

        this.username= username;
        this.gender = gender;
        this.foodPreference = new HashMap<String, Object>();
        this.exercisePreference = new HashMap<String, Object>();
        this.riskFactor = new HashMap<String, Object>();
        this.personalData = new HashMap<String, Object>();
    }

    public String getUsername() {
        return username;
    }

    public String getGender() {
        return gender;
    }

    public HashMap<String, Object> getFoodPreference() {
        return foodPreference;
    }

    public HashMap<String, Object> getExercisePreference() {
        return exercisePreference;
    }

    public HashMap<String, Object> getRiskFactor() {
        return riskFactor;
    }

    public HashMap<String, Object> getPersonalData() {
        return personalData;
    }

    public void setFoodPreference(String key, Object value) {
        foodPreference.put(key, value);
    }

    public void setExercisePreference(String key, Object value) {
        exercisePreference.put(key, value);
    }

    public void setRiskFactor(String key, Object value) {
        riskFactor.put(key, value);
    }

    public void setPersonalData(String key, Object value) {
        personalData.put(key, value);
    }

}
