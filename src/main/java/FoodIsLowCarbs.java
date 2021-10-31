public class FoodIsLowCarbs extends FoodFilterCriterion {
    @Override
    boolean isSatisfiedBy(Food food) {
        return food.getCarbohydrate() < 0.15;     // i.e. food has percentage of carbs under 15% of the recommended daily value of intake.
    }
}
