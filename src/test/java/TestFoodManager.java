import controllers.APIController;
import entities.*;
import usecases.FoodManager;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *This file contains Junit test case for FoodManager.java.
 **/

public class TestFoodManager {

    private static final List<FoodFilterCriterion> criteriaList = new ArrayList<>();
    private static final List<IFood> foodList = APIController.getFood();

    @Before
    public void setUpForEachTest() {
        criteriaList.clear();
    }

    @Test(timeout = 5000)
    public void testGetFoodByCriteriaVegetarian() {
        criteriaList.add(new FoodIsVegetarian());
        List<IFood> filterFoodList = convertMapToList(FoodManager.getFoodByCriteria(criteriaList));
        for (IFood food : foodList) {
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
        List<IFood> filterFoodList = convertMapToList(FoodManager.getFoodByCriteria(criteriaList));
        for (IFood food : foodList) {
            if (FoodManager.foodSatisfiesCriteria(food, criteriaList)) {
                filterFoodList.remove(food);
            }
        }
        assert filterFoodList.isEmpty();
    }

    @Test(timeout = 5000)
    public void testGetFoodByCriteriaLowFat() {
        criteriaList.add(new FoodIsLowFat());
        List<IFood> filterFoodList = convertMapToList(FoodManager.getFoodByCriteria(criteriaList));
        for (IFood food : foodList) {
            if (FoodManager.foodSatisfiesCriteria(food, criteriaList)) {
                filterFoodList.remove(food);
            }
        }
        assert filterFoodList.isEmpty();
    }

    @Test(timeout = 5000)
    public void testGetFoodByCriteriaLowSugar() {
        criteriaList.add(new FoodIsLowSugar());
        List<IFood> filterFoodList = convertMapToList(FoodManager.getFoodByCriteria(criteriaList));
        for (IFood food : foodList) {
            if (FoodManager.foodSatisfiesCriteria(food, criteriaList)) {
                filterFoodList.remove(food);
            }
        }
        assert filterFoodList.isEmpty();
    }


    /*
     * Helpers
     * */
    private List<IFood> convertMapToList(HashMap<String, List<IFood>> foodMap) {
        List<IFood> foodList = new ArrayList<>();
        for (String key : foodMap.keySet()) {
            foodList.addAll(foodMap.get(key));
        }
        return foodList;
    }
}
