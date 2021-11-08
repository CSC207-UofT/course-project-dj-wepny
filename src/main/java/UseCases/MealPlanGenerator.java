package UseCases;

import Entities.Food;
import Entities.FoodFilterCriterion;
import Entities.User;
import java.util.ArrayList;
import java.util.List;

/**
This class generates meal plans for user base on user input on FoodPreference,
and based on the Energy requirement (measured in calories) of the User
 */
public class MealPlanGenerator {

    // TODO: does this class not implement UserAnalyzer like the other functionalities? -n

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
