package me.hikemc;

import org.bukkit.ChatColor;

public class ChatUtils {

    public static String fix(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }
}
