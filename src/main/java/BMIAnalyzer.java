package main.java;

import java.util.HashMap;

public class BMIAnalyzer extends UserAnalyzer {
    /**
     * Subclass of UserAnalyzer. Returns user BMI.
     */

    @Override
    public Object analyze(User user) {
        HashMap<String, Object> personalData = user.getPersonalData();
        float userHeight = Float.parseFloat((String)personalData.get("height"));
        float userWeight = Float.parseFloat((String)personalData.get("weight"));
        float bmi = (userHeight / (userWeight * userWeight));
        user.setPersonalData("BMI", bmi);
        return bmi;
    }

}
