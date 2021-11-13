/**
 *This file contains Junit test case for DiseaseAnalyzer.java
 */

import Entities.User;
import UseCases.DiseaseAnalyzer;
import org.junit.*;
import static org.junit.Assert.*;
import java.util.HashMap;


public class TestDiseaseAnalyzer {
    DiseaseAnalyzer disease;
    User user;
    @Before
    public void setUp() {
        disease = new DiseaseAnalyzer();
        HashMap<String, Object> userInfo = new HashMap<>();
        userInfo.put("height", "1.70");
        userInfo.put("weight", "58");
        userInfo.put("age", "21");
        user = new User(20, "Amy", "F", userInfo);
        user.addRiskFactor("yellowish_skin");
        user.addRiskFactor("nausea");

    }

    @Test(timeout = 500)
    public void testOutput() {
//        disease.analyze(user);    TODO: resolve the compile time error once commented out
        assertEquals("[Hepatitis E, hepatitis A, Chronic cholestasis, Hepatitis C, Hepatitis D]",
                disease.getResult());
    }
}
