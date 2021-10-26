import java.util.HashMap;

public class EERAnalyzer implements UserAnalyzer{

    /*
    This Class Analyzes the Energy Requirement per day (EER) for the user.
     */

    String result;

    @Override
    public void analyze(User user) {
        String gender = user.getGender();
        String activityRating = user.getActivityRating();
        double physicalActivity = getPAValueFromActivityRating(gender, activityRating);

        HashMap<String, Object> personalData = user.getPersonalData();
        float userHeight = Float.parseFloat((String)personalData.get("height"));
        float userWeight = Float.parseFloat((String)personalData.get("weight"));
        int age = Integer.parseInt((String)personalData.get("age"));

        double energyRequirement= calculateEER(gender, physicalActivity, userHeight, userWeight, age);

        result = "Your Energy Requirement (EER) per day is roughly: " + energyRequirement;
    }

    /**
     * Helper method to get the related PA value based on the gender of the user and their activity rating.
     * @param gender A string representing the Gender of the user.
     * @param activityRating A string representing the activity rating of the user.
     * @return the integer value of the corresponding PA.
     */
    private double getPAValueFromActivityRating(String gender, String activityRating){
        if (gender.equals("M")){
            return switch (activityRating) {
                case "Sedentary" -> 1.0;
                case "Low active" -> 1.12;
                case "Active" -> 1.27;
                default -> 1.54;
            };

        }

        if (gender.equals("F")){
            return switch (activityRating) {
                case "Sedentary" -> 1.0;
                case "Low active" -> 1.14;
                case "Active" -> 1.27;
                default -> 1.45;
            };
        }

        return 0.0;
    }

    /**
     * This is a helper method to calculate EER based on user info.
     * @param gender A string indicating the gender of the user.
     * @param physicalActivity A double indicating physical activity of the user.
     * @param height A float indicating the user's height.
     * @param weight A float indicating the user's weight.
     * @param age An integer indicating the user's age.
     * @return A double indicating the EER of the user.
     */
    private double calculateEER(String gender, double physicalActivity, float height, float weight, int age){
        if (gender.equals("M")){
            return 864 - 9.72 * age + physicalActivity * (14.2 * weight + 503 * height);
        }
        return 387 - 7.31 * age + physicalActivity * (10.9 * weight + 660.7 * height);
    }
}
