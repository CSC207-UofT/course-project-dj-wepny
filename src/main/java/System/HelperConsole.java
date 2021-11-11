package System;

import Controllers.Presenter;
import Controllers.RunCommand;
import Constants.Constants;

import java.util.*;

/**
 * This class contains all the helper function used both
 * by ExistingUserController and NewUserController
 */
public class HelperConsole {

    /**
     * A helper method that prompts the user for their basic information.
     * Returns an array of strings in the order of [name, age, gender].
     * Note that this method is subject to change (Maybe ArrayList instead of Array).
     *
     * @param reader The scanner used for the user input.
     * @return an array of strings of some basic information about the user.
     */
    public static String[] getBasicUserInfo(Scanner reader) {
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
     * @return an array of strings of the user's height, weight, and age
     */
    public static String[] getPersonalUserInfo(Scanner reader) {
        System.out.println(Constants.HEIGHT_PROMPT);
        String height = reader.nextLine();

        // checking to make sure the height input is a number between 0 m to 2.5 m
        while (isNotNum(height) || Float.parseFloat(height) <= 0 || Float.parseFloat(height) >= 2.5) {
            System.out.println(Constants.INVALID_INPUT + Constants.HEIGHT_PROMPT);
            height = reader.nextLine();
        }

        System.out.println(Constants.WEIGHT_PROMPT);
        String weight = reader.nextLine();

        // checking to make sure the weight input is a number larger than 0
        while (isNotNum(height) || Float.parseFloat(weight) <= 0) {
            System.out.println(Constants.INVALID_INPUT + Constants.WEIGHT_PROMPT);
            weight = reader.nextLine();
        }

        System.out.println(Constants.AGE_PROMPT);
        String age = reader.nextLine();

        // checking to make sure the age input is a number larger or equal to 0
        while (isNotNum(age) || Integer.parseInt(age) < 0) {
            System.out.println(Constants.INVALID_INPUT + Constants.AGE_PROMPT);
            age = reader.nextLine();
        }

        return new String[]{height, weight, age};
    }

    /**
     * Checks if user already exists in the system.
     *
     * @param reader reads user input
     * @return true if the user identify themselves as an existing user
     */
    public static boolean checkExisting(Scanner reader) {
        System.out.println(Constants.ASK_EXISTING);
        String exists = reader.nextLine();

        // making sure the input is equal to Y or N
        while (!exists.equals("Y") && !exists.equals("N")) {
            System.out.println(Constants.INVALID_INPUT + Constants.ASK_EXISTING);
            exists = reader.nextLine();
        }
        return exists.equals("Y");
    }

    /**
     * Returns true if the user wants to log out.
     *
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

    /**
     * return true if the user want to return to their main menu and restart
     *
     * @param reader reads user info
     * @return true if the user would like to return to the main menu
     */
    public static boolean reStart(Scanner reader) {
        System.out.println(Constants.RESTART_PROGRAM);
        String restart = reader.nextLine();

        while (!restart.equals("N") & !restart.equals("Y")) {
            System.out.println(Constants.INVALID_INPUT + Constants.RESTART_PROGRAM);
            restart = reader.nextLine();
        }
        return restart.equals("Y");
    }

    /**
     * Helper function that lets the user input their activity level
     *
     * @param reader reads the user input
     * @return the activity level represented by the user's input
     */
    public static String activityLevel(Scanner reader) {

        System.out.println(Constants.ACTIVITY_MENU);
        String userActivityLevel = reader.nextLine();

        //check to make sure the input is a number within the correct range
        while (isNotNum(userActivityLevel) ||
                notInRange(Integer.parseInt(userActivityLevel), 3)) {
            System.out.println(Constants.INVALID_INPUT);
            userActivityLevel = reader.nextLine();
        }

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

    /**
     * Helper function that lets the user input their exercise preference
     *
     * @param reader reads in the user input
     * @return a string of the user's input
     */
    public static String[] exercisePreference(Scanner reader) {

        System.out.println(Constants.EXERCISE_START + Constants.EXERCISE_MAJOR);
        String majorMuscle = reader.nextLine();

        // making sure the user input is a valid major muscle
        while (!Constants.ALL_MAJOR_MUSCLES.contains(majorMuscle)) {
            System.out.println(Constants.EXERCISE_MAJOR_ERROR);
            majorMuscle = reader.nextLine();
        }
        System.out.println(Constants.EXERCISE_MINOR);
        String minorMuscle = reader.nextLine();

        // making sure the user input is a valid minor muscle
        while (!Constants.ALL_MINOR_MUSCLES.contains(minorMuscle)) {
            System.out.println(Constants.EXERCISE_MINOR_ERROR);
            minorMuscle = reader.nextLine();
        }

        System.out.println(Constants.EXERCISE_EQUIPMENT);
        StringBuilder equipment = new StringBuilder(reader.nextLine());

        // making sure the user input is a valid equipment
        while (!Constants.ALL_EQUIPMENTS.contains(equipment.toString())) {
            System.out.println(Constants.EXERCISE_EQUIPMENT_ERROR_BG);
            equipment = new StringBuilder(reader.nextLine());
        }

        System.out.println(Constants.EXERCISE_EQUIPMENT2);
        String new_equipment = reader.nextLine();

        // allow the user to enter as many equipments they want until they enter "None"
        while (!new_equipment.equals("None")) {
            while (!Constants.ALL_EQUIPMENTS.contains(new_equipment) && !new_equipment.equals("None")) {
                System.out.println(Constants.EXERCISE_EQUIPMENT_ERROR_AF);
                new_equipment = reader.nextLine();
            }
            if (equipment.toString().contains(new_equipment)) {
                System.out.println(Constants.EXERCISE_EQUIPMENT3);
            } else if (new_equipment.equals("None")) {
                break;
            } else {
                equipment.append('/').append(new_equipment);
                System.out.println(Constants.EXERCISE_EQUIPMENT2);
            }
            new_equipment = reader.nextLine();
        }

        return new String[]{majorMuscle, minorMuscle, equipment.toString()};
    }

    /**
     * Helper function that lets the user input their symptoms
     *
     * @param reader          reads in the user input
     * @param commandExecutor pass in the RunCommand to execute the diseaseAnalyzer
     * @return the list of disease the user might have according to their inputted symptoms
     */
    public static String diseaseList(Scanner reader, RunCommand commandExecutor) throws Exception {
        commandExecutor.resetPotentialDisease();
        int potentialDisease;

        ArrayList<String> currentSymptoms = new ArrayList<>();

        System.out.println(Constants.DISEASE_START);

        while (true) {
            potentialDisease = commandExecutor.executeCommandDisease(currentSymptoms);
            if (potentialDisease <= 6) {
                break;
            }
            //outputs how many potential disease client could have
            // pass in empty array list if it is first round.

            System.out.println(Constants.SYMPTOMS_DESC);

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

        // returns the user's potential diseases
        return analyze_results.retrieveOutput();
    }

    /**
     * Helper function that lets the user input their food preference
     *
     * @param reader read the user input
     * @return an array list of foodFilterCriterion
     */
    public static ArrayList<Object> foodPreference(Scanner reader){
        System.out.println(Constants.MEALPLAN_WELCOME);
        System.out.println(Constants.LOWCARBS);
        String lowCarb = reader.nextLine();
        // checking to make sure the input is valid
        while (!Objects.equals(lowCarb, "Y") && !Objects.equals(lowCarb, "N")) {
            System.out.println(Constants.INVALID_INPUT);
            lowCarb = reader.nextLine();
        }
        System.out.println(Constants.LOWFAT);
        String lowFat = reader.nextLine();
        while (!Objects.equals(lowFat, "Y") && !Objects.equals(lowFat, "N")) {
            System.out.println(Constants.INVALID_INPUT);
            lowFat = reader.nextLine();
        }
        System.out.println(Constants.LOWSUGAR);
        String lowSugar = reader.nextLine();
        while (!Objects.equals(lowSugar, "Y") && !Objects.equals(lowSugar, "N")) {
            System.out.println(Constants.INVALID_INPUT);
            lowSugar = reader.nextLine();
        }
        System.out.println(Constants.VEG);
        String vegetarian = reader.nextLine();
        while (!Objects.equals(vegetarian, "Y") && !Objects.equals(vegetarian, "N")) {
            System.out.println(Constants.INVALID_INPUT);
            vegetarian = reader.nextLine();
        }
        System.out.println(Constants.NUM_FOODS);
        String numFoods = reader.nextLine();
        while (isNotNum(numFoods) || Integer.parseInt(numFoods) > 25) {
            System.out.println(Constants.INVALID_INPUT);
            numFoods = reader.nextLine();
        }
        String[] foodCriterion = {lowCarb, lowFat, lowSugar, vegetarian};
        ArrayList<Object> foodFilterCriterion = new ArrayList<>();
        for (String criterion : foodCriterion) {
            if (criterion.equals("Y")) {
                foodFilterCriterion.add(true);
            } else {
                foodFilterCriterion.add(false);
            }
        }
        foodFilterCriterion.add(numFoods);

        return foodFilterCriterion;

    }

    /**
     * Helper function that updates the user's information
     *
     * @param reader          reads in user's input
     * @param commandExecutor pass in a RunCommand to help execute the command
     * @return a msg to tell the user the update is successful
     */
    public static String updateUser(Scanner reader, RunCommand commandExecutor) throws Exception {
        System.out.println(Constants.USER_UPDATE);
        int secondCommand = Integer.parseInt(reader.nextLine());
        RunCommand command = new RunCommand(4);

        if (secondCommand == 1) { // update username
            System.out.println(Constants.CHANGE_USERNAME);
            String newName = reader.nextLine();
            System.out.println(Constants.UPDATED_USERNAME);
            commandExecutor.executeCommandUpdateInfo(secondCommand, newName);
        }
        //update Height
        else if (secondCommand == 2) {
            System.out.println(Constants.CHANGE_HEIGHT);
            String newHeight = reader.nextLine();
            System.out.println(Constants.UPDATED_HEIGHT);
            commandExecutor.executeCommandUpdateInfo(secondCommand, newHeight);
        }
        //update Weight
        else if (secondCommand == 3) {
            System.out.println(Constants.CHANGE_WEIGHT);
            String newWeight = reader.nextLine();
            System.out.println(Constants.UPDATED_WEIGHT);
            commandExecutor.executeCommandUpdateInfo(secondCommand, newWeight);

        }
        //change age
        else if (secondCommand == 4) {
            System.out.println(Constants.CHANGE_AGE);
            String newAge = reader.nextLine();
            System.out.println(Constants.UPDATED_AGE);
            commandExecutor.executeCommandUpdateInfo(secondCommand, newAge);
        }
        //change gender
        else if (secondCommand == 5) {
            System.out.println(Constants.CHANGE_GENDER);
            String newGender = reader.nextLine();
            System.out.println(Constants.UPDATED_GENDER);
            commandExecutor.executeCommandUpdateInfo(secondCommand, newGender);

        }
        //update activity level
        else if (secondCommand == 6) {
            String level = activityLevel(reader);
            commandExecutor.addInfo(level, 2);
        }
        //update exercise preference
        else if (secondCommand == 7) {
            commandExecutor.addInfo(exercisePreference(reader), 3);
        }
        //update disease
        else if (secondCommand == 8) {
            // resetting the symptoms list to empty first
            command.addInfo("", 6);
            // overwriting the symptoms given the new input
            return diseaseList(reader, command);
        } else if (secondCommand == 9) {
            // resetting the food preference to empty first
            commandExecutor.addInfo("", 7);
            // overwriting the food preference to the new input
            commandExecutor.addInfo(foodPreference(reader), 5);

        }
        return Constants.UPDATED_PROFILE; // this can be used for general cases 1-6
    }

    /**
     * Helper function that checks to make sure that the command is within the valid range
     *
     * @param input  the input that needs to be checked
     * @param reader reads in the user's new input if the previous one is not valid
     * @param type   making sure the range is within the specified type
     * @return the first valid input the user inputs
     */
    public static String checkCommand(String input, Scanner reader, int type) {
        boolean check = true;
        while (check) {
            if (isNotNum(input) || notInRange(Integer.parseInt(input), type)) {
                System.out.println(Constants.INVALID_INPUT);
                input = reader.nextLine();
            } else {
                check = false;
            }
        }
        return input;
    }


    /**
     * Checks if the input is a number of not
     *
     * @param input the information that needs to be checked
     * @return true if the input is not a number
     */
    public static boolean isNotNum(String input) {
        try {
            Integer.parseInt(input);

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
        int id = Integer.parseInt((String) command.retrieveUser("id"));
        if (!command.getAllExistingUser().containsKey(id)) {
            command.setToExisting();
        }
    }
}
