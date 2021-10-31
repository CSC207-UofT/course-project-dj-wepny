import java.util.ArrayList;
import java.util.List;

public class DiseaseAnalyzer implements UserAnalyzer{

    /*
    This class takes in the list of the symptoms of a user and outputs a list of possible diseases
    that the user may have.
     */

    private String result;


    @Override
    public void analyze(User user) {

        // Initializes a result ArrayList.
        ArrayList<String> possibleDiseaseList = new ArrayList<>();
        // We first create a list of diseases using DiseaseAPI.
        // Note that we are directly accessing the API in this use case.
        Disease[] diseases = DiseaseAPI.readFromDiseaseCSV();

        // Get the list of symptoms Based on the user input.
        // Assuming that the key of the Hashmap riskFactor is the username
        // and the value is a list of symptoms that the user has.
        String username = user.getUsername();
        ArrayList<String> userSymptoms = (ArrayList<String>) user.getRiskFactor().get(username);

        //check if any of the disease in the diseases list contains all the symptoms in userSymptoms.
        for (Disease d: diseases){
            if (d.getSymptoms().containsAll(userSymptoms)){
                possibleDiseaseList.add(d.getDisease());
            }
        }

        result = "Based on your symptoms, you may have: " + possibleDiseaseList;
    }

    @Override
    public String getResult() {
        return result;
    }

}
