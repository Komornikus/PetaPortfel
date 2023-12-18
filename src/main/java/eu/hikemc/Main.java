package eu.hikemc;

import eu.hikemc.commands.wPLNSklep;
import eu.hikemc.commands.PortfelCommand;
import eu.hikemc.commands.ReloadCommand;
import eu.hikemc.commands.aportfel;
import eu.hikemc.data.Database;
import eu.hikemc.data.Statystyki;
import eu.hikemc.listeners.GuiListener;
import eu.hikemc.listeners.MainListeners;
import eu.hikemc.placeholders.WalletPlaceholder;
import eu.hikemc.tab.tabCompleterA;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.sql.*;

public final class Main extends JavaPlugin {

    public static Main instance;

    private Database database;

    private Statystyki statystyki;

    @Override
    public void onEnable() {
        saveDefaultConfig();

        File dataFolder = getDataFolder();
        File configFile = new File(dataFolder, "config.yml");
        if (!configFile.exists()) {
            saveResource("config.yml", false);
        }

        instance = this;

        // Connecting to mysql
        this.database = new Database();
        try {
            this.database.connect();
        } catch (SQLException e) {
            System.out.println("Błąd z połączeniem z bazą danych");
            e.printStackTrace();
        }
        finally {
            System.out.println("Connected to database!");
        }

        this.database.initializeDatabase();

        // Registering
        getServer().getPluginManager().registerEvents(new MainListeners(database), this);
        getServer().getPluginManager().registerEvents(new GuiListener(this, database), this);
        getCommand("portfel").setExecutor(new PortfelCommand(database));
        getCommand("aportfel").setExecutor(new aportfel(database));
        getCommand("petaportfel-reload").setExecutor(new ReloadCommand(this));
        getCommand("wplnsklep").setExecutor(new wPLNSklep());

        // Tab completers
        getCommand("aportfel").setTabCompleter(new tabCompleterA());

        // Registering placeholders
        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            new WalletPlaceholder(this).register();
        }
    }

    @Override
    public void onDisable() {
        // Turning off message
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
