import java.util.Scanner;
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


        while(Float.parseFloat(height) < 0){
            System.out.println("Invalid input, please try again.");
            System.out.println("Please enter your height (in m):");
            height = reader.nextLine();
        }

        System.out.println("Please enter your weight (in kg):");
        String weight = reader.nextLine();
        while(Float.parseFloat(weight) < 0){
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

    public static String inOut(Scanner reader) throws Exception{
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

        // pass the command number into the commandExecutor
        String output = commandExecutor.executeCommand(command, basicUserInfo, personalUserInfo);
//        System.out.println(output);

        return output;
    }
//
//    public static void main(String[] args) throws Exception {
//        Scanner reader = new Scanner(System.in);
//        //Instantiate a RunCommand Class and a Presenter Class here
//        RunCommand commandExecutor = new RunCommand();
//        // Presenter displayResult = new Presenter();
//
//        System.out.println("We will start from some basic information.");
//        String[] basicUserInfo = getBasicUserInfo(reader);
//        System.out.println("Now, we would like to know some of your personal data.");
//        String[] personalUserInfo = getPersonalUserInfo(reader);
//
//        // Pass in the two arrays to the commandExecutor, and instantiate the classes accordingly.
//
//        System.out.println("Welcome, " + basicUserInfo[0] + ", What would you like to do today?");
//        System.out.println(" You may choose the following options: \n" +
//                " 1. Analyze Body Mass Index (BMI) \n" +
//                " 2. Analyze Energy Required per day (EER) \n" +
//                " 3. Analyze Workout \n" +
//                " 4. Analyze Disease \n" +
//                " 5. Generate a meal plan \n");
//        int command = Integer.parseInt(reader.nextLine());
//
//        // pass the command number into the commandExecutor
//        String output = commandExecutor.executeCommand(command, basicUserInfo, personalUserInfo);
//        // Then we do things in the commandExecutor.
//        System.out.println(output);
//
//
//
//    }
}
