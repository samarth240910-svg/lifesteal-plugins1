package lifesteal;

import lifesteal.combat.CombatTagManager;
import lifesteal.heart.HeartManager;
import lifesteal.listeners.DeathListener;
import lifesteal.listeners.JoinListener;
import lifesteal.listeners.LootListener;
import lifesteal.commands.HeartCommand;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class LifestealPlugin extends JavaPlugin {

    private HeartManager heartManager;
    private CombatTagManager combatTagManager;

    @Override
    public void onEnable() {
        heartManager = new HeartManager(this);
        combatTagManager = new CombatTagManager();

        Bukkit.getPluginManager().registerEvents(
                new DeathListener(heartManager, combatTagManager), this);
        Bukkit.getPluginManager().registerEvents(
                new LootListener(), this);
        Bukkit.getPluginManager().registerEvents(
                new JoinListener(heartManager), this);

        getCommand("hearts").setExecutor(
                new HeartCommand(heartManager));

        heartManager.registerRecipe();

        getLogger().info("Lifesteal enabled successfully.");
    }
}

