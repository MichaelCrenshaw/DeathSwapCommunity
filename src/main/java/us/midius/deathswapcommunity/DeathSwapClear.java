package us.midius.deathswapcommunity;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;

public class DeathSwapClear implements CommandExecutor {

    DeathSwapCommunity plugin;

    public DeathSwapClear(DeathSwapCommunity plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        plugin.getExemptPlayers().clear();
        sender.sendMessage("Exemptions removed");
        return true;
    }
}
