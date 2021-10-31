import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class DiseaseAPI {
    /**
     * Create a Disease Object based on the inputs in data.
     * @param data a string array based on each line of the CSV file.
     * @return a disease object.
     */
    private static Disease createDisease(String[] data){
        // create an ArrayList for the symptoms.
        ArrayList<String> result = new ArrayList<>();

        String name = data[0];

        // A simple for-loop to iterate through the array and append symptoms to the list.
        for (int i = 1; i < data.length; i++){
             if(data[i].equals("")){continue;}
             result.add(data[i]);
        }
        return new Disease(name, result);
    }

    /**
     * Read from the Disease CSV and create a List of Disease Objects.
     * @return a List of Disease Objects.
     */
    private static List<Disease> readFromDiseaseCSV(){
        List<Disease> diseaseList = new ArrayList<>();

        Path pathToFile = Paths.get("GlobalDiseaseData.csv");

        // create an instance of BufferedReader
        // Use a try-catch block for unexpected errors.
        try (BufferedReader br = Files.newBufferedReader(pathToFile, StandardCharsets.US_ASCII)) {
            // We are going to skip the first line of the file because it yields no useful information.
            br.readLine();
            String line = br.readLine();

            // Now we create disease objects and append them to the list.

            // TODO: Wrangle the data somehow to get rid of duplicate names.
            while (line != null) {
                String[] data = line.split(",");
                Disease diseaseItem = createDisease(data);
                diseaseList.add(diseaseItem);
                line = br.readLine(); //read next line before looping
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return diseaseList;
    }

}
