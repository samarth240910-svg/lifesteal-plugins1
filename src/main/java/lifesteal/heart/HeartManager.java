package lifesteal.heart;

import org.bukkit.BanList;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.plugin.java.JavaPlugin;

public class HeartManager {

    private final JavaPlugin plugin;
    private static final int MAX_HEARTS = 20;

    public HeartManager(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    public int getHearts(Player player) {
        return (int) (player.getAttribute(Attribute.GENERIC_MAX_HEALTH)
                .getBaseValue() / 2);
    }

    public void setHearts(Player player, int hearts) {
        hearts = Math.max(0, Math.min(MAX_HEARTS, hearts));
        player.getAttribute(Attribute.GENERIC_MAX_HEALTH)
                .setBaseValue(hearts * 2.0);
    }

    public void addHeart(Player player) {
        setHearts(player, getHearts(player) + 1);
    }

    public void removeHeart(Player player) {
        int hearts = getHearts(player) - 1;

        if (hearts <= 0) {
            banPlayer(player);
        } else {
            setHearts(player, hearts);
        }
    }

    private void banPlayer(Player player) {
        dropEnderChest(player);

        Bukkit.getBanList(BanList.Type.NAME)
                .addBan(player.getName(), "Out of hearts", null, "Lifesteal");

        Bukkit.getScheduler().runTask(plugin, () ->
                player.kickPlayer(ChatColor.RED + "Out of hearts"));
    }

    private void dropEnderChest(Player player) {
        Location loc = player.getLocation();

        for (ItemStack item : player.getEnderChest().getContents()) {
            if (item != null) {
                loc.getWorld().dropItemNaturally(loc, item);
            }
        }
        player.getEnderChest().clear();
    }

    public void registerRecipe() {
        ShapedRecipe recipe = new ShapedRecipe(
                new NamespacedKey(plugin, "heart"),
                HeartItem.create(1)
        );

        recipe.shape("DND", "NTN", "DND");
        recipe.setIngredient('D', Material.DIAMOND);
        recipe.setIngredient('N', Material.NETHERITE_INGOT);
        recipe.setIngredient('T', Material.TOTEM_OF_UNDYING);

        Bukkit.addRecipe(recipe);
    }
}

