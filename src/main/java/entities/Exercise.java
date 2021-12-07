package entities;

import java.util.List;
/**
 * This class stores an Exercise Object from the database,
 * describing the characteristics of the workout move
 */
public class Exercise implements IExercise{
    private final String name;
    private final List<String> type;
    private final List<String> minorMuscleExercised;
    private final List<String> majorMuscleExercised;
    private final List<String> equipmentNeeded;

    /**
     * Constructor for the Exercise moves
     * @param name the name of the exercise
     * @param type the type of the exercise
     * @param minorMuscle is a list of specific muscle this move trains
     * @param majorMuscle is a list of major muscle this moves trains
     * @param equipmentNeeded is a list of equipments this move needs
     */
    public Exercise(String name, List<String> type, List<String> minorMuscle,
                    List<String> majorMuscle, List<String> equipmentNeeded){
        this.name = name;
        this.type = type;
        this.minorMuscleExercised = minorMuscle;
        this.majorMuscleExercised = majorMuscle;
        this.equipmentNeeded = equipmentNeeded;
    }


    // getter methods for all its instance attributes
    public String getName() {return name;}

    public List<String> getType() {
        return type;
    }

    public List<String> getMinorMuscleExercised() {
        return minorMuscleExercised;
    }

    public List<String> getEquipmentNeeded() {
        return equipmentNeeded;
    }

    public List<String> getMajorMuscleExercised() {
        return majorMuscleExercised;
    }
}
