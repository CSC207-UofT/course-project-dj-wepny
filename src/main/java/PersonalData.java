/* Below is the RiskFactor class which is a child class of UserInformation, and stores user's symptoms.
 */

public class PersonalData extends UserInformation{

    public int height;
    public int weight;
    public int age;
    public String activityLevel;
    public int BMI;
    public int EER;


    /**
     * Construct PersonalData of the user
     *
     * @param height The height of user
     * @param weight  The weight of user
     * @param age  The age of user
     * @param activityLevel  User's daily activity level
     * @param BMI  User's BMI
     * @param EER  User's EER
     */


    public PersonalData(int height, int weight, int age, String activityLevel, int BMI, int EER) {
        this.height = height;
        this.weight = weight;
        this.age = age;
        this.activityLevel = activityLevel;
        this.BMI = BMI;
        this.EER = EER;
    }
}
