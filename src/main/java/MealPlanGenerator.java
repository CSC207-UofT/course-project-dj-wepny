import java.util.ArrayList;
import java.util.List;

public class MealPlanGenerator {
    /*
    This class generates meal plans for user base on user input on FoodPreference,
    and based on the Energy requirement (measured in calories) of the User
     */
    // TODO: how does Energy requirement play a role here? It's not explicit on the CRC cards.

    public List<Food> generateMealPlan(User user) {
        return FoodManager.getFoodByCriteria(getFoodFilterCriteriaFrom(user));
    }

    private List<FoodFilterCriterion> getFoodFilterCriteriaFrom(User user) {
        List<FoodFilterCriterion> FoodFilterCriteriaList = new ArrayList<>();
        for (FoodFilterCriterion criterion : user.getFoodPreference().keySet()) {
            if (user.getFoodPreference().get(criterion)) {
                FoodFilterCriteriaList.add(criterion);
            }
        }
        return FoodFilterCriteriaList;
    }
}
