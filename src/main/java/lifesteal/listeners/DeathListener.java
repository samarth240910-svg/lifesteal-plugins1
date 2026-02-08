package lifesteal.listeners;

import lifesteal.combat.CombatTagManager;
import lifesteal.heart.HeartItem;
import lifesteal.heart.HeartManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class DeathListener implements Listener {

    private final HeartManager heartManager;
    private final CombatTagManager combat;

    public DeathListener(HeartManager heartManager, CombatTagManager combat) {
        this.heartManager = heartManager;
        this.combat = combat;
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        Player victim = event.getEntity();
        Player killer = victim.getKiller();

        heartManager.removeHeart(victim);

        if (killer != null) {
            if (heartManager.getHearts(killer) < 20) {
                heartManager.addHeart(killer);
            } else {
                victim.getWorld().dropItemNaturally(
                        victim.getLocation(),
                        HeartItem.create(1)
                );
            }
        }
    }
}

