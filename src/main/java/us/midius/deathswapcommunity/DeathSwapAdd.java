package us.midius.deathswapcommunity;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class DeathSwapAdd implements CommandExecutor {

    DeathSwapManager deathSwapManager;
    DeathSwapCommunity plugin;

    public DeathSwapAdd(DeathSwapManager deathSwapManager, DeathSwapCommunity plugin) {
        this.deathSwapManager = deathSwapManager;
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 1) {
            if (plugin.getExemptPlayers().remove(args[0])){
                sender.sendMessage("Player removed");
            } else {
                sender.sendMessage("Player not found");
            }
            return true;
        } else {
            sender.sendMessage("Please add an actual argument");
            return false;
        }
    }
}
