import java.util.Scanner;


public class Main {

    public static void main(String[] args) throws Exception {

        while (true) {
            Scanner reader = new Scanner(System.in);
            String output = Console.inOut(reader);
            System.out.println(output);

            System.out.println("\nWould you like to start again? (Y/N):\n");
            String restart = reader.nextLine();

            if (restart.equals("N")) break;
        }
    }
}



