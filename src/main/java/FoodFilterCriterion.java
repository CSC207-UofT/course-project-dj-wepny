/**
 * This class sets up a framework for other criteria of nutrition such as Low Carbs,
 * Low Fat, Low Sugar, etc.
 */

abstract public class FoodFilterCriterion {
    abstract boolean isSatisfiedBy(Food food);
}
