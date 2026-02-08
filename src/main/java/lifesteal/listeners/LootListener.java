package lifesteal.listeners;

import lifesteal.heart.HeartItem;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.LootGenerateEvent;

public class LootListener implements Listener {

    @EventHandler
    public void onLoot(LootGenerateEvent event) {
        event.getLoot().removeIf(HeartItem::isHeart);
    }
}

