package Entities;

public class FoodIsVegetarian extends FoodFilterCriterion {
    @Override
    public boolean isSatisfiedBy(IFood food) {
        return food.getVegFriendly();
    }
}
