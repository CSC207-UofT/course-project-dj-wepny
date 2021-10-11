/* Below is the ExercisePreference class which is a child class of UserInformation and stores users exercise
 * preferences.
 */

import java.util.ArrayList;

public class ExercisePreference extends UserInformation{
    String muscleParts;
    String exerciseType;
    ArrayList<String> equipment;

    /**
     * Construct ExercisePreference of the user
     *
     * @param muscleParts The muscle part that the user want to work on
     * @param exerciseType  User's preference of the type of exercise
     * @param equipment  List of equipment that the user have
     */

    public ExercisePreference(String muscleParts, String exerciseType, String equipment){
        this.muscleParts = muscleParts;
        this.exerciseType = exerciseType;
        this.equipment = new ArrayList<String>();
    }
}
