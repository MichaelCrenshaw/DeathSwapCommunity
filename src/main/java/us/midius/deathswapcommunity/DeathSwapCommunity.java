package us.midius.deathswapcommunity;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;

public final class DeathSwapCommunity extends JavaPlugin {

    private ArrayList<Player> exemptPlayers;
    private int minDelay = 40;
    private int maxDelay = 120;
    private boolean emergencyStop = false;
    private DeathSwapAnnouncer deathSwapAnnouncer;

    @Override
    public void onEnable() {
        // Plugin startup logic
        DeathSwapManager deathSwapManager = new DeathSwapManager(this);
        deathSwapAnnouncer = new DeathSwapAnnouncer(deathSwapManager, this);
        this.getCommand("ds").setExecutor(new DeathSwap());
        this.getCommand("ds-start").setExecutor(new DeathSwapStart(deathSwapManager, this));
        this.getCommand("ds-delay").setExecutor(new DeathSwapDelay(this));
        this.getCommand("ds-remove").setExecutor(new DeathSwapRemove(deathSwapManager, this));
        this.getCommand("ds-add").setExecutor(new DeathSwapClear(this));
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public int getMinDelay() {
        return minDelay;
    }

    public void setMinDelay(int minDelay) {
        this.minDelay = minDelay;
    }

    public int getMaxDelay() {
        return maxDelay;
    }

    public void setMaxDelay(int maxDelay) {
        this.maxDelay = maxDelay;
    }

    public ArrayList<Player> getExemptPlayers() {
        return exemptPlayers;
    }

    public void setExemptPlayers(ArrayList<Player> exemptPlayers) {
        this.exemptPlayers = exemptPlayers;
    }

    public boolean isEmergencyStop() {
        return emergencyStop;
    }

    public void setEmergencyStop(boolean emergencyStop) {
        this.emergencyStop = emergencyStop;
    }

    public DeathSwapAnnouncer getDeathSwapAnnouncer() {
        return deathSwapAnnouncer;
    }
}
