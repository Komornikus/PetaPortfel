package eu.hikemc.listeners;

import eu.hikemc.data.Database;
import eu.hikemc.data.Statystyki;
import eu.hikemc.utils.ChatUtils;
import eu.hikemc.Main;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.sql.SQLException;
import java.util.UUID;

public class GuiListener implements Listener {

    private final Database database;
    private final Main plugin;

    public GuiListener(Main plugin, Database database) {
        this.plugin = plugin;
        this.database = database;
    }

    @EventHandler
    public void onGuiClick(InventoryClickEvent e) {
        FileConfiguration config = plugin.getConfig();
        Player player = (Player) e.getWhoClicked();
        ItemStack clickedItem = e.getCurrentItem();
        UUID playerUUID = player.getUniqueId();
        String stringedUUID = playerUUID.toString();
        if (clickedItem == null || clickedItem.getType() == Material.AIR) {
            return;
        }
        if (e.getView().getTitle().equals(ChatUtils.fix(config.getString("sklep-gui.gui-name")))) {
            e.setCancelled(true);

            if (clickedItem.getItemMeta().getDisplayName().equalsIgnoreCase(ChatUtils.fix(config.getString("sklep-gui.item-1.nazwa")))) {
                try {
                    if (database.getPlayerMoney(stringedUUID) > config.getInt("sklep-gui.item-1.cena")) {
                        try {
                            Statystyki statystyki = database.getPlayerStatystyki(player);
                            database.removePlayerMoney(statystyki, config.getInt("sklep-gui.item-1.cena"), player);
                            player.sendMessage("Pomyslnie kupiles przedmiot " + ChatUtils.fix(config.getString("sklep-gui.item-1.nazwa")));
                            if(config.getBoolean("sklep-gui.item-1.broadcast-message.enable")) {
                                Bukkit.broadcastMessage(ChatUtils.replace("sklep-gui.item-1.broadcast-message.message", player.getName(), config.getString("sklep-gui.item-1.nazwa")));
                            }
                        } catch (SQLException exception) {
                            exception.printStackTrace();
                        }
                    } else {
                        player.sendMessage(ChatUtils.fix("&cNie stac cie na ten przedmiot!"));
                    }
                    if (database.getPlayerMoney(stringedUUID) > config.getInt("sklep-gui.item-2.cena")) {
                        try {
                            Statystyki statystyki = database.getPlayerStatystyki(player);
                            database.removePlayerMoney(statystyki, config.getInt("sklep-gui.item-2.cena"), player);
                            player.sendMessage("Pomyslnie kupiles przedmiot " + ChatUtils.fix(config.getString("sklep-gui.item-2.nazwa")));
                            if(config.getBoolean("sklep-gui.item-2.broadcast-message.enable")) {
                                Bukkit.broadcastMessage(ChatUtils.replace("sklep-gui.item-2.broadcast-message.message", player.getName(), config.getString("sklep-gui.item-2.nazwa")));
                            }
                        } catch (SQLException exception) {
                            exception.printStackTrace();
                        }
                    } else {
                        player.sendMessage(ChatUtils.fix("&cNie stac cie na ten przedmiot!"));
                    }
                    if (database.getPlayerMoney(stringedUUID) > config.getInt("sklep-gui.item-3.cena")) {
                        try {
                            Statystyki statystyki = database.getPlayerStatystyki(player);
                            database.removePlayerMoney(statystyki, config.getInt("sklep-gui.item-3.cena"), player);
                            player.sendMessage("Pomyslnie kupiles przedmiot " + ChatUtils.fix(config.getString("sklep-gui.item-3.nazwa")));
                            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), config.getString(""));
                            if(config.getBoolean("sklep-gui.item-3.broadcast-message.enable")) {
                                Bukkit.broadcastMessage(ChatUtils.replace("sklep-gui.item-3.broadcast-message.message", player.getName(), config.getString("sklep-gui.item-3.nazwa")));
                            }
                        } catch (SQLException exception) {
                            exception.printStackTrace();
                        }
                    } else {
                        player.sendMessage(ChatUtils.fix("&cNie stac cie na ten przedmiot!"));
                    }
                    if (database.getPlayerMoney(stringedUUID) > config.getInt("sklep-gui.item-4.cena")) {
                        try {
                            Statystyki statystyki = database.getPlayerStatystyki(player);
                            database.removePlayerMoney(statystyki, config.getInt("sklep-gui.item-4.cena"), player);
                            player.sendMessage("Pomyslnie kupiles przedmiot " + ChatUtils.fix(config.getString("sklep-gui.item-4.nazwa")));
                            if(config.getBoolean("sklep-gui.item-4.broadcast-message.enable")) {
                                Bukkit.broadcastMessage(ChatUtils.replace("sklep-gui.item-4.broadcast-message.message", player.getName(), config.getString("sklep-gui.item-4.nazwa")));
                            }
                        } catch (SQLException exception) {
                            exception.printStackTrace();
                        }
                    } else {
                        player.sendMessage(ChatUtils.fix("&cNie stac cie na ten przedmiot!"));
                    }
                    if (database.getPlayerMoney(stringedUUID) > config.getInt("sklep-gui.item-5.cena")) {
                        try {
                            Statystyki statystyki = database.getPlayerStatystyki(player);
                            database.removePlayerMoney(statystyki, config.getInt("sklep-gui.item-5.cena"), player);
                            player.sendMessage("Pomyslnie kupiles przedmiot " + ChatUtils.fix(config.getString("sklep-gui.item-5.nazwa")));
                            if(config.getBoolean("sklep-gui.item-5.broadcast-message.enable")) {
                                Bukkit.broadcastMessage(ChatUtils.replace("sklep-gui.item-5.broadcast-message.message", player.getName(), config.getString("sklep-gui.item-5.nazwa")));
                            }
                        } catch (SQLException exception) {
                            exception.printStackTrace();
                        }
                    } else {
                        player.sendMessage(ChatUtils.fix("&cNie stac cie na ten przedmiot!"));
                    }
                    if (database.getPlayerMoney(stringedUUID) > config.getInt("sklep-gui.item-6.cena")) {
                        try {
                            Statystyki statystyki = database.getPlayerStatystyki(player);
                            database.removePlayerMoney(statystyki, config.getInt("sklep-gui.item-6.cena"), player);
                            player.sendMessage("Pomyslnie kupiles przedmiot " + ChatUtils.fix(config.getString("sklep-gui.item-6.nazwa")));
                            if(config.getBoolean("sklep-gui.item-6.broadcast-message.enable")) {
                                Bukkit.broadcastMessage(ChatUtils.replace("sklep-gui.item-6.broadcast-message.message", player.getName(), config.getString("sklep-gui.item-6.nazwa")));
                            }
                        } catch (SQLException exception) {
                            exception.printStackTrace();
                        }
                    } else {
                        player.sendMessage(ChatUtils.fix("&cNie stac cie na ten przedmiot!"));
                    }
                } catch (SQLException exception) {
                    player.sendMessage(ChatUtils.fix("&cWystapil problem podczas finalizowania platnosci! Skontaktuj sie z administratorem serwera!"));
                    exception.printStackTrace();
                }
            }
        }
    }
}
