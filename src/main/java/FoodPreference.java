/* Below is the FoodPreference class which is a child class of UserInformation and stores users food preferences.
 */

public class FoodPreference extends UserInformation{
    public int nutritionalScore;
    public String saltPreference;
    public String sugarPreference;
    public String fatPreference;
    public String foodProcessingPreference;
    public boolean vegetarian;

    /**
     * Construct FoodPreference of the user
     *
     * @param nutritionalScore The nutritional score of food that the user prefer on scale of 1-10
     * @param saltPreference  User's salt preference
     * @param sugarPreference  User's sugar preference
     * @param fatPreference  User's fat preference
     * @param foodProcessingPreference  User's food process preference
     * @param vegetarian  True if the user is vegetarian, and false otherwise
     */

    public FoodPreference(int nutritionalScore, String saltPreference, String sugarPreference, String fatPreference,
                          String foodProcessingPreference, boolean vegetarian){
        this.nutritionalScore = nutritionalScore;
        this.saltPreference = saltPreference;
        this.sugarPreference = sugarPreference;
        this.fatPreference = fatPreference;
        this.foodProcessingPreference = foodProcessingPreference;
        this.vegetarian = vegetarian;

    }

}
