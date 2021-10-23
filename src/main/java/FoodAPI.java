import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FoodAPI {

    private static Food createFood(String[] metadata){
        String id =  metadata[0];
        String name = metadata[1];
        String foodType = metadata[2];
        double calories = Double.parseDouble(metadata[3]);
        double fat= Double.parseDouble(metadata[4]);
        double proteins = Double.parseDouble(metadata[5]);
        double carbohydrates = Double.parseDouble(metadata[6]);
        double sugar= Double.parseDouble(metadata[7]);
        boolean vegFriendly = (metadata[2].contains("Vegetable") || metadata[2].contains("Fruits"));

        // Note: I can't find a column in the dataset that corresponds to a nutrient score,
        // so I'm going to set it to default 0 for now.
        int nutrientScore = 0;

        // Create and return a new food object.
        return new Food(name, calories,carbohydrates,proteins,fat,sugar,foodType,id,vegFriendly,nutrientScore);

    }

    private static List<Food> readFoodFromCSV() {
        List<Food> foodList = new ArrayList<>();
        Path pathToFile = Paths.get("sample.csv");

        // create an instance of BufferedReader
        // Use a try-catch block for unexpected errors.
        try (BufferedReader br = Files.newBufferedReader(pathToFile, StandardCharsets.US_ASCII)) {

            // We are going to skip the first four lines because they don't have the date format we are looking for.
            // Initialize a line, then use loop to skip the next three lines.
            String line = br.readLine();
            for (int i = 1; i <= 4; i++){
                line = br.readLine();
            }
            // By the end of this loop, line corresponds to line 5 of the Datafile.

            // Now we create food objects and append them to the list.
            while (line != null) {
                String[] attributes = line.split(",");
                Food foodItem = createFood(attributes);
                foodList.add(foodItem);
                line = br.readLine(); //read next line before looping
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return foodList;
    }

}