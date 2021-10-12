/* Below is the Disease class which stores Disease object from the database.
 */

import java.util.ArrayList;

public class Disease {

    private final String disease;
    private final ArrayList<String> symptoms;

    /**
     *
     * @param disease the name of the disease
     * @param symptoms a list of symptoms that the disease have
     */

    public Disease(String disease, ArrayList<String> symptoms) {
        this.disease = disease;
        this.symptoms = new ArrayList<String>();
    }

    public String getDisease() {
        return disease;
    }

    public ArrayList<String> getSymptoms() {
        return symptoms;
    }
}
