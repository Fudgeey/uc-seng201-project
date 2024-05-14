package seng201.team43.models;

public class ProductionUpgrade extends Upgrade {
    private final int units;

    public ProductionUpgrade(int units) {
        super(100, "Production Upgrade");

        this.units = units;
    }

    public void apply(Tower tower) {
        tower.increaseProductionUnits(this.units);
    }

    public String getDescription() {
        return String.format("Increases a tower's production by %s units.", this.units);
    }
}
