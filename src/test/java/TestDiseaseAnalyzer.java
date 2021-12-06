/*
 *This file contains Junit test case for DiseaseAnalyzer.java
 */


import api.DiseaseAPI;
import entities.User;
import usecases.DiseaseAnalyzer;
import org.junit.*;

import static org.junit.Assert.*;

import java.util.*;

public class TestDiseaseAnalyzer {
    DiseaseAnalyzer disease;
    User user;
    @Before
    public void setUp() {
        HashMap<String, Object> userInfo = new HashMap<>();
        userInfo.put("height", "1.70");
        userInfo.put("weight", "58");
        userInfo.put("age", "21");
        user = new User(20, "Amy", "F", userInfo);
        disease = new DiseaseAnalyzer(user);
    }

    @Test(timeout = 500)
    public void testGenerateFinalDiseases() {
        user.addRiskFactor("yellowish_skin");
        user.addRiskFactor("nausea");
        disease.analyze();
        assertEquals("These are your potential diseases: (if output = [], there is no disease that match " +
                        "the current symptoms you are experiencing)\n" +
                        "[Hepatitis E, hepatitis A, Chronic cholestasis, Hepatitis C, Hepatitis D]",
                disease.getResult());
    }

    @After
    public void teardown() {
        user.resetRiskFactor();
        DiseaseAnalyzer.resetPotentialDisease();
    }

    @Test(timeout = 500)
    public void testPromptDiseaseOptions() {
        user.addRiskFactor("vomiting");

        disease.analyze();

        //because everytime the symptoms are randomly generated, we can only check whether the options generated
        //actually correspond to diseases that include the given symptom.
        //these are the possible symptoms prompted
        String result = disease.getResult();

        // convert them into list in desired format for program
        result = result.replaceAll("[\\[\\](){}]", "");
        String[] symptomsList = result.split(",");
        List<String> finalOptionsList;
        finalOptionsList = Arrays.asList(symptomsList);
        ArrayList<String> finalOptionsList1 = new ArrayList<>();

        for (String item : finalOptionsList){
           item = item.trim();
           finalOptionsList1.add(item);
        }

        HashMap<String, Set<String>> dataset = DiseaseAPI.readFromDiseaseCSV();
        ArrayList<String> allSymptoms = new ArrayList<>();
        //Collect all possible symptoms to be generated in the options by filtering using the risk factors of the user
        for (String disease: dataset.keySet()){
            if(dataset.get(disease).containsAll(user.getRiskFactor())){
                allSymptoms.addAll(dataset.get(disease));

            }
        }

        //check whether the options generated are truly contained in the possible options
        assertTrue(allSymptoms.containsAll(finalOptionsList1));
    }

    @Test(timeout = 500)
    public void testNoValidDiseases() {
        user.addRiskFactor("yellowish_skin");
        user.addRiskFactor("nausea");
        user.addRiskFactor("continuous_sneezing");
        user.addRiskFactor("acidity");
        disease.analyze();
        assertEquals("These are your potential diseases: (if output = [], there is no disease that match " +
                        "the current symptoms you are experiencing)\n" +
                        "[]",
                disease.getResult());
    }

//    @Test(timeout = 500)
//    public void testNoRiskFactor() {
//        disease.analyze();
//        //These are the options generated
//        String result = disease.getResult();
//        result = result.replaceAll("[\\[\\](){}]", "");
//        String[] symptomsList = result.split(",");
//        // convert them into list in desired format for program
//        List<String> finalOptionsList;
//
//        finalOptionsList = Arrays.asList(symptomsList);
//        ArrayList<String> finalOptionsList1 = new ArrayList<>();
//
//        for (String item : finalOptionsList){
//            item = item.trim();
//            finalOptionsList1.add(item);
//        }
//        //Get all Diseases from Data set
//        HashMap<String, Set<String>> dataset = DiseaseAPI.readFromDiseaseCSV();
//        ArrayList<String> allSymptoms = new ArrayList<>();
//        //Collect all possible symptoms to be generated in the options
//        for (String disease: dataset.keySet()){
//            for(String item: finalOptionsList) {
//                if (dataset.get(disease).contains(item)) {
//                    allSymptoms.remove(item);
//                }
//            }
//            }
//        //Check whether the prompted symptoms belong to the data set
//            assertTrue(allSymptoms.isEmpty());
//
//        }
}
