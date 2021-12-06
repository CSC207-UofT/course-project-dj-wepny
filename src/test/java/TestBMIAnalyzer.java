/*
 *This file contains Junit test case for BMIAnalyzer.java
 */

import entities.User;
import usecases.BMIAnalyzer;
import org.junit.*;
import static org.junit.Assert.assertEquals;

import java.util.HashMap;

public class TestBMIAnalyzer {
    BMIAnalyzer bmi_healthy, bmi_underweight, bmi_overweight, bmi_obese;
    User user_healthy, user_underweight, user_overweight, user_obese;

    @Before
    public void setUp() {
        // Setup user info
        HashMap<String, Object> userInfoHealthy = new HashMap<>();
        HashMap<String, Object> userInfoUnderweight = new HashMap<>();
        HashMap<String, Object> userInfoOverweight = new HashMap<>();
        HashMap<String, Object> userInfoObese = new HashMap<>();

        // Underweight user and bmi analyzer creation
        userInfoUnderweight.put("height", "1.70");
        userInfoUnderweight.put("weight", "20");
        userInfoUnderweight.put("age", "21");
        user_underweight = new User(20, "Amy", "F", userInfoUnderweight);
        bmi_underweight = new BMIAnalyzer(user_underweight);

        // Healthy user and bmi analyzer creation
        userInfoHealthy.put("height", "1.70");
        userInfoHealthy.put("weight", "58");
        userInfoHealthy.put("age", "21");
        user_healthy = new User(21, "Sarah", "F", userInfoHealthy);
        bmi_healthy = new BMIAnalyzer(user_healthy);

        // Overweight user and bmi analyzer creation
        userInfoOverweight.put("height", "1.70");
        userInfoOverweight.put("weight", "80");
        userInfoOverweight.put("age", "21");
        user_overweight = new User(22, "Sabrina", "F", userInfoOverweight);
        bmi_overweight = new BMIAnalyzer(user_overweight);

        // Obese user and bmi analyzer creation
        userInfoObese.put("height", "1.70");
        userInfoObese.put("weight", "100");
        userInfoObese.put("age", "21");
        user_obese = new User(23, "Ivy", "F", userInfoObese);
        bmi_obese = new BMIAnalyzer(user_obese);
    }

    @Test(timeout = 500)
    public void testUnderweightOutput() {
        bmi_underweight.analyze();
        assertEquals("\n*****************************************************************************\n" +
                        "The Body Mass Index (BMI) is a number calculated given your weight and height. \n" +
                        "High BMI (>25.0) can indicate high body fatness, and considered as overweight. \n" +
                        "BMI can indicate health problems such as obesity and malnutrition.\n" +
                        "Your Body Mass Index is 6.92.\n" +
                        "\n" +
                        user_underweight.getUsername() + ", your BMI is considered: Underweight.\n" +
                        "*****************************************************************************\n",
                bmi_underweight.getResult());
    }

    @Test(timeout = 500)
    public void testHealthyOutput() {
        bmi_healthy.analyze();
        assertEquals("\n*****************************************************************************\n" +
                        "The Body Mass Index (BMI) is a number calculated given your weight and height. \n" +
                        "High BMI (>25.0) can indicate high body fatness, and considered as overweight. \n" +
                        "BMI can indicate health problems such as obesity and malnutrition.\n" +
                        "Your Body Mass Index is 20.07.\n" +
                        "\n" +
                        user_healthy.getUsername() + ", your BMI is considered: Healthy Weight.\n" +
                        "*****************************************************************************\n",
                bmi_healthy.getResult());
    }

    @Test(timeout = 500)
    public void testOverweightOutput() {
        bmi_overweight.analyze();
        assertEquals("\n*****************************************************************************\n" +
                        "The Body Mass Index (BMI) is a number calculated given your weight and height. \n" +
                        "High BMI (>25.0) can indicate high body fatness, and considered as overweight. \n" +
                        "BMI can indicate health problems such as obesity and malnutrition.\n" +
                        "Your Body Mass Index is 27.68.\n" +
                        "\n" +
                        user_overweight.getUsername() + ", your BMI is considered: Overweight.\n" +
                        "*****************************************************************************\n",
                bmi_overweight.getResult());
    }

    @Test(timeout = 500)
    public void testObeseOutput() {
        bmi_obese.analyze();
        assertEquals("\n*****************************************************************************\n" +
                        "The Body Mass Index (BMI) is a number calculated given your weight and height. \n" +
                        "High BMI (>25.0) can indicate high body fatness, and considered as overweight. \n" +
                        "BMI can indicate health problems such as obesity and malnutrition.\n" +
                        "Your Body Mass Index is 34.6.\n" +
                        "\n" +
                        user_obese.getUsername() + ", your BMI is considered: Obese.\n" +
                        "*****************************************************************************\n",
                bmi_obese.getResult());
    }


}