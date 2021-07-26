package us.midius.deathswapcommunity;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;

public class DeathSwapDelay implements CommandExecutor {

    DeathSwapCommunity plugin;

    public DeathSwapDelay(DeathSwapCommunity plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 2) {
            int arg1;
            int arg2;
            try {
                arg1 = Integer.parseInt(args[0]);
                arg2 = Integer.parseInt(args[1]);
            } catch (NumberFormatException e) {
                sender.sendMessage("Args are not both numbers, please try again.");
                return false;
            }
            plugin.setMinDelay(arg1);
            plugin.setMaxDelay(arg2);
        }
        return false;
    }
}


