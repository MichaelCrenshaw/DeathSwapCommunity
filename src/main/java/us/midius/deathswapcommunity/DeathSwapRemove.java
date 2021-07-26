package us.midius.deathswapcommunity;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;

public class DeathSwapRemove implements CommandExecutor {

    DeathSwapManager deathSwapManager;
    DeathSwapCommunity plugin;

    public DeathSwapRemove(DeathSwapManager deathSwapManager, DeathSwapCommunity plugin) {
        this.deathSwapManager = deathSwapManager;
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 1) {
            plugin.getExemptPlayers().add(Bukkit.getPlayer(args[0]));
            return true;
        } else {
            sender.sendMessage("Please specify a player to exempt from death swaps");
            return false;
        }
    }
}
