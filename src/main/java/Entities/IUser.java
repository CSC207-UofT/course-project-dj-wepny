package Entities;

import java.util.ArrayList;
import java.util.HashMap;

public interface IUser {
    int getId();

    String getUsername();

    String getGender();

    int getNumFood();

    HashMap<FoodFilterCriterion, Boolean> getFoodPreference();

    HashMap<String, String> getExercisePreference();

    ArrayList<String> getRiskFactor();

    HashMap<String, Object> getPersonalData();

    // setter functions
    void setFoodPreference(FoodFilterCriterion key, Boolean value);

    void setNumFoods(int numFoods);

    void setExercisePreference(String key, String value);

    void addRiskFactor(String addedSymptoms);

    void resetRiskFactor();

    void setPersonalData(String key, Object value);

    void resetFoodPreference();

    void setUserName(String newName);

    void setUserGender(String newGender);
}
