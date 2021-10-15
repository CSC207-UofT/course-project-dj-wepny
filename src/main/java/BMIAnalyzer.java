import static java.lang.Math.round;
import java.util.HashMap;
/**
 * Subclass of UserAnalyzer. Returns user BMI.
 */
public class BMIAnalyzer extends UserAnalyzer {

    @Override
    public Object analyze(User user) {
        HashMap<String, Object> personalData = user.getPersonalData();
        float userHeight = Float.parseFloat((String)personalData.get("height"));
        float userWeight = Float.parseFloat((String)personalData.get("weight"));
        float bmi = (userWeight / (userHeight * userHeight));
        user.setPersonalData("BMI", bmi);

        String intro =  "*****************************************************************************\n " +
                "The Body Mass Index (BMI) is a number calculated given your weight and height. \n" +
                "High BMI (>25.0) can indicate high body fatness, and considered as overweight. \n" +
                "BMI can indicate health problems such as obesity and malnutrition.\nYour Body Mass Index is " +
            (double) Math.round(bmi * 100) / 100;


        String health;
        if (bmi >= 30){
            health = "Obesity.";
        }
        else if (bmi >= 25){
            health = "OverWeight.";
        }
        else if (bmi >= 18.5) {
            health = "Healthy Weight.";
        }
        else{
            health = "Underweight.";
        }
        return intro + ".\n\nThis is considered: " + health +
                "\n*****************************************************************************\n";
    }

}
