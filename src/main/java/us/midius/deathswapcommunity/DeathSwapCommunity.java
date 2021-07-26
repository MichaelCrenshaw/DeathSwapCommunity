package us.midius.deathswapcommunity;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.util.io.BukkitObjectInputStream;

import java.util.ArrayList;

public final class DeathSwapCommunity extends JavaPlugin {

    private ArrayList<Player> exemptPlayers = new ArrayList<Player>();
    private int minDelay = 40;
    private int maxDelay = 120;
    private boolean emergencyStop = false;
    private DeathSwapAnnouncer deathSwapAnnouncer;
    private DeathSwapManager deathSwapManager;

    @Override
    public void onEnable() {
        // Plugin startup logic
        Scoreboard scoreboard = Bukkit.getScoreboardManager().getMainScoreboard();
        try {
            scoreboard.getObjective("kills").unregister();
            scoreboard.getObjective("health").unregister();
        } catch (NullPointerException e) {
            Bukkit.getConsoleSender().sendMessage("At least one objective did not need to be removed, this is expected behavior.");
        }
        deathSwapManager = new DeathSwapManager(this);
        deathSwapAnnouncer = new DeathSwapAnnouncer(deathSwapManager, this);
        this.getCommand("ds").setExecutor(new DeathSwap());
        this.getCommand("ds-start").setExecutor(new DeathSwapStart(deathSwapManager, this));
        this.getCommand("ds-delay").setExecutor(new DeathSwapDelay(this));
        this.getCommand("ds-remove").setExecutor(new DeathSwapRemove(deathSwapManager, this));
        this.getCommand("ds-clear").setExecutor(new DeathSwapClear(deathSwapManager, this));
        this.getCommand("ds-list").setExecutor(new DeathSwapList(deathSwapManager, this));
        this.getCommand("ds-add").setExecutor(new DeathSwapAdd(deathSwapManager, this));
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        Scoreboard scoreboard = Bukkit.getScoreboardManager().getMainScoreboard();
        scoreboard.getObjective("kills").unregister();
        scoreboard.getObjective("health").unregister();
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
