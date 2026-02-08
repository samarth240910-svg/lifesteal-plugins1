package lifesteal.combat;

import org.bukkit.entity.Player;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class CombatTagManager {

    private final Set<UUID> combatTagged = new HashSet<>();

    public void tag(Player player) {
        combatTagged.add(player.getUniqueId());
    }

    public void untag(Player player) {
        combatTagged.remove(player.getUniqueId());
    }

    public boolean isTagged(Player player) {
        return combatTagged.contains(player.getUniqueId());
    }
}

