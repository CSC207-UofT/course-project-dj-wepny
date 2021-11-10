/**
 * This file contains Junit test case for the ExerciseAnalyzer.java
 * It tests for the case when no exercises is found to match the user's inputs.
 */

import org.junit.*;
import static org.junit.Assert.*;
import java.util.HashMap;
import UseCases.ExerciseAnalyzer;
import Entities.User;

public class TestExerciseAnalyzerNoExercise {
    ExerciseAnalyzer exercises;
    User user;

    @Before
    public void setUp() {
        exercises = new ExerciseAnalyzer();
        HashMap<String, Object> userInfo = new HashMap<>();

        userInfo.put("height", "1.70");
        userInfo.put("weight", "58");
        userInfo.put("age", "21");

        userInfo.put("height", "1.80");
        userInfo.put("weight", "65");
        userInfo.put("age", "21");
        user = new User(2112, "Bobby", "M", userInfo);
        user.setExercisePreference("major muscle", "Arms");
        user.setExercisePreference("minor muscle", "Waist");
        user.setExercisePreference("equipment", "Body Weight");

    }

    // core, chest, body weight
    @Test(timeout = 500)
    public void testOutput() {
        exercises.analyze();
        String expected_string = "*****************************************************************************\n" +
                "Exercises for " + user.getUsername() + ": " +
                "The following exercises are based on your preferences on the muscles exercised and equipment.\n" +
                "\nUnfortunately, we currently do not have any exercises that match these preferences. " +
                "Please try again.";
        assertEquals(expected_string,
                exercises.getResult());
    }
}
