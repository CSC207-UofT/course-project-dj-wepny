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

            String output = Console.inOut(reader);
            System.out.println(output);

            System.out.println("\nWould you like to start again? (Y/N):\n");
            String restart = reader.nextLine();

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



