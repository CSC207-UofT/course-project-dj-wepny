package api;

import constants.SystemConstants;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

/**
 * A Disease API that reads from the Disease Dataset, returning
 * a hashmap of all diseases mapped to their symptoms
 */
public class DiseaseAPI {

    public static void main(String[] args) {
        readFromDiseaseCSV();
    }

    /**
     * Read from the Disease CSV and return all the information in a hashmap
     *
     * @return a Hashmap of all diseases mapped to their symptoms
     */
    public static HashMap<String, Set<String>> readFromDiseaseCSV() {
        HashMap<String, Set<String>> diseaseMap = new HashMap<>();
        Path pathToFile = Paths.get(SystemConstants.DISEASE_DATASET_PATH);

        try (BufferedReader br = Files.newBufferedReader(pathToFile, StandardCharsets.US_ASCII)) {
            // Skipping the header.
            br.readLine();

            String line = br.readLine();

            while (line != null) {
                // data contains the disease name and symptoms
                String[] data = line.split(",");
                putDataToMap(diseaseMap, data);
                // read next line before looping
                line = br.readLine();
            }

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        return diseaseMap;
    }

    /**
     * A helper method to put data read from the file into a hashmap.
     *
     * @param map  A hashmap that we are passing the data in.
     * @param data An array of strings containing the data of interest.
     */
    private static void putDataToMap(HashMap<String, Set<String>> map, String[] data) {
        String diseaseName = data[0];
        String[] symptomsList = Arrays.copyOfRange(data, 1, data.length);

        for (String symptom : symptomsList) {
            if (!map.containsKey(diseaseName)) {
                // add disease to map for the first time
                map.put(diseaseName, new HashSet<>());
            }
            // add symptom to map only if it is not ""
            if (!symptom.equals("")) {
                String mySymptom = symptom.replace(" ", "");

                map.get(diseaseName).add(mySymptom);
            }
        }

    }

}
