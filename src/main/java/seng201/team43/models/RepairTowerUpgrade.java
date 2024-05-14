package seng201.team43.models;

/**
 * Class for repair tower upgrade
 * @author Luke Hallett, Riley Jeffcote
 */
public class RepairTowerUpgrade extends Upgrade {
    public RepairTowerUpgrade() {
        super(50, "Repair Tower");
    }

    @Override
    public void apply(Tower tower) {
        tower.setBroken(false);
    }

    @Override
    public String getDescription() {
        return "Repair a broken tower.";
    }
}
