package me.hikemc;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;

public class ReloadCommand implements CommandExecutor {

    private Main plugin;

    public ReloadCommand(Main plugin) {
        this.plugin = plugin;
    }


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        FileConfiguration config = Main.getInstance().getConfig();
        String permisja = config.getString("permissions.reloadPermission");
        String permisjamessage = config.getString("messages.noPermissionReload");
        String przeladowano = config.getString("messages.reloadSuccess");
        if (!sender.hasPermission(permisja)) {
            sender.sendMessage(ChatUtils.fix(permisjamessage));
            return true;
        }

        if (plugin.getConfig() == null) {
            sender.sendMessage("§cConfig error! check your config");
            return true;
        }

        plugin.reloadConfig();


        sender.sendMessage(ChatUtils.fix(przeladowano));

        return true;
    }
}
