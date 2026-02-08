package lifesteal.listeners;

import lifesteal.heart.HeartManager;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinListener implements Listener {

    private final HeartManager heartManager;

    public JoinListener(HeartManager heartManager) {
        this.heartManager = heartManager;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        if (player.getAttribute(Attribute.GENERIC_MAX_HEALTH) == null) return;

        int hearts = heartManager.getHearts(player);
        heartManager.setHearts(player, hearts);
    }
}

