import java.util.ArrayList;
import java.util.List;

public class WorkoutAnalyzer implements UserAnalyzer {

    /** This class takes in user's exercise preferences and outputs a string describing what
     * exercises are best suited for them.
     */

    private String result;

    @Override
    public void analyze() {
        User user = UserManager.getCurrentUser();
        // Initializes an Arraylist for the resulting Exercise names.
        ArrayList<String> exerciseNameResults = new ArrayList<>();

        // Create a List of exercises using the ExerciseAPI.
        List<Exercise> exercises = ExerciseAPI.readFromExerciseCSV();

        // Assumptions in User:
        // The exercisePreference Hashmap in User has the following:
        // <"Major Muscle", ArrayList<String>>, <"Minor Muscle", ArrayList<String>>
        // <"Exercise Type", ArrayList<String>>, <"Equipments", ArrayList<String>>

        // Get the relative info from User.
        ArrayList<String> majorMuscle = (ArrayList<String>) user.getExercisePreference().get("Major Muscle");
        ArrayList<String> minorMuscle = (ArrayList<String>) user.getExercisePreference().get("Minor Muscle");
        ArrayList<String> exerciseType = (ArrayList<String>) user.getExercisePreference().get("Exercise Type");
        ArrayList<String> equipments = (ArrayList<String>) user.getExercisePreference().get("Equipments");

        // Note that the 4 lines above may be refactored.

        // Loop in exercises and get the name if everything matches.
        for (Exercise e: exercises){
            if (e.getEquipmentNeeded().containsAll(equipments) &&
                e.getType().containsAll(exerciseType) &&
                e.getMajorMuscleExercised().containsAll(majorMuscle) &&
                e.getMinorMuscleExercised().containsAll(minorMuscle)){
                exerciseNameResults.add(e.getName());
            }
        }

        // Creating a result string.
        result = "Based on your input, here's a list of suggested exercises: " + exerciseNameResults;

    }

    @Override
    public String getResult() {
        return result;
    }
}
