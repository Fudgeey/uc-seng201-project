package seng201.team43.services;

import seng201.team43.models.GameManager;
import seng201.team43.models.Resource;
import seng201.team43.models.Tower;

public class SetupService {
    private final GameManager gameManager;

    public SetupService(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    public void setName(String name) {
        this.gameManager.setName(name);
    }

    public void addStartingTower(Resource resource) {
        this.gameManager.getInventory().addActiveTower(new Tower(resource));
    }
}
