import java.util.ArrayList;
import java.util.List;

public class FoodManager {
    /*
    This class stores a list of food objects. TODO: write better class description.
     */

    private static final List<Food> foodList = FoodAPI.readFoodFromCSV();   // Could this initialization take too much time?

    public static List<Food> getFoodByCriteria(List<FoodFilterCriterion> criteriaList) {
        List<Food> filteredFoodList = new ArrayList<>();
        for (Food food : foodList) {
            if (foodSatisfiesCriteria(food, criteriaList)) {
                filteredFoodList.add(food);
            }
        }
        return filteredFoodList;
    }

    private static boolean foodSatisfiesCriteria(Food food, List<FoodFilterCriterion> criteriaList) {
        for (FoodFilterCriterion criterion : criteriaList) {
            if (!criterion.isSatisfiedBy(food)) {return false;}
        }
        return true;
    }
}
