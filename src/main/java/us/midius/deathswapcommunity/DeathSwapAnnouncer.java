package us.midius.deathswapcommunity;

import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.plugin.Plugin;

public class DeathSwapAnnouncer implements Runnable{

    DeathSwapManager deathSwapManager;
    DeathSwapCommunity plugin;

    public DeathSwapAnnouncer (DeathSwapManager deathSwapManager, DeathSwapCommunity plugin) {
        this.deathSwapManager = deathSwapManager;
        this.plugin = plugin;
    }

    @Override
    public void run() {
        if (!plugin.isEmergencyStop()) {
            Bukkit.broadcastMessage("§4Swapping in five seconds!");
            Bukkit.getScheduler().runTaskLater(plugin, deathSwapManager, 100);
        } else {
            Bukkit.broadcast("Death swap game successfully ended", Server.BROADCAST_CHANNEL_ADMINISTRATIVE);
        }
    }
}
