package me.hikemc;

import me.hikemc.Placeholder.WalletPlaceholder;
import me.hikemc.tab.tabCompleterA;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.sql.*;

public final class Main extends JavaPlugin {



    public static Main instance;


    @SuppressWarnings({"all"})
    private Database database;
    private Statystyki Statystyki;

    @SuppressWarnings({"all"})
    @Override
    public void onEnable() {
        getCommand("hikewallet-reload").setExecutor(new ReloadCommand(this));





        getCommand("aportfel").setTabCompleter(new tabCompleterA());


        File dataFolder = getDataFolder();
        File configFile = new File(dataFolder, "config.yml");
        if (!configFile.exists()) {
            saveResource("config.yml", false);
        }
        instance = this;
        this.database = new Database();
        try {
            this.database.initializeDatabase();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Nie udało się połączyć z bazą danych");
            getServer().getPluginManager().disablePlugin(this);
        }
        getServer().getPluginManager().registerEvents(new Listeners(database), this);
        getCommand("portfel").setExecutor(new PortfelCommand(database));
        getCommand("aportfel").setExecutor(new aportfel(database));
        saveDefaultConfig();
        System.out.println("---------------------------");
        System.out.println("");
        System.out.println("Plugin HikeWallet został pomyślnie uruchomiony");
        System.out.println("Autor: xGabriell, podateK_");
        System.out.println("Wersja pluginu: 1.0");
        System.out.println("");
        System.out.println("---------------------------");
        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            new WalletPlaceholder(this).register();
        }
    }


    @SuppressWarnings({"all"})
    @Override
    public void onDisable() {
        System.out.println("---------------------------");
        System.out.println("");
        System.out.println("Plugin HikeWallet został pomyślnie wyłączony");
        System.out.println("Autor: xGabriell, podateK_");
        System.out.println("Wersja pluginu: 1.0");
        System.out.println("");
        System.out.println("---------------------------");
    }


    public static Main getInstance() {
        return instance;
    }

    @Override
    public @NotNull FileConfiguration getConfig() {
        return super.getConfig();
    }
    public Database getDatabase() {
        return database;
    }
}
