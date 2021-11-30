package UseCases;

import Entities.IUser;
import Constants.*;

import java.util.HashMap;

/**
 * This Class Analyzes the Energy Requirement per day (EER) for the user.
 */
public class EERAnalyzer implements UserAnalyzer {
    private IUser user;
    String result;

    /**
     * Initiating a ExerciseAnalyzer with no parameter.
     */
    public EERAnalyzer() {
    }

    /**
     * An overloading constructor that initiate a ExerciseAnalyzer which take user as its parameter.
     *
     * @param user The user that the ExerciseAnalyzer is analyzing for.
     */
    public EERAnalyzer(IUser user) {
        this.user = user;
    }

    /**
     * This method calculate the user's estimated energy requirement (EER).
     */
    @Override
    public void analyze() {

        IUser user = UserManager.getCurrentUser();
        // check if user is null
        if (user == null) {
            user = this.user;
        }

        //Calculating EER.
        double energyRequirement = calculateEER(user);

        //Setting the resulting String.
        result = SystemConstants.DIVIDER + EERConstants.EER_DESCRIPTION + (double) Math.round(energyRequirement * 100) / 100 +
                SystemConstants.DIVIDER;
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
                case EERConstants.SED:
                    return EERConstants.M_SEDENTARY;
                case EERConstants.LOW:
                    return EERConstants.M_LOW;
                case EERConstants.MID:
                    return EERConstants.M_ACTIVE;
                default:
                    return EERConstants.M_HIGH;
            }

        }

        // check PA value for female
        if (gender.equals("F")) {
            switch (activityRating) {
                case EERConstants.SED:
                    return EERConstants.F_SEDENTARY;
                case EERConstants.LOW:
                    return EERConstants.F_LOW;
                case EERConstants.MID:
                    return EERConstants.F_ACTIVE;
                default:
                    return EERConstants.F_HIGH;
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
    private double calculateEER(IUser user) {
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
