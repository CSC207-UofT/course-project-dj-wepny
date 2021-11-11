package Entities;

import java.util.Objects;

public interface IFood {
    boolean equals(Object obj);

    StringBuilder toStrings();

    String getFoodName();

    double getCalories();

    double getCarbohydrate();

    double getProteins();

    double getSugar();

    int getNutrientScore();

    String getFoodType();

    String getId();

    double getFat();

    boolean getVegFriendly();
}
