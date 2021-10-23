import java.util.Scanner;

import static java.util.Objects.isNull;

/**
 * This class interact with the users and receives their input, then it sends
 *       the inputs to the controller class.
 */


public class Console {

    /**
     * A helper method that prompts the user for their basic information.
     * Returns an array of strings in the order of [name, age, gender].
     * Note that this method is subject to change (Maybe ArrayList instead of Array).
     * @param reader The scanner used for the user input.
     * @return an array of strings of some basic information about the user.
     */
    private static String[] getBasicUserInfo(Scanner reader){
        System.out.println("Please enter your name:");
        String name = reader.nextLine();

        System.out.println("Please enter your gender (M/F):");
        String gender = reader.nextLine();

        while (!gender.equals("M") && !gender.equals("F")){
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
     * @param reader The scanner used for the user input.
     * @return an array of strings of some personal data of the user.
     */
    private static String[] getPersonalUserInfo (Scanner reader){
        System.out.println("Please enter your height (in m):");
        String height = reader.nextLine();


        while(Float.parseFloat(height) <= 0){
            System.out.println("Invalid input, please try again.");
            System.out.println("Please enter your height (in m):");
            height = reader.nextLine();
        }

        System.out.println("Please enter your weight (in kg):");
        String weight = reader.nextLine();
        while(Float.parseFloat(weight) <= 0){
            System.out.println("Invalid input, please try again.");
            System.out.println("Please enter your weight (in kg):");
            weight = reader.nextLine();
        }

        System.out.println("Please enter your age:");
        String age = reader.nextLine();
        while (Integer.parseInt(age) < 0){
            System.out.println("Invalid input, please try again.");
            System.out.println("Please enter your age:");
            age = reader.nextLine();
        }

        return new String[]{height, weight, age};
    }

    //may have to move this to run command?
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


    public static String inOut1(Scanner reader) throws Exception{
        RunCommand commandExecutor = new RunCommand();

        System.out.println("We will start from some basic information.");
        String[] basicUserInfo = getBasicUserInfo(reader);
        System.out.println("Now, we would like to know some of your personal data.");
        String[] personalUserInfo = getPersonalUserInfo(reader);

        // Pass in the two arrays to the commandExecutor, and instantiate the classes accordingly.
        System.out.println("Welcome, " + basicUserInfo[0] + ", What would you like to do today?");
        System.out.println(" You may choose the following options: (Please enter a number from 1 to 5) \n" +
                " 1. Analyze Body Mass Index (BMI) \n" +
                " 2. Analyze Energy Required per day (EER) \n" +
                " 3. Analyze Workout \n" +
                " 4. Analyze Disease \n" +
                " 5. Generate a meal plan \n");
        int command = Integer.parseInt(reader.nextLine());

        // currently used as a test to debug, please don't delete
//        HashMap<Integer, User> allLoadedUser = UserManager.getExistingUsers();
//        User user = allLoadedUser.get(6008);
//        String[] basic = {user.getUsername(), user.getGender()};
//        String[] personal = {(String) user.getPersonalData().get("height"), (String) user.getPersonalData().get("weight"),
//                (String) user.getPersonalData().get("age")};
//        return commandExecutor.executeCommand(command, basic ,personal);

        // pass the command number into the commandExecutor
        return commandExecutor.executeCommand1(command, basicUserInfo, personalUserInfo);
    }
    public static void inOut2(Scanner reader) throws Exception{
        RunCommand commandExecutor2 = new RunCommand();

        System.out.println("Please enter your personal ID");
        String id = reader.nextLine();
        while(isNull(UserManager.getExistingUser(Integer.parseInt(id)))){
            System.out.println("Invalid ID, please enter again");
            System.out.println("Please enter your personal ID");
            id = reader.nextLine();
        }
        User userInfo = UserManager.getExistingUser(Integer.parseInt(id));

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
        if (command == 6){ //special case where user chooses to change their personal info.
            System.out.println(" You may choose the following options: (Please enter a number from 1 to 5) \n" +
                    " 1. Change Username \n" +
                    " 2. Change Food Preferences \n" +
                    " 3. Analyze Workout \n" +
                    " 4. Analyze Disease \n" +
                    " 5. Generate a meal plan \n" +
                    " 6. Edit Profile");
            int secondCommand = Integer.parseInt(reader.nextLine());
            //Code here may seem to be messy, but to make it better, I would need to place the print messages inside
            //RunCommand. Not sure if that's allowed.
            if(secondCommand == 1){
                System.out.println("Please enter your new Username");
                String newName = reader.nextLine();
                System.out.println("Thank you. Currently updating your new username.");
                commandExecutor2.executeCommand3(secondCommand, userInfo, newName);
            }

            System.out.println("Your username has been updated");//this can be used for general cases 1-6
        }
        else{

            commandExecutor2.executeCommand2(command, userInfo);//regular operations from 1-5
        }
}}
