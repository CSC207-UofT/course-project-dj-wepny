package UseCases;

import Entities.Food;
import Entities.FoodFilterCriterion;
import Entities.IUser;
import Constants.Constants;

import java.util.ArrayList;
import java.util.List;
import java.util.*;

/**
 * This class generates meal plans for user base on user input on FoodPreference,
 * and the number of Food items to be returned.
 */
public class MealPlanGenerator implements UserAnalyzer {

    private String result;

    /**
     * Generate a list of food objects (i.e. meal plan) based on user data.
     */
    public void analyze() throws Exception {
        IUser user = UserManager.getCurrentUser();
        int numFoods = user.getNumFood();        // 1. get food from dataset that meets the user's FoodPreference.
        HashMap<String, List<Food>> foodMetCriteria = FoodManager.getFoodByCriteria(getFoodFilterCriteriaFrom(user));

        // check if the total number of food items in foodMap >= numFoods or not.
        if (!isNumberOfFoodInMapNoLessThan(foodMetCriteria, numFoods)) {
            throw new Exception("Requested number of food items greater than total food items in food map.");
        }
        // 2. perform additional filters to only keep numFoods items
        String intro = user.getUsername() + Constants.MEALPLAN_INTRO;
        StringBuilder msg = new StringBuilder();
        for (Food food : filterFoodMap(foodMetCriteria, numFoods)) {
            msg.append(food.toStrings());
        }

        this.result = Constants.DIVIDER + intro + msg + Constants.DIVIDER;
    }

    /**
     * Filter food based on the foodMap and the number of foods requested by the user by calling
     * generateFoodListGivenKey.
     *
     * @param foodMap  A hashmap the maps a string to a list of the food.
     * @param numFoods is a integer of the number of food that the user requested.
     * @return A list of Food.
     */
    private List<Food> filterFoodMap(HashMap<String, List<Food>> foodMap, int numFoods) {
        return generateFoodListGivenKeys(foodMap, numFoods, new ArrayList<>(foodMap.keySet()), new ArrayList<>(), 1);
    }

    /**
     * The function chooses a random Food object of each food type T in the list keys such that the random
     * Food objects chosen are not already in foodsChosen. If numFoods > foodMap.keySet().size(), the function
     * continues to try to choose a random Food object of each food type that is not already chosen.
     * <p>
     * The input depth is used to calculate the keys list for recursive calls of this function
     * (i.e. the new keys list used as input for a recursive call satisfies the condition that for all
     * K in keys, foodMap.get(K).size() > depth).
     * <p>
     * Precondition:
     * 1) for all key in foodMap.keySet(), foodMap.get(key).size() > 0.
     * 2) every Food object in foodMap is distinct.
     *
     * @param foodMap     is a hashmap that map a string to a list of food.
     * @param numFoods    the number of food requested by the user
     * @param keys        is a list of keys of the foodMap
     * @param foodsChosen is a list of food chosen for the user
     * @param depth       is an integer used to calculate the keys list for recursive calls of this function
     * @return the foodsChosen for the user.
     */
    private List<Food> generateFoodListGivenKeys(HashMap<String, List<Food>> foodMap, int numFoods,
                                                 List<String> keys, List<Food> foodsChosen, int depth) {

        List<String> newKeys = new ArrayList<>();
        if (numFoods == 0) {
            return foodsChosen;
        }  // early return
        for (String key : keys) {
            if (foodsChosen.size() == numFoods) {
                return foodsChosen;
            }        // return foodList if numFoods food items have been added
            List<Food> foodListOfType = foodMap.get(key);
            if (foodListOfType.size() > depth) {
                newKeys.add(key);
            }
            Food randomFood = chooseRandomFoodFromList(foodListOfType, foodsChosen);
            foodsChosen.add(randomFood);
        }

        // if numFoods >= keys.size(), add the remaining numFoods - keys.size() food items
        List<Food> foodsChosenCopy = new ArrayList<>(foodsChosen);
        foodsChosen.addAll(generateFoodListGivenKeys(foodMap, numFoods - keys.size(), newKeys,
                foodsChosenCopy, depth + 1));

        return foodsChosen;
    }

    /**
     * Generate a random Food object from the foodList that is not in foodsChosen
     *
     * @param foodList    is a random list of food
     * @param foodsChosen is a list of food already chosen for the user.
     * @return Food object that is chosen for the user
     */
    private Food chooseRandomFoodFromList(List<Food> foodList, List<Food> foodsChosen) {
        Random r = new Random();
        Food randomFood;

        do {
            randomFood = foodList.get(r.nextInt(foodList.size()));
        }
        while (foodsChosen.contains(randomFood)); //Loop continues if randomFood is in foodsChosen

        return randomFood;
    }

    /**
     * A method that check if the total number of food items in foodMap >= k or not.
     *
     * @param foodMap is a hashmap that map a string to a list of food.
     * @param k       an integer to check the number of food items against.
     * @return true if the number of food items is >= k and false otherwise.
     */
    private boolean isNumberOfFoodInMapNoLessThan(HashMap<String, List<Food>> foodMap, int k) {
        int numFood = 0;
        for (String key : foodMap.keySet()) {
            numFood += foodMap.get(key).size();
        }
        return numFood >= k;
    }

    /**
     * A method that gets the food filter criterion from the user.
     *
     * @param user the user that the mealPlanGenerator is outputting food for.
     * @return a list of FoodFilterCriterion that the user entered.
     */
    private List<FoodFilterCriterion> getFoodFilterCriteriaFrom(IUser user) {
        List<FoodFilterCriterion> FoodFilterCriteriaList = new ArrayList<>();
        //get user food preference
        for (FoodFilterCriterion criterion : user.getFoodPreference().keySet()) {
            if (user.getFoodPreference().get(criterion)) {
                FoodFilterCriteriaList.add(criterion);
            }
        }
        return FoodFilterCriteriaList;
    }

    /**
     * A getter method for the result.
     *
     * @return The potential disease for the user.
     */
    public String getResult() {
        return this.result;
    }
}
