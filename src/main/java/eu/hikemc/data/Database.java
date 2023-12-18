package eu.hikemc.data;

import java.sql.*;

import eu.hikemc.Main;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class Database {
    public Connection connection;

    public void connect() throws SQLException {
        FileConfiguration config = Main.getInstance().getConfig();
        String url = config.getString("database.url");
        String user = config.getString("database.username");
        String password = config.getString("database.password");

        this.connection = DriverManager.getConnection(url, user, password);
    }

    public Connection getConnection() {
        return connection;
    }

    public void initializeDatabase() {

        try {
            Connection connection = getConnection();
            Statement statement = connection.createStatement();

            StringBuilder createTableQuery = new StringBuilder("CREATE TABLE IF NOT EXISTS statystyki (")
                    .append("uuid VARCHAR(36) NOT NULL,")
                    .append("money DOUBLE NOT NULL")
                    .append(")");

            statement.executeUpdate(createTableQuery.toString());

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Statystyki checkPlayerStats(String uuid) throws SQLException {
        Statystyki Statystyki = null;

        try (PreparedStatement statement = getConnection().prepareStatement("SELECT * FROM statystyki WHERE uuid = ?")) {
            statement.setString(1, uuid);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Statystyki = new Statystyki(
                            resultSet.getString("uuid"),
                            resultSet.getDouble("money")
                    );
                }
            }
        }

        return Statystyki;
    }

    public Statystyki getPlayerStatystyki(Player player) throws SQLException {
        Statystyki statystyki = checkPlayerStats(player.getUniqueId().toString());

        if (statystyki == null) {
            statystyki = new Statystyki(player.getUniqueId().toString(), 0.0);
            dodajDoBazy(statystyki);
            updatujBaze(statystyki);
        }

        return statystyki;
    }

    public double getPlayerMoney(String uuid) throws SQLException {
        try (PreparedStatement statement = getConnection().prepareStatement("SELECT money FROM statystyki WHERE uuid = ?")) {
            statement.setString(1, uuid);
            double money = 0.0;

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    money = resultSet.getDouble("money");
                }
            } catch (SQLException exception) {
                exception.printStackTrace();
            } catch (NumberFormatException exception) {
                exception.printStackTrace();
            }

            return money;
        }
    }

    public void dodajDoBazy(Statystyki Statystyki) throws SQLException {
        PreparedStatement statement = getConnection().prepareStatement("INSERT INTO statystyki(uuid, money) VALUES (?, ?)");
        statement.setString(1, Statystyki.getPlayerUUID());
        statement.setDouble(2, Statystyki.getPlayers_money());

        statement.executeUpdate();
        statement.close();
    }

    public void updatujBaze(Statystyki Statystyki) throws SQLException {
        PreparedStatement statement = getConnection().prepareStatement("UPDATE statystyki SET money = ? WHERE uuid = ?");
        statement.setDouble(1, Statystyki.getPlayers_money());
        statement.setString(2, Statystyki.getPlayerUUID());

        statement.executeUpdate();
        statement.close();
    }

    public void setPlayerMoney(String newBalance, Player player) throws SQLException {
        PreparedStatement statement = getConnection().prepareStatement("UPDATE statystyki SET money = ? WHERE uuid = ?");

        try {
            double newerBalance = Double.parseDouble(newBalance);
            statement.setDouble(1, newerBalance);
            statement.setString(2, player.getUniqueId().toString());

            int rowsUpdated = statement.executeUpdate();

        } catch (NumberFormatException | SQLException ex) {
            ex.printStackTrace();
            player.sendMessage(ChatColor.RED + "Wystąpił błąd podczas ustawiania stanu konta gracza");
        } finally {
            statement.close();
        }
    }

    public void addPlayerMoney(Statystyki statystyki, String newBalance, Player player) throws SQLException {
        PreparedStatement statement = getConnection().prepareStatement("UPDATE statystyki SET money = money + ? WHERE uuid = ?");

        try {
            double additionalBalance = Double.parseDouble(newBalance);
            statement.setDouble(1, additionalBalance);
            statement.setString(2, statystyki.getPlayerUUID());

            int rowsUpdated = statement.executeUpdate();

        } catch (NumberFormatException | SQLException ex) {
            ex.printStackTrace();
            player.sendMessage(ChatColor.RED + "Wystąpił błąd podczas dodawania środków do portfela gracza");
        } finally {
            statement.close();
        }
    }

    public void removePlayerMoney(Statystyki statystyki, double newBalance, Player player) throws SQLException {
        PreparedStatement statement = getConnection().prepareStatement("UPDATE statystyki SET money = money - ? WHERE uuid = ?");

        try {
            statement.setDouble(1, newBalance);
            statement.setString(2, statystyki.getPlayerUUID());

            int rowsUpdated = statement.executeUpdate();

        } catch (NumberFormatException | SQLException ex) {
            ex.printStackTrace();
            player.sendMessage(ChatColor.RED + "Wystąpił błąd podczas usuwania środków z portfela gracza");
        } finally {
            statement.close();
        }
    }

    private void closeResources(Statement statement, Connection connection) {
        try {
            if (statement != null) {
                statement.close();
            }

            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
