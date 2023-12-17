package me.hikemc.placeholders;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import me.hikemc.Main;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.sql.SQLException;

public class WalletPlaceholder extends PlaceholderExpansion {

    private final Main plugin;

    public WalletPlaceholder(Main plugin) {
        this.plugin = plugin;
    }

    @Override
    public @NotNull String getIdentifier() {
        return "hikewallet";
    }

    @Override
    public @NotNull String getAuthor() {
        return "xGabriel";
    }

    @Override
    public @NotNull String getVersion() {
        return "1.0";
    }

    @Override
    public String onPlaceholderRequest(Player player, @NotNull String params) {
        if (player == null) {
            return "0.0";
        }
        if (params.equalsIgnoreCase("vpln")) {
            try {
                double kasa = plugin.getDatabase().getPlayerMoney(player.getUniqueId().toString());
                return String.valueOf(kasa);
            } catch (SQLException e) {
                e.printStackTrace();
                return "0";
            }
        }
        return null;
    }
}
