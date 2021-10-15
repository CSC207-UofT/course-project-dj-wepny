import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ReadFile {

    public static void main(String... args) {
        List<Food> foodItems = readFoodFromCSV("sample2.txt"); // let's print all the person read from CSV file
        for (Food b : foodItems) { System.out.println(b);}}



        // let's print all the person read from CSV file for (Book b : books) { System.out.println(b); } }
       private static List<Food> readFoodFromCSV(String fileName) {
           List<Food> foodList = new ArrayList<>();
           Path pathToFile = Paths.get(fileName);
           // create an instance of BufferedReader
           // using try with resource, Java 7 feature to close resources
           try (BufferedReader br = Files.newBufferedReader(pathToFile, StandardCharsets.US_ASCII)) {
               String line = br.readLine();

               while (line != null) {
                   String[] attributes = line.split(",");
                   Food foodItem = createFood(attributes);
                   foodList.add(foodItem);
                   line = br.readLine(); //read next line before looping
               }}
           catch (IOException ioe) { ioe.printStackTrace(); }
           return foodList;

            }

      private static Food createFood(String[] metadata){
        double calories = Double.parseDouble(metadata[1]);
        double carbohydrates = Double.parseDouble(metadata[2]);
        double proteins = Double.parseDouble(metadata[3]);
        double fat= Double.parseDouble(metadata[4]);
        double sugar= Double.parseDouble(metadata[5]);

        String foodType = metadata[6];
        String id =  metadata[0];
        boolean vegFriendly = metadata[7] == "true";
        int nutrientScore = Integer.parseInt(metadata[8]);
                   // create and return book of this metadata return new Book(name, price, author); }
          return new Food(calories,carbohydrates,proteins,fat,sugar,foodType,id,vegFriendly,nutrientScore);

    }
}