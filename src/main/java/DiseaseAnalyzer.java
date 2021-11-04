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

//    public static void main(String[] args) {
//        ArrayList<String> s= new ArrayList<String>();
//        s.add(" distention_of_abdomen");
//        System.out.println(newPotentialDiseases(DiseaseAPI.readFromDiseaseCSV(), s));
//        System.out.println(DiseaseAPI.readFromDiseaseCSV());
//        System.out.println(DiseaseAPI.readFromDiseaseCSV().get("Alcoholic hepatitis").contains(" distention_of_abdomen"));
//
//    }

    public static void main(String[] args) {

    }
    @Override
    public void analyze(User user) {
//        System.out.println("potentialDisease" + potentialDisease);
        ArrayList<String> userInput = user.getRiskFactor(); //what users input
//        System.out.println("userInput" + userInput);
        HashMap<String, Set<String>> newDisease = newPotentialDiseases(potentialDisease, userInput);
//        System.out.println("newDisease" + newDisease);
        ArrayList<String> options = generateOptions(newDisease);
        if (potentialDisease.size() <= 6){result = newDisease.keySet().toString();}
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
//                System.out.println(removeDiseases);
            }
        }

        oldPotentialDisease.keySet().removeAll(removeDiseases);
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
