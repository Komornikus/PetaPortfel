package eu.hikemc.commands;

import eu.hikemc.Main;
import eu.hikemc.data.Database;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.sql.SQLException;
import java.util.UUID;

public class PortfelCommand implements CommandExecutor {

    private final Database database;

    public PortfelCommand(Database database) {
        this.database = database;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        FileConfiguration config = Main.getInstance().getConfig();
        String perm = config.getString("permissions.portfelPermission");

        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "Musisz być graczem, aby sprawdzić swoje statystyki.");
            return true;
        }

        Player player = (Player) sender;
        UUID playerUUID = player.getUniqueId();

        if (!player.hasPermission(perm)) {
            sender.sendMessage(ChatColor.RED + "Nie masz uprawnień do użycia tej komendy.");
            return true;
        }

        if (args.length == 0) {
            try {
                double playerMoney = database.getPlayerMoney(playerUUID.toString());
                sender.sendMessage("§8» §7Twój stan konta wynosi: §a" + playerMoney + " zł");
            } catch (SQLException exception) {
                exception.printStackTrace();
                sender.sendMessage(ChatColor.RED + "Wystąpił błąd podczas sprawdzania stanu konta.");
            }
            return true;
        }

        if (args.length == 1) {
            Player targetPlayer = Bukkit.getPlayer(args[0]);

            if (targetPlayer == null) {
                sender.sendMessage(ChatColor.RED + "Gracz o podanym nicku nie został znaleziony.");
                return true;
            }

            try {
                double money = database.getPlayerMoney(targetPlayer.getUniqueId().toString());
                sender.sendMessage("§8» §7Portfel gracza: §a" + targetPlayer.getName());
                sender.sendMessage("§8» §7Stan konta wynosi: §a" + database.getPlayerMoney(targetPlayer.getUniqueId().toString()) + "$");
            } catch (SQLException exception) {
                exception.printStackTrace();
                sender.sendMessage(ChatColor.RED + "Wystąpił błąd podczas sprawdzania stanu konta gracza " + targetPlayer.getName());
            }
        } else {
            sender.sendMessage(ChatColor.RED + "Poprawne użycie: /portfel [NICK]");
        }

        return true;
    }
}
