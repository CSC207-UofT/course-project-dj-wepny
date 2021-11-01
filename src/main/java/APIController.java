import java.util.List;

public class APIController {
    public static List<Food> getFood(){
        List<Food> foodList = FoodAPI.readFoodFromCSV();
        return foodList;
    }

    public static Disease[] getDisease() {
        Disease[] diseases = DiseaseAPI.readFromDiseaseCSV();
        return diseases;
    }

}