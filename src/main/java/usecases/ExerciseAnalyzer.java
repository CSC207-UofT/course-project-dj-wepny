package usecases;

import api.ExerciseAPI;
import entities.IExercise;
import entities.IUser;
import constants.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Subclass of UserAnalyzer. Return exercises recommended to the user.
 */

public class ExerciseAnalyzer implements UserAnalyzer {

    private String result;
    private IUser user;

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
    public ExerciseAnalyzer(IUser user){
        this.user = user;
    }

    /**
     * This method calculate the user's estimated energy requirement (EER).
     */
    @Override
    public void analyze() {
        IUser user = UserManager.getCurrentUser();

        if (user == null) {
            user = this.user;
        }

        HashMap<String, String> user_preference = user.getExercisePreference();
        // read the exercises from CSV file
        List<IExercise> exercises = ExerciseAPI.readFromExerciseCSV();
        List<IExercise> user_exercises = new ArrayList<>();
        StringBuilder exercise_names = new StringBuilder();

        // add each exercise to user_exercises and exercise_names
        for (IExercise exercise : exercises) {
            if (exercise_match(exercise, user_preference)) {
                user_exercises.add(exercise);
                exercise_names.append(addNewExercise(exercise));
            }
        }

        String intro = SystemConstants.DIVIDER +
                ExerciseConstants.EXERCISE_INTRO1 + user.getUsername() + ExerciseConstants.EXERCISE_INTRO2;

        if (user_exercises.isEmpty()) { // no exercise suggestions :(
            this.result = intro + Exceptions.NO_EXERCISES_FOUND + SystemConstants.DIVIDER;
        } else { // return exercise suggestions
            this.result = intro + exercise_names + SystemConstants.DIVIDER;
        }
    }

    /**
     * Helper method to create a new StringBuilder description for one new exercise to suggest to user.
     *
     * @param exercise to create description from
     * @return description for new exercise
     */
    private StringBuilder addNewExercise(IExercise exercise) {
        StringBuilder new_exercise = new StringBuilder();
        new_exercise.append(ExerciseConstants.TAB).append(exercise.getName()).
                append(ExerciseConstants.EX_DESC_TYPE).append(exercise.getType()).
                append(ExerciseConstants.EX_DESC_USES).append(exercise.getEquipmentNeeded().toString()).
                append(ExerciseConstants.EX_DESC_MAJOR).append(exercise.getMajorMuscleExercised()).
                append(ExerciseConstants.EX_DESC_MINOR).append(exercise.getMinorMuscleExercised()).
                append(SystemConstants.EMPTY_LINE);
        return new_exercise;
    }

    /**
     * A helper method used to determine if the exercise matches the user's exercise preferences
     */
    private boolean exercise_match(IExercise exercise, HashMap<String, String> user_preference) {
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