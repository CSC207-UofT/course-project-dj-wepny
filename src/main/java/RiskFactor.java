/* Below is the RiskFactor class which is a child class of UserInformation and stores user's symptoms.
 */

import java.util.ArrayList;

public class RiskFactor extends UserInformation{

    public ArrayList<Boolean> symptomsList;

    /**
     * Construct RiskFactor of the user
     *
     * @param symptomsList  List of boolean for which each element map to a symptom. If it is true then the
     *                   user has the symptom and false otherwise.
     */

    public RiskFactor(ArrayList<String> symptomsList) {
        this.symptomsList = new ArrayList<Boolean>();
    }
}
