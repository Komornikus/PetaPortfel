package eu.hikemc.listeners;

import eu.hikemc.data.Database;
import eu.hikemc.data.Statystyki;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.sql.SQLException;

public class MainListeners implements Listener {

    private final Database database;

    public MainListeners(Database database) {
        this.database = database;
    }

    public Statystyki getPlayerStatystyki(Player player) throws SQLException {
        Statystyki Statystyki = database.checkPlayerStats(player.getUniqueId().toString());

        if(Statystyki == null) {
            Statystyki = new Statystyki(player.getUniqueId().toString(), 0.0);
            database.dodajDoBazy(Statystyki);
            database.updatujBaze(Statystyki);
        }

        return Statystyki;
    }


    @SuppressWarnings({"all"})

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();

        try {
            Statystyki Statystyki = getPlayerStatystyki(p);
            database.updatujBaze(Statystyki);
        } catch (SQLException exception) {
            exception.printStackTrace();
            System.out.println("Nie można dodać nowego gracza do bazy danych!");
        }
    }
}
