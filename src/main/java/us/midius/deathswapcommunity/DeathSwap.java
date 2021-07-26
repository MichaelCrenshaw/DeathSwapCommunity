package us.midius.deathswapcommunity;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class DeathSwap implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender.isOp()) {
            sender.sendMessage("How to use the Death Swap plugin! \n Credit to SethBling for initial gamemode \n ds-start:     Start death swap round \n ds-delay:      [int]{minimum delay} [int]{maximum delay} Time between swaps");
            return true;
        } else {
            return false;
        }
    }
}
