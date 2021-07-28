package us.midius.deathswapcommunity;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.*;

import java.util.ArrayList;

public class DeathSwapManager implements Runnable{

    private ArrayList<Player> playerList = new ArrayList<Player>();
    private DeathSwapCommunity plugin;
    public Objective health;
    public Objective kills;

    public DeathSwapManager(DeathSwapCommunity plugin) {
        this.plugin = plugin;
        this.health = Bukkit.getScoreboardManager().getMainScoreboard().registerNewObjective("health", Criterias.HEALTH, "Health", RenderType.HEARTS);
        this.kills = Bukkit.getScoreboardManager().getMainScoreboard().registerNewObjective("kills", "dummy", "Kills");
    }

    @Override
    public void run() {
        if (plugin.isEmergencyStop()) {
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "scoreboard players reset @a kills");
            playerList.clear();
            return;
        } else {
            if (playerList.size() > 1) {
                Location firstLoc = playerList.get(0).getLocation();
                for (int i = 0; (i+1) <= playerList.size(); i++) {
                    if ((i+1) < playerList.size()) {
                        Player player = playerList.get(i);
                        Location location = playerList.get(i + 1).getLocation();
                        player.teleport(location);
                        playerList.get(i).getPlayer().sendMessage("You swapped with " + playerList.get(i + 1).getName());
                    } else {
                        playerList.get(i).teleport(firstLoc);
                        playerList.get(i).getPlayer().sendMessage("You swapped with " + playerList.get(0).getName());
                        Bukkit.getScheduler().runTaskLater(plugin, plugin.getDeathSwapAnnouncer(), makeDelay());
                        return;
                    }
                }
            } else {
                endGame(playerList.get(0));
                return;
            }
        }
    }

    public int makeDelay() {
        int min = plugin.getMinDelay();
        int max = plugin.getMaxDelay();
        return (int) (((Math.random() * (max - min)) + min) * 20) - 100; //creates random number in range, converts to ticks, removes five seconds, casts to int.
    }

    public void endGame(Player winner) {
        Scoreboard scoreboard = Bukkit.getScoreboardManager().getMainScoreboard();
        Bukkit.broadcastMessage("The winner is " + winner.getName() + "!");
        playerList.clear();
    }

    public void removeExempt (ArrayList<Player> exemptList) {
        for (Player player: exemptList) {
            playerList.remove(player);
        }
    }

    public ArrayList<Player> getPlayerList() {
        return playerList;
    }

    public void setPlayerList(ArrayList<Player> playerList) {
        this.playerList = playerList;
    }
}
