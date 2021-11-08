package System;

import Controllers.Presenter;
import Controllers.RunCommand;
import UseCases.UserManager;
import Constants.Constants;

import java.util.*;

/**
 * This class interact with the users and receives their input, then it sends
 * the inputs to the controller class.
 */


public class Console {

    private final static int[] COMMAND = {1, 2, 3, 4, 5};

    /**
     * A helper method that prompts the user for their basic information.
     * Returns an array of strings in the order of [name, age, gender].
     * Note that this method is subject to change (Maybe ArrayList instead of Array).
     *
     * @param reader The scanner used for the user input.
     * @return an array of strings of some basic information about the user.
     */
    private static String[] getBasicUserInfo(Scanner reader) {
        System.out.println(Constants.NAME_PROMPT);
        String name = reader.nextLine();

        System.out.println(Constants.GENDER_PROMPT);
        String gender = reader.nextLine();

        while (!gender.equals("M") && !gender.equals("F")) {
            System.out.println(Constants.INVALID_INPUT + Constants.GENDER_PROMPT);
            gender = reader.nextLine();
        }

        return new String[]{name, gender};
    }


    /**
     * A helper method that prompts the user for their personal data such as
     * height, weight, etc.
     * Returns an array of strings in the order of [height, weight].
     * Note that the items in this array will be stored in the information entity class,
     * and the method is subject to change (Maybe ArrayList instead of Array).
     *
     * @param reader The scanner used for the user input.
     * @return an array of strings of some personal data of the user.
     */
    private static String[] getPersonalUserInfo(Scanner reader) {
        System.out.println(Constants.HEIGHT_PROMPT);
        String height = reader.nextLine();


        while (Float.parseFloat(height) <= 0) {
            System.out.println(Constants.INVALID_INPUT + Constants.HEIGHT_PROMPT);
            height = reader.nextLine();
        }

        System.out.println(Constants.WEIGHT_PROMPT);
        String weight = reader.nextLine();
        while (Float.parseFloat(weight) <= 0) {
            System.out.println(Constants.INVALID_INPUT + Constants.WEIGHT_PROMPT);
            weight = reader.nextLine();
        }

        System.out.println(Constants.AGE_PROMPT);
        String age = reader.nextLine();
        while (Integer.parseInt(age) < 0) {
            System.out.println(Constants.INVALID_INPUT + Constants.AGE_PROMPT);
            age = reader.nextLine();
        }

        return new String[]{height, weight, age};
    }

    /**
     * Checks if user already exists in the system.
     *
     * @param reader reads user input
     * @return whether user exists already or not.
     */
    public static boolean checkExisting(Scanner reader) {
        System.out.println(Constants.ASK_EXISTING);
        String exists = reader.nextLine();

        while (!exists.equals("Y") && !exists.equals("N")) {
            System.out.println(Constants.INVALID_INPUT + Constants.ASK_EXISTING);
            exists = reader.nextLine();
        }

        return exists.equals("Y");
    }

    public static void gatherInfo(Scanner reader) throws Exception {
        System.out.println(Constants.BASIC_INFO);
        String[] basicUserInfo = getBasicUserInfo(reader);
        System.out.println(Constants.PERSONAL_INFO);
        String[] personalUserInfo = getPersonalUserInfo(reader);

        RunCommand.createUser(basicUserInfo, personalUserInfo);
        RunCommand infoGetter = new RunCommand();
        System.out.println( Constants.ID_MESSAGE1 + infoGetter.retrieveUser("id") + Constants.ID_MESSAGE2);
    }

    public static String NewUserMenu(Scanner reader) throws Exception {
        RunCommand infoGetter = new RunCommand();
        System.out.println(Constants.WELCOME1 + infoGetter.retrieveUser("name") + Constants.WELCOME2 +
                Constants.MAIN_MENU);

        String input = reader.nextLine();

        // checking if the command is an integer between 1 and 5
        input = checkCommand(input, reader, 1);

        int command = Integer.parseInt(input);

        RunCommand commandExecutor = new RunCommand(command);
        switch (command) {
            case 2:
                String level = activityLevel(reader);
                commandExecutor.addInfo(level, command);
                break;
            case 3:
                commandExecutor.addInfo(exercisePreference(reader), command);
                break;
            case 4:
                return diseaseList(reader, commandExecutor);
        }

        commandExecutor.executeCommand();

        Presenter analyze_results = new Presenter(commandExecutor.getAnalyzer());
        return analyze_results.retrieveOutput();
    }

    public static int loginPage(Scanner reader) throws Exception {
        System.out.println(Constants.ID_PROMPT);
        String id = reader.nextLine();
        while (!UserManager.getExistingUsers().containsKey(Integer.parseInt(id))) {
            System.out.println(Constants.INVALID_INPUT + Constants.ID_PROMPT);
            id = reader.nextLine();
        }
        return Integer.parseInt(id);
    }

    public static String ExistingUserMenu(Scanner reader, int id) throws Exception {

        RunCommand currentUser = new RunCommand();
        currentUser.setCurrentUser(id);

        System.out.println(Constants.WELCOME_EXISTING + currentUser.retrieveUser("name") +
                Constants.WELCOME2 + Constants.EXISTING_USER_MENU);

        String input = reader.nextLine();

        //check whether the command is an integer from 1 to 6
        input = checkCommand(input, reader, 2);

        int command = Integer.parseInt(input);
        RunCommand commandExecutor = new RunCommand(command);

        switch (command) {
            case 2:
                if (noInfoFound(command)) {
                    System.out.println(Constants.NOT_ENOUGH_INFO);
                    String level = activityLevel(reader);
                    commandExecutor.addInfo(level, command);
                }
                break;
            case 3:
                if (noInfoFound(command)) {
                    System.out.println(Constants.NOT_ENOUGH_INFO);
                    String[] exercises = exercisePreference(reader);
                    commandExecutor.addInfo(exercises, command);
                }
                break;
            case 4:
                if (noInfoFound(command)) {
                    System.out.println(Constants.NOT_ENOUGH_INFO);
                    return diseaseList(reader, commandExecutor);
                }
                break;
                //  TODO: case 5

            case 6:
                return updateUser(reader, commandExecutor);
        }
            commandExecutor.executeCommand(); //regular operations from 1-5
            Presenter analyze_results = new Presenter(commandExecutor.getAnalyzer());
            return Constants.REPORT + analyze_results.retrieveOutput();
    }

    /**
     * Returns true if the user wants to log out.
     * @param reader reads user info
     * @return True if the user wants to log out
     */
    public static boolean logOut(Scanner reader) {
        System.out.println(Constants.RETURN_MENU);
        String logOut = reader.nextLine();

        while (!logOut.equals("Y") && !logOut.equals("N")) {
            System.out.println(Constants.INVALID_INPUT + Constants.RETURN_MENU);
            logOut = reader.nextLine();
        }
        return logOut.equals("N");
    }

    public static boolean reStart(Scanner reader) {
        System.out.println(Constants.RESTART_PROGRAM);
        String restart = reader.nextLine();

        // if they don't want to restart, adds its info back into the file
        while (!restart.equals("N") & !restart.equals("Y")) {
            System.out.println(Constants.INVALID_INPUT + Constants.RESTART_PROGRAM);
            restart = reader.nextLine();
        }
        return restart.equals("Y");
    }

    public static boolean isInteger(String input) {
        try {
            Integer.parseInt(input);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public static boolean notInRange(int i, int type) {
        if (type == 1) {
            return i <= 0 || i >= 6;
        }
        else{
            return i <= 0 || i >= 7;
        }
    }

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
        } else {
            return true;
        }
    }

    public static String activityLevel(Scanner reader) {

        System.out.println(Constants.ACTIVITY_MENU);
        String userActivityLevel = reader.nextLine();

        switch (userActivityLevel) {
            case "1":
                return Constants.SED;
            case "2":
                return Constants.LOW;
            case "3":
                return Constants.MID;
            case "4":
                return Constants.HIGH;
            default:
                return Constants.INVALID_INPUT;
        }
    }

    public static String[] exercisePreference(Scanner reader) {

        System.out.println(Constants.EXERCISE_START + Constants.EXERCISE_MAJOR);
        String majorMuscle = reader.nextLine();

        System.out.println(Constants.EXERCISE_MINOR);
        String minorMuscle = reader.nextLine();

        System.out.println(Constants.EXERCISE_EQUIPMENT);
        String equipment = reader.nextLine();

        return new String[]{majorMuscle, minorMuscle, equipment};
    }


    public static String diseaseList(Scanner reader, RunCommand commandExecutor) throws Exception {
        commandExecutor.resetPotentialDisease();
        int potentialDisease;

        ArrayList<String> currentSymptoms = new ArrayList<>();

        System.out.println("Hi Welcome to the Disease Predictor, given the lists of potential symptoms,\n" +
                "please enter the symptoms you are experiencing, and the program will generate potential\n" +
                "diseases that you may be diagnosed for.");

        while (true) {
            potentialDisease = commandExecutor.executeCommandDisease(currentSymptoms);
            if (potentialDisease <= 6) {
                break;
            }
            //outputs how many potential disease client could have
            // pass in empty array list if it is first round.

            System.out.println("\nThese are the symptom options. " +
                    "If you are currently experiencing more than one, please separate the input using a comma ','\n" +
                    "for example, 'high_fever,back_pain' (notice there is no space in between)\n" +
                    "\nIf none of them apply to you, please type in N/A.");

            Presenter analyze_results = new Presenter(commandExecutor.getAnalyzer());
            System.out.println(analyze_results.retrieveOutput()); //first time giving options

            String symptoms = reader.nextLine(); //client's input of symptoms
            if (!symptoms.equals("N/A")) {
                symptoms = symptoms.replaceAll("[\\[\\](){}]", "");
                String[] symptomsList = symptoms.split(",");
                List<String> finalSymptomsList;
                finalSymptomsList = Arrays.asList(symptomsList);
                //convert client symptoms into a list of symptoms;
                //add those symptoms into the current symptoms that client has
                currentSymptoms.addAll(finalSymptomsList);
            }
        }
        commandExecutor.executeCommand();
        Presenter analyze_results = new Presenter(commandExecutor.getAnalyzer());

        return analyze_results.retrieveOutput();
    }

    public static String updateUser(Scanner reader, RunCommand commandExecutor) throws Exception {
        System.out.println(" You may choose the following options: (Please enter a number from 1 to 5) \n" +
                " 1. Change Username \n" +
                " 2. Change Food Preferences \n"); //TODO: Are we adding more here?
        int secondCommand = Integer.parseInt(reader.nextLine());

        if (secondCommand == 1) {
            System.out.println("Please enter your new Username");
            String newName = reader.nextLine();
            System.out.println("Thank you. Currently updating your new username.");
            commandExecutor.executeCommandUpdateInfo(secondCommand, newName);
        }
        else if (secondCommand == 2){
            // TODO: allow user to change their exercise preference
        }
        return Constants.UPDATED_PROFILE; //this can be used for general cases 1-6
    }

    public static String checkCommand(String input, Scanner reader, int type){
        boolean check = true;
        while (check) {
//            if (isInteger(input) && notInRange(Integer.parseInt(input), type)) {
//                System.out.println("Sorry, your command is invalid. Please try again.");
//                input = reader.nextLine();
//            } else if (!isInteger(input)) {
//                System.out.println("Sorry, your command is invalid. Please enter a number only, no other characters." +
//                        "Try again:");
//                input = reader.nextLine();
//            }
            if (!isInteger(input) || notInRange(Integer.parseInt(input), type)) {
                System.out.println(Constants.INVALID_INPUT);
                input = reader.nextLine();
            } else {
                check = false;
            }
        }
        return input;
    }
    public static void addToExisting(){
        RunCommand command = new RunCommand();
        int id = Integer.parseInt((String) command.retrieveUser("id"));
        if (!command.getAllExistingUser().containsKey(id)){
            command.setToExisting();
        }
    }
}
