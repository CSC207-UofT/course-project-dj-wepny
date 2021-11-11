package Entities;

/**
 * Child class of FoodFilterCriterion, a condition that filters the food so it only
 * contains food that has percentage of sugar under 20% of the recommended daily value of intake.
 */
public class FoodIsLowSugar extends FoodFilterCriterion{
    @Override
    public boolean isSatisfiedBy(Food food) {
        return food.getSugar() < 0.2;     // i.e. food has percentage of sugar under 20% of the recommended daily value of intake.
    }
}
