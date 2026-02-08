package lifesteal.commands;

import lifesteal.heart.HeartManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HeartCommand implements CommandExecutor {

    private final HeartManager hearts;

    public HeartCommand(HeartManager hearts) {
        this.hearts = hearts;
    }

    @Override
    public boolean onCommand(CommandSender sender,
                             Command command,
                             String label,
                             String[] args) {

        if (!sender.isOp() || args.length < 3) return true;

        Player target = sender.getServer().getPlayer(args[1]);
        if (target == null) return true;

        int amount;
        try {
            amount = Integer.parseInt(args[2]);
        } catch (NumberFormatException e) {
            return true;
        }

        switch (args[0].toLowerCase()) {
            case "set" -> hearts.setHearts(target, amount);
            case "add" -> hearts.setHearts(
                    target, hearts.getHearts(target) + amount);
            case "remove" -> hearts.setHearts(
                    target, hearts.getHearts(target) - amount);
        }
        return true;
    }
}

