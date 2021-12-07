package consoleforgui;

import constants.EERConstants;
import controllers.Presenter;
import controllers.RunCommand;
import controllers.UserController;

import java.lang.reflect.Array;
import java.util.*;

/**
 * This class contains all the helper function used both
 * by ExistingUserController and NewUserController
 */
public class HelperConsole {
// TODO: several methods are never used (or the methods calling them are never used), refactor if time allows.

    /**
     * Checks that the ID input belongs to a valid user.
     * @param input of the user
     * @return whether the ID is valid or not.
     */
    public static boolean validID(String input){
        return !HelperConsole.isNotNum(input) && UserController.checkUserExist(input);
    }

    /**
     * Creates a new user.
     * @param basicUserInfo of the new user
     * @param personalUserInfo of the new user
     */
    public static void createUser(String[] basicUserInfo, String[] personalUserInfo) {
        RunCommand.createUser(basicUserInfo, personalUserInfo);
    }

    // check invalid gender input in GUI
    public static boolean validGender(String gender) {
        return gender.equals("M") || gender.equals("F");
    }

    // check invalid height input in GUI
    public static boolean validHeight(String height) {
        return !isNotNum(height) && !(Float.parseFloat(height) <= 0) && !(Float.parseFloat(height) >= 2.5);
    }

    // check invalid weight input in GUI
    public static boolean validWeight(String weight) {
        return !isNotNum(weight) && !(Float.parseFloat(weight) <= 0);
    }

    // check invalid age input in GUI
    public static boolean validAge(String age) {
        return !isNotNum(age) && Integer.parseInt(age) >= 0;
    }

    /**
     * Checks if the input is a number of not
     *
     * @param input the information that needs to be checked
     * @return true if the input is not a number
     */
    public static boolean isNotNum(String input) {
        try {
            Float.parseFloat(input);

        } catch (Exception e) {
            return true;
        }
        return false;
    }

    /**
     * Helper function that checks the command the user inputted is correct
     * and within the range wanted
     *
     * @param i    the command the user inputs
     * @param type specifies the range we want the number i to be in
     * @return true if int 'i' is within the desired range
     */
    public static boolean notInRange(int i, int type) {
        if (type == 1) {
            return i <= 0 || i >= 6;
        } else if (type == 2) {
            return i <= 0 || i >= 7;
        } else {
            return i <= 0 || i >= 5;
        }
    }

    /**
     * Helper function that checks if the user has the wanted information stored in its
     * object
     *
     * @param command specifies which type of information we are
     *                checking for in the user object
     * @return true if the information is empty in the user object
     */
    public static boolean noInfoFound(int command) {
        RunCommand commandExecutor = new RunCommand();
        if (command == 2) {
            HashMap personalData = (HashMap) commandExecutor.retrieveUser("personal data");
            return !personalData.containsKey("activity level");
        }
        if (command == 3) {
            HashMap exerciseData = (HashMap) commandExecutor.retrieveUser("exercise");
            return exerciseData.isEmpty();
        }
        if (command == 4) {
            List riskFactors = (ArrayList) commandExecutor.retrieveUser("risk");
            return riskFactors.isEmpty();
        }
        if (command == 5) {
            HashMap foodData = (HashMap) commandExecutor.retrieveUser("food");
            return foodData.isEmpty();
        } else {
            return true;
        }
    }


    /**
     * Add the user to existingUser
     */
    public static void addToExisting() {
        RunCommand command = new RunCommand();
        if (command.checkExistingUsers()) {
            command.setToExisting();
        }
    }

    /**
     * Checks whether the symptoms the user inputted is correct
     *
     * @param symptomList      the list of symptoms the user inputs before formatting
     * @param promptedSymptoms the list of prompted symptoms before formatting
     * @return true if user input is a valid input
     */
    public static boolean checkSymptomInput(String symptomList, String promptedSymptoms) {
        List<String> newSymptomList = convertInputToList(symptomList);
        List<String> newPromptedSymptoms = convertInputToList(promptedSymptoms);
        for (String symptom : newSymptomList) {
            if (!newPromptedSymptoms.contains(symptom)) {
                return false;
            }
        }
        return true;

    }

    /**
     * converts a String into a usable form of List array
     *
     * @param inputSymptoms    a String that contains the information that the user inputs
     * @return an list of strings of the user's input
     */
    public static List<String> convertInputToList(String inputSymptoms){
        List<String> newSymptoms = new ArrayList<>();
        String symptoms = inputSymptoms.replaceAll("[\\[\\](){}]", "");
        String[] symptomsList = symptoms.split(",");
        List<String> finalSymptomsList = new ArrayList<>();
        // format the spaces
        for(String symptom: symptomsList) {
            symptom = symptom.trim();
            finalSymptomsList.add(symptom);
        }
        newSymptoms.addAll(finalSymptomsList);
        return newSymptoms;
    }

    /**
     * Helper method that returns a String that corresponds to the user's
     * exercise preference based on the buttons they clicked
     * @param userInput a string that corresponds to which button the user pressed
     * @return corresponding activity level
     */
    public static String exerciseLevel(String userInput){
        String level = "";
        switch (userInput) {
            case "1":
                level = EERConstants.SED;
                break;
            case "2":
                level = EERConstants.LOW;
                break;
            case "3":
                level = EERConstants.MID;
                break;
            case "4":
                level = EERConstants.HIGH;
                break;
        }
        return level;
    }
}
