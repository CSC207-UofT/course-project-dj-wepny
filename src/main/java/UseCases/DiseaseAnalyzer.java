package UseCases;

import API.DiseaseAPI;
import Entities.IUser;
import java.util.*;

/**
 * This class takes in the list of the symptoms of a user and outputs a list of possible diseases
 * that the user may have.
 */

public class DiseaseAnalyzer implements UserAnalyzer {

    private String result;
    private static HashMap<String, Set<String>> potentialDisease = DiseaseAPI.readFromDiseaseCSV();
    
    public DiseaseAnalyzer(IUser user){
        this.user = user;
    }
    public DiseaseAnalyzer(){}

    /**
     * This method analyze the user's potential diseases using their list of symptoms.
     */
    @Override
    public void analyze() {
        IUser user = UserManager.getCurrentUser();
        if(user == null){
            user = this.user;
        }
        
        // get the symptoms of user
        ArrayList<String> userInput = user.getRiskFactor();

        HashMap<String, Set<String>> newDisease = newPotentialDiseases(potentialDisease, userInput);
        // generate new options for the user
        ArrayList<String> options = generateOptions(newDisease);
        String msg = "These are your potential diseases: (if output = [], " +
                "there is no disease that match the current symptoms you are experiencing)\n";
        // set result to the string that will be prompt to the user is potential diseases is less than or
        // equal to 6
        if (potentialDisease.size() <= 6) {
            result = msg + newDisease.keySet();
        }
        // set result to the new options of symptoms for the user to select
        else {
            result = options.toString();
        }
        potentialDisease = newDisease;

    }

    /**
     * A getter method for the result.
     *
     * @return The potential disease for the user.
     */
    @Override
    public String getResult() {
        return result;
    }

    /**
     * A helper method for analyze. For the diseases in the old potential disease list, if the disease does not contain the chosen symptoms it
     * is removed for the potential diseases.
     *
     * @param oldPotentialDisease is a Hashmap of the current of potential diseases.
     * @param chosenSymptoms      is a ArrayList of the symptoms that the user selected.
     * @return a new hashmap of potential diseases with the unwanted diseases removed.
     */
    private static HashMap<String, Set<String>> newPotentialDiseases(HashMap<String, Set<String>> oldPotentialDisease,
                                                                     ArrayList<String> chosenSymptoms) {
        // given the chosen symptoms, remove the diseases that don't involve these symptoms and return
        // the possible new HashMap of diseases.
        ArrayList<String> removeDiseases = new ArrayList<>();
        for (String disease : oldPotentialDisease.keySet()) {
            if (!oldPotentialDisease.get(disease).containsAll(chosenSymptoms)) {
                removeDiseases.add(disease);
            }
        }
        removeDiseases.forEach(oldPotentialDisease.keySet()::remove);
        return oldPotentialDisease;
    }

    /**
     * A helper function for analyze. Based on the selected potential diseases, generate a list of possible symptoms
     * for the user to choose from.
     *
     * @param givenDisease A ArrayList that is the current potential diseases based on the user's previous selection.
     * @return A list of symptoms for the user to select.
     */
    private static ArrayList<String> generateOptions(HashMap<String, Set<String>> givenDisease) {
        ArrayList<String> Symptoms = new ArrayList<>();
        // generates the options based on the given hashmap of potential diseases
        List<String> allSymptoms = new ArrayList<>();
        // add the symptoms of potential diseases to the list allSymptoms
        for (Set<String> symptoms : givenDisease.values()) {
            for (String symptom : symptoms) {
                if (!allSymptoms.contains(symptom)) {
                    allSymptoms.add(symptom);
                }
            }
        }
        int numberOfOptions = numberOfOptions(allSymptoms.size());
        Random random = new Random();
        // add random symptoms from allSymptoms list to the Symptoms ArrayList non-repeatedly to form a list of
        // length determined by algorithm
        for (int i = 0; i < numberOfOptions; i++) {
            String randomSymptom = allSymptoms.get(random.nextInt(allSymptoms.size()));
            if (!Symptoms.contains(randomSymptom)) {
                Symptoms.add(randomSymptom);
            }
        }

        return Symptoms;
    }

    /**
     * A helper method for generateOption. Based on the current number of symptoms, determine the number of option
     * to output to the user.
     *
     * @param numberOfSymptoms An int indicating the current number of symptoms.
     * @return the number of option to generate to the user.
     */
    private static int numberOfOptions(int numberOfSymptoms) {
        // algorithm for how many options we generate.
        double optionsAmount = numberOfSymptoms;
        while (optionsAmount >= 10) {
            numberOfSymptoms = numberOfSymptoms / 2;
            optionsAmount = numberOfSymptoms;
        }
        return (int) Math.round(optionsAmount);
    }

    /**
     * A getter method for the potentialDiseases
     *
     * @return a hashmap of potentialDiseases
     */
    public static HashMap<String, Set<String>> getPotentialDisease() {
        return potentialDisease;
    }

    /**
     * Get the potential diseases from the csv file using the DiseaseAPI.
     */
    public static void resetPotentialDisease() {
        potentialDisease = DiseaseAPI.readFromDiseaseCSV();
    }
}
