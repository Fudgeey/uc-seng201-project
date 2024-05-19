package seng201.team43.models;

/**
 * Class for repair tower upgrade
 * @author Luke Hallett, Riley Jeffcote
 */
public class RepairTowerUpgrade extends Upgrade {
    /**
     * Initialises repair tower upgrade.
     */
    public RepairTowerUpgrade() {
        super(50, "Repair Tower");
    }

    /**
     * Applies the upgrade to a tower.
     * @param tower passed in which the upgrade will be applied to.
     */
    @Override
    public void apply(Tower tower) {
        tower.setBroken(false);
    }

    @Override
    public String getDescription() {
        return "Repair a broken tower.";
    }
}
