package controllers;

import api.DiseaseAPI;
import api.FoodAPI;
import entities.IFood;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

/**
 * A Controller Class that is responsible for directing the information obtained from the
 * API to the corresponding use cases
 */
public class APIController {
    /**
     * A function that returns all the food objects read from the FoodAPI
     * @return a List containing all the Food Objects created from the food csv file
     */
    public static List<IFood> getFood(){
        return FoodAPI.readFoodFromCSV();
    }

    /**
     * A function that returns all the disease read from the FoodAPI in a Hashmap
     * @return a Hashmap containing all the diseases mapped to their symptoms
     */
    public static HashMap<String, Set<String>> getDisease() {
        return DiseaseAPI.readFromDiseaseCSV();
    }

}