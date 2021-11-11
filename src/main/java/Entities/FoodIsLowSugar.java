package Entities;

public class FoodIsLowSugar extends FoodFilterCriterion{
    @Override
    public boolean isSatisfiedBy(IFood food) {
        return food.getSugar() < 0.2;     // i.e. food has percentage of sugar under 20% of the recommended daily value of intake.
    }
}
