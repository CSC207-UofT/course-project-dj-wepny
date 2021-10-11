/* Below is the UserInformation class which stores user's food preference, exercise preference,
 * risk factor, and personal data
 */
import java.util.HashMap;

public class UserInformation {

    private final HashMap<String, Object> foodPreference;
    private final HashMap<String, Object> exercisePreference;
    private final HashMap<String, Object> riskFactor;
    private final HashMap<String, Object> personalData;

    /**
     *
     * @param foodPreference contains user's preference on nutritional score, salt, sugar, fat, food processing, and
     *                       whether the user is vegetarian
     * @param exercisePreference contains user's preference on the muscle they want to work on, the type of exercise
     *                           they want to do, and the equipment they have.
     * @param riskFactor contains the symptoms that the user has
     * @param personalData contains user's height, weight, age, daily activity level, BMI, and EER
     */

    public UserInformation(HashMap<String, Object> foodPreference,
                           HashMap<String, Object> exercisePreference,
                           HashMap<String, Object> riskFactor,
                           HashMap<String, Object> personalData){
        this.foodPreference = new HashMap<String, Object>();
        this.exercisePreference = new HashMap<String, Object>();
        this.riskFactor = new HashMap<String, Object>();
        this.personalData = new HashMap<String, Object>();
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
}
