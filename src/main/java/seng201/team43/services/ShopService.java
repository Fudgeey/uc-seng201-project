package seng201.team43.services;

import seng201.team43.exceptions.GameError;
import seng201.team43.models.GameManager;
import seng201.team43.models.Purchasable;

/**
 * Shop service to deal with logic for shop
 *
 * @author Riley Jeffcote, Luke Hallett
 */
public class ShopService {
    private final GameManager gameManager;

    public ShopService(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    /**
     * Removes money from player and adds item to inventory.
     * @param item tower or upgrade to add
     */
    public void buyItem(Purchasable item) throws GameError {
        if(item == null) {
            throw new GameError("Invalid item was purchased.");
        }

        this.gameManager.removeMoney(item.getCost());
        this.gameManager.getInventory().addItem(item);
    }
}
