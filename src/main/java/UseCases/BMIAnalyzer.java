package UseCases;

import Entities.User;
import Constants.Constants;
import java.util.HashMap;

/**
 * Subclass of UserAnalyzer. Returns user BMI.
 */
public class BMIAnalyzer implements UserAnalyzer {
    private User user;
    public BMIAnalyzer() {}
    public BMIAnalyzer(User user){
        this.user = user;
    }

    private String result;

    @Override
    public void analyze() {
        User user = UserManager.getCurrentUser();
        if(user == null){
            user = this.user;
        }

        HashMap<String, Object> personalData = user.getPersonalData();
        float userHeight = Float.parseFloat((String)personalData.get("height"));
        float userWeight = Float.parseFloat((String)personalData.get("weight"));
        float bmi = (userWeight / (userHeight * userHeight));
        user.setPersonalData("BMI", bmi);

        String intro =  Constants.DIVIDER + Constants.BMI_INTRO + (double) Math.round(bmi * 100) / 100 + ".";
        String health = getBMIStatus(bmi);
        String username = user.getUsername();

        this.result = intro + Constants.EMPTY_LINE + username + Constants.BMI_ANALYSIS + health + Constants.DIVIDER;
    }

    /**
     * Helper method for analyze.
     * @param bmi of the user.
     * @return Health state of the user given their bmi.
     */
    private String getBMIStatus(float bmi) {
        String health;
        if (bmi >= Constants.OBESE_BMI){
            health = Constants.OBESE;
        } else if (bmi >= Constants.OVERWEIGHT_BMI){
            health = Constants.OVERWEIGHT;
        } else if (bmi >= Constants.HEALTHY_BMI) {
            health = Constants.HEALTHY;
        } else {
            health = Constants.UNDERWEIGHT;
        }
        return health;
    }

    public String getResult() { return this.result; }

}
