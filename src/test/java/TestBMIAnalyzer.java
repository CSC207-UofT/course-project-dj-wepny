/*
 *This file contains Junit test case for BMIAnalyzer.java
 */

import entities.User;
import usecases.BMIAnalyzer;
import org.junit.*;
import static org.junit.Assert.assertEquals;

import java.util.HashMap;


public class TestBMIAnalyzer {
    BMIAnalyzer bmi;
    User user;

    @Before
    public void setUp() {
        HashMap<String, Object> userInfo = new HashMap<>();

        userInfo.put("height", "1.70");
        userInfo.put("weight", "58");
        userInfo.put("age", "21");
        user = new User(20, "Amy", "F", userInfo);

        bmi = new BMIAnalyzer(user);
    }

    @Test(timeout = 500)
    public void testOutput() {
        bmi.analyze();
        assertEquals("\n*****************************************************************************\n" +
                        "The Body Mass Index (BMI) is a number calculated given your weight and height. \n" +
                        "High BMI (>25.0) can indicate high body fatness, and considered as overweight. \n" +
                        "BMI can indicate health problems such as obesity and malnutrition.\n" +
                        "Your Body Mass Index is 20.07.\n" +
                        "\n" +
                        user.getUsername() + ", your BMI is considered: Healthy Weight.\n" +
                        "*****************************************************************************\n",
                bmi.getResult());
    }


}