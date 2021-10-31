import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws Exception {

        while (true) {
            Scanner reader = new Scanner(System.in);

            try {
                ArrayList<String> userInfo = UserParser.readUserInfo();
                System.out.println(userInfo);
                if (!userInfo.isEmpty()){
                    UserController.readExistingUser(userInfo);
                }
            }
            catch (Exception exception) {
                System.out.println("Uh oh, an error has occurred! " +
                        "Please check that the files exist and are in the specified position.");
            }

            if (!Console.checkExisting(reader)){
                String output = Console.inOutNewUser(reader);
                System.out.println(output);
            }
            else{
                Console.inOutExistingUser(reader);

            }
            //We should probably put this as a separate method in Console? -Jenny, cuz main method needs to be short

            String output = Console.inOutNewUser(reader);
            System.out.println(output);

            System.out.println("\nWould you like to start again? (Y/N):\n");
            String restart = reader.nextLine();

            //if they don't want to restart, adds its info back into the file
            while(!restart.equals("N") & !restart.equals("Y")){
                System.out.println("Invalid input, please try again. ");
                System.out.println("Would you like to start again? (Y/N):");
                restart = reader.nextLine();
            }
            if (restart.equals("N")){
               UserParser.updateUserInfo();
               break;
            }
        }
    }

}



