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
        System.out.println("Please enter your name:");
        String name = reader.nextLine();

        System.out.println("Please enter your gender (M/F):");
        String gender = reader.nextLine();

        while (!gender.equals("M") && !gender.equals("F")) {
            System.out.println("Invalid Input, Please re-enter.");
            System.out.println("Please enter your gender (M/F):");
            gender = reader.nextLine();
        }
        // Note: later on we would have to make an error checker for the
        // validity of the inputs.

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
        System.out.println("Please enter your height (in m):");
        String height = reader.nextLine();


        while (Float.parseFloat(height) <= 0) {
            System.out.println("Invalid input, please try again.");
            System.out.println("Please enter your height (in m):");
            height = reader.nextLine();
        }

        System.out.println("Please enter your weight (in kg):");
        String weight = reader.nextLine();
        while (Float.parseFloat(weight) <= 0) {
            System.out.println("Invalid input, please try again.");
            System.out.println("Please enter your weight (in kg):");
            weight = reader.nextLine();
        }

        System.out.println("Please enter your age:");
        String age = reader.nextLine();
        while (Integer.parseInt(age) < 0) {
            System.out.println("Invalid input, please try again.");
            System.out.println("Please enter your age:");
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
        System.out.println("Hi there! This is DJ WEPNY Personal Health software, " +
                "before we start, please enter the following question!\n");
        System.out.println("Are you an existing user? (Y/N)");
        String exists = reader.nextLine();


        while (!exists.equals("Y") && !exists.equals("N")) {
            System.out.println("Invalid Input, Please re-enter.");
            System.out.println("Are you an existing user? (Y/N)");
            exists = reader.nextLine();
        }

        return exists.equals("Y");
    }

    public static void gatherInfo(Scanner reader) throws Exception {
        System.out.println("We will start from some basic information.");
        String[] basicUserInfo = getBasicUserInfo(reader);
        System.out.println("Now, we would like to know some of your personal data.");
        String[] personalUserInfo = getPersonalUserInfo(reader);

        RunCommand.createUser(basicUserInfo, personalUserInfo);
        RunCommand infoGetter = new RunCommand();
        System.out.println("You have now created an account with us." +
                "\n\nYour Personal Identification Number (PIN) is " + infoGetter.retrieveUser("id"));
        System.out.println("!!!Please keep this number as you would need it to access your account in the future.\n");
    }

    public static String NewUserMenu(Scanner reader) throws Exception {
        RunCommand infoGetter = new RunCommand();
        System.out.println("Welcome, " + infoGetter.retrieveUser("name") + ", What would you like to do today?");
        System.out.println(" You may choose the following options: (Please enter a number from 1 to 5) \n" +
                " 1. Analyze Body Mass Index (BMI) \n" +
                " 2. Analyze Energy Required per day (EER) \n" +
                " 3. Analyze Workout \n" +
                " 4. Analyze Disease \n" +
                " 5. Generate a meal plan \n");

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
            case 5:
                commandExecutor.addInfo(foodPreference(reader), command);
        }

        commandExecutor.executeCommand();

        Presenter analyze_results = new Presenter(commandExecutor.getAnalyzer());
        return analyze_results.retrieveOutput();
    }

    public static int loginPage(Scanner reader) throws Exception {
        System.out.println("Please enter your personal ID");
        String id = reader.nextLine();
        // I think this while loop doesn't work and I can't figure out why -Naomi
        while (!UserManager.getExistingUsers().containsKey(Integer.parseInt(id))) {
            System.out.println("Invalid ID, please enter again");
            System.out.println("Please enter your personal ID");
            id = reader.nextLine();
        }
        return Integer.parseInt(id);
    }

    public static String ExistingUserMenu(Scanner reader, int id) throws Exception {

        RunCommand currentUser = new RunCommand();
        currentUser.setCurrentUser(id);

        System.out.println("Welcome, " + currentUser.retrieveUser("name") + "! " +
                " Great to have you back with us, which report would you like to view today?");
        System.out.println("You may choose the following options: (Please enter a number from 1 to 5) \n" +
                "If you would like to update your profile information, please enter '6'\n\n" +
                " 1. Analyze Body Mass Index (BMI) \n" +
                " 2. Analyze Energy Required per day (EER) \n" +
                " 3. Analyze Workout \n" +
                " 4. Analyze Disease \n" +
                " 5. Generate a meal plan \n" +
                " 6. Edit Profile");

        String input = reader.nextLine();

        //check whether the command is an integer from 1 to 6
        input = checkCommand(input, reader, 2);

        int command = Integer.parseInt(input);
        RunCommand commandExecutor = new RunCommand(command);

        switch (command) {
            case 2:
                if (noInfoFound(command)) {
                    System.out.println("Oh no! There is currently not enough information " +
                            "in your profile to generate this report");
                    System.out.println("Please fill in the following information:\n");
                    String level = activityLevel(reader);
                    commandExecutor.addInfo(level, command);
                }
                break;
            case 3:
                if (noInfoFound(command)) {
                    System.out.println("Oh no! There is currently not enough information " +
                            "in your profile to generate this report");
                    System.out.println("Please fill in the following information:\n");
                    String[] exercises = exercisePreference(reader);
                    commandExecutor.addInfo(exercises, command);
                }
                break;
            case 4:
                if (noInfoFound(command)) {
                    System.out.println("Oh no! There is currently not enough information " +
                            "in your profile to generate this report");
                    System.out.println("Please fill in the following information:\n");
                    return diseaseList(reader, commandExecutor);
                }
                break;
            case 5:
                if (noInfoFound(command)) {
                    System.out.println("Oh no! There is currently not enough information " +
                            "in your profile to generate this report");
                    System.out.println("Please fill in the following information:\n");
                    commandExecutor.addInfo(foodPreference(reader), command);
                }
                break;

            case 6:
                return updateUser(reader, commandExecutor);
        }
            String msg = "Here is your report based on previously entered information: \n\n";
            commandExecutor.executeCommand(); //regular operations from 1-5
            Presenter analyze_results = new Presenter(commandExecutor.getAnalyzer());
            return msg + analyze_results.retrieveOutput();
    }

    public static boolean logOut(Scanner reader) {
        System.out.println("Do you want to go back to your user main menu page? (Y/N)");
        String logOut = reader.nextLine();

        while (!logOut.equals("Y") && !logOut.equals("N")) {
            System.out.println("Invalid Input, Please re-enter.");
            System.out.println("Do you want to log out of your profile?");
            logOut = reader.nextLine();
        }

        return logOut.equals("N");
    }

    public static boolean reStart(Scanner reader) {
        System.out.println("\nWould you like to exit the program entirely (Y/N):\n");
        String restart = reader.nextLine();

        //if they don't want to restart, adds its info back into the file
        while (!restart.equals("N") & !restart.equals("Y")) {
            System.out.println("Invalid input, please try again. ");
            System.out.println("Would you like to start again? (Y/N):");
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
        }
        if (command == 5) {
            HashMap foodData = (HashMap) commandExecutor.retrieveUser("food");
            return foodData.isEmpty();
        }
        else {
            return true;
        }
    }

    public static String activityLevel(Scanner reader) {

        System.out.println("Please enter your daily activity level: (Please enter a number from 1 to 4) \n" +
                " 1. Sedentary \n" +
                " 2. Low Active \n" +
                " 3. Active \n" +
                " 4. Very Active \n");
        String userActivityLevel = reader.nextLine();

        switch (userActivityLevel) {
            case "1":
                return "Sedentary";
            case "2":
                return "Low Active";
            case "3":
                return "Active";
            case "4":
                return "Very Active";
            default:
                return "Enter Again";
        }
    }

    public static String[] exercisePreference(Scanner reader) {

        System.out.println("This exercise analyzer generate a list of exercising moves based on your preference.");
        System.out.println("PLease select only ONE major muscle you want to exercise from the following list:\n" +
                "\"Arms, Core, Full Body, Legs, Back\"");
        String majorMuscle = reader.nextLine();

        System.out.println("PLease select only ONE minor muscle you want to exercise from the following list:\n" +
                "\"Bicep, Shoulders, Outer Thigh, Glutes, Hamstrings, Quads, \n" +
                "Calves, Chest, Inner Thigh, Tricep, Lats, Oblique\"");
        String minorMuscle = reader.nextLine();

        System.out.println("Please select the equipment you have or want to use:\n" +
                "\"Dumbbells, Bar, Cable, Body Weight, Platform, Machine, Band, Kettle Bell, Medicine Ball, Bosu Ball\"");
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

    public static ArrayList<Object> foodPreference(Scanner reader) throws Exception {
        System.out.println("Welcome to the Meal Plan Generator!");
        System.out.println("Please enter Y or N for the following options");
        System.out.println("I prefer low carbohydrate");
        String lowCarb = reader.nextLine();
        System.out.println("I prefer low fat");
        String lowFat = reader.nextLine();
        System.out.println("I prefer low sugar");
        String lowSugar = reader.nextLine();
        System.out.println("I am vegetarian");
        String vegetarian = reader.nextLine();
        System.out.println("How many foods do you want?");
        String numFoods = reader.nextLine();
        String[] foodCriterion = {lowCarb, lowFat, lowSugar, vegetarian};
        ArrayList<Object> foodFilterCriterion = new ArrayList<>();
        for(String criterion : foodCriterion) {
            if (criterion.equals("Y")) {
                foodFilterCriterion.add(true);
            }
            else {
                foodFilterCriterion.add(false);
            }
        }
        foodFilterCriterion.add(numFoods);

        return foodFilterCriterion;

    }


    public static String updateUser(Scanner reader, RunCommand commandExecutor) throws Exception {

        System.out.println("You may choose the following options: (Please enter a number from 1 to 5) \n" +
                " 1. Change Username \n" +
                " 2. Change Height \n" +
                " 3. Change Weight \n" +
                " 4. Change Age \n" +
                " 5. Change Gender \n" +
                " 6. Change Activity Level \n" +
                " 7. Change Exercise Preferences \n" +
                " 8. Change Symptoms \n" +
                " 9. Change Food Preferences \n");
        int secondCommand = Integer.parseInt(reader.nextLine());
        RunCommand command = new RunCommand(4);

        if (secondCommand == 1) {
            System.out.println("Please enter your new Username:");
            String newName = reader.nextLine();
            System.out.println("Thank you. Currently updating your new username.");
            commandExecutor.executeCommandUpdateInfo(secondCommand, newName);
        }
        //update Height
        else if (secondCommand == 2){
            System.out.println("Please enter your new height:");
            String newHeight = reader.nextLine();
            System.out.println("Thank you. Currently updating your new height.");
            commandExecutor.executeCommandUpdateInfo(secondCommand, newHeight);
        }
        //update Weight
        else if (secondCommand == 3){
            System.out.println("Please enter your new weight:");
            String newWeight = reader.nextLine();
            System.out.println("Thank you. Currently updating your new weight.");
            commandExecutor.executeCommandUpdateInfo(secondCommand, newWeight);

        }
        //change age
        else if (secondCommand == 4){
            System.out.println("Please enter your new age:");
            String newAge = reader.nextLine();
            System.out.println("Thank you. Currently updating your new gender.");
            commandExecutor.executeCommandUpdateInfo(secondCommand, newAge);
        }
        //change gender
        else if (secondCommand == 5){
            System.out.println("Please enter your new gender:");
            String newGender = reader.nextLine();
            System.out.println("Thank you. Currently updating your new gender.");
            commandExecutor.executeCommandUpdateInfo(secondCommand, newGender);

        }
        //update activity level
        else if (secondCommand == 6){
            String level = activityLevel(reader);
            commandExecutor.addInfo(level, 2);
        }
        //update exercise preference
        else if (secondCommand == 7){
            commandExecutor.addInfo(exercisePreference(reader), 3);
        }
        //update disease
        else if (secondCommand == 8){
            command.addInfo("", 6);
            return diseaseList(reader, command);
        }
        else if (secondCommand == 9){
            commandExecutor.addInfo("", 7);
            commandExecutor.addInfo(foodPreference(reader), 5);

        }
        return "Your profile has been updated"; //this can be used for general cases 1-6
    }

    public static String checkCommand(String input, Scanner reader, int type){
        boolean check = true;
        while (check) {
            if (isInteger(input) && notInRange(Integer.parseInt(input), type)) {
                System.out.println("Sorry, your command is invalid. Please try again.");
                input = reader.nextLine();
            } else if (!isInteger(input)) {
                System.out.println("Sorry, your command is invalid. Please enter a number only, no other characters." +
                        "Try again:");
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
