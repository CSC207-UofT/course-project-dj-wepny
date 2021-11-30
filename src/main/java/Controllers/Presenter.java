package Controllers;

import Constants.*;
import UseCases.UserAnalyzer;

/**
 * This is a Presenter class that retrieves the results from the use cases, and then is called by
 * the console to return the result calculated from the different use cases
 */
public class Presenter {

    private final UserAnalyzer userAnalyzer;

    /**
     * Initiating a Presenter by declaring the type of analyzer it needs to retrieve results from
     *
     * @param analyzer an analyzer that is of type UserAnalyzer
     */
    public Presenter(UserAnalyzer analyzer) {
        this.userAnalyzer = analyzer;
    }


    /**
     * This method retrieves the output of one use case and instantiates it into one of the
     * private variable.
     *
     * @return returns the output calculated by the userAnalyzer
     */
    public String retrieveOutput() {
        return userAnalyzer.getResult();
    }

    public static void printWelcome(String username) {
        System.out.println(SystemConstants.WELCOME1 + username + SystemConstants.WELCOME2 +
                SystemConstants.MAIN_MENU);
    }

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

    public static void printUserIDMessage(String userID) {
        System.out.println( SystemConstants.ID_MESSAGE1 + userID + SystemConstants.ID_MESSAGE2);
    }

    public static void printIDPrompt() {
        System.out.println(SystemConstants.ID_PROMPT);

    }

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

    public static void printExistingUserWelcome(String name) {
        System.out.println(SystemConstants.WELCOME_EXISTING + name +
                SystemConstants.WELCOME2 + SystemConstants.EXISTING_USER_MENU);

    }

    public static void printNotEnoughInfoPrompt() {
        System.out.println(Exceptions.NOT_ENOUGH_INFO);
    }

    public static void printAskExisting() {
        System.out.println(SystemConstants.ASK_EXISTING);
    }

    public static void printReturnMenu() {
        System.out.println(SystemConstants.RETURN_MENU);

    }

    public static void printRestart() {
        System.out.println(SystemConstants.RESTART_PROGRAM);
    }

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

    public static void DiseasePrompt(String prompt) {
        switch (prompt) {
            case "start":
                System.out.println(DiseaseConstants.DISEASE_START);
                break;
            case "description":
                System.out.println(DiseaseConstants.SYMPTOMS_DESC);
                break;
        }
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
