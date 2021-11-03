import java.util.ArrayList;
import java.util.List;

/**
  * This class stores a list of food objects, and returns a new list that satisfies the criteria
  * in the amount of Carbs, Fat, Sugar, etc.
  */
public class FoodManager {


    private static final List<Food> foodList = APIController.getFood();

    /**
     * Return a list of food objects that satisfies a given criteria.
     * @param criteriaList A list of criteria that the food must satisfy.
     * @return A list of food objects that meet the criteria.
     */
    public static List<Food> getFoodByCriteria(List<FoodFilterCriterion> criteriaList) {
        List<Food> filteredFoodList = new ArrayList<>();
        for (Food food : foodList) {
            if (foodSatisfiesCriteria(food, criteriaList)) {
                filteredFoodList.add(food);
            }
        }
        return filteredFoodList;
    }

    /**
     * This method returns true if the given food satisfy all conditions in a given criteria, returns
     * false otherwise.
     * @param food A food object of interest
     * @param criteriaList A list of criteria for the food to fufill.
     * @return A boolean.
     */
    private static boolean foodSatisfiesCriteria(Food food, List<FoodFilterCriterion> criteriaList) {
        for (FoodFilterCriterion criterion : criteriaList) {
            if (!criterion.isSatisfiedBy(food)) {return false;}
        }
        return true;
    }
}
