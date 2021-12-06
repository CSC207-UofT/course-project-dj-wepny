package constants;

/**
 * This class contains constants related to the BMIAnalyzer function.
 */

public class BMIConstants {

    // BMI Constants
    public static final double OBESE_BMI = 30.0;
    public static final double OVERWEIGHT_BMI = 25.0;
    public static final double HEALTHY_BMI = 18.5;

    // Classifications
    public static final String OBESE = "Obese.";
    public static final String OVERWEIGHT = "Overweight.";
    public static final String HEALTHY = "Healthy Weight.";
    public static final String UNDERWEIGHT = "Underweight.";

    // Messages
    public static final String BMI_INTRO =
            "The Body Mass Index (BMI) is a number calculated given your weight and height. \n" +
                    "High BMI (>25.0) can indicate high body fatness, and considered as overweight. \n" +
                    "BMI can indicate health problems such as obesity and malnutrition.\nYour Body Mass Index is ";
    public static final String BMI_ANALYSIS = ", your BMI is considered: ";
}
