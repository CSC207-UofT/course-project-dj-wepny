import Entities.Food;
import Entities.IFood;
import Entities.User;
import UseCases.MealPlanGenerator;
import UseCases.UserManager;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.*;

/**
 *This file contains Junit test case for MealPlanGenerator.java.
 **/

public class TestMealPlanGenerator {
    private static final MealPlanGenerator mealPlanGenerator = new MealPlanGenerator();
    private static final HashMap<String, List<IFood>> foodMapTypical = new HashMap<>();
    private static final HashMap<String, List<IFood>> foodMapOneType = new HashMap<>();
    private static final HashMap<String, List<IFood>> foodMapAsymmetric = new HashMap<>();
    private static final String foodType1 = "foodType1";
    private static final String foodType2 = "foodType2";
    private static final String foodType3 = "foodType3";
    private final HashMap<String, Integer> numFoodOfTypeMap = new HashMap<>();
    private List<IFood> foodList = new ArrayList<>();
    private List<Integer> expectedNumFoodOfType = new ArrayList<>();

    @BeforeClass
    public static void setUp() {
        // create foodMaps for each test
        Food food1 = new Food("food1", -1, -1, -1, -1, foodType1, "1", true);
        Food food2 = new Food("food2", -1, -1, -1, -1, foodType1, "2", true);
        Food food3 = new Food("food3", -1, -1, -1, -1, foodType2, "3", true);
        Food food4 = new Food("food4", -1, -1, -1, -1, foodType2, "4", false);
        Food food5 = new Food("food5", -1, -1, -1, -1, foodType3, "5", false);
        Food food6 = new Food("food6", -1, -1, -1, -1, foodType3, "6", false);
        Food food7 = new Food("food6", -1, -1, -1, -1, foodType3, "7", false);


        // foodMap for testFilterFoodMapTypical
        foodMapTypical.put(foodType1, Arrays.asList(food1, food2));
        foodMapTypical.put(foodType2, Arrays.asList(food3, food4));
        foodMapTypical.put(foodType3, Arrays.asList(food5, food6));

        // foodMap for testFilterFoodMapOneType
        foodMapOneType.put(foodType3, Arrays.asList(food5, food6, food7));

        // foodMap for testFilterFoodMapAsymmetric
        foodMapAsymmetric.put(foodType1, List.of(food1));
        foodMapAsymmetric.put(foodType2, Arrays.asList(food3, food4));
        foodMapAsymmetric.put(foodType3, Arrays.asList(food5, food6, food7));
    }

    @Before
    public void setUpForEachTest() {
        // reset numFoodOfTypeMap, expectedNumFoodOfType, and foodList for each test
        numFoodOfTypeMap.put(foodType1, 0);
        numFoodOfTypeMap.put(foodType2, 0);
        numFoodOfTypeMap.put(foodType3, 0);
        foodList.clear();
        expectedNumFoodOfType.clear();
    }

    @Test(timeout = 500)
    public void testFilterFoodMapTypical1() {
        foodList = mealPlanGenerator.filterFoodMap(foodMapTypical, 0);
        updateNumFoodOfTypeMap(foodList);
        expectedNumFoodOfType = new ArrayList<>(Arrays.asList(0, 0, 0));

        assert checkNumFoodOfTypeIsCorrect(numFoodOfTypeMap, expectedNumFoodOfType);
    }

    @Test(timeout = 500)
    public void testFilterFoodMapTypical2() {
        foodList = mealPlanGenerator.filterFoodMap(foodMapTypical, 2);
        updateNumFoodOfTypeMap(foodList);
        expectedNumFoodOfType = new ArrayList<>(Arrays.asList(0, 1, 1));

        assert checkNumFoodOfTypeIsCorrect(numFoodOfTypeMap, expectedNumFoodOfType);
    }

    @Test(timeout = 500)
    public void testFilterFoodMapTypical3() {
        foodList = mealPlanGenerator.filterFoodMap(foodMapTypical, 3);
        updateNumFoodOfTypeMap(foodList);
        expectedNumFoodOfType = new ArrayList<>(Arrays.asList(1, 1, 1));

        assert checkNumFoodOfTypeIsCorrect(numFoodOfTypeMap, expectedNumFoodOfType);
    }

    @Test(timeout = 500)
    public void testFilterFoodMapTypical4() {
        foodList = mealPlanGenerator.filterFoodMap(foodMapTypical, 5);
        updateNumFoodOfTypeMap(foodList);
        expectedNumFoodOfType = new ArrayList<>(Arrays.asList(2, 2, 1));

        assert checkNumFoodOfTypeIsCorrect(numFoodOfTypeMap, expectedNumFoodOfType);
    }

    @Test(timeout = 500)
    public void testFilterFoodMapTypical5() {
        foodList = mealPlanGenerator.filterFoodMap(foodMapTypical, 6);
        updateNumFoodOfTypeMap(foodList);
        expectedNumFoodOfType = new ArrayList<>(Arrays.asList(2, 2, 2));

        assert checkNumFoodOfTypeIsCorrect(numFoodOfTypeMap, expectedNumFoodOfType);
    }

    @Test(timeout = 500)
    public void testFilterFoodMapOneType1() {
        foodList = mealPlanGenerator.filterFoodMap(foodMapOneType, 0);
        updateNumFoodOfTypeMap(foodList);
        expectedNumFoodOfType = new ArrayList<>(Arrays.asList(0, 0, 0));

        assert checkNumFoodOfTypeIsCorrect(numFoodOfTypeMap, expectedNumFoodOfType);
    }

    @Test(timeout = 500)
    public void testFilterFoodMapOneType2() {
        foodList = mealPlanGenerator.filterFoodMap(foodMapOneType, 2);
        updateNumFoodOfTypeMap(foodList);
        expectedNumFoodOfType = new ArrayList<>(Arrays.asList(2, 0, 0));

        assert checkNumFoodOfTypeIsCorrect(numFoodOfTypeMap, expectedNumFoodOfType);
    }

    @Test(timeout = 500)
    public void testFilterFoodMapOneType3() {
        foodList = mealPlanGenerator.filterFoodMap(foodMapOneType, 3);
        updateNumFoodOfTypeMap(foodList);
        expectedNumFoodOfType = new ArrayList<>(Arrays.asList(3, 0, 0));

        assert checkNumFoodOfTypeIsCorrect(numFoodOfTypeMap, expectedNumFoodOfType);
    }

    @Test(timeout = 500)
    public void testFilterFoodMapAsymmetric1() {
        foodList = mealPlanGenerator.filterFoodMap(foodMapAsymmetric, 2);
        updateNumFoodOfTypeMap(foodList);
        expectedNumFoodOfType = new ArrayList<>(Arrays.asList(1, 1, 0));

        assert checkNumFoodOfTypeIsCorrect(numFoodOfTypeMap, expectedNumFoodOfType);
    }

    @Test(timeout = 500)
    public void testFilterFoodMapAsymmetric2() {
        foodList = mealPlanGenerator.filterFoodMap(foodMapAsymmetric, 4);
        updateNumFoodOfTypeMap(foodList);
        expectedNumFoodOfType = new ArrayList<>(Arrays.asList(1, 2, 1));

        assert checkNumFoodOfTypeIsCorrect(numFoodOfTypeMap, expectedNumFoodOfType);
    }

    @Test(timeout = 500)
    public void testFilterFoodMapAsymmetric3() {
        foodList = mealPlanGenerator.filterFoodMap(foodMapAsymmetric, 5);
        updateNumFoodOfTypeMap(foodList);
        expectedNumFoodOfType = new ArrayList<>(Arrays.asList(1, 2, 2));

        assert checkNumFoodOfTypeIsCorrect(numFoodOfTypeMap, expectedNumFoodOfType);
    }

    @Test(timeout = 500)
    public void testFilterFoodMapAsymmetric4() {
        foodList = mealPlanGenerator.filterFoodMap(foodMapAsymmetric, 6);
        updateNumFoodOfTypeMap(foodList);
        expectedNumFoodOfType = new ArrayList<>(Arrays.asList(1, 2, 3));

        assert checkNumFoodOfTypeIsCorrect(numFoodOfTypeMap, expectedNumFoodOfType);
    }

    @Test(timeout = 5000, expected = Exception.class)
    public void testAnalyzeThrowException() throws Exception {
        // initiate a new user
        User user = new User(1931, "John P Wintergreen", "M", new HashMap<>());
        UserManager.addUser(true, user);
        user.setNumFoods(1000000);
        mealPlanGenerator.analyze();
    }

    /*
    * Helpers
    * */
    private void updateNumFoodOfTypeMap(List<IFood> foodList) {
        for (IFood food : foodList) {
            numFoodOfTypeMap.put(food.getFoodType(), numFoodOfTypeMap.get(food.getFoodType()) + 1);
        }
    }

    private boolean checkNumFoodOfTypeIsCorrect(HashMap<String, Integer> output, List<Integer> expected) {
        for (int value : output.values()) {
            if (expected.contains(value)) {
                expected.remove(Integer.valueOf(value));
            } else {
                return false;
            }
        }
        return true;
    }
}