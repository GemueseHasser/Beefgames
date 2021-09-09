package de.jonas.beefgames.listener;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.jetbrains.annotations.NotNull;

/**
 * In diesem {@link Listener} wird die Aktion ausgeführt, sobald ein {@link Player Nutzer} das Netzwerk betritt.
 */
public final class JoinListener implements Listener {

    @EventHandler
    public void onJoin(@NotNull final PlayerJoinEvent e) {
        // get event player
        final Player player = e.getPlayer();

        // send welcome title
        player.sendTitle(
            ChatColor.AQUA.toString() + ChatColor.BOLD + "Willkommen!",
            ChatColor.GOLD + "Beefgames",
            20,
            30,
            20
        );
    }

}
