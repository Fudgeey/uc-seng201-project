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
    public void buyItem(Purchasable item) {
        try {
            this.gameManager.removeMoney(item.getCost());
            this.gameManager.getInventory().addItem(item);
        } catch(GameError e) {
            e.displayError();
        }
    }
}
