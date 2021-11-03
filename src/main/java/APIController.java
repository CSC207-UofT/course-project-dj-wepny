import java.util.List;

/**
 This class is responsible for getting the data from the APIs.
*/

public class APIController {


    public static List<Food> getFood(){
        return FoodAPI.readFoodFromCSV();
    }

    public static Disease[] getDisease() {
        return DiseaseAPI.readFromDiseaseCSV();
    }

}