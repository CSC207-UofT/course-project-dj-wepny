import entities.User;
import system.HelperConsole;
import usecases.UserManager;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Scanner;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;


public class TestHelperConsole {
    User user;
    /*
     *This file contains Junit test case for the HelperConsole.java
     */

    @Test(timeout = 500)
    public void testCheckCommand() {
        Scanner reader = new Scanner(System.in);
        // check if the user inputted a number within the correct range
        String output = HelperConsole.checkCommand("6", reader, 2);
        assertEquals("6", output);
        String output1 = HelperConsole.checkCommand("4", reader, 3);
        assertEquals("4", output1);
    }

    @Test(timeout = 500)
    public void testIsNotNum() {
        // testing to check if the function correctly identifies the input as a number or not
        boolean notNum = HelperConsole.isNotNum("10");
        assertFalse(notNum);

        boolean notNum1 = HelperConsole.isNotNum("hi");
        assertTrue(notNum1);
    }


    @Before
    public void setUp() {
        HashMap<String, Object> userInfo = new HashMap<>();
        userInfo.put("height", "1.88");
        userInfo.put("weight", "65");
        userInfo.put("age", "19");
        // initiate a new user
        user = new User(2030, "Frank", "M", userInfo);
        UserManager.addUser(true, user);

        //setting the user's activity level and exercise preference
        UserManager.getCurrentUser().setPersonalData("activity level", "Active");
        UserManager.getCurrentUser().setExercisePreference("major muscle", "Legs");
        UserManager.getCurrentUser().setExercisePreference("minor muscle", "Quads");
        UserManager.getCurrentUser().setExercisePreference("equipment", "Bar");
    }

    @Test(timeout = 500)
    public void testNoInfoFound() {
        // checking to if the user have activity level, exercise preference, risk factor and food preference
        // stored in its profile
        boolean noActivityInfo = HelperConsole.noInfoFound(2);
        boolean noExerciseInfo = HelperConsole.noInfoFound(3);
        boolean noRiskFactorInfo = HelperConsole.noInfoFound(4);
        boolean noFoodInfo = HelperConsole.noInfoFound(5);

        assertFalse(noActivityInfo);
        assertFalse(noExerciseInfo);
        assertTrue(noRiskFactorInfo);
        assertTrue(noFoodInfo);
    }
}


