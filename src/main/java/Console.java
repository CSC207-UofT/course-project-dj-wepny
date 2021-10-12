package main.java;

import java.util.Scanner;

public class Console {

    /*
      This class interact with the users and receives their input, then it sends
      the inputs to the controller class.
     */

    // Maybe implement an interface? If so, what would be the name of it?
    // What would it do?

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

        System.out.println("Please enter your age:");
        String age = reader.nextLine();
        while (Integer.parseInt(age) < 0){
            System.out.println("Invalid input, please try again.");
            System.out.println("Please enter your age:");
            age = reader.nextLine();
        }

        System.out.println("Please enter your gender (M/F):");
        String gender = reader.nextLine();
        while (!gender.equals("M") && !gender.equals("F")){
            System.out.println("Invalid input, please try again.");
            System.out.println("Please enter your gender (M/F):");
            gender = reader.nextLine();
        }

        // Note: later on we would have to make an error checker for the
        // validity of the inputs.
        // e.g age >= 0, Gender must follow the type of M or F, etc.

        return new String[]{name, age, gender};
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
        System.out.println("Please enter your height (in cm):");
        String height = reader.nextLine();
        while(Float.parseFloat(height) < 0){
            System.out.println("Invalid input, please try again.");
            System.out.println("Please enter your height (in cm):");
            height = reader.nextLine();
        }

        System.out.println("Please enter your weight (in kg):");
        String weight = reader.nextLine();
        while(Float.parseFloat(weight) < 0){
            System.out.println("Invalid input, please try again.");
            System.out.println("Please enter your weight (in cm):");
            weight = reader.nextLine();
        }

        return new String[]{height, weight};
    }



    // I don't know if writing a "main" method here is appropriate.
    public static void main(String[] args) {

        Scanner reader = new Scanner(System.in);
        //Instantiate a RunCommand Class here
        RunCommand commandExecutor = new RunCommand(0);

        System.out.println("We will start from some basic information.");
        String[] basicUserInfo = getBasicUserInfo(reader);
        System.out.println("Now, we would like to know some of your personal data.");
        String[] personalUserInfo = getPersonalUserInfo(reader);

        // Pass in the two arrays to the commandExecutor, and instantiate the classes accordingly.
        commandExecutor.createUser(basicUserInfo, personalUserInfo);


        System.out.println("Welcome, " + basicUserInfo[0] + ", What would you like to do today?");
        System.out.println(" You may choose the following options: \n" +
                           " 1. Analyze Body Mass Index (BMI) \n" +
                           " 2. Analyze Energy Required per day (EER) \n" +
                           " 3. Analyze Workout \n" +
                           " 4. Analyze Disease \n" +
                           " 5. Generate a meal plan \n");
        int command = Integer.parseInt(reader.nextLine());

        // Update the commandExecutor's commandNumber variable.
        commandExecutor.setCommandNumber(command);
        // Then we do things in the commandExecutor.





    }
}
