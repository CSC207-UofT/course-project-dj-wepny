package entities;

import java.util.List;

/**
 *  This is interface is an abstraction of Exercise Entity
 */
public interface IExercise {

    String getName();

    List<String> getType();

    List<String> getMinorMuscleExercised();

    List<String> getEquipmentNeeded();

    List<String> getMajorMuscleExercised();
}
