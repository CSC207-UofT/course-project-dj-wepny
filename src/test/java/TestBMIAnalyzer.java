/*
 *This file contains Junit test case for BMIAnalyzer.java
 */

import Entities.User;
import UseCases.BMIAnalyzer;
import UseCases.UserManager;
import org.junit.*;

import static org.junit.Assert.*;

public class TestBMIAnalyzer {
    BMIAnalyzer bmi;
    User user;

    @Before
    public void setUp() {
        // call UserManager to create a new user
        UserManager.createNewUser(new String[]{"Amy", "F"}, new String[]{"1.70", "58", "21"});
        // set user to the currentUser
        user = UserManager.getCurrentUser();

        // initiate a new BMIAnalyzer of the current user
        bmi = new BMIAnalyzer(user);
    }

    @Test(timeout = 500)
    public void testOutput() {
        // analyze the bmi of user
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