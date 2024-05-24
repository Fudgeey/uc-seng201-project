package seng201.team43.services;

import seng201.team43.exceptions.GameException;
import seng201.team43.models.*;

import java.util.List;

/**
 * Shop service to deal with logic for shop
 *
 * @author Riley Jeffcote, Luke Hallett
 */
public class ShopService {
    private final GameManager gameManager;

    /**
     * Initialises the shop service with the data from game manager.
     * @param gameManager gameManager to use for pulling data through and linking to Shop Screen.
     */
    public ShopService(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    public double getMoney() {
        return this.gameManager.getMoney();
    }

    public List<Purchasable> getShopItems() {
        return this.gameManager.getShopItems();
    }

    /**
     * Removes money from player and adds item to inventory.
     * @param item tower or upgrade to add
     * @throws GameException if there is an error adding the item
     */
    public void buyItem(Purchasable item) throws GameException {
        if(item == null) {
            throw new GameException("Invalid item was purchased.");
        }

        if(this.getMoney() - item.getCost() < 0) {
            throw new GameException("You do not have enough money to buy this.");
        }

        this.gameManager.getInventory().addItem(item);
        this.gameManager.removeMoney(item.getCost());

        item.setPurchased();
    }
}
