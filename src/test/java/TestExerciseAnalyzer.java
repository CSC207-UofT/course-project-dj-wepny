/*
 *This file contains Junit test case for the ExerciseAnalyzer.java for when
 * an appropriate list of exercises is given
 */

import Constants.ExerciseConstants;
import org.junit.*;
import static org.junit.Assert.*;
import java.util.HashMap;
import UseCases.ExerciseAnalyzer;
import Entities.User;
import Constants.*;

public class TestExerciseAnalyzer {
    ExerciseAnalyzer exercises_exist, exercises_no_match;
    User user_exist, user_no_match;

    @Before
    public void setUp() {
        HashMap<String, Object> userInfoExist = new HashMap<>();
        HashMap<String, Object> userInfoNoMatch = new HashMap<>();

        userInfoExist.put("height", "1.80");
        userInfoExist.put("weight", "65");
        userInfoExist.put("age", "21");
        user_exist = new User(2012, "John", "M", userInfoExist);
        user_exist.setExercisePreference("major muscle", "Arms");
        user_exist.setExercisePreference("minor muscle", "Biceps");
        user_exist.setExercisePreference("equipment", "Dumbbells");
        exercises_exist = new ExerciseAnalyzer(user_exist);

        userInfoNoMatch.put("height", "1.80");
        userInfoNoMatch.put("weight", "70");
        userInfoNoMatch.put("age", "21");
        user_no_match = new User(2013, "Johnny", "M", userInfoNoMatch);
        user_no_match.setExercisePreference("major muscle", "Arms");
        user_no_match.setExercisePreference("minor muscle", "Biceps");
        user_no_match.setExercisePreference("equipment", "Platform");
        exercises_no_match = new ExerciseAnalyzer(user_no_match);

    }
    // core, chest, body weight
    @Test(timeout = 500)
    public void testValidOutput() {
        exercises_exist.analyze();
        String expected_string = SystemConstants.DIVIDER  +
                "Exercises for " + user_exist.getUsername() + ": \n" +
                "The following exercises are based on your preferences on the muscles exercised and equipment. \n\n" +
                "-  Arnold Press\n" +
                "    -> Type of the exercise: [Weight]\n" +
                "    -> Uses: [Dumbbells];\n" +
                "    -> The major muscle exercised is: [Arms]\n" +
                "    -> The minor muscle exercised is: [Bicep, Shoulders]\n" +
                "\n" +
                "\n" +
                "-  Chest Press\n" +
                "    -> Type of the exercise: [Weight]\n" +
                "    -> Uses: [Dumbbells];\n" +
                "    -> The major muscle exercised is: [Arms]\n" +
                "    -> The minor muscle exercised is: [Chest]\n" +
                "\n" +
                "\n" +
                "-  Close to Wide Grip Burnout\n" +
                "    -> Type of the exercise: [Weight]\n" +
                "    -> Uses: [Dumbbells];\n" +
                "    -> The major muscle exercised is: [Arms]\n" +
                "    -> The minor muscle exercised is: [Bicep]\n" +
                "\n" +
                "\n" +
                "-  Side Arm / Lateral Raise\n" +
                "    -> Type of the exercise: [Weight]\n" +
                "    -> Uses: [Dumbbells];\n" +
                "    -> The major muscle exercised is: [Arms]\n" +
                "    -> The minor muscle exercised is: [Lats]\n" +
                "\n" +
                "\n" +
                "-  Tricep Kick-Back\n" +
                "    -> Type of the exercise: [Weight]\n" +
                "    -> Uses: [Dumbbells];\n" +
                "    -> The major muscle exercised is: [Arms]\n" +
                "    -> The minor muscle exercised is: [Tricep]\n" +
                "\n" +
                "\n" +
                "-  Tricep Overhead Press\n" +
                "    -> Type of the exercise: [Weight]\n" +
                "    -> Uses: [Dumbbells];\n" +
                "    -> The major muscle exercised is: [Arms]\n" +
                "    -> The minor muscle exercised is: [Tricep]\n" +
                "\n" +
                SystemConstants.DIVIDER;
        assertEquals(expected_string,
                exercises_exist.getResult());
    }

    @Test(timeout = 500)
    public void testNoOutput() {
        exercises_no_match.analyze();
        String expected_string = SystemConstants.DIVIDER +
        ExerciseConstants.EXERCISE_INTRO1 + user_no_match.getUsername() + ExerciseConstants.EXERCISE_INTRO2 +
                Exceptions.NO_EXERCISES_FOUND + SystemConstants.DIVIDER;
        assertEquals(expected_string,
                exercises_no_match.getResult());
    }

}
