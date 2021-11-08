package UseCases;

import Entities.User;
import Constants.Constants;
import java.util.HashMap;

/**
 * Subclass of UserAnalyzer. Returns user BMI.
 */
public class BMIAnalyzer implements UserAnalyzer {

    public BMIAnalyzer() {}

    private String result;

    @Override
    public void analyze() {
        User user = UserManager.getCurrentUser();

        HashMap<String, Object> personalData = user.getPersonalData();
        float userHeight = Float.parseFloat((String)personalData.get("height"));
        float userWeight = Float.parseFloat((String)personalData.get("weight"));
        float bmi = (userWeight / (userHeight * userHeight));
        user.setPersonalData("BMI", bmi);

        String intro =  Constants.DIVIDER + Constants.BMI_INTRO + (double) Math.round(bmi * 100) / 100;
        String health = getBMIStatus(bmi);
        String username = user.getUsername();

        this.result = intro + Constants.EMPTY_LINE + username + Constants.BMI_ANALYSIS + health + Constants.DIVIDER;
    }

    /**
     * Helper method for analyze.
     * @param bmi of the user.
     * @return classification of user's bmi.
     */
    private String getBMIStatus(float bmi) {
        String health;
        if (bmi >= 30){
            health = Constants.OBESE;
        } else if (bmi >= 25){
            health = Constants.OVERWEIGHT;
        } else if (bmi >= 18.5) {
            health = Constants.HEALTHY;
        } else {
            health = Constants.UNDERWEIGHT;
        }
        return health;
    }

    public String getResult() { return this.result; }

}
