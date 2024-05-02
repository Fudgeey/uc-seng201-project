package seng201.team43.models;

public class ProductionUpgrade extends Upgrade {
    public ProductionUpgrade() {
        super(100, "Production Upgrade");
    }

    public void apply(Tower tower) {
        tower.increaseProductionUnits(10);
    }

    public String getDescription() {
        return "Increases a tower's production by 10 units.";
    }
}
