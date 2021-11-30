package API;

import Constants.SystemConstants;
import Entities.IExercise;
import Entities.Exercise;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A Exercise API that reads from the Exercise Moves Dataset, creates Exercise Objects
 * based on the data, and returns a List of all Exercise objects
 */
public class ExerciseAPI {

    /**
     * Read from the Exercise Moves CSV, create Exercise Objects and return all the Exercise
     * Objects in a List
     *
     * @return a List of Exercise Objects
     */
    public static List<IExercise> readFromExerciseCSV() {

        List<IExercise> exerciseList = new ArrayList<>();

        Path pathToFile = Paths.get(SystemConstants.EXERCISE_DATASET_PATH);

        // create an instance of BufferedReader
        // Use a try-catch block for unexpected errors.
        try (BufferedReader br = Files.newBufferedReader(pathToFile, StandardCharsets.US_ASCII)) {
            // We are going to skip the first line of the file because it yields no useful information.
            br.readLine();

            // Set the line of interest to line 2 of the file.
            String line = br.readLine();

            // Now we create Exercise objects and append them to the list.
            while (line != null) {
                String[] data = line.split("\\*");
                // create the Exercise Object using a helper function
                IExercise exerciseItem = createExercise(data);
                exerciseList.add(exerciseItem);
                line = br.readLine(); //read next line before looping
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return exerciseList;
    }

    /**
     * A helper function that creates an Exercise object with relative information.
     *
     * @param data a string array with relative information (name, equipment, major and/or minor muscle, etc.)
     * @return an Exercise object.
     */
    private static IExercise createExercise(String[] data) {

        // Initializes the variables needed for Exercise's constructor.
        ArrayList<String> equipments = new ArrayList<>();
        ArrayList<String> exerciseType = new ArrayList<>();
        ArrayList<String> majorMuscle = new ArrayList<>();
        ArrayList<String> minorMuscle = new ArrayList<>();

        String name = data[0];

        // Using a helper method to extract and append corresponding parts into the ArrayLists.
        // Note that if there's no Minor muscles involved in the exercise,
        // the minorMuscle ArrayList should only contain a single space character.
        addElementToList(data, equipments, 1);
        addElementToList(data, exerciseType, 2);
        addElementToList(data, majorMuscle, 3);
        addElementToList(data, minorMuscle, 4);

        return new Exercise(name, exerciseType, minorMuscle, majorMuscle, equipments);
    }

    /**
     * Helper method that helps with splitting the commas within the string.
     *
     * @param s a string that contains commas.
     * @return A string Array with the commas split out.
     */
    private static String[] splitting(String s) {
        return s.split(",");
    }

    /**
     * Helper method that adds elements that's in a String array to a corresponding List.
     *
     * @param data     A string of array with elements to be passed into the list.
     * @param variable An arraylist of strings that will receive inputs.
     * @param index    the index of the string array.
     */
    private static void addElementToList(String[] data, ArrayList<String> variable, int index) {
        if (data[index].contains(",")) {
            String[] equipmentInfo = splitting(data[index]);
            variable.addAll(Arrays.asList(equipmentInfo));
        } else {
            variable.add(data[index]);
        }
    }
}
