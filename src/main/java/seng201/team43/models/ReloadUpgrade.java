package seng201.team43.models;

public class ReloadUpgrade extends Upgrade {
    public ReloadUpgrade() {
        super(100, "Reload Upgrade");
    }

    public void apply(Tower tower) {
        tower.decreaseReloadSpeed(1);
    }

    public String getDescription() {
        return "Decreases a tower's reload speed by 1 second.";
    }
}
