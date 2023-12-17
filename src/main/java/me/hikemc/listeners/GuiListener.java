package me.hikemc.listeners;

import me.hikemc.data.utils.ChatUtils;
import me.hikemc.data.Database;
import me.hikemc.Main;
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

    private Database database;

    private GuiListener(Database database) {
        this.database = database;
    }
    static final FileConfiguration config = Main.getInstance().getConfig();

    @EventHandler
    public void onGuiClick(InventoryClickEvent e) {
        Player player = (Player) e.getWhoClicked();
        ItemStack clickedItem = e.getCurrentItem();
        UUID playerUUID = player.getUniqueId();
        String stringedUUID = playerUUID.toString();

        if (clickedItem == null || clickedItem.getType() == Material.AIR) {
            return;
        }
        if (e.getView().getTitle().equals(ChatUtils.fix(config.getString("sklep-gui.gui-name")))) {
            e.setCancelled(true);

            if(clickedItem.getItemMeta().getDisplayName().equalsIgnoreCase(ChatUtils.fix(config.getString("sklep-gui.item-1.nazwa")))) {
                try {
                    if(database.getPlayerMoney(stringedUUID) > config.getInt("sklep-gui.item-1.cena")) {
//                        database.removePlayerMoney();
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
