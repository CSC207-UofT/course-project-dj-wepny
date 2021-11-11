package Entities;

/**
 * Child class of FoodFilterCriterion, a condition that filters the food so it only
 * contains food that has no "meat" in its name
 */
public class FoodIsVegetarian extends FoodFilterCriterion {
    @Override
    public boolean isSatisfiedBy(Food food) {
        return food.getVegFriendly();
    }
}
