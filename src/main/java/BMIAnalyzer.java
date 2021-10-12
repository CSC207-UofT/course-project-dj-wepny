package main.java;

import java.util.HashMap;

public class BMIAnalyzer extends UserAnalyzer {
    /**
     * Subclass of UserAnalyzer. Returns user BMI.
     */

    @Override
    public Object analyze(User user) {
        HashMap<String, Object> personalData = user.getPersonalData();
        double userHeight = (double) personalData.get("height");
        double userWeight = (double) personalData.get("weight");
        return (userHeight / (userWeight * userWeight));
    }

}
