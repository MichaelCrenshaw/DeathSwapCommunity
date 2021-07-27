package us.midius.deathswapcommunity;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;

public class DeathSwapStart implements CommandExecutor {

    DeathSwapManager deathSwapManager;
    DeathSwapCommunity plugin;

    public DeathSwapStart(DeathSwapManager deathSwapManager, DeathSwapCommunity plugin) {
        this.deathSwapManager = deathSwapManager;
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        deathSwapManager.getPlayerList().clear();
        plugin.setEmergencyStop(false);
        for (Player player:Bukkit.getOnlinePlayers()) {
            deathSwapManager.getPlayerList().add(player);
        }
        deathSwapManager.removeExempt(plugin.getExemptPlayers());
        Bukkit.broadcastMessage("§4Death swap is starting in ten seconds, get ready!");
        Bukkit.getScheduler().runTaskLater(plugin, deathSwapManager, 200);
        return true;
    }
}
