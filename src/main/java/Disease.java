import java.util.ArrayList;
/**
 * This class stores Disease object from the database.
 */
public class Disease {

    private final String disease;
    private final ArrayList<String> symptoms;

    /**
     *Constructor for the disease object
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
