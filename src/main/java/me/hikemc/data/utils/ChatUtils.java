package me.hikemc.data.utils;

import org.bukkit.ChatColor;

public class ChatUtils {

    public static String fix(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }
}
