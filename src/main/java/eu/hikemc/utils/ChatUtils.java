package eu.hikemc.utils;

import eu.hikemc.Main;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;

import static org.bukkit.loot.LootTables.PLAYER;

public class ChatUtils {



    public static String fix(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }


    public static String replace(String path, String playerName, String itemName) {
        FileConfiguration config = Main.getInstance().getConfig();
        String message = config.getString(path)
                .replace("{PLAYER}", playerName)
                .replace("{ITEM-NAME}", itemName);


        return message;
    }
}
