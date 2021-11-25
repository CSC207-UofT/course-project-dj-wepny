/*
 *This file contains Junit test case for DiseaseAnalyzer.java
 */


import API.DiseaseAPI;
import Entities.User;
import UseCases.DiseaseAnalyzer;
import org.junit.*;
//import org.junit.jupiter.api.BeforeEach;

import javax.swing.*;

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
    public void testOutputFinalDiseases() {
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
        disease.resetPotentialDisease();
    }

    @Test(timeout = 500)
    public void testPromptDiseaseOptions() {
        user.addRiskFactor("vomiting");

        disease.analyze();

        //because everytime the symptoms are randomly generated, we can only check whether the options generated
        //actually correspond to diseases that include the given symptom.
        String result = disease.getResult();
        result = result.replaceAll("[\\[\\](){}]", "");
        String[] symptomsList = result.split(",");
        //These are the possible options to be generated
        // converted into list
        List<String> finalOptionsList;

        finalOptionsList = Arrays.asList(symptomsList);
        ArrayList<String> finalOptionsList1 = new ArrayList<>();

        for (String item : finalOptionsList){
           item = item.trim();
           finalOptionsList1.add(item);
        }

        HashMap<String, Set<String>> dataset = DiseaseAPI.readFromDiseaseCSV();
        ArrayList<String> allSymptoms = new ArrayList<>();
        //All possible symptoms to be generated in the options
        for (String disease: dataset.keySet()){
            if(dataset.get(disease).containsAll(user.getRiskFactor())){
                for(String symptom: dataset.get(disease)) {
                    allSymptoms.add(symptom);
                }

            }
        }

        //check whether the options generated are truly contained in the possible options
        assertTrue(allSymptoms.containsAll(finalOptionsList1));
    }
}
