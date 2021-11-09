/*
 *This file contains Junit test case for the ExerciseAnalyzer.java
 */

import org.junit.*;
import static org.junit.Assert.*;
import java.util.HashMap;

public class TestExerciseAnalyzer {
    ExerciseAnalyzer exercises;
    User user;

    @Before
    public void setUp() {
        exercises = new ExerciseAnalyzer();
        HashMap<String, Object> userInfo = new HashMap<>();

        userInfo.put("height", "1.70");
        userInfo.put("weight", "58");
        userInfo.put("age", "21");
        user = new User(20, "Amy", "F", userInfo);

    }

    @Test(timeout = 500)
    public void testOutput() {
        exercises.analyze();
        assertEquals("*****************************************************************************\n" +
                "Exercises for " + user.getUsername() + ": " +
                "The following exercises are based on your preferences on the muscles exercised and equipment.\n",
                exercises.getResult());
    }

}
