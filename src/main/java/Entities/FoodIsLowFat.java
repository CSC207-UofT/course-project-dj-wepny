package Entities;

/**
 * Child class of FoodFilterCriterion, a condition that filters the food so that it only
 * contains food that has percentage of fat under 15% of the recommended daily value of intake.
 */
public class FoodIsLowFat extends FoodFilterCriterion{
    @Override
    public boolean isSatisfiedBy(Food food) {
        return food.getFat() < 0.2;     // i.e. food has percentage of fat under 20% of the recommended daily value of intake.
    }
}
