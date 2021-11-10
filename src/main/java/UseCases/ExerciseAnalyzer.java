package UseCases;

import API.ExerciseAPI;
import Entities.Exercise;
import Entities.User;
import Constants.Constants;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Subclass of UserAnalyzer. Return exercises recommended to the user.
 */

public class ExerciseAnalyzer implements UserAnalyzer{
    private String result;

    public ExerciseAnalyzer(){}

    @Override
    public void analyze() {
        User user = UserManager.getCurrentUser();

        HashMap<String, String> user_preference = user.getExercisePreference();
        List<Exercise> exercises = ExerciseAPI.readFromExerciseCSV();
        ArrayList<Exercise> user_exercises = new ArrayList<Exercise>(); // May not be needed
        StringBuilder exercise_names = new StringBuilder();

        // add each exercise to user_exercises and exercise_names
        for(Exercise exercise: exercises){
            if (exercise_match(exercise, user_preference)) {
                user_exercises.add(exercise);
                exercise_names.append(addNewExercise(exercise));
            }
        }

        String intro =  Constants.DIVIDER +
                Constants.EXERCISE_INTRO1 + user.getUsername() + Constants.EXERCISE_INTRO2;

        if (user_exercises.isEmpty()){ // no exercise suggestions :(
            this.result = intro + Constants.NO_EXERCISES_FOUND + Constants.DIVIDER;
        } else { // return exercise suggestions
            this.result = intro + exercise_names +Constants.DIVIDER;
        }
    }

    /**
     * Helper method to create a new StringBuilder description for one new exercise to suggest to user.
     * @param exercise to create description from
     * @return description for new exercise
     */
    private StringBuilder addNewExercise(Exercise exercise) {
        StringBuilder new_exercise = new StringBuilder();
        new_exercise.append(Constants.TAB).append(exercise.getName()).
                append(Constants.EX_DESC_TYPE).append(exercise.getType()).
                append(Constants.EX_DESC_USES).append(exercise.getEquipmentNeeded().toString()).
                append(Constants.EX_DESC_MAJOR).append(exercise.getMajorMuscleExercised()).
                append(Constants.EX_DESC_MINOR).append(exercise.getMinorMuscleExercised()).
                append(Constants.EMPTY_LINE);
        return new_exercise;
    }

    /**
     *  A helper method used to determine if the exercise matches the user's exercise preferences
     */
    private boolean exercise_match(Exercise exercise, HashMap<String, String> user_preference){
        String majorMuscle =  user_preference.get("major muscle");
        String minorMuscle = user_preference.get("minor muscle");
        List<String> equipments = Arrays.asList(user_preference.get("equipment").split(","));
        boolean hasMajMuscle = exercise.getMajorMuscleExercised().contains(majorMuscle);
        boolean hasMinMuscle = exercise.getMinorMuscleExercised().contains(minorMuscle);
        boolean hasEquip = equipments.containsAll(exercise.getEquipmentNeeded());

        return (hasMinMuscle || hasMajMuscle) && hasEquip;
    }

    @Override
    public String getResult() {
        return this.result;
    }
}