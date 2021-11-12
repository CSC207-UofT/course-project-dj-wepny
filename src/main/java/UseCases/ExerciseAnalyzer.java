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

public class ExerciseAnalyzer implements UserAnalyzer {

    private String result;
    private User user;

    /**
     * Initiating a ExerciseAnalyzer with no parameter.
     */
    public ExerciseAnalyzer() {
    }

    /**
     * An overloading constructor that initiate a ExerciseAnalyzer which take user as its parameter.
     *
     * @param user The user that the ExerciseAnalyzer is analyzing for.
     */
    public ExerciseAnalyzer(User user) {
        this.user = user;
    }

    /**
     * This method calculate the user's estimated energy requirement (EER).
     */
    @Override
    public void analyze() {
        User user = UserManager.getCurrentUser();

        if (user == null) {
            user = this.user;
        }

        HashMap<String, String> user_preference = user.getExercisePreference();
        // read the exercises from CSV file
        List<Exercise> exercises = ExerciseAPI.readFromExerciseCSV();
        ArrayList<Exercise> user_exercises = new ArrayList<>();
        StringBuilder exercise_names = new StringBuilder();

        // add each exercise to user_exercises and exercise_names
        for (Exercise exercise : exercises) {
            if (exercise_match(exercise, user_preference)) {
                user_exercises.add(exercise);
                exercise_names.append(addNewExercise(exercise));
            }
        }

        String intro = Constants.DIVIDER +
                Constants.EXERCISE_INTRO1 + user.getUsername() + Constants.EXERCISE_INTRO2;

        if (user_exercises.isEmpty()) { // no exercise suggestions :(
            this.result = intro + Constants.NO_EXERCISES_FOUND + Constants.DIVIDER;
        } else { // return exercise suggestions
            this.result = intro + exercise_names + Constants.DIVIDER;
        }
    }

    /**
     * Helper method to create a new StringBuilder description for one new exercise to suggest to user.
     *
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
     * A helper method used to determine if the exercise matches the user's exercise preferences
     */
    private boolean exercise_match(Exercise exercise, HashMap<String, String> user_preference) {
        String majorMuscle = user_preference.get("major muscle");
        String minorMuscle = user_preference.get("minor muscle");
        List<String> equipments = Arrays.asList(user_preference.get("equipment").split("/"));

        // check is exercise contains the user's exercise preferences
        boolean hasMajMuscle = exercise.getMajorMuscleExercised().contains(majorMuscle);
        boolean hasMinMuscle = exercise.getMinorMuscleExercised().contains(minorMuscle);
        boolean hasEquip = equipments.containsAll(exercise.getEquipmentNeeded());

        return (hasMinMuscle || hasMajMuscle) && hasEquip;
    }

    /**
     * A getter method for the result.
     *
     * @return The potential disease for the user.
     */
    @Override
    public String getResult() {
        return this.result;
    }
}