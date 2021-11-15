package Entities;

import java.util.ArrayList;

/**
 *  This is interface is an abstraction of Exercise Entity
 */
public interface IExercise {

    String getName();

    ArrayList<String> getType();

    ArrayList<String> getMinorMuscleExercised();

    ArrayList<String> getEquipmentNeeded();

    ArrayList<String> getMajorMuscleExercised();
}
