package lifesteal.heart;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.ChatColor;

public class HeartItem {

    public static final NamespacedKey KEY =
            new NamespacedKey("lifesteal", "heart");

    public static ItemStack create(int amount) {
        ItemStack item = new ItemStack(Material.NETHER_STAR, amount);
        ItemMeta meta = item.getItemMeta();

        meta.setDisplayName(ChatColor.RED + "❤ Heart");
        meta.getPersistentDataContainer().set(
                KEY, PersistentDataType.INTEGER, 1
        );

        item.setItemMeta(meta);
        return item;
    }

    public static boolean isHeart(ItemStack item) {
        if (item == null || !item.hasItemMeta()) return false;

        return item.getItemMeta()
                .getPersistentDataContainer()
                .has(KEY, PersistentDataType.INTEGER);
    }
}

