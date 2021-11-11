package Controllers;

import API.DiseaseAPI;
import API.FoodAPI;
import Entities.IFood;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class APIController {
    public static List<IFood> getFood(){
        List<IFood> foodList = FoodAPI.readFoodFromCSV();
        return foodList;
    }

    public static HashMap<String, Set<String>> getDisease() {
        HashMap<String, Set<String>> diseases = DiseaseAPI.readFromDiseaseCSV();
        return diseases;
    }

}