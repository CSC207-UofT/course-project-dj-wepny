package UseCases;

import Entities.User;
import Constants.Constants;

import java.util.HashMap;

/**
 * This Class Analyzes the Energy Requirement per day (EER) for the user.
 */
public class EERAnalyzer implements UserAnalyzer {

    String result;

    /**
     * This method calculate the user's estimated energy requirement (EER).
     */
    @Override
    public void analyze() {
        User user = UserManager.getCurrentUser();

        //Calculating EER.
        double energyRequirement = calculateEER(user);

        //Setting the resulting String.
        result = Constants.DIVIDER + Constants.EER_DESCRIPTION + (double) Math.round(energyRequirement * 100) / 100 +
                Constants.DIVIDER;
    }

    /**
     * A getter method for the result.
     *
     * @return The EER for the user.
     */
    @Override
    public String getResult() {
        return result;
    }

    /**
     * Helper method to get the related PA value based on the gender of the user and their activity rating.
     *
     * @param gender         A string representing the Gender of the user.
     * @param activityRating A string representing the activity rating of the user.
     * @return the integer value of the corresponding PA.
     */
    private double getPAValueFromActivityRating(String gender, String activityRating) {
        // check PA value for male
        if (gender.equals("M")) {
            switch (activityRating) {
                case Constants.SED:
                    return Constants.M_SEDENTARY;
                case Constants.LOW:
                    return Constants.M_LOW;
                case Constants.MID:
                    return Constants.M_ACTIVE;
                default:
                    return Constants.M_HIGH;
            }

        }

        // check PA value for female
        if (gender.equals("F")) {
            switch (activityRating) {
                case Constants.SED:
                    return Constants.F_SEDENTARY;
                case Constants.LOW:
                    return Constants.F_LOW;
                case Constants.MID:
                    return Constants.F_ACTIVE;
                default:
                    return Constants.F_HIGH;
            }
        }

        return 0.0;
    }

    /**
     * This is a helper method to calculate EER based on user info.
     *
     * @param user is the User that the EER is calculated for.
     * @return A double indicating the EER of the user.
     */
    private double calculateEER(User user) {
        //get user information
        String gender = user.getGender();
        String activityRating = (String) user.getPersonalData().get("activity level");
        double physicalActivity = getPAValueFromActivityRating(gender, activityRating);

        HashMap<String, Object> personalData = user.getPersonalData();
        float userHeight = Float.parseFloat((String) personalData.get("height"));
        float userWeight = Float.parseFloat((String) personalData.get("weight"));
        int age = Integer.parseInt((String) personalData.get("age"));

        //calculate the EER of the user
        if (gender.equals("M")) {
            return 662 - 9.53 * age + physicalActivity * (15.91 * userWeight + 539.6 * userHeight);
        }
        return 354 - 6.91 * age + physicalActivity * (9.36 * userWeight + 726 * userHeight);
    }
}
