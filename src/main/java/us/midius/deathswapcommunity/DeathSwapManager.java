package us.midius.deathswapcommunity;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.scoreboard.Criterias;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.RenderType;

import java.util.ArrayList;

public class DeathSwapManager implements Runnable{

    private ArrayList<Player> playerList = (ArrayList<Player>) Bukkit.getOnlinePlayers();
    private DeathSwapCommunity plugin;
    private Objective kills;

    public DeathSwapManager(DeathSwapCommunity plugin) {
        this.plugin = plugin;
        Objective health = Bukkit.getScoreboardManager().getMainScoreboard().registerNewObjective("health", Criterias.HEALTH, "Health", RenderType.HEARTS);
        health.setDisplaySlot(DisplaySlot.PLAYER_LIST);
        this.kills = Bukkit.getScoreboardManager().getMainScoreboard().registerNewObjective("kills", "dummy", "Kills");
    }

    @Override
    public void run() {
        if (plugin.isEmergencyStop()) {
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "scoreboard players reset @a kills");
            return;
        }
        if (playerList.size() > 1) {
            Location firstLoc = playerList.get(0).getLocation();
            for (int i = 0; i > playerList.size(); i++) {
                if (i != playerList.size()) {
                    playerList.get(i).teleport(playerList.get((i+1)));
                    playerList.get(i).getPlayer().sendMessage("You swapped with " + playerList.get(i+1).getName());
                } else {
                    playerList.get(i).teleport(firstLoc);
                    playerList.get(i).getPlayer().sendMessage("You swapped with " + playerList.get(0).getName());
                }
            }
            Bukkit.getScheduler().runTaskLater(plugin, plugin.getDeathSwapAnnouncer(), makeDelay());
        } else {
            printGameEnd(playerList.get(0));
            return;
        }
    }

    public int makeDelay() {
        int min = plugin.getMinDelay();
        int max = plugin.getMaxDelay();
        return (int) (((Math.random() * (max - min)) + min) * 20) - 100; //creates random number in range, converts to ticks, removes five seconds, casts to int.
    }

    @EventHandler
    public void onDeath (PlayerDeathEvent event) {
        Player player = event.getEntity().getPlayer();
        int index = playerList.indexOf(player) -1;
        if (index == -1) { index = playerList.size(); }
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "scoreboard " + event.getEntity().getPlayer().getName() + " add 1");
        player.setGameMode(GameMode.SPECTATOR);
        event.setDeathMessage(event.getDeathMessage() + "\n" + player.getName() + " died to " + playerList.get(index).getName());
        playerList.remove(player);
        return;
    }

    public void printGameEnd(Player winner) {
        Bukkit.broadcastMessage("The winner is " + winner.getName() + "!" + "\n Top killers: \n" + Bukkit.getScoreboardManager().getMainScoreboard().getScores("kills"));
    }

    public void removeExempt (ArrayList<Player> exemptList) {
        for (Player player: exemptList) {
            playerList.remove(player);
        }
    }
}
