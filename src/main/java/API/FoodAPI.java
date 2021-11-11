package API;

import Constants.Constants;
import Entities.Food;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
/**
  This class reads the CSV file of all food data, extracts the variables of interest, and create
  Food objects and List of Food objects.
 **/

public class FoodAPI {

    /**
     * Helper method to convert from String to Double. Returns 0 if input.equals("NULL").
     * @param input A string, either a number with decimals or "NULL".
     * @return A double variable.
     */
    private static Double convertFromStringToDouble(String input){
        if (!input.equals("NULL")){
            return Double.parseDouble(input);
        }
        return 0.0;
    }

    /**
     * Creates a food object based on the data provided.
     * @param metadata An array of strings with data of interest.
     * @return A food object.
     */
    private static Food createFood(String[] metadata){
        String id =  metadata[0];
        String name = metadata[1];
        String foodType = metadata[2];
        double calories = convertFromStringToDouble(metadata[3]);
        double fat = convertFromStringToDouble(metadata[4]) / Constants.FAT_PER_DAY;
        double proteins = convertFromStringToDouble(metadata[5]) / Constants.PROTEIN_PER_DAY;
        double carbohydrates = convertFromStringToDouble(metadata[6]) / Constants.CARB_PER_DAY;
        double sugar = convertFromStringToDouble(metadata[7]) / Constants.SUGAR_PER_DAY;
        boolean vegFriendly = (!metadata[2].contains("Meats"));

        // Create and return a new food object.
        return new Food(name, calories,carbohydrates, fat,sugar,foodType,id,vegFriendly);

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