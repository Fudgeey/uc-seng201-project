package seng201.team43.services;

import seng201.team43.exceptions.GameError;
import seng201.team43.models.GameManager;
import seng201.team43.models.Purchasable;
import seng201.team43.models.Tower;
import seng201.team43.models.Upgrade;

import java.util.List;

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
     * Goes back to the inventory screen.
     */
    public void close() {
        this.gameManager.closeShopScreen();
    }

    /**
     * Goes to the pause screen.
     */
    public void pause() {
        this.gameManager.launchPauseScreen();
    }

    public List<Purchasable> getShopItems() {
        return this.gameManager.getShopItems();
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
