import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class DiseaseAPI {

    private static final String DISEASE_DATASET_PATH =
            "src/main/java/GlobalDiseaseData.csv";

    /**
     * Read from the Disease CSV and create a List of Disease Objects.
     * @return a List of Disease Objects.
     */

    public static void main(String[] args) {
        readFromDiseaseCSV();
    }

    public static HashMap<String, Set<String>> readFromDiseaseCSV(){
        HashMap<String, Set<String>> diseaseMap = new HashMap<>();
        Path pathToFile = Paths.get(DISEASE_DATASET_PATH);

        try (BufferedReader br = Files.newBufferedReader(pathToFile, StandardCharsets.US_ASCII)) {
            // Skipping the header.
            br.readLine();

            String line = br.readLine();
            
            while (line != null) {
                String[] data = line.split(",");       // data contains the disease name and symptoms
                putDataToMap(diseaseMap, data);
                line = br.readLine();                       // read next line before looping
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        // convert diseaseMap to an array of Disease objects
        return diseaseMap;
    }

    private static Disease createDiseaseObject(String name, Set<String> symptoms){
        ArrayList<String> symptomsArrayList = new ArrayList<>(symptoms);
        return new Disease(name, symptomsArrayList);
    }

    private static Disease[] returnListFromMap(HashMap<String, Set<String>> map) {
        Disease[] diseaseList = new Disease[map.keySet().size()];
        int i = 0;      // index to keep track of where to add new Disease object in diseaseList.
        for (String diseaseName : map.keySet()) {
            Disease d = createDiseaseObject(diseaseName, map.get(diseaseName));
            diseaseList[i] = d;
        }
        return diseaseList;
    }

    private static void putDataToMap(HashMap<String, Set<String>> map,String[] data) {
        String diseaseName = data[0];
        String[] symptomsList = Arrays.copyOfRange(data, 1, data.length);

        for (String symptom : symptomsList) {
            if (!map.containsKey(diseaseName)) {
                map.put(diseaseName, new HashSet<>());      // add disease to map for the first time
            }
            if (!symptom.equals("")) {                      // add symptom to map only if it is not ""
                map.get(diseaseName).add(symptom);
            }
        }

    }

}
