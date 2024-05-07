package seng201.team43.models;

public interface Purchasable {
    Boolean purchased = false;

    int getCost();
    String getName();
    String getDescription();
    void setPurchased();
    Boolean getPurchased();
}
