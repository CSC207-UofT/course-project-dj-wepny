package Controllers;

import Constants.Constants;
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
        System.out.println(Constants.WELCOME1 + username + Constants.WELCOME2 +
                Constants.MAIN_MENU);
    }

    public static void printUserInfo(String infoType) {
        switch (infoType) {
            case "basic":
                System.out.println(Constants.BASIC_INFO);
                break;
            case "personal":
                System.out.println(Constants.PERSONAL_INFO);
                break;
            case "name":
                System.out.println(Constants.NAME_PROMPT);
                break;
            case "gender":
                System.out.println(Constants.GENDER_PROMPT);
                break;
            case "height":
                System.out.println(Constants.HEIGHT_PROMPT);
                break;
            case "weight":
                System.out.println(Constants.WEIGHT_PROMPT);
                break;
            case "age":
                System.out.println(Constants.AGE_PROMPT);
                break;
        }
    }

    public static void printUserIDMessage(String userID) {
        System.out.println( Constants.ID_MESSAGE1 + userID + Constants.ID_MESSAGE2);
    }

    public static void printIDPrompt() {
        System.out.println(Constants.ID_PROMPT);

    }

    public static void printInvalidPrompt(String infoType) {
        switch (infoType) {
            case "invalid":
                System.out.println(Constants.INVALID_INPUT);
                break;
            case "ID":
                System.out.println(Constants.INVALID_INPUT + Constants.ID_PROMPT);
                break;
            case "gender":
                System.out.println(Constants.INVALID_INPUT + Constants.GENDER_PROMPT);
                break;
            case "height":
                System.out.println(Constants.INVALID_INPUT + Constants.HEIGHT_PROMPT);
                break;
            case "weight":
                System.out.println(Constants.INVALID_INPUT + Constants.WEIGHT_PROMPT);
                break;
            case "age":
                System.out.println(Constants.INVALID_INPUT + Constants.AGE_PROMPT);
                break;
            case "existing":
                System.out.println(Constants.INVALID_INPUT + Constants.ASK_EXISTING);
                break;
            case "return":
                System.out.println(Constants.INVALID_INPUT + Constants.RETURN_MENU);
                break;
            case "restart":
                System.out.println(Constants.INVALID_INPUT + Constants.RESTART_PROGRAM);
                break;
        }
    }

    public static void printExistingUserWelcome(String name) {
        System.out.println(Constants.WELCOME_EXISTING + name +
                Constants.WELCOME2 + Constants.EXISTING_USER_MENU);

    }

    public static void printNotEnoughInfoPrompt() {
        System.out.println(Constants.NOT_ENOUGH_INFO);
    }

    public static void printAskExisting() {
        System.out.println(Constants.ASK_EXISTING);
    }

    public static void printReturnMenu() {
        System.out.println(Constants.RETURN_MENU);

    }

    public static void printRestart() {
        System.out.println(Constants.RESTART_PROGRAM);
    }

    public static void ActivityLevelPrompt(String prompt) {
        switch (prompt) {
            case "menu":
                System.out.println(Constants.ACTIVITY_MENU);
                break;
            case "invalid":
                System.out.println(Constants.INVALID_INPUT);
                break;
        }
    }

    public static void ExercisePrompt(String prompt) {
        switch (prompt) {
            case "start":
                System.out.println(Constants.EXERCISE_START + Constants.EXERCISE_MAJOR);
                break;
            case "major error":
                System.out.println(Constants.EXERCISE_MAJOR_ERROR);
                break;
            case "minor":
                System.out.println(Constants.EXERCISE_MINOR);
                break;
            case "minor error":
                System.out.println(Constants.EXERCISE_MINOR_ERROR);
                break;
            case "equipment1":
                System.out.println(Constants.EXERCISE_EQUIPMENT);
                break;
            case "equipment error 1":
                System.out.println(Constants.EXERCISE_EQUIPMENT_ERROR_BG);
                break;
            case "equipment2":
                System.out.println(Constants.EXERCISE_EQUIPMENT2);
                break;
            case "equipment error 2":
                System.out.println(Constants.EXERCISE_EQUIPMENT_ERROR_AF);
                break;
            case "equipment3":
                System.out.println(Constants.EXERCISE_EQUIPMENT3);
                break;
        }
    }

    public static void DiseasePrompt(String prompt) {
        switch (prompt) {
            case "start":
                System.out.println(Constants.DISEASE_START);
                break;
            case "description":
                System.out.println(Constants.SYMPTOMS_DESC);
                break;
        }
    }

    public static void printDiseaseOutput(String output) {
        System.out.println(output);
    }

    public static void FoodPrompt(String prompt) {
        switch (prompt) {
            case "welcome":
                System.out.println(Constants.MEALPLAN_WELCOME);
                break;
            case "low carb":
                System.out.println(Constants.LOWCARBS);
                break;
            case "low fat":
                System.out.println(Constants.LOWFAT);
                break;
            case "low sugar":
                System.out.println(Constants.LOWSUGAR);
                break;
            case "veg":
                System.out.println(Constants.VEG);
                break;
            case "num food":
                System.out.println(Constants.NUM_FOODS);
                break;
        }
    }

    public static void updateUser(String prompt) {
        switch (prompt) {
            case "update user":
                System.out.println(Constants.USER_UPDATE);
                break;
            case "change name":
                System.out.println(Constants.CHANGE_USERNAME);
                break;
            case "update name":
                System.out.println(Constants.UPDATED_USERNAME);
                break;
            case "change height":
                System.out.println(Constants.CHANGE_HEIGHT);
                break;
            case "update height":
                System.out.println(Constants.UPDATED_HEIGHT);
                break;
            case "change weight":
                System.out.println(Constants.CHANGE_WEIGHT);
                break;
            case "update weight":
                System.out.println(Constants.UPDATED_WEIGHT);
                break;
            case "change age":
                System.out.println(Constants.CHANGE_AGE);
                break;
            case "update age":
                System.out.println(Constants.UPDATED_AGE);
                break;
            case "change gender":
                System.out.println(Constants.CHANGE_GENDER);
                break;
            case "update gender":
                System.out.println(Constants.UPDATED_GENDER);
                break;
        }
    }
}
