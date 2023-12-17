package eu.hikemc.gui;

import eu.hikemc.Main;
import eu.hikemc.utils.ChatUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class SklepGUI{

    static FileConfiguration config = Main.getInstance().getConfig();


    public static void openGUI(Player player) {
        @SuppressWarnings({"all"})
        Inventory SklepGUI = Bukkit.createInventory(null, config.getInt("sklep-gui.gui-size"), ChatUtils.fix(config.getString("sklep-gui.gui-name")));

        @SuppressWarnings({"all"})
        ItemStack item1 = new ItemStack(Material.matchMaterial(config.getString("sklep-gui.item-1.material")));
        ItemMeta item1Meta = item1.getItemMeta();
        item1Meta.setDisplayName(ChatUtils.fix(ChatUtils.fix(config.getString("sklep-gui.item-1.nazwa"))));
        List<String> lore1 = config.getStringList("sklep-gui.item-1.opis" +
                "§aCena: " + config.getString("sklep-gui.item-1.cena"));
        item1Meta.setLore(lore1);
        item1.setItemMeta(item1Meta);
        @SuppressWarnings({"all"})
        ItemStack item2 = new ItemStack(Material.matchMaterial(config.getString("sklep-gui.item-2.material")));
        ItemMeta item2Meta = item2.getItemMeta();
        item2Meta.setDisplayName(ChatUtils.fix(ChatUtils.fix(config.getString("sklep-gui.item-2.nazwa"))));
        List<String> lore2 = config.getStringList("sklep-gui.item-2.opis" +
                "§aCena: " + config.getString("sklep-gui.item-2.cena"));
        item2Meta.setLore(lore2);
        item2.setItemMeta(item2Meta);
        @SuppressWarnings({"all"})
        ItemStack item3 = new ItemStack(Material.matchMaterial(config.getString("sklep-gui.item-3.material")));
        ItemMeta item3Meta = item3.getItemMeta();
        item3Meta.setDisplayName(ChatUtils.fix(ChatUtils.fix(config.getString("sklep-gui.item-3.nazwa"))));
        List<String> lore3 = config.getStringList("sklep-gui.item-3.opis" +
                "§aCena: " + config.getString("sklep-gui.item-3.cena"));
        item3Meta.setLore(lore3);
        item3.setItemMeta(item3Meta);
        @SuppressWarnings({"all"})
        ItemStack item4 = new ItemStack(Material.matchMaterial(config.getString("sklep-gui.item-4.material")));
        ItemMeta item4Meta = item4.getItemMeta();
        item4Meta.setDisplayName(ChatUtils.fix(config.getString("sklep-gui.item-4.nazwa")));
        List<String> lore4 = config.getStringList("sklep-gui.item-4.opis" + "\n" +
                "§aCena: " + config.getString("sklep-gui.item-4.cena"));
        item4Meta.setLore(lore4);
        item4.setItemMeta(item4Meta);
        @SuppressWarnings({"all"})
        ItemStack item5 = new ItemStack(Material.matchMaterial(config.getString("sklep-gui.item-5.material")));
        ItemMeta item5Meta = item5.getItemMeta();
        item5Meta.setDisplayName(ChatUtils.fix(config.getString("sklep-gui.item-5.nazwa")));
        List<String> lore5 = config.getStringList("sklep-gui.item-5.opis" +
                "§aCena: " + config.getString("sklep-gui.item-5.cena"));
        item5Meta.setLore(lore5);
        item5.setItemMeta(item5Meta);
        @SuppressWarnings({"all"})
        ItemStack item6 = new ItemStack(Material.matchMaterial(config.getString("sklep-gui.item-6.material")));
        ItemMeta item6Meta = item6.getItemMeta();
        item6Meta.setDisplayName(ChatUtils.fix(config.getString("sklep-gui.item-6.nazwa")));
        List<String> lore6 = config.getStringList("sklep-gui.item-6.opis" +
                "§aCena: " + config.getString("sklep-gui.item-6.cena"));
        item6Meta.setLore(lore6);
        item6.setItemMeta(item6Meta);


    }
}