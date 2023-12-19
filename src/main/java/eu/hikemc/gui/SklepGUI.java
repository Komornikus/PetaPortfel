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

import java.util.ArrayList;
import java.util.List;

public class SklepGUI {

    static FileConfiguration config = Main.getInstance().getConfig();


    public static void openGUI(Player player) {
        @SuppressWarnings({"all"})
        Inventory SklepGUI = Bukkit.createInventory(null, config.getInt("sklep-gui.gui-size"), ChatUtils.fix(config.getString("sklep-gui.gui-name")));

        @SuppressWarnings({"all"})
        ItemStack item1 = new ItemStack(Material.matchMaterial(config.getString("sklep-gui.item-1.material")));
        ItemMeta item1Meta = item1.getItemMeta();
        item1Meta.setDisplayName(ChatUtils.fix(ChatUtils.fix(config.getString("sklep-gui.item-1.nazwa"))));
        List<String> lore1 = new ArrayList<>();
        lore1.add(ChatUtils.fix(config.getString("sklep-gui.item-1.opis.linia1")));
        lore1.add(ChatUtils.fix(config.getString("sklep-gui.item-1.opis.linia1")));
        lore1.add(ChatUtils.fix("&aCena: &2" + config.getInt("sklep-gui.item-1.cena") + " " + config.getString("waluta")));
        item1Meta.setLore(lore1);
        item1.setItemMeta(item1Meta);
        @SuppressWarnings({"all"})
        ItemStack item2 = new ItemStack(Material.matchMaterial(config.getString("sklep-gui.item-2.material")));
        ItemMeta item2Meta = item2.getItemMeta();
        item2Meta.setDisplayName(ChatUtils.fix(ChatUtils.fix(config.getString("sklep-gui.item-2.nazwa"))));
        List<String> lore2 = new ArrayList<>();
        lore2.add(ChatUtils.fix(config.getString("sklep-gui.item-2.opis.linia1")));
        lore2.add(ChatUtils.fix(config.getString("sklep-gui.item-2.opis.linia1")));
        lore2.add(ChatUtils.fix("&aCena: &2" + config.getInt("sklep-gui.item-2.cena") + " " + config.getString("waluta")));
        item2Meta.setLore(lore2);
        item2.setItemMeta(item2Meta);
        @SuppressWarnings({"all"})
        ItemStack item3 = new ItemStack(Material.matchMaterial(config.getString("sklep-gui.item-3.material")));
        ItemMeta item3Meta = item3.getItemMeta();
        item3Meta.setDisplayName(ChatUtils.fix(ChatUtils.fix(config.getString("sklep-gui.item-3.nazwa"))));
        List<String> lore3 = new ArrayList<>();
        lore3.add(ChatUtils.fix(config.getString("sklep-gui.item-3.opis.linia1")));
        lore3.add(ChatUtils.fix(config.getString("sklep-gui.item-3.opis.linia1")));
        lore3.add(ChatUtils.fix("&aCena: &2" + config.getInt("sklep-gui.item-3.cena") + " " + config.getString("waluta")));
        item3Meta.setLore(lore3);
        item3.setItemMeta(item3Meta);
        @SuppressWarnings({"all"})
        ItemStack item4 = new ItemStack(Material.matchMaterial(config.getString("sklep-gui.item-4.material")));
        ItemMeta item4Meta = item4.getItemMeta();
        item4Meta.setDisplayName(ChatUtils.fix(config.getString("sklep-gui.item-4.nazwa")));
        List<String> lore4 = new ArrayList<>();
        lore4.add(ChatUtils.fix(config.getString("sklep-gui.item-4.opis.linia1")));
        lore4.add(ChatUtils.fix(config.getString("sklep-gui.item-4.opis.linia1")));
        lore4.add(ChatUtils.fix("&aCena: &2" + config.getInt("sklep-gui.item-4.cena") + " " + config.getString("waluta")));
        item4Meta.setLore(lore4);
        item4.setItemMeta(item4Meta);
        @SuppressWarnings({"all"})
        ItemStack item5 = new ItemStack(Material.matchMaterial(config.getString("sklep-gui.item-5.material")));
        ItemMeta item5Meta = item5.getItemMeta();
        item5Meta.setDisplayName(ChatUtils.fix(config.getString("sklep-gui.item-5.nazwa")));
        List<String> lore5 = new ArrayList<>();
        lore5.add(ChatUtils.fix(config.getString("sklep-gui.item-5.opis.linia1")));
        lore5.add(ChatUtils.fix(config.getString("sklep-gui.item-5.opis.linia1")));
        lore5.add(ChatUtils.fix("&aCena: &2" + config.getInt("sklep-gui.item-5.cena") + " " + config.getString("waluta")));
        item5Meta.setLore(lore5);
        item5.setItemMeta(item5Meta);
        @SuppressWarnings({"all"})
        ItemStack item6 = new ItemStack(Material.matchMaterial(config.getString("sklep-gui.item-6.material")));
        ItemMeta item6Meta = item6.getItemMeta();
        item6Meta.setDisplayName(ChatUtils.fix(config.getString("sklep-gui.item-6.nazwa")));
        List<String> lore6 = new ArrayList<>();
        lore6.add(ChatUtils.fix(config.getString("sklep-gui.item-6.opis.linia1")));
        lore6.add(ChatUtils.fix(config.getString("sklep-gui.item-6.opis.linia1")));
        lore6.add(ChatUtils.fix("&aCena: &2" + config.getInt("sklep-gui.item-6.cena") + " " + config.getString("waluta")));
        item6Meta.setLore(lore6);
        item6.setItemMeta(item6Meta);


        SklepGUI.setItem(config.getInt("sklep-gui.item-1.slot"), item1);
        SklepGUI.setItem(config.getInt("sklep-gui.item-2.slot"), item2);
        SklepGUI.setItem(config.getInt("sklep-gui.item-3.slot"), item3);
        SklepGUI.setItem(config.getInt("sklep-gui.item-4.slot"), item4);
        SklepGUI.setItem(config.getInt("sklep-gui.item-5.slot"), item5);
        SklepGUI.setItem(config.getInt("sklep-gui.item-6.slot"), item6);

        player.openInventory(SklepGUI);


    }
}