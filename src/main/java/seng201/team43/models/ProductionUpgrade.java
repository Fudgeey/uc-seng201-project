package seng201.team43.models;

public class ProductionUpgrade extends Upgrade {
    public ProductionUpgrade() {
        super(100);
    }

    public void apply(Tower tower) {
        tower.increaseProductionUnits(10);
    }
}
