package eu.hikemc.listeners;

import eu.hikemc.data.Database;
import eu.hikemc.data.Statystyki;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.sql.SQLException;

public class MainListeners implements Listener {

    public Database database;

    public MainListeners(Database database) {
        this.database = database;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        try {
            if (database != null) {
                Player player = e.getPlayer();
                Statystyki statystyki = database.getPlayerStatystyki(player);
                database.updatujBaze(statystyki);
            } else if(database == null) {
                System.out.println("Błąd: Obiekt bazy danych jest null!");
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
            System.out.println("Nie można dodać nowego gracza do bazy danych!");
        }




    }
}
