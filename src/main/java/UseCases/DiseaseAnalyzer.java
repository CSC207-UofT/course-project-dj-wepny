package UseCases;

import API.DiseaseAPI;
import Entities.User;
import java.lang.reflect.Array;
import java.util.*;

/**
This class takes in the list of the symptoms of a user and outputs a list of possible diseases
that the user may have.
 */

public class DiseaseAnalyzer implements UserAnalyzer{
    private User user;
    private String result;
    private  static HashMap<String, Set<String>> potentialDisease = DiseaseAPI.readFromDiseaseCSV();

    public DiseaseAnalyzer(User user){
        this.user = user;
    }
    public DiseaseAnalyzer(){}
    @Override
    public void analyze() {
        User user = UserManager.getCurrentUser();
        if(user == null){
            user = this.user;
        }
        ArrayList<String> userInput = user.getRiskFactor(); //what users input
        HashMap<String, Set<String>> newDisease = newPotentialDiseases(potentialDisease, userInput);
        ArrayList<String> options = generateOptions(newDisease);
        String msg = "These are your potential diseases: (if output = [], " +
                "there is no disease that match the current symptoms you are experiencing)\n";
        if (potentialDisease.size() <= 6){result = msg + newDisease.keySet().toString();}
        else{result = options.toString();}
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
        ArrayList<String> removeDiseases = new ArrayList<String>();
        for (String disease: oldPotentialDisease.keySet()){
            if(!oldPotentialDisease.get(disease).containsAll(chosenSymptoms)){
                removeDiseases.add(disease);
            }
        }

        removeDiseases.forEach(oldPotentialDisease.keySet()::remove);
        return oldPotentialDisease;
    }


    private static ArrayList<String> generateOptions(HashMap<String, Set<String>> givenDisease) {
        ArrayList<String> Symptoms = new ArrayList<String>();
        // generates the options based on the given hashmap of potential diseases
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

    public static void resetPotentialDisease(){
        potentialDisease = DiseaseAPI.readFromDiseaseCSV();
    }
}
