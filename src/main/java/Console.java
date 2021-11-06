import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.IntStream;

import static java.util.Objects.isNull;
/**
 * This class interact with the users and receives their input, then it sends
 *       the inputs to the controller class.
 */


public class Console {

    private final static int[] COMMAND = {1,2,3,4,5};
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

    //may have to move this to run command?
    /**
     * Checks if user already exists in the system.
     * @param reader reads user input
     * @return whether user exists already or not.
     */
    public static boolean checkExisting (Scanner reader){
        System.out.println("Are you an existing user? (Y/N)");
        String exists = reader.nextLine();


        while (!exists.equals("Y") && !exists.equals("N")){
            System.out.println("Invalid Input, Please re-enter.");
            System.out.println("Are you an existing user? (Y/N)");
            exists = reader.nextLine();
        }

        return exists.equals("Y");
    }

    public static void gatherInfo(Scanner reader) throws Exception{
        System.out.println("We will start from some basic information.");
        String[] basicUserInfo = getBasicUserInfo(reader);
        System.out.println("Now, we would like to know some of your personal data.");
        String[] personalUserInfo = getPersonalUserInfo(reader);

        RunCommand.createUser(basicUserInfo, personalUserInfo);
        RunCommand infoGetter = new RunCommand();
        System.out.println("Hi there! This is DJ WEPNY Personal Health software,\nyou have now created an account with us." +
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


//        int command = Integer.parseInt(reader.nextLine());
        String input = reader.nextLine();

        boolean check = true;
        while(check){
            if (isInteger(input) && !isRange(Integer.parseInt(input))){
                System.out.println("Sorry, your command is invalid. Please enter a number from 1 to 5 only." +
                        "Try again:");
                input = reader.nextLine();
            }
            else if (!isInteger(input)){
                System.out.println("Sorry, your command is invalid. Please enter a number only, no other characters." +
                        "Try again:");
                input = reader.nextLine();
            }
            else{
                check = false;
            }

        }
        int command = Integer.parseInt(input);

        RunCommand commandExecutor = new RunCommand(command);

        if (command == 2) {
            String level = activityLevel(reader);
            commandExecutor.addInfo(level, 2);
        }

        if (command == 4){
            commandExecutor.resetPotentialDisease();
            commandExecutor.changeInfo(new ArrayList<String>(), 4);
            int potentialDisease;

            ArrayList<String> currentSymptoms = new ArrayList<>();

            while(true) {
                    potentialDisease = commandExecutor.executeCommandDisease(currentSymptoms);
                    if(potentialDisease <= 6){
                        break;
                    }
                    //outputs how many potential disease client could have
                    // pass in empty array list if it is first round.
                    System.out.println("Hi Welcome to the Disease Predictor, given the lists of potential symptoms,\n" +
                            "please enter the symptoms you are experiencing, and the program will generate potential\n" +
                            "diseases that you may be diagnosed for.");
                    System.out.println("\nThese are the symptom options. " +
                            "If you are currently experiencing more than one, please separate the input using a comma ','\n" +
                            "for example, 'high_fever,back_pain' (notice there is no space in between)\n"+
                            "\nIf none of them apply to you, please type in N/A.");

                    Presenter analyze_results = new Presenter(commandExecutor.getAnalyzer());
                    System.out.println(analyze_results.retrieveOutput()); //first time giving options

                    String symptoms = reader.nextLine(); //client's input of symptoms
                    if(!symptoms.equals("N/A")){
                        symptoms = symptoms.replaceAll("[\\[\\](){}]","");
                        String[] symptomsList = symptoms.split(",");
//                        String SymptomsList[] = symptoms.split(",");
                        List<String> finalSymptomsList;
                        finalSymptomsList = Arrays.asList(symptomsList);
                        //convert client symptoms into a list of symptoms;
                        //add those symptoms into the current symptoms that client has
                        currentSymptoms.addAll(finalSymptomsList);
                    }
            }
            System.out.println("These are your potential diseases: (if output = [], " +
                    "there is no disease that match the current symptoms you are experiencing)");
            commandExecutor.executeCommand();
//            Presenter analyze_results = new Presenter(analyzer);
            Presenter analyze_results = new Presenter(commandExecutor.getAnalyzer());
            return analyze_results.retrieveOutput();
        }
        else {
            commandExecutor.executeCommand();

            Presenter analyze_results = new Presenter(commandExecutor.getAnalyzer());
            return analyze_results.retrieveOutput();
        }
    }
   
//    ********************

    public static int loginPage(Scanner reader) throws Exception{
        System.out.println("Please enter your personal ID");
        String id = reader.nextLine();
        // I think this while loop doesn't work and I can't figure out why -Naomi
        while(!UserManager.getExistingUsers().containsKey(Integer.parseInt(id))){
            System.out.println("Invalid ID, please enter again");
            System.out.println("Please enter your personal ID");
            id = reader.nextLine();
        }
        return Integer.parseInt(id);
    }

    public static String ExistingUserMenu(Scanner reader, int id) throws Exception{

        RunCommand currentUser = new RunCommand();
        currentUser.setCurrentUser(id);

        // We probably shouldn't have duplicate code here -Naomi
        // Pass in the two arrays to the commandExecutor, and instantiate the classes accordingly.
        System.out.println("Welcome, " + currentUser.retrieveUser("name") + ", What would you like to do today?");
        System.out.println(" You may choose the following options: (Please enter a number from 1 to 5) \n" +
                " 1. Analyze Body Mass Index (BMI) \n" +
                " 2. Analyze Energy Required per day (EER) \n" +
                " 3. Analyze Workout \n" +
                " 4. Analyze Disease \n" +
                " 5. Generate a meal plan \n" +
                " 6. Edit Profile");

        int command = Integer.parseInt(reader.nextLine());
//        UserAnalyzer analyzer = COMMANDS.get(command);
        RunCommand commandExecutor = new RunCommand(command);

        if (command == 2) {
            if (!checkInfoExist(2)){
                System.out.println("Oh no! Information missing for this report.");
                String level = activityLevel(reader);
                commandExecutor.addInfo(level, 2);
            }
            commandExecutor.executeCommand();
        }

        if (command == 6){ //special case where user chooses to change their personal info.
            System.out.println(" You may choose the following options: (Please enter a number from 1 to 5) \n" +
                    " 1. Change Username \n" +
                    " 2. Change Food Preferences \n");
            int secondCommand = Integer.parseInt(reader.nextLine());
            //Code here may seem to be messy, but to make it better, I would need to place the print messages inside
            //RunCommand. Not sure if that's allowed.
            if(secondCommand == 1){
                System.out.println("Please enter your new Username");
                String newName = reader.nextLine();
                System.out.println("Thank you. Currently updating your new username.");
                commandExecutor.executeCommandUpdateInfo(secondCommand, newName);
            }

            return "Your profile has been updated"; //this can be used for general cases 1-6
        }
        else{
            commandExecutor.executeCommand(); //regular operations from 1-5
            Presenter analyze_results = new Presenter(commandExecutor.getAnalyzer());
            return analyze_results.retrieveOutput();
        }

    }

    public static boolean logOut(Scanner reader){
        System.out.println("Do you want to log out of your profile?");
        String logOut = reader.nextLine();

        while (!logOut.equals("Y") && !logOut.equals("N")){
            System.out.println("Invalid Input, Please re-enter.");
            System.out.println("Do you want to log out of your profile?");
            logOut = reader.nextLine();
        }

        return logOut.equals("Y");
    }

    public static boolean reStart(Scanner reader){
        System.out.println("\nWould you like to exit the program entirely (Y/N):\n");
        String restart = reader.nextLine();

        //if they don't want to restart, adds its info back into the file
        while(!restart.equals("N") & !restart.equals("Y")){
            System.out.println("Invalid input, please try again. ");
            System.out.println("Would you like to start again? (Y/N):");
            restart = reader.nextLine();
        }
        return restart.equals("Y");
    }

    public static boolean isInteger(String input) {
        try {
            Integer.parseInt(input);

        } catch(Exception e) {
            return false;
        }
        return true;
    }

    public static boolean isRange(int i){
        return i > 0 && i < 6;
    }

    public static boolean checkInfoExist(int command) {
        RunCommand commandExecutor = new RunCommand();
        if (command == 2) {
            HashMap personalData = (HashMap) commandExecutor.retrieveUser("personal data");
            return personalData.containsKey("activity level");
        }
        else{
            return false;
        }
    }

    public static String activityLevel(Scanner reader){

        System.out.println("Please enter your daily activity level: (Please enter a number from 1 to 4) \n" +
                " 1. Sedentary \n" +
                " 2. Low Active \n" +
                " 3. Active \n" +
                " 4. Very Active \n" );
        String userActivityLevel = reader.nextLine();

        switch (userActivityLevel) {
            case "1": return "Sedentary";
            case "2": return "Low Active";
            case "3": return "Active";
            case "4": return "Very Active";
            default: return "Enter Again";
        }
    }

}
