package entities;

import java.util.ArrayList;
/**
 * This class stores an Exercise Object from the database,
 * describing the characteristics of the workout move
 */
public class Exercise implements IExercise{
    private final String name;
    private final ArrayList<String> type;
    private final ArrayList<String> minorMuscleExercised;
    private final ArrayList<String> majorMuscleExercised;
    private final ArrayList<String> equipmentNeeded;

    /**
     * Constructor for the Exercise moves
     * @param name the name of the exercise
     * @param type the type of the exercise
     * @param minorMuscle is a list of specific muscle this move trains
     * @param majorMuscle is a list of major muscle this moves trains
     * @param equipmentNeeded is a list of equipments this move needs
     */
    public Exercise(String name, ArrayList<String> type, ArrayList<String> minorMuscle,
                    ArrayList<String> majorMuscle, ArrayList<String> equipmentNeeded){
        this.name = name;
        this.type = type;
        this.minorMuscleExercised = minorMuscle;
        this.majorMuscleExercised = majorMuscle;
        this.equipmentNeeded = equipmentNeeded;
    }


    // getter methods for all its instance attributes
    public String getName() {return name;}

    public ArrayList<String> getType() {
        return type;
    }

    public ArrayList<String> getMinorMuscleExercised() {
        return minorMuscleExercised;
    }

    public ArrayList<String> getEquipmentNeeded() {
        return equipmentNeeded;
    }

    public ArrayList<String> getMajorMuscleExercised() {
        return majorMuscleExercised;
    }
}
