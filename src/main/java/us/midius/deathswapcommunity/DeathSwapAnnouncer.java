package us.midius.deathswapcommunity;

import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.plugin.Plugin;

import java.util.Timer;
import java.util.TimerTask;

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
            Bukkit.getScheduler().runTaskLater(plugin, deathSwapManager, 100);
            Bukkit.getScheduler().runTaskTimer(plugin, new Runnable() {
                private int time = 5;
                @Override
                public void run() {
                    if (this.time == 0) { return; }
                    Bukkit.broadcastMessage("§4Swapping in " + Integer.toString(time) + " seconds!");
                    this.time--;
                }
            },0, 20);
        } else {
            Bukkit.broadcast("Death swap game successfully ended", Server.BROADCAST_CHANNEL_ADMINISTRATIVE);
        }
    }
}
