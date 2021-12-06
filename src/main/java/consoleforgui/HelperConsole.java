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

//    /**
//     * A helper method that prompts the user for their basic information.
//     * Returns an array of strings in the order of [name, age, gender].
//     * Note that this method is subject to change (Maybe ArrayList instead of Array).
//     *
//     * @param input The scanner used for the user input.
//     * @return an array of strings of some basic information about the user.
//     */
//    public static String[] getBasicUserInfo(String input) {
//        Presenter.printUserInfo("name");
//        String name = input;
//
//        Presenter.printUserInfo("gender");
//        String gender = input;
//
//        while (!gender.equals("M") && !gender.equals("F")) {
//            Presenter.printInvalidPrompt("gender");
//            gender = input;
//        }
//
//        return new String[]{name, gender};
//    }
//
//
//    /**
//     * A helper method that prompts the user for their personal data such as
//     * height, weight, etc.
//     * Returns an array of strings in the order of [height, weight].
//     * Note that the items in this array will be stored in the information entity class,
//     * and the method is subject to change (Maybe ArrayList instead of Array).
//     *
//     * @param input The scanner used for the user input.
//     * @return an array of strings of the user's height, weight, and age
//     */
//    public static String[] getPersonalUserInfo(String input) {
//        Presenter.printUserInfo("height");
//        String height = input;
//
//        // checking to make sure the height input is a number between 0 m to 2.5 m
//        while (isNotNum(height) || Float.parseFloat(height) <= 0 || Float.parseFloat(height) >= 2.5) {
//            Presenter.printInvalidPrompt("height");
//            height = input;
//        }
//
//        Presenter.printUserInfo("weight");
//        String weight = input;
//
//        // checking to make sure the weight input is a number larger than 0
//        while (isNotNum(weight) || Float.parseFloat(weight) <= 0) {
//            Presenter.printInvalidPrompt("weight");
//            weight = input;
//        }
//
//        Presenter.printUserInfo("age");
//        String age = input;
//
//        // checking to make sure the age input is a number larger or equal to 0
//        while (isNotNum(age) || Integer.parseInt(age) < 0) {
//            Presenter.printInvalidPrompt("age");
//            age = input;
//        }
//
//        return new String[]{height, weight, age};
//    }

    // check invalid gender input in GUI
    public static boolean validGender(String gender) {
        if (!gender.equals("M") && !gender.equals("F")) {
            return false;
        }
        return true;
    }

    // check invalid height input in GUI
    public static boolean validHeight(String height) {
        if (isNotNum(height) || Float.parseFloat(height) <= 0 || Float.parseFloat(height) >= 2.5) {
            return false;
        }
        return true;
    }

    // check invalid weight input in GUI
    public static boolean validWeight(String weight) {
        if (isNotNum(weight) || Float.parseFloat(weight) <= 0) {
            return false;
        }
        return true;
    }

    // check invalid age input in GUI
    public static boolean validAge(String age) {
        if (isNotNum(age) || Integer.parseInt(age) < 0) {
            return false;
        }
        return true;
    }

//    /**
//     * Returns true if the user wants to log out.
//     *
//     * @param reader reads user info
//     * @return True if the user wants to log out
//     */
//    public static boolean logOut(Scanner reader) {
//        Presenter.printReturnMenu();
//        String logOut = reader.nextLine();
//
//        while (!logOut.equals("Y") && !logOut.equals("N")) {
//            Presenter.printInvalidPrompt("return");
//            logOut = reader.nextLine();
//        }
//        return logOut.equals("N");
//    }
//
//    /**
//     * return true if the user want to return to their main menu and restart
//     *
//     * @param reader reads user info
//     * @return true if the user would like to return to the main menu
//     */
//    public static boolean reStart(Scanner reader) {
//        Presenter.printRestart();
//        String restart = reader.nextLine();
//
//        while (!restart.equals("N") & !restart.equals("Y")) {
//            Presenter.printInvalidPrompt("restart");
//            restart = reader.nextLine();
//        }
//        return restart.equals("Y");
//    }

    /**
     * Helper function that checks to make sure that the command is within the valid range
     *
     * @param input  the input that needs to be checked
     * @param reader reads in the user's new input if the previous one is not valid
     * @param type   making sure the range is within the specified type
     * @return the first valid input the user inputs
     */
//    public static String checkCommand(String input, Scanner reader, int type) {
//        boolean check = true;
//        while (check) {
//            if (isNotNum(input) || notInRange(Integer.parseInt(input), type)) {
//                Presenter.printInvalidPrompt("invalid");
//                input = reader.nextLine();
//            } else {
//                check = false;
//            }
//        }
//        return input;
//    }


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
            ArrayList riskFactors = (ArrayList) commandExecutor.retrieveUser("risk");
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
        ArrayList<String> newSymptomList = convertInputToList(symptomList);
        ArrayList<String> newPromptedSymptoms = convertInputToList(promptedSymptoms);
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
     * @return an ArrayList of strings of the user's input
     */
    public static ArrayList<String> convertInputToList(String inputSymptoms){
        ArrayList<String> newSymptoms = new ArrayList<String>();
        String symptoms = inputSymptoms.replaceAll("[\\[\\](){}]", "");
        String[] symptomsList = symptoms.split(",");
        List<String> finalSymptomsList = new ArrayList<String>();
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
