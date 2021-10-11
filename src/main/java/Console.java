package main.java;

import java.util.Scanner;
// import main.java.Controller

public class Console {

    /**
     * This class interact with the users and receives their input, then it sends
     * the inputs to the controller class.
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
        System.out.println("Please enter your gender (M/F):");
        String gender = reader.nextLine();

        // Note: later on we would have to make an error checker for the
        // validity of the inputs.
        // e.g age >= 0, Gender must follow the type of M or F, etc.

        String[] result = {name, age, gender};
        return result;
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
        System.out.println("Please enter your weight (in kg):");
        String weight = reader.nextLine();

        String[] result = {height, weight};
        return result;
    }

    /**
     * A helper method that calls the controller to do operations based on command given.
     * @param command An integer that represents the option that the user wants to choose.
     */
    private static void operations(int command){
        if (command == 1){
            // We do something, most likely call the controller to let it call
            // The BMI analyzer.
        }
    }

    // I don't know if writing a "main" method here is appropriate.
    public static void main(String[] args) {

        Scanner reader = new Scanner(System.in);

        System.out.println("We will start from some basic information.");
        String[] BasicUserInfo = getBasicUserInfo(reader);
        System.out.println("Now, we would like to know some of your personal data.");
        String[] PersonalUserInfo = getPersonalUserInfo(reader);

        // Now we pass down the two arrays to the Controller class which creates the user objects
        // TODO: wait for the Controller class to be written so we can implement its method.

        System.out.println("Welcome, " + BasicUserInfo[0] + ", What would you like to do today?");
        System.out.println("You may choose the following options: \n" +
                "1. Check your BMI \n");
        int command = Integer.valueOf(reader.nextLine());

        operations(command);


    }
}
