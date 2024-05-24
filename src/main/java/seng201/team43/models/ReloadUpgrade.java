package seng201.team43.models;

import seng201.team43.exceptions.GameException;

/**
 * Class for reload upgrade, extending abstract features of upgrade
 */
public class ReloadUpgrade extends Upgrade {
    /**
     * Constructor for the reload upgrade class
     * @param cost Cost to purchase the upgrade.
     */
    public ReloadUpgrade(int cost) {
        super(cost, "Reload Upgrade");
    }

    /**
     * Apply reload upgrade by decreasing reload speed
     * @param tower tower to upgrade
     * @throws GameException if reload speed tries to go below 1
     */
    @Override
    public void apply(Tower tower) throws GameException {
        tower.decreaseReloadSpeed(1);
    }

    @Override
    public String getDescription() {
        return "Decreases a tower's reload speed by 1 second.";
    }
}
