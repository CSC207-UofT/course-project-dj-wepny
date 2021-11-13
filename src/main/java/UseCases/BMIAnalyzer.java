package UseCases;

import Entities.IUser;
import Constants.Constants;

import java.util.HashMap;

/**
 * Subclass of UserAnalyzer. Returns user BMI.
 */
public class BMIAnalyzer implements UserAnalyzer {

    private IUser user;
    private String result;

    /**
     * Initiating a BMIAnalyzer with no parameter.
     */
    public BMIAnalyzer() {
    }

    /**
     * An overloading constructor that initiate a BMIAnalyzer which take user as its parameter.
     *
     * @param user The user that the BMI Analyzer is calculating the BMI for.
     */
    public BMIAnalyzer(User user) {

        this.user = user;
    }


    /**
     * This method calculate the User's BMI using their height and weight.
     */
    @Override
    public void analyze() {
        IUser user = UserManager.getCurrentUser();
        // check if user is null
        if(user == null){
            user = this.user;
        }

        // get information needed from user
        HashMap<String, Object> personalData = user.getPersonalData();
        float userHeight = Float.parseFloat((String) personalData.get("height"));
        float userWeight = Float.parseFloat((String) personalData.get("weight"));

        // calculating BMI
        float bmi = (userWeight / (userHeight * userHeight));
        user.setPersonalData("BMI", bmi);

        String intro = Constants.DIVIDER + Constants.BMI_INTRO + (double) Math.round(bmi * 100) / 100 + ".";
        String health = getBMIStatus(bmi);
        String username = user.getUsername();

        // set result to the string that will be prompt to the user
        this.result = intro + Constants.EMPTY_LINE + username + Constants.BMI_ANALYSIS + health + Constants.DIVIDER;
    }

    /**
     * Helper method for analyze.
     *
     * @param bmi A float indicating the bmi of the user.
     * @return Health state of the user given their bmi.
     */
    private String getBMIStatus(float bmi) {
        String health;

        //checking the health state of user
        if (bmi >= Constants.OBESE_BMI) {
            health = Constants.OBESE;
        } else if (bmi >= Constants.OVERWEIGHT_BMI) {
            health = Constants.OVERWEIGHT;
        } else if (bmi >= Constants.HEALTHY_BMI) {
            health = Constants.HEALTHY;
        } else {
            health = Constants.UNDERWEIGHT;
        }
        return health;
    }

    /**
     * A getter method for the result.
     *
     * @return The BMI of the User along with a short interpretation of the BMI.
     */
    public String getResult() {
        return this.result;
    }

}
