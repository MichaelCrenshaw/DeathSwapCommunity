package us.midius.deathswapcommunity;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class DeathSwapList implements CommandExecutor {

    DeathSwapManager deathSwapManager;
    DeathSwapCommunity plugin;

    public DeathSwapList(DeathSwapManager deathSwapManager, DeathSwapCommunity plugin) {
        this.deathSwapManager = deathSwapManager;
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        ArrayList<Player> exemptPlayers = plugin.getExemptPlayers();
        sender.sendMessage("Exempt players are:");
        for (Player player:exemptPlayers) {
            sender.sendMessage(player.getName());
        }
        return true;
    }
}
