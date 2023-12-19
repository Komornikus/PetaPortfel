package eu.hikemc.commands;

import eu.hikemc.gui.SklepGUI;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class wPLNSklep implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {

        if (!(sender instanceof Player)) {
            sender.sendMessage("Ta komende moga uzyc tylko gracze!");
            return true;
        }
        Player player = (Player) sender;
        SklepGUI.openGUI(player);

        return false;
    }
}
