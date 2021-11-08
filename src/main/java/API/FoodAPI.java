package API;

import Entities.Food;
import Constants.Constants;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FoodAPI {
    /*
    This class reads the CSV file of all food data, extracts the variables of interest, and create
    Food objects and List of Food objects.
     */


    private static Food createFood(String[] metadata){
        String id =  metadata[0];
        String name = metadata[1];
        String foodType = metadata[2];
        double calories = Double.parseDouble(metadata[3]);
        double fat= Double.parseDouble(metadata[4]) / Constants.FAT_PER_DAY;
        double proteins = Double.parseDouble(metadata[5]) / Constants.PROTEIN_PER_DAY;
        double carbohydrates = Double.parseDouble(metadata[6]) / Constants.CARB_PER_DAY;
        double sugar= Double.parseDouble(metadata[7]) / Constants.SUGAR_PER_DAY;
        boolean vegFriendly = (metadata[2].contains("Vegetable") || metadata[2].contains("Fruits"));

        // Note: I can't find a column in the dataset that corresponds to a nutrient score,
        // so I'm going to set it to default 0 for now.
        int nutrientScore = 0;

        // Create and return a new food object.
        return new Food(name, calories,carbohydrates,proteins,fat,sugar,foodType,id,vegFriendly,nutrientScore);

    }

    /**
     * Read from the Food CSV and create a List of Food Objects.
     * @return a List of Food Objects.
     */
    public static List<Food> readFoodFromCSV() {
        List<Food> foodList = new ArrayList<>();
        Path pathToFile = Paths.get(Constants.FOOD_DATASET_PATH);

        // create an instance of BufferedReader
        // Use a try-catch block for unexpected errors.
        try (BufferedReader br = Files.newBufferedReader(pathToFile, StandardCharsets.US_ASCII)) {

            // We are going to skip the first four lines because they don't have the date format we are looking for.
            // Loop through the first 4 lines, then initialize line, so it corresponds to line 5 of the file.
            for (int i = 1; i <= 4; i++){
                br.readLine();
            }
            String line = br.readLine();

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