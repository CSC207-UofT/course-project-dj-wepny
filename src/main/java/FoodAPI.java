public class FoodAPI {


    /**
     * Preliminary report:
     *
     * Constructor for the Food Object reference
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
     * @param veg indicates whether this food is vegetarian friendly
     * @param score is a calculated nutrient score based on the nutrient content of the food
     *
     *              information about the csv files:
     *              branded_food: [fdc_id] to [ingredients] & [branded_food_category] mapping.
     *              (branded_food_category contains a vegetarian frozen meat category).
     *              food: [fdc_id] to [name of food] mapping; vastly incomplete [fdc_id] to [food_category_id] mapping. (> 1M entries)
     *              food_nutrient: [fdc_id] to [nutrient_id] & [amount per 100g of food] (unit of amount is not explicit) mapping.
     *              nutrient: [nutrient_nbr] to [name of nutrient] & [unit of nutrient] mapping.
     *              (nutrients contained: Energy (kcal), Carbohydrates (g), Protein (g), Total lipid (fat) (g), Sugars, Total NLEA (g)).
     *
     *              Note:
     *              We will create a new file that contains processed food data first. Then, we will use algorithms
     *              to filter food data on the file. Finally, we will load filtered food data into the app as Food
     *              objects.
     *
     *              Outline:
     *              A. Create a new (csv/text) file that contains processed food data.
     *              New file should contain the following 9 columns.
     *              fdc_id | name | branded_food_category | Energy | Carbohydrates | Protein | Total lipid (fat) | Sugars, Total NLEA | Vegetarian
     *
     *              This data wrangling process can be done separately from the app.
     *
     *              TODO: create this new file using whatever means that is the most convenient.
     *
     *              B. Create algorithms to filter food data in the new file.
     *              1. TODO: To proceed, need specifications for the algorithm.
     *
     *              C. Load filtered food data into the app as Food objects.
     *              1. Each row in the new file corresponds to a Food object (trivial).
     */
}
