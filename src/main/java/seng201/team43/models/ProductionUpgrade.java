package seng201.team43.models;

/**
 * Class for the production upgrade, which inherits the upgrade class
 */
public class ProductionUpgrade extends Upgrade {
    private final int units;

    /**
     * Initialises the cost and production units of a production upgrade.
     * @param units units for upgrade
     */
    public ProductionUpgrade(int cost, int units) {
        super(cost, "Production Upgrade");

        this.units = units;
    }

    /**
     * Applies it to a tower by increasing the tower's production units by production upgrades units.
     * @param tower tower to apply upgrade to
     */
    @Override
    public void apply(Tower tower) {
        tower.increaseProductionUnits(this.units);
    }

    @Override
    public String getDescription() {
        return String.format("Increases a tower's production by %s units.", this.units);
    }
}
