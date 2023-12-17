package me.hikemc;

import java.sql.*;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class Database {




    private Connection connection;

    @SuppressWarnings({"all"})

    public Connection getConnection() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            return connection;
        }

        FileConfiguration config = Main.getInstance().getConfig();
        String url = config.getString("database.url");
        String user = config.getString("database.username");
        String password = config.getString("database.password");

        Connection connection = DriverManager.getConnection(url, user, password);

        this.connection = connection;

        return connection;
    }

    @SuppressWarnings({"all"})

    public void initializeDatabase() throws SQLException {
        Connection connection = null;
        Statement statement = null;

        try {
            connection = getConnection();
            statement = connection.createStatement();

            StringBuilder createTableQuery = new StringBuilder("CREATE TABLE IF NOT EXISTS statystyki (")
                    .append("uuid VARCHAR(36) NOT NULL,")
                    .append("money DOUBLE NOT NULL")
                    .append(")");

            statement.executeUpdate(createTableQuery.toString());

        } finally {
            closeResources(statement, connection);
        }
    }


    @SuppressWarnings({"all"})
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


    @SuppressWarnings({"all"})
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
            } try {
                return money;
            } catch (NumberFormatException exceptions) {
                exceptions.printStackTrace();
            }

            return money;
        }
    }


    @SuppressWarnings({"all"})

    public void dodajDoBazy(Statystyki Statystyki) throws SQLException {
        PreparedStatement statement = getConnection().prepareStatement("INSERT INTO statystyki(uuid, money) VALUES (?, ?)");
        statement.setString(1, Statystyki.getPlayerUUID());
        statement.setDouble(2, Statystyki.getPlayers_money());

        statement.executeUpdate();
        statement.close();
    }

    @SuppressWarnings({"all"})

    public void updatujBaze(Statystyki Statystyki) throws SQLException {
        PreparedStatement statement = getConnection().prepareStatement("UPDATE statystyki SET money = ? WHERE uuid = ?");
        statement.setDouble(1, Statystyki.getPlayers_money());
        statement.setString(2, Statystyki.getPlayerUUID());

        statement.executeUpdate();
        statement.close();
    }


    @SuppressWarnings({"all"})
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

    @SuppressWarnings({"all"})
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

    @SuppressWarnings({"all"})
    public void removePlayerMoney(Statystyki statystyki, String newBalance, Player player) throws SQLException {
        PreparedStatement statement = getConnection().prepareStatement("UPDATE statystyki SET money = money - ? WHERE uuid = ?");

        try {
            double removalBalance = Double.parseDouble(newBalance);
            statement.setDouble(1, removalBalance);
            statement.setString(2, statystyki.getPlayerUUID());

            int rowsUpdated = statement.executeUpdate();

        } catch (NumberFormatException | SQLException ex) {
            ex.printStackTrace();
            player.sendMessage(ChatColor.RED + "Wystąpił błąd podczas usuwania środków z portfela gracza");
        } finally {
            statement.close();
        }
    }

    @SuppressWarnings({"all"})

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
