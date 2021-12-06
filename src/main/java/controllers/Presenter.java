package controllers;

import constants.*;
import usecases.UserAnalyzer;

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
     * Prints a welcome message for the user.
     * @param username of the user
     */
    public static void printWelcome(String username) {
        System.out.println(SystemConstants.WELCOME1 + username + SystemConstants.WELCOME2 +
                SystemConstants.MAIN_MENU);
    }

    /**
     * Prints the information of the user.
     * @param infoType what information of the user to print.
     */
    public static void printUserInfo(String infoType) {
        switch (infoType) {
            case "basic":
                System.out.println(SystemConstants.BASIC_INFO);
                break;
            case "personal":
                System.out.println(SystemConstants.PERSONAL_INFO);
                break;
            case "name":
                System.out.println(SystemConstants.NAME_PROMPT);
                break;
            case "gender":
                System.out.println(SystemConstants.GENDER_PROMPT);
                break;
            case "height":
                System.out.println(SystemConstants.HEIGHT_PROMPT);
                break;
            case "weight":
                System.out.println(SystemConstants.WEIGHT_PROMPT);
                break;
            case "age":
                System.out.println(SystemConstants.AGE_PROMPT);
                break;
        }
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
     * Asks the user to enter their ID.
     */
    public static void printIDPrompt() {
        System.out.println(SystemConstants.ID_PROMPT);

    }

    /**
     * Tells the user their input is invalid.
     * @param infoType of input the user didn't enter correctly.
     */
    public static void printInvalidPrompt(String infoType) {
        switch (infoType) {
            case "invalid":
                System.out.println(Exceptions.INVALID_INPUT);
                break;
            case "ID":
                System.out.println(Exceptions.INVALID_INPUT + SystemConstants.ID_PROMPT);
                break;
            case "gender":
                System.out.println(Exceptions.INVALID_INPUT + SystemConstants.GENDER_PROMPT);
                break;
            case "height":
                System.out.println(Exceptions.INVALID_INPUT + SystemConstants.HEIGHT_PROMPT);
                break;
            case "weight":
                System.out.println(Exceptions.INVALID_INPUT + SystemConstants.WEIGHT_PROMPT);
                break;
            case "age":
                System.out.println(Exceptions.INVALID_INPUT + SystemConstants.AGE_PROMPT);
                break;
            case "existing":
                System.out.println(Exceptions.INVALID_INPUT + SystemConstants.ASK_EXISTING);
                break;
            case "return":
                System.out.println(Exceptions.INVALID_INPUT + SystemConstants.RETURN_MENU);
                break;
            case "restart":
                System.out.println(Exceptions.INVALID_INPUT + SystemConstants.RESTART_PROGRAM);
                break;
        }
    }

    /**
     * Prints a welcome message for a returning user.
     * @param name of the user.
     */
    public static void printExistingUserWelcome(String name) {
        System.out.println(SystemConstants.WELCOME_EXISTING + name +
                SystemConstants.WELCOME2 + SystemConstants.EXISTING_USER_MENU);

    }

    /**
     * Tells the user there's not enough information, and asks them to enter their information.
     */
    public static void printNotEnoughInfoPrompt() {
        System.out.println(Exceptions.NOT_ENOUGH_INFO);
    }

    /**
     * Asks the user if they're an existing user.
     */
    public static void printAskExisting() {
        System.out.println(SystemConstants.ASK_EXISTING);
    }

    /**
     * Asks the user if they want to go back to the main menu.
     */
    public static void printReturnMenu() {
        System.out.println(SystemConstants.RETURN_MENU);

    }

    /**
     * Asks the user if they want to exit the program.
     */
    public static void printRestart() {
        System.out.println(SystemConstants.RESTART_PROGRAM);
    }

    /**
     * Asks the user for their activity level, or tells them their input is invalid.
     * @param prompt decides whether to print the menu or the invalid input message.
     */
    public static void ActivityLevelPrompt(String prompt) {
        switch (prompt) {
            case "menu":
                System.out.println(SystemConstants.ACTIVITY_MENU);
                break;
            case "invalid":
                System.out.println(Exceptions.INVALID_INPUT);
                break;
        }
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
//              System.out.println(DiseaseConstants.DISEASE_START1);
                return DiseaseConstants.DISEASE_START1;
            case "start2":
//              System.out.println(DiseaseConstants.DISEASE_START2);
                return DiseaseConstants.DISEASE_START2;
            case "description1":
//              System.out.println(DiseaseConstants.SYMPTOMS_DESC1);
                return DiseaseConstants.SYMPTOMS_DESC1;
            case "description2":
//                System.out.println(DiseaseConstants.SYMPTOMS_DESC2);
                return DiseaseConstants.SYMPTOMS_DESC2;
            case "example":
//                System.out.println(DiseaseConstants.SYMPTOMS_DESC_EX);
                return DiseaseConstants.SYMPTOMS_DESC_EX;
        }
        return null;
    }

    public static void printDiseaseOutput(String output) {
        System.out.println(output);
    }

    public static void FoodPrompt(String prompt) {
        switch (prompt) {
            case "welcome":
                System.out.println(MealPlanConstants.MEALPLAN_WELCOME);
                break;
            case "low carb":
                System.out.println(MealPlanConstants.LOWCARBS);
                break;
            case "low fat":
                System.out.println(MealPlanConstants.LOWFAT);
                break;
            case "low sugar":
                System.out.println(MealPlanConstants.LOWSUGAR);
                break;
            case "veg":
                System.out.println(MealPlanConstants.VEG);
                break;
            case "num food":
                System.out.println(MealPlanConstants.NUM_FOODS);
                break;
        }
    }

    public static void updateUser(String prompt) {
        switch (prompt) {
            case "update user":
                System.out.println(SystemConstants.USER_UPDATE);
                break;
            case "change name":
                System.out.println(SystemConstants.CHANGE_USERNAME);
                break;
            case "update name":
                System.out.println(SystemConstants.UPDATED_USERNAME);
                break;
            case "change height":
                System.out.println(SystemConstants.CHANGE_HEIGHT);
                break;
            case "update height":
                System.out.println(SystemConstants.UPDATED_HEIGHT);
                break;
            case "change weight":
                System.out.println(SystemConstants.CHANGE_WEIGHT);
                break;
            case "update weight":
                System.out.println(SystemConstants.UPDATED_WEIGHT);
                break;
            case "change age":
                System.out.println(SystemConstants.CHANGE_AGE);
                break;
            case "update age":
                System.out.println(SystemConstants.UPDATED_AGE);
                break;
            case "change gender":
                System.out.println(SystemConstants.CHANGE_GENDER);
                break;
            case "update gender":
                System.out.println(SystemConstants.UPDATED_GENDER);
                break;
        }
    }
}
