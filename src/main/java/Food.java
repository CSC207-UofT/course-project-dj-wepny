import java.util.Objects;

/**
 * This class stores a Food Object from the database,
 * describing its nutrient level
 */
public class Food {
    private final String foodName;
    private final double calories;
    private final double carbohydrate;
    private final double proteins;
    private final double fat;
    private final double sugar;
    private final String foodType;
    private final String id;
    private final boolean vegFriendly;
    private final int nutrientScore;

    /**
     * Constructor for the Food Object
     *
     * @param name is the name of the food
     * @param calories is how many calories a serving of this food contains
     * @param carbs is a percentage that describes how much carbohydrate this food contains
     *              as a ratio of recommended carbohydrate/day
     * @param protein is a percentage that describes how much protein this food contains
     *      *       as a ratio of recommended protein/day
     * @param fat is a percentage that describes how much fat this food contains
     *      *       as a ratio of recommended fat/day
     * @param sugar is a percentage that describes how much sugar this food contains
     *              as a ratio of recommended sugar/day
     * @param type is the type of the food
     * @param id is a number that is unique to each food
     * @param veg indicates whether this food is vegeterianFriendly
     * @param score is a calculated nutrient score based on the nutrient content of the food
     */
    public Food(String name, double calories, double carbs, double protein, double fat, double sugar,
                String type, String id, boolean veg, int score){

        this.foodName = name;
        this.calories = calories;
        this.carbohydrate = carbs;
        this.proteins = protein;
        this.fat = fat;
        this.sugar = sugar;
        this.foodType = type;
        this.id = id;
        this.vegFriendly = veg;
        this.nutrientScore = score;

    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj){
            return true;
        }
        if (obj == null){
            return false;
        }
        if (this.getClass() != obj.getClass()){
            return false;
        }

        Food other = (Food) obj;
        return Objects.equals(this.id, other.id);
    }


    // Getters and setters for the private variables:

    public String getFoodName() {
        return foodName;
    }

    public double getCalories() {
        return calories;
    }

    public double getCarbohydrate() {
        return carbohydrate;
    }

    public double getProteins() {
        return proteins;
    }

    public double getSugar() {
        return sugar;
    }

    public int getNutrientScore() {
        return nutrientScore;
    }

    public String getFoodType() {
        return foodType;
    }

    public String getId() {
        return id;
    }

    public double getFat() {
        return fat;
    }

    public boolean getVegFriendly(){
        return vegFriendly;
    }
}
