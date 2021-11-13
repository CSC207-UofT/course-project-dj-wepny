package Entities;

import java.util.ArrayList;

public interface IExercise {

    String getName();

    ArrayList<String> getType();

    ArrayList<String> getMinorMuscleExercised();

    ArrayList<String> getEquipmentNeeded();

    ArrayList<String> getMajorMuscleExercised();
}
