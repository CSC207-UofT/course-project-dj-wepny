/*
 *This file contains Junit test case for BMIAnalyzer.java
 */
package test;
import main.java.BMIAnalyzer;
import main.java.User;
import org.junit.*;

import java.util.HashMap;

import static org.junit.Assert.*;
public class TestBMIAnalyzer {
    BMIAnalyzer bmi;
    User user;

    @Before
    public void setUp() throws Exception {
        bmi = new BMIAnalyzer();
        HashMap<String, Object> userInfo = new HashMap<String, Object>();

        userInfo.put("height", "1.70");
        userInfo.put("weight", "58");
        userInfo.put("age", "21");
        user = new User("Amy", "F", userInfo);

    }

    @Test(timeout = 500)
     public void testOutput() {
        assertEquals("*****************************************************************************\n" +
                " The Body Mass Index (BMI) is a number calculated given your weight and height. \n" +
                "High BMI (>25.0) can indicate high body fatness, and considered as overweight. \n" +
                "BMI can indicate health problems such as obesity and malnutrition.\n" +
                "Your Body Mass Index is 20.07.\n" +
                "\n" +
                "This is considered: Healthy Weight.\n" +
                "*****************************************************************************", bmi.analyze(user));
    }


}
