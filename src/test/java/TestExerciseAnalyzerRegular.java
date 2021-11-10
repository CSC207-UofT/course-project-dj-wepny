/**
 *This file contains Junit test case for the ExerciseAnalyzer.java for when
 * an appropriate list of exercises is given
 **/

import org.junit.*;
import static org.junit.Assert.*;
import java.util.HashMap;
import UseCases.ExerciseAnalyzer;
import Entities.User;

public class TestExerciseAnalyzerRegular {
    ExerciseAnalyzer exercises;
    User user;

    @Before
    public void setUp() {
        exercises = new ExerciseAnalyzer();
        HashMap<String, Object> userInfo = new HashMap<>();

        userInfo.put("height", "1.80");
        userInfo.put("weight", "65");
        userInfo.put("age", "21");
        user = new User(2012, "John", "M", userInfo);
        user.setExercisePreference("major muscle", "Arms");
        user.setExercisePreference("minor muscle", "Biceps");
        user.setExercisePreference("equipment", "Dumbbells");

    }
    // core, chest, body weight
    @Test(timeout = 500)
    public void testOutput() {
        exercises.analyze();
        String expected_string ="*****************************************************************************\n" +
                "Exercises for " + user.getUsername() + ": " +
                "The following exercises are based on your preferences on the muscles exercised and equipment.\n" +
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
                "\n";
        assertEquals(expected_string,
                exercises.getResult());
    }

}
