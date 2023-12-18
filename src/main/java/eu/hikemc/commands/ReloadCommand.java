package eu.hikemc.commands;

import eu.hikemc.utils.ChatUtils;
import eu.hikemc.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;

import java.io.IOException;

public class ReloadCommand implements CommandExecutor {

    private Main plugin;

    public ReloadCommand(Main plugin) {
        this.plugin = plugin;
    }

    FileConfiguration config = Main.getInstance().getConfig();
    String permisja = config.getString("permissions.reloadPermission");
    String permisjamessage = config.getString("messages.noPermissionReload");
    String przeladowano = config.getString("messages.reloadSuccess");


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!sender.hasPermission(permisja)) {
            sender.sendMessage(ChatUtils.fix(permisjamessage));
            return true;
        }

        if (plugin.getConfig() == null) {
            sender.sendMessage("§cConfig error! check your config");
            return true;
        }


        try{

            config.save("config.yml");
            config.load("config.yml");
            plugin.reloadConfig();
            sender.sendMessage("&2PetaPortfel: " + ChatUtils.fix(przeladowano));
        } catch (IOException exception) {
            exception.printStackTrace();
        } catch (InvalidConfigurationException e) {
            e.printStackTrace();
        }
        return true;
    }
}
