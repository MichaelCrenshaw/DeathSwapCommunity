package us.midius.deathswapcommunity;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

public class DeathSwapAnnouncer implements Runnable{

    DeathSwapManager deathSwapManager;
    Plugin plugin;

    public DeathSwapAnnouncer (DeathSwapManager deathSwapManager, Plugin plugin) {
        this.deathSwapManager = deathSwapManager;
        this.plugin = plugin;
    }

    @Override
    public void run() {
        Bukkit.broadcastMessage("§4Swapping in five seconds!");
        Bukkit.getScheduler().runTaskLater(plugin, deathSwapManager, 100);
    }
}
