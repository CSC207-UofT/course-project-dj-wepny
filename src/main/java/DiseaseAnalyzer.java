import java.lang.reflect.Array;
import java.util.*;

public class DiseaseAnalyzer implements UserAnalyzer{

    /*
    This class takes in the list of the symptoms of a user and outputs a list of possible diseases
    that the user may have.
     */

    private String result;
    private  static HashMap<String, Set<String>> potentialDisease = DiseaseAPI.readFromDiseaseCSV();
    // we may have to set this to be the whole hashmap at first


    @Override
    public void analyze(User user) {
        ArrayList<String> userInput = user.getRiskFactor(); //what users input
        HashMap<String, Set<String>> newDisease = newPotentialDiseases(potentialDisease, userInput);
        ArrayList<String> options = generateOptions(newDisease);
        result = options.toString();
        potentialDisease = newDisease;
    }

    @Override
    public String getResult() {
        return result;
    }


    private static HashMap<String, Set<String>> newPotentialDiseases(HashMap<String, Set<String>> oldPotentialDisease,
                                                                     ArrayList<String> chosenSymptoms){
        //given the chosen symptoms, remove the diseases that don't involve these symptoms and return
        //the possible new HashMap of diseases.

        for (String disease: oldPotentialDisease.keySet()){
            if(!oldPotentialDisease.get(disease).containsAll(chosenSymptoms)){
                oldPotentialDisease.remove(disease);
            }
        };
        return oldPotentialDisease;
    }



    private static ArrayList<String> generateOptions(HashMap<String, Set<String>> givenDisease) {
        ArrayList<String> Symptoms = new ArrayList<String>();
        // generates the options based on the given hashmap of potential diseases
//        List<String> potentialDisease = new ArrayList<String>(givenDisease.keySet());
        List<String> allSymptoms = new ArrayList<>();
        for(Set<String> symptoms: givenDisease.values()){
            for(String symptom: symptoms){
                if (!allSymptoms.contains(symptom)){
                allSymptoms.add(symptom);};
            }
        }
        int numberOfOptions = numberOfOptions(allSymptoms.size());
        Random random = new Random();
        for(int i = 0; i < numberOfOptions; i++){
            String randomSymptom = allSymptoms.get(random.nextInt(allSymptoms.size()));
            if(!Symptoms.contains(randomSymptom)){
                Symptoms.add(randomSymptom);}
        }

        return Symptoms;
    }

    private static int numberOfOptions(int numberOfSymptoms){
        // algorithm for how many options we generate.
        double optionsAmount = numberOfSymptoms;
        while (optionsAmount >= 10){
            numberOfSymptoms = numberOfSymptoms/2;
            optionsAmount = numberOfSymptoms;
        }
        return (int) Math.round(optionsAmount);
    }

    public static HashMap<String, Set<String>> getPotentialDisease() {
        return potentialDisease;
    }
}
