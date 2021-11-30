package entities;

/**
 * Child class of FoodFilterCriterion, a condition that filters the food so it only
 * contains food that has percentage of carbs under 15% of the recommended daily value of intake.
 */
public class FoodIsLowCarbs extends FoodFilterCriterion {
    @Override
    public boolean isSatisfiedBy(IFood food) {
        // i.e. food has percentage of carbs under 15% of the recommended daily value of intake.
        return food.getCarbohydrate() < 0.15;
    }
}
