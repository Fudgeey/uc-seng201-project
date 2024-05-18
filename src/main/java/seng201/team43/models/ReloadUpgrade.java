package seng201.team43.models;

import seng201.team43.exceptions.GameException;

public class ReloadUpgrade extends Upgrade {
    public ReloadUpgrade() {
        super(100, "Reload Upgrade");
    }

    @Override
    public void apply(Tower tower) throws GameException {
        tower.decreaseReloadSpeed(1);
    }

    @Override
    public String getDescription() {
        return "Decreases a tower's reload speed by 1 second.";
    }
}
