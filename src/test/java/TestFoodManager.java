import Controllers.APIController;
import Entities.*;
import UseCases.FoodManager;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *This file contains Junit test case for FoodManager.java.
 **/

public class TestFoodManager {

    private static final User user = new User(2021, "Xiangling", "F", new HashMap<>());
    private static final List<FoodFilterCriterion> criteriaList = new ArrayList<>();
    private static final List<Food> foodList = APIController.getFood();

    @Before
    public void setUpForEachTest() {
        criteriaList.clear();
    }

    @Test(timeout = 5000)
    public void testGetFoodByCriteriaVegetarian() {
        criteriaList.add(new FoodIsVegetarian());
        List<Food> filterFoodList = convertMapToList(FoodManager.getFoodByCriteria(criteriaList));
        for (Food food : foodList) {
            if (FoodManager.foodSatisfiesCriteria(food, criteriaList)) {
                filterFoodList.remove(food);
            }
        }
        assert filterFoodList.isEmpty();
    }

    @Test(timeout = 5000)
    public void testGetFoodByCriteriaVegetarianAndLowCarbs() {
        criteriaList.add(new FoodIsVegetarian());
        criteriaList.add(new FoodIsLowCarbs());
        List<Food> filterFoodList = convertMapToList(FoodManager.getFoodByCriteria(criteriaList));
        for (Food food : foodList) {
            if (FoodManager.foodSatisfiesCriteria(food, criteriaList)) {
                filterFoodList.remove(food);
            }
        }
        assert filterFoodList.isEmpty();
    }


    /*
     * Helpers
     * */
    private List<Food> convertMapToList(HashMap<String, List<Food>> foodMap) {
        List<Food> foodList = new ArrayList<>();
        for (String key : foodMap.keySet()) {
            foodList.addAll(foodMap.get(key));
        }
        return foodList;
    }
}
