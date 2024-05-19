package seng201.team43.models;

/**
 * Interface class for purchasable (tower or upgrade) with generic features
 */
public interface Purchasable {
    Boolean purchased = false;

    int getCost();
    String getName();
    String getDescription();
    void setPurchased();
    Boolean getPurchased();
    int getSellPrice();
}
