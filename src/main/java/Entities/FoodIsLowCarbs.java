package Entities;

public class FoodIsLowCarbs extends FoodFilterCriterion {
    @Override
    public boolean isSatisfiedBy(IFood food) {
        return food.getCarbohydrate() < 0.15;     // i.e. food has percentage of carbs under 15% of the recommended daily value of intake.
    }
}
