import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Subclass of UserAnalyzer. Returns exercises recommended to the user.
 */

public class ExerciseAnalyzer implements UserAnalyzer{
    private String result;
    private User user;

    public ExerciseAnalyzer(){
        this.user = null;
    }

    public ExerciseAnalyzer(User user_given){
        this.user = user_given;
    }
    @Override
    public void analyze() {
        User user;
        if(this.user == null){
            user = UserManager.getCurrentUser();
        }
        else{
            user = this.user;
        }

        String intro =  "*****************************************************************************\n" +
                "Exercises for " + user.getUsername() + ": " +
                "The following exercises are based on your preferences on the muscles exercised and equipment.\n";

        HashMap<String, String> user_preference = user.getExercisePreference();
        List<Exercise> exercises = ExerciseAPI.readFromExerciseCSV();
        ArrayList<Exercise> user_exercises = new ArrayList<Exercise>(); // May not be needed
        StringBuilder exercise_names = new StringBuilder();

        for(Exercise exercise: exercises){
            if(exercise_match(exercise, user_preference)){
                user_exercises.add(exercise);
                String exercise_equipment = exercise.getEquipmentNeeded().toString();
                exercise_names.append("\n-  ").append(exercise.getName()).
                        append("\n    -> Type of the exercise: ").append(exercise.getType()).
                        append("\n    -> Uses: ").append(exercise_equipment).append(";").
                        append("\n    -> The major muscle exercised is: ").append(exercise.getMajorMuscleExercised()).
                        append("\n    -> The minor muscle exercised is: ").append(exercise.getMinorMuscleExercised()).
                        append("\n\n");
            }
        }
        if (user_exercises.isEmpty()){
            String msg = "\nUnfortunately, no exercise moves matches this particular set of preference, " +
                    "please try again!";
            this.result = intro + msg +
                    "\n**********************************************************************************************\n";
        }
        else {
            this.result = intro + exercise_names +
                    "**********************************************************************************************\n";
        }
    }

    /**
     *  A method used to determine if the exercise matches the user's exercise preferences
     */
    public boolean exercise_match(Exercise exercise, HashMap<String, String> user_preference){
        String majorMuscle =  user_preference.get("major muscle");
        String minorMuscle = user_preference.get("minor muscle");
        String equipment = user_preference.get("equipment");
        boolean hasMajMuscle = exercise.getMajorMuscleExercised().contains(majorMuscle);
        boolean hasMinMuscle = exercise.getMinorMuscleExercised().contains(minorMuscle);
        boolean hasEquip = exercise.getEquipmentNeeded().contains(equipment);

        return (hasMinMuscle || hasMajMuscle) && hasEquip;
    }

    @Override
    public String getResult() {
        return this.result;
    }
}