package controllers;

import constants.*;
import usecases.UserAnalyzer;

import java.io.File;

/**
 * This is a Presenter class that retrieves the results from the use cases, and then is called by
 * the console to return the result calculated from the different use cases
 */
public class Presenter {

    /**
     * Abstract interface for the use cases that this presenter retrieves the results from.
     */
    private static UserAnalyzer userAnalyzer;

    /**
     * Initiating a Presenter by declaring the type of analyzer it needs to retrieve results from
     *
     * @param analyzer an analyzer that is of type UserAnalyzer
     */
    public Presenter(UserAnalyzer analyzer) {
        userAnalyzer = analyzer;
    }


    /**
     * This method retrieves the output of one use case and instantiates it into one of the
     * private variable.
     *
     * @return returns the output calculated by the userAnalyzer
     */
    public static String retrieveOutput() {
        return userAnalyzer.getResult();
    }

    /**
     * Prints a welcome message with the user's new ID.
     * @param userID to print
     * @return the ID message
     */
    public static String printUserIDMessage(String userID) {
        return SystemConstants.ID_MESSAGE1 + userID + SystemConstants.ID_MESSAGE2;
    }


    /**
     * Prints the file paths related to the GUI.
     * @param prompt to indicate which path
     * @return path
     */
    public static File printImgFile(String prompt) {
        switch (prompt) {

            case "bmi":
                return GUIFormatConstants.bmiGeneratorImgFile;
            case "disease":
                return GUIFormatConstants.diseaseAnalyzerImgFile;
            case "eer":
                return GUIFormatConstants.eerAnalyzerImgFile;
            case "login":
                return GUIFormatConstants.loginImgFile;
            case "meal":
                return GUIFormatConstants.mealPlanGeneratorImgFile;
            case "workout":
                return GUIFormatConstants.workoutAnalyzerImgFile;
            default: // intro
                return GUIFormatConstants.introImgFile;
        }
    }

    /**
     * Prints the file path for the user information
     * @return file path for UserInfo in String format.
     */
    public static String printUserFile() {
        return SystemConstants.USER_FILE;
    }

    /**
     * Prints a pathway not found error.
     * @return error message
     */
    public static String pathwayNotFound() { return Exceptions.PATHWAY_NOT_FOUND; }

    /**
     * Sets general text for GUI components.
     * @param prompt to set text to
     * @return message
     */
    public static String setTextButtons(String prompt) {
        switch (prompt) {
            case "return":
                return SystemConstants.RETURN_TO_MENU;
            case "enter":
                return SystemConstants.ENTER;
            case "invalid":
                return Exceptions.INVALID_SHORT;
            case "invalid2":
                return Exceptions.INVALID_INPUT;
            case "updated":
                return SystemConstants.UPDATED_SUCCESSFULLY;
            case "enterpin":
                return SystemConstants.ENTER_PIN;
        }
        return "";
    }

    /**
     * Asks the user for their activity level, or tells them their input is invalid.
     * @param prompt decides whether to print the menu or the invalid input message.
     */
    public static String ActivityLevelPrompt(String prompt) {
        switch (prompt) {
            case "menu":
                return EERConstants.ACTIVITY_MENU;
            case "1":
                return EERConstants.SED;
            case "2":
                return EERConstants.LOW;
            case "3":
                return EERConstants.MID;
            case "4":
                return EERConstants.HIGH;
            case "invalid":
                return Exceptions.INVALID_INPUT;
        }
        return "";
    }

    public static void ExercisePrompt(String prompt) {
        switch (prompt) {
            case "start":
                System.out.println(ExerciseConstants.EXERCISE_START +
                        ExerciseConstants.EXERCISE_MAJOR);
                break;
            case "major error":
                System.out.println(ExerciseConstants.EXERCISE_MAJOR_ERROR);
                break;
            case "minor":
                System.out.println(ExerciseConstants.EXERCISE_MINOR);
                break;
            case "minor error":
                System.out.println(ExerciseConstants.EXERCISE_MINOR_ERROR);
                break;
            case "equipment1":
                System.out.println(ExerciseConstants.EXERCISE_EQUIPMENT);
                break;
            case "equipment error 1":
                System.out.println(ExerciseConstants.EXERCISE_EQUIPMENT_ERROR_BG);
                break;
            case "equipment2":
                System.out.println(ExerciseConstants.EXERCISE_EQUIPMENT2);
                break;
            case "equipment error 2":
                System.out.println(ExerciseConstants.EXERCISE_EQUIPMENT_ERROR_AF);
                break;
            case "equipment3":
                System.out.println(ExerciseConstants.EXERCISE_EQUIPMENT3);
                break;
        }
    }

    public static String DiseasePrompt(String prompt) {
        switch (prompt) {
            case "start1":
                return DiseaseConstants.DISEASE_START1;
            case "start2":
                return DiseaseConstants.DISEASE_START2;
            case "description1":
                return DiseaseConstants.SYMPTOMS_DESC1;
            case "description2":
                return DiseaseConstants.SYMPTOMS_DESC2;
            case "example":
                return DiseaseConstants.SYMPTOMS_DESC_EX;
        }
        return null;
    }

    public static String MealPlanPrompt(String prompt) {
        switch (prompt) {
            case "welcome":
                return MealPlanConstants.MEALPLAN_WELCOME;
            case "low carb":
                return MealPlanConstants.LOWCARBS;
            case "low fat":
                return MealPlanConstants.LOWFAT;
            case "low sugar":
                return MealPlanConstants.LOWSUGAR;
            case "veg":
                return MealPlanConstants.VEG;
            case "num food":
                return MealPlanConstants.NUM_FOODS;
            case "intro":
                return MealPlanConstants.MEALPLAN_INTRO_GUI;
        }
        return "";
    }
}