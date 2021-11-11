package API;

import Entities.Disease;
import Entities.IDisease;
import Constants.Constants;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

/**
 * This class creates individual disease objects using a Disease Dataset and
 * creates a list of disease object by reading from the file.
 */
public class DiseaseAPI {

    public static void main(String[] args) {
        readFromDiseaseCSV();
    }

    /**
     * Read from the Disease CSV and create a List of Disease Objects.
     * @return a List of Disease Objects.
     */
    public static HashMap<String, Set<String>> readFromDiseaseCSV(){
        HashMap<String, Set<String>> diseaseMap = new HashMap<>();
        Path pathToFile = Paths.get(Constants.DISEASE_DATASET_PATH);

        try (BufferedReader br = Files.newBufferedReader(pathToFile, StandardCharsets.US_ASCII)) {
            // Skipping the header.
            br.readLine();

            String line = br.readLine();
            
            while (line != null) {
                String[] data = line.split(",");       // data contains the disease name and symptoms
//                System.out.print("data: " + data.toString());
                putDataToMap(diseaseMap, data);
                line = br.readLine();                       // read next line before looping
            }

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        // convert diseaseMap to an array of Disease objects
        return diseaseMap;
    }

    /**
     * Helper method to create disease objects.
     * @param name A string, the name of the disease.
     * @param symptoms A set of strings indicating the symtoms of the disease.
     * @return A Disease object.
     */
    private static IDisease createDiseaseObject(String name, Set<String> symptoms){
        ArrayList<String> symptomsArrayList = new ArrayList<>(symptoms);
        return new Disease(name, symptomsArrayList);
    }

    /**
     * Helper method to convert the disease hashmap into a list of disease objects.
     * @param map A hashmap with the disease name as key and a set of symptoms as value.
     * @return A list of disease objects.
     */
    private static IDisease[] returnListFromMap(HashMap<String, Set<String>> map) {
        IDisease[] diseaseList = new Disease[map.keySet().size()];
        int i = 0;      // index to keep track of where to add new Disease object in diseaseList.
        for (String diseaseName : map.keySet()) {
            IDisease d = createDiseaseObject(diseaseName, map.get(diseaseName));
            diseaseList[i] = d;
        }
        return diseaseList;
    }

    /**
     * A helper method to put data read from the file into a hashmap.
     * @param map A hashmap that we are passing the data in.
     * @param data An array of strings containing the data of interest.
     */
    private static void putDataToMap(HashMap<String, Set<String>> map,String[] data) {
        String diseaseName = data[0];
        String[] symptomsList = Arrays.copyOfRange(data, 1, data.length);
//        System.out.print("symptom LIST:"+symptomsList.toString());


        for (String symptom : symptomsList) {
            if (!map.containsKey(diseaseName)) {

                map.put(diseaseName, new HashSet<>());      // add disease to map for the first time
            }
            if (!symptom.equals("")) {                      // add symptom to map only if it is not ""
                 String mySymptom = symptom.replace(" ", "");

//                System.out.print("symptom:"+mySymptom);
                map.get(diseaseName).add(mySymptom);
            }
        }

    }

}
