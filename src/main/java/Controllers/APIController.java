package Controllers;

import API.DiseaseAPI;
import API.FoodAPI;
import Entities.Food;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class APIController {
    public static List<Food> getFood(){
        List<Food> foodList = FoodAPI.readFoodFromCSV();
        return foodList;
    }

    public static HashMap<String, Set<String>> getDisease() {
        HashMap<String, Set<String>> diseases = DiseaseAPI.readFromDiseaseCSV();
        return diseases;
    }

}