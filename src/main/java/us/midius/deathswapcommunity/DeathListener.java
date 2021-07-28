package us.midius.deathswapcommunity;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import java.util.ArrayList;

public class DeathListener implements Listener {

    DeathSwapManager DSManager;

    public DeathListener(DeathSwapManager deathSwapManager) {
        this.DSManager = deathSwapManager;
    }

    @EventHandler
    public void onPlayerDeath (PlayerDeathEvent event) {
        ArrayList<Player> playerList = DSManager.getPlayerList();
        Bukkit.broadcastMessage("onDeaty called");
        int index = playerList.indexOf(event.getEntity());
        Player player = playerList.get(index);
        int killerIndex = index + 1;
        Bukkit.broadcastMessage("passed declarations");
        if (killerIndex == playerList.size()) {
            killerIndex = 0;
        } else {
            killerIndex = index + 1;
        }
        Bukkit.broadcastMessage("passed if statement");
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "scoreboard players add " + playerList.get(killerIndex).getName() + " kills 1");
        player.setGameMode(GameMode.SPECTATOR);
        Bukkit.broadcastMessage("gamemode set");
        event.setDeathMessage(event.getDeathMessage() + "\n" + player.getName() + " died to " + playerList.get(killerIndex).getName());
        playerList.remove(player);
        return;
    }

}
