package Entities;

/**
 *  This is interface is an abstraction of the Food Entity
 */
public interface IFood {

    double getCarbohydrate();

    double getSugar();

    String getFoodType();

    String getId();

    double getFat();

    boolean getVegFriendly();

    StringBuilder toStrings();
}
