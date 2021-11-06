import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Subclass of UserAnalyzer. Returns exercises recommended to the user.
 */

public class ExerciseAnalyzer implements UserAnalyzer{
    private String result;

    public ExerciseAnalyzer(){
    }

    @Override
    public void analyze() {
        User user = UserManager.getCurrentUser();
        String intro =  "*****************************************************************************\n" +
                "Exercises for " + user.getUsername() + ": " +
                "The following exercises are based on your preferences on the muscles exercised and equipment. \n";

        HashMap<String, Object> user_preference = user.getExercisePreference();
        List<Exercise> exercises = ExerciseAPI.readFromExerciseCSV();
        ArrayList<Exercise> user_exercises = new ArrayList<Exercise>(); // May not be needed
        StringBuilder exercise_names = new StringBuilder();

        for(Exercise exercise: exercises){
            if(exercise_match(exercise, user_preference)){
                user_exercises.add(exercise);
                String exercise_equipment = exercise.getEquipmentNeeded().toString();
                exercise_names.append("-  ").append(exercise.getName()).
                        append(": Uses\n").append(exercise_equipment).append("; ").
                        append("The major muscle exercised is ").append(exercise.getMajorMuscleExercised()).
                        append(", and the minor muscle exercised is ").append(exercise.getMinorMuscleExercised());
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