//package system;
//
//import controllers.Presenter;
//import controllers.RunCommand;
//
//import java.util.*;
//
///**
// * This class contains all the helper function used both
// * by ExistingUserController and NewUserController
// */
//public class HelperConsole {
//
//    /**
//     * A helper method that prompts the user for their basic information.
//     * Returns an array of strings in the order of [name, age, gender].
//     * Note that this method is subject to change (Maybe ArrayList instead of Array).
//     *
//     * @param reader The scanner used for the user input.
//     * @return an array of strings of some basic information about the user.
//     */
//    public static String[] getBasicUserInfo(Scanner reader) {
////        System.out.println(Constants.NAME_PROMPT);
//        Presenter.printUserInfo("name");
//        String name = reader.nextLine();
//
////        System.out.println(Constants.GENDER_PROMPT);
//        Presenter.printUserInfo("gender");
//        String gender = reader.nextLine();
//
//        while (!gender.equals("M") && !gender.equals("F")) {
////            System.out.println(Constants.INVALID_INPUT + Constants.GENDER_PROMPT);
//            Presenter.printInvalidPrompt("gender");
//            gender = reader.nextLine();
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
//     * @param reader The scanner used for the user input.
//     * @return an array of strings of the user's height, weight, and age
//     */
//    public static String[] getPersonalUserInfo(Scanner reader) {
////        System.out.println(Constants.HEIGHT_PROMPT);
//        Presenter.printUserInfo("height");
//        String height = reader.nextLine();
//
//        // checking to make sure the height input is a number between 0 m to 2.5 m
//        while (isNotNum(height) || Float.parseFloat(height) <= 0 || Float.parseFloat(height) >= 2.5) {
////            System.out.println(Constants.INVALID_INPUT + Constants.HEIGHT_PROMPT);
//            Presenter.printInvalidPrompt("height");
//            height = reader.nextLine();
//        }
//
////        System.out.println(Constants.WEIGHT_PROMPT);
//        Presenter.printUserInfo("weight");
//        String weight = reader.nextLine();
//
//        // checking to make sure the weight input is a number larger than 0
//        while (isNotNum(weight) || Float.parseFloat(weight) <= 0) {
////            System.out.println(Constants.INVALID_INPUT + Constants.WEIGHT_PROMPT);
//            Presenter.printInvalidPrompt("weight");
//            weight = reader.nextLine();
//        }
//
////        System.out.println(Constants.AGE_PROMPT);
//        Presenter.printUserInfo("age");
//        String age = reader.nextLine();
//
//        // checking to make sure the age input is a number larger or equal to 0
//        while (isNotNum(age) || Integer.parseInt(age) < 0) {
////            System.out.println(Constants.INVALID_INPUT + Constants.AGE_PROMPT);
//            Presenter.printInvalidPrompt("age");
//            age = reader.nextLine();
//        }
//
//        return new String[]{height, weight, age};
//    }
//
//    /**
//     * Checks if user already exists in the system.
//     *
//     * @param reader reads user input
//     * @return true if the user identify themselves as an existing user
//     */
//    public static boolean checkExisting(Scanner reader) {
////        System.out.println(Constants.ASK_EXISTING);
//        Presenter.printAskExisting();
//        String exists = reader.nextLine();
//
//        // making sure the input is equal to Y or N
//        while (!exists.equals("Y") && !exists.equals("N")) {
////            System.out.println(Constants.INVALID_INPUT + Constants.ASK_EXISTING);
//            Presenter.printInvalidPrompt("existing");
//            exists = reader.nextLine();
//        }
//        return exists.equals("Y");
//    }
//
//    /**
//     * Returns true if the user wants to log out.
//     *
//     * @param reader reads user info
//     * @return True if the user wants to log out
//     */
//    public static boolean logOut(Scanner reader) {
////        System.out.println(Constants.RETURN_MENU);
//        Presenter.printReturnMenu();
//        String logOut = reader.nextLine();
//
//        while (!logOut.equals("Y") && !logOut.equals("N")) {
////            System.out.println(Constants.INVALID_INPUT + Constants.RETURN_MENU);
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
////        System.out.println(Constants.RESTART_PROGRAM);
//        Presenter.printRestart();
//        String restart = reader.nextLine();
//
//        while (!restart.equals("N") & !restart.equals("Y")) {
////            System.out.println(Constants.INVALID_INPUT + Constants.RESTART_PROGRAM);
//            Presenter.printInvalidPrompt("restart");
//            restart = reader.nextLine();
//        }
//        return restart.equals("Y");
//    }
//
//    /**
//     * Helper function that checks to make sure that the command is within the valid range
//     *
//     * @param input  the input that needs to be checked
//     * @param reader reads in the user's new input if the previous one is not valid
//     * @param type   making sure the range is within the specified type
//     * @return the first valid input the user inputs
//     */
//    public static String checkCommand(String input, Scanner reader, int type) {
//        boolean check = true;
//        while (check) {
//            if (isNotNum(input) || notInRange(Integer.parseInt(input), type)) {
////                System.out.println(Constants.INVALID_INPUT);
//                Presenter.printInvalidPrompt("invalid");
//                input = reader.nextLine();
//            } else {
//                check = false;
//            }
//        }
//        return input;
//    }
//
//
//    /**
//     * Checks if the input is a number of not
//     *
//     * @param input the information that needs to be checked
//     * @return true if the input is not a number
//     */
//    public static boolean isNotNum(String input) {
//        try {
//            Float.parseFloat(input);
//
//        } catch (Exception e) {
//            return true;
//        }
//        return false;
//    }
//
//    /**
//     * Helper function that checks the command the user inputted is correct
//     * and within the range wanted
//     *
//     * @param i    the command the user inputs
//     * @param type specifies the range we want the number i to be in
//     * @return true if int 'i' is within the desired range
//     */
//    public static boolean notInRange(int i, int type) {
//        if (type == 1) {
//            return i <= 0 || i >= 6;
//        } else if (type == 2) {
//            return i <= 0 || i >= 7;
//        } else {
//            return i <= 0 || i >= 5;
//        }
//    }
//
//    /**
//     * Helper function that checks if the user has the wanted information stored in its
//     * object
//     *
//     * @param command specifies which type of information we are
//     *                checking for in the user object
//     * @return true if the information is empty in the user object
//     */
//    public static boolean noInfoFound(int command) {
//        RunCommand commandExecutor = new RunCommand();
//        if (command == 2) {
//            HashMap personalData = (HashMap) commandExecutor.retrieveUser("personal data");
//            return !personalData.containsKey("activity level");
//        }
//        if (command == 3) {
//            HashMap exerciseData = (HashMap) commandExecutor.retrieveUser("exercise");
//            return exerciseData.isEmpty();
//        }
//        if (command == 4) {
//            List riskFactors = (ArrayList) commandExecutor.retrieveUser("risk");
//            return riskFactors.isEmpty();
//        }
//        if (command == 5) {
//            HashMap foodData = (HashMap) commandExecutor.retrieveUser("food");
//            return foodData.isEmpty();
//        } else {
//            return true;
//        }
//    }
//
//    /**
//     * Add the user to existingUser
//     */
//    public static void addToExisting() {
//        RunCommand command = new RunCommand();
//        if (command.checkExistingUsers()) {
//            command.setToExisting();
//        }
//    }
//}
