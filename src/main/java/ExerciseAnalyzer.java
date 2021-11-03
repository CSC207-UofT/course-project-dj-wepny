import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Subclass of UserAnalyzer. Returns exercises recommended to the user.
 */

public class ExerciseAnalyzer implements UserAnalyzer{

    public ExerciseAnalyzer(){
    }

    private String result;
    @Override
    public void analyze(User user) {
        String intro =  "*****************************************************************************\n" +
                "Exercises for " + user.getUsername() + ": " +
                "The following exercises are based on your preferences on the muscles exercised and equipment. \n";

        HashMap<String, Object> user_preference = user.getExercisePreference();
        List<Exercise> exercises = ExerciseAPI.readFromExerciseCSV();
        ArrayList<Exercise> user_exercises = new ArrayList<Exercise>(); // May not be needed
        String exercise_names = "";

        for(Exercise exercise: exercises){
            if(exercise_match(exercise, user_preference)){
                user_exercises.add(exercise);
                String exercise_equipment = exercise.getEquipmentNeeded().toString();
                exercise_names += "-  " + exercise.getName() + ": Uses\n" + exercise_equipment + "; " +
                        "The major muscle exercised is " + exercise.getMajorMuscleExercised() +
                        ", and the minor muscle exercised is " + exercise.getMinorMuscleExercised();
            }
        }

        this.result = intro + exercise_names +
                "\n*****************************************************************************\n";

    }

    /**
     *  A method used to determine if the exercise matches the user's exercise preferences
     */
    public boolean exercise_match(Exercise exercise, HashMap<String, Object> user_preference){
        boolean has_maj_muscle = user_preference.containsValue(exercise.getMajorMuscleExercised());
        boolean has_min_muscle = user_preference.containsValue(exercise.getMinorMuscleExercised());
        if(has_maj_muscle && has_min_muscle){
            for(String equipment:exercise.getEquipmentNeeded()){
                if(!user_preference.containsValue(equipment)){
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    @Override
    public String getResult() {
        return this.result;
    }
}
