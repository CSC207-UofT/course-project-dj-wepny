import java.lang.reflect.Array;
import java.util.*;

import static java.util.Objects.isNull;
/**
 * This class interact with the users and receives their input, then it sends
 *       the inputs to the controller class.
 */


public class Console {

    // TODO: move this to Constants
    public static final HashMap<Integer, UserAnalyzer> COMMANDS = new HashMap<Integer, UserAnalyzer>();

    static {
        COMMANDS.put(1, new BMIAnalyzer());
        COMMANDS.put(4, new DiseaseAnalyzer());
        // Add other functionalities here
    }

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

    public static User gatherInfo(Scanner reader) throws Exception{
        System.out.println("We will start from some basic information.");
        String[] basicUserInfo = getBasicUserInfo(reader);
        System.out.println("Now, we would like to know some of your personal data.");
        String[] personalUserInfo = getPersonalUserInfo(reader);

        User user = RunCommand.createUser(basicUserInfo, personalUserInfo);

        return user;
    }
    public static String NewUserMenu(Scanner reader, User user) throws Exception {

        // Pass in the two arrays to the commandExecutor, and instantiate the classes accordingly.
        System.out.println("Welcome, " + user.getUsername() + ", What would you like to do today?");
        System.out.println(" You may choose the following options: (Please enter a number from 1 to 5) \n" +
                " 1. Analyze Body Mass Index (BMI) \n" +
                " 2. Analyze Energy Required per day (EER) \n" +
                " 3. Analyze Workout \n" +
                " 4. Analyze Disease \n" +
                " 5. Generate a meal plan \n");

        // currently used as a test to debug, please don't delete
//        HashMap<Integer, User> allLoadedUser = UserManager.getExistingUsers();
//        User user = allLoadedUser.get(6008);
//        String[] basic = {user.getUsername(), user.getGender()};
//        String[] personal = {(String) user.getPersonalData().get("height"), (String) user.getPersonalData().get("weight"),
//                (String) user.getPersonalData().get("age")};
//        return commandExecutor.executeCommand(command, basic ,personal);

        int command = Integer.parseInt(reader.nextLine());
        while (!COMMANDS.containsKey(command)) {
            System.out.println("Sorry, your command is invalid. Please try again.");
            // TODO: Make it print the options again
            command = Integer.parseInt(reader.nextLine());
        }
        UserAnalyzer analyzer = COMMANDS.get(command);
        RunCommand commandExecutor = new RunCommand(analyzer);
        if (command == 4){
            int potentialDisease = 1000;
            ArrayList<String> currentSymptoms = new ArrayList<>();
            while(potentialDisease > 6) {
//                    System.out.println("While while while");
                    potentialDisease = commandExecutor.executeCommandDisease(user, currentSymptoms);
                    if(potentialDisease <= 6){
//                        System.out.println("Breaking");
//                        System.out.println("blahe:" + potentialDisease);

                        break;
                    }
                    //outputs how many potential disease client could have
                    // pass in empty array list if it is first round.
                    System.out.println("These are your options:");
                    Presenter analyze_results = new Presenter(analyzer);
                    System.out.println(analyze_results.retrieveOutput()); //first time giving options
                    String symptoms = reader.nextLine(); //client's input of symptoms
                    symptoms.replaceAll("[\\[\\](){}]","");
                    String SymptomsList[] = symptoms.split(",");
                    List<String> finalSymptomsList = new ArrayList<String>();
                    finalSymptomsList = Arrays.asList(SymptomsList);
                    System.out.println(finalSymptomsList);
                //convert client symptoms into a list of symptoms;
                    for(String symptom: finalSymptomsList){
                        currentSymptoms.add(symptom); //add those symptoms into the current symptoms that client has
                    }
            }
            System.out.println("These are your potential diseases");
            commandExecutor.executeCommand(user);
            Presenter analyze_results = new Presenter(analyzer);
            return analyze_results.retrieveOutput();
        }
        else {
            commandExecutor.executeCommand(user);

            Presenter analyze_results = new Presenter(analyzer);
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

        User userInfo = UserManager.getExistingUsers().get(id);

        // We probably shouldn't have duplicate code here -Naomi
        // Pass in the two arrays to the commandExecutor, and instantiate the classes accordingly.
        System.out.println("Welcome, " + userInfo.getUsername() + ", What would you like to do today?");
        System.out.println(" You may choose the following options: (Please enter a number from 1 to 5) \n" +
                " 1. Analyze Body Mass Index (BMI) \n" +
                " 2. Analyze Energy Required per day (EER) \n" +
                " 3. Analyze Workout \n" +
                " 4. Analyze Disease \n" +
                " 5. Generate a meal plan \n" +
                " 6. Edit Profile");

        int command = Integer.parseInt(reader.nextLine());
        UserAnalyzer analyzer = COMMANDS.get(command);
        RunCommand commandExecutor = new RunCommand(analyzer);
        // It shouldn't ask for basic information like their name again since it's an existing user -Naomi (resolved -J)


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
                commandExecutor.executeCommandUpdateInfo(secondCommand, userInfo, newName);
            }

            return "Your profile has been updated"; //this can be used for general cases 1-6
        }
        else{
            commandExecutor.executeCommand(command, userInfo); //regular operations from 1-5
            Presenter analyze_results = new Presenter(analyzer);
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
}
