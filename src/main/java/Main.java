import java.util.Scanner;


public class Main {

    public static void main(String[] args) throws Exception {

        while (true) {
            Scanner reader = new Scanner(System.in);
            String output = Console.inOut(reader);
            System.out.println(output);

            System.out.println("\nWould you like to start again? (Y/N):\n");
            String restart = reader.nextLine();

            while(!restart.equals("N") & !restart.equals("Y")){
                System.out.println("Invalid input, please try again. ");
                System.out.println("Would you like to start again? (Y/N):");
                restart = reader.nextLine();
            }
            if (restart.equals("N")) break;
        }
    }
}



