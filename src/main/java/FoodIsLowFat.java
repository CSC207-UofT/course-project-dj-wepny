public class FoodIsLowFat extends FoodFilterCriterion{
    @Override
    boolean isSatisfiedBy(Food food) {
        return food.getFat() < 0.2;     // i.e. food has percentage of fat under 20% of the recommended daily value of intake.
    }
}