package Entities;

public class FoodIsVegetarian extends FoodFilterCriterion {
    @Override
    public boolean isSatisfiedBy(Food food) {
        return food.getVegFriendly();
    }
}
