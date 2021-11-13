package Entities;

public interface IFood {

    double getCarbohydrate();

    double getSugar();

    String getFoodType();

    String getId();

    double getFat();

    boolean getVegFriendly();

    StringBuilder toStrings();
}
