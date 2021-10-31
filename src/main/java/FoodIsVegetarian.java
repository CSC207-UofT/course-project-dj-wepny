public class FoodIsVegetarian extends FoodFilterCriterion {
    @Override
    boolean isSatisfiedBy(Food food) {
        return food.getVegFriendly();
    }
}
