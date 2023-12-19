package eu.hikemc.listeners;

import eu.hikemc.Main;
import eu.hikemc.data.Database;
import eu.hikemc.data.Statystyki;
import eu.hikemc.utils.ChatUtils;
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

            if (e.isLeftClick()) {
                try {
                    Statystyki statystyki = database.getPlayerStatystyki(player);
                    double playerMoney = database.getPlayerMoney(stringedUUID);

                    if (clickedItem.getItemMeta().getDisplayName().equalsIgnoreCase(ChatUtils.fix(config.getString("sklep-gui.item-1.nazwa")))) {
                        handlePurchase(player, playerMoney, config, statystyki, "sklep-gui.item-1");
                    } else if (clickedItem.getItemMeta().getDisplayName().equalsIgnoreCase(ChatUtils.fix(config.getString("sklep-gui.item-2.nazwa")))) {
                        handlePurchase(player, playerMoney, config, statystyki, "sklep-gui.item-2");
                    } else if (clickedItem.getItemMeta().getDisplayName().equalsIgnoreCase(ChatUtils.fix(config.getString("sklep-gui.item-3.nazwa")))) {
                        handlePurchase(player, playerMoney, config, statystyki, "sklep-gui.item-3");
                    } else if (clickedItem.getItemMeta().getDisplayName().equalsIgnoreCase(ChatUtils.fix(config.getString("sklep-gui.item-4.nazwa")))) {
                        handlePurchase(player, playerMoney, config, statystyki, "sklep-gui.item-4");
                    } else if (clickedItem.getItemMeta().getDisplayName().equalsIgnoreCase(ChatUtils.fix(config.getString("sklep-gui.item-5.nazwa")))) {
                        handlePurchase(player, playerMoney, config, statystyki, "sklep-gui.item-5");
                    } else if (clickedItem.getItemMeta().getDisplayName().equalsIgnoreCase(ChatUtils.fix(config.getString("sklep-gui.item-6.nazwa")))) {
                        handlePurchase(player, playerMoney, config, statystyki, "sklep-gui.item-6");
                    }
                } catch (SQLException exception) {
                    player.sendMessage(ChatUtils.fix("&cWystapil problem podczas finalizowania platnosci! Skontaktuj sie z administratorem serwera!"));
                    exception.printStackTrace();
                }
            }
        }
    }

    private void handlePurchase(Player player, double playerMoney, FileConfiguration config, Statystyki statystyki, String itemName) throws SQLException {
        double itemCost = config.getInt(itemName + ".cena");

        if (playerMoney > itemCost) {
            database.removePlayerMoney(statystyki, itemCost, player);
            player.sendMessage("Pomyslnie kupiles przedmiot " + ChatUtils.fix(config.getString(itemName + ".nazwa")));

            if (config.getBoolean(itemName + ".broadcast-message.enable")) {
                Bukkit.broadcastMessage(ChatUtils.fix(ChatUtils.replace(itemName + ".broadcast-message.message", player.getName(), config.getString(itemName + ".nazwa"))));
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), config.getString(itemName + ".komenda").replace("{PLAYER}", player.getName()));
            }

            player.closeInventory();
        } else {
            player.sendMessage(ChatUtils.fix("&cNie stac cie na ten przedmiot!"));



    }
}
