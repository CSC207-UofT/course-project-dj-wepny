package UseCases;

import Controllers.APIController;
import Entities.FoodFilterCriterion;
import Entities.IFood;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * This class stores a list of food objects, and returns a new list that satisfies the criteria
 * in the amount of Carbs, Fat, Sugar, etc.
 */
public class FoodManager {

    private static final List<IFood> foodList = APIController.getFood();

    /**
     * Return a hashmap with keys being the types of food and values being the list of food objects of the type,
     * such that the food items satisfy the given criteria.
     *
     * @param criteriaList A list of criteria that the food must satisfy.
     * @return A hashmap containing Food objects that meet the criteria.
     */
    public static HashMap<String, List<IFood>> getFoodByCriteria(List<FoodFilterCriterion> criteriaList) {
        HashMap<String, List<IFood>> filteredFoodMap = new HashMap<>();

        for (IFood food : foodList) {
            if (foodSatisfiesCriteria(food, criteriaList)) {
                if (!filteredFoodMap.containsKey(food.getFoodType())) {
                    // add food to map for the first time of its type
                    filteredFoodMap.put(food.getFoodType(), new ArrayList<>());
                }
                filteredFoodMap.get(food.getFoodType()).add(food);
            }
        }
        return filteredFoodMap;
    }

    /**
     * This method returns true if the given food satisfy all conditions in a given criteria, returns
     * false otherwise.
     *
     * @param food         A food object of interest
     * @param criteriaList A list of criteria for the food to fufill.
     * @return A boolean.
     */
    public static boolean foodSatisfiesCriteria(IFood food, List<FoodFilterCriterion> criteriaList) {
        for (FoodFilterCriterion criterion : criteriaList) {
            // check if food satisfy the criterion given
            if (!criterion.isSatisfiedBy(food)) {
                return false;
            }
        }
        return true;
    }
}
