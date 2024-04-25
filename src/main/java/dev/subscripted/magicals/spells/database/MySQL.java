package dev.subscripted.magicals.spells.database;

import lombok.SneakyThrows;

import java.sql.*;
import java.util.UUID;

public class MySQL {

    private static String host = "localhost";
    private static int port = 3306;
    private static String database = "spells";
    private static String username = "root";
    private static String password = null;
    private static Connection connection;


    @SneakyThrows
    public static void connect() {
        if (connection != null && !connection.isClosed()) {
            return;
        }

        String url = "jdbc:mysql://" + host + ":" + port + "/" + database + "?useSSL=false";

        connection = DriverManager.getConnection(url, username, password);
    }

    public static void disconnect() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }

    @SneakyThrows
    public static boolean hasGroup(UUID uuid, String group) {
        String query = "SELECT * FROM spell_data WHERE uuid = ? AND spell_group = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, uuid.toString());
            statement.setString(2, group);

            ResultSet resultSet = statement.executeQuery();
            return resultSet.next();
        }
    }


    @SneakyThrows
    public static void createTable() throws SQLException {
        String query = "CREATE TABLE IF NOT EXISTS spell_data (" +
                "id INT AUTO_INCREMENT PRIMARY KEY," +
                "uuid VARCHAR(36) NOT NULL," +
                "level INT NOT NULL," +
                "xp INT NOT NULL DEFAULT 0," +
                "spell1 VARCHAR(255)," +
                "spell2 VARCHAR(255)," +
                "spell3 VARCHAR(255)," +
                "spell_group VARCHAR(50)" +
                ")";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.executeUpdate();
        }
    }

    public static void insertPlayerData(UUID uuid, int level, int xp, String spell1, String spell2, String spell3, String group) throws SQLException {
        String query = "INSERT INTO spell_data (uuid, level, xp, spell1, spell2, spell3, spell_group) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, uuid.toString());
            statement.setInt(2, level);
            statement.setInt(3, xp);
            statement.setString(4, spell1);
            statement.setString(5, spell2);
            statement.setString(6, spell3);
            statement.setString(7, group);

            statement.executeUpdate();
        }
    }

    public static void updatePlayerData(UUID uuid, int level, int xp, String spell1, String spell2, String spell3, String group) throws SQLException {
        String query = "UPDATE spell_data SET level = ?, xp = ?, spell1 = ?, spell2 = ?, spell3 = ?, spell_group = ? WHERE uuid = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, level);
            statement.setInt(2, xp);
            statement.setString(3, spell1);
            statement.setString(4, spell2);
            statement.setString(5, spell3);
            statement.setString(6, group);
            statement.setString(7, uuid.toString());

            statement.executeUpdate();
        }
    }

    public static ResultSet getPlayerData(UUID uuid) throws SQLException {
        String query = "SELECT * FROM spell_data WHERE uuid = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, uuid.toString());

            return statement.executeQuery();
        }
    }

    public static void setSpell1(UUID uuid, String spell1) throws SQLException {
        String query = "UPDATE spell_data SET spell1 = ? WHERE uuid = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, spell1);
            statement.setString(2, uuid.toString());

            statement.executeUpdate();
        }
    }

    public static void setSpell2(UUID uuid, String spell2) throws SQLException {
        String query = "UPDATE spell_data SET spell2 = ? WHERE uuid = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, spell2);
            statement.setString(2, uuid.toString());

            statement.executeUpdate();
        }
    }

    public static void setSpell3(UUID uuid, String spell3) throws SQLException {
        String query = "UPDATE spell_data SET spell3 = ? WHERE uuid = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, spell3);
            statement.setString(2, uuid.toString());

            statement.executeUpdate();
        }
    }

    public static String getSpell1(UUID uuid) throws SQLException {
        String query = "SELECT spell1 FROM spell_data WHERE uuid = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, uuid.toString());

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getString("spell1");
            }
        }

        return null;
    }

    public static String getSpell2(UUID uuid) throws SQLException {
        String query = "SELECT spell2 FROM spell_data WHERE uuid = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, uuid.toString());

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getString("spell2");
            }
        }

        return null;
    }

    public static String getSpell3(UUID uuid) throws SQLException {
        String query = "SELECT spell3 FROM spell_data WHERE uuid = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, uuid.toString());

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getString("spell3");
            }
        }

        return null;
    }



    public static int getLevel(UUID uuid) throws SQLException {
        String query = "SELECT level FROM spell_data WHERE uuid = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, uuid.toString());

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("level");
            }
        }

        return -1;
    }

    public static void setLevel(UUID uuid, int level) throws SQLException {
        String query = "UPDATE spell_data SET level = ? WHERE uuid = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, level);
            statement.setString(2, uuid.toString());

            statement.executeUpdate();
        }
    }

    public static boolean hasPlayerData(UUID uuid) throws SQLException {
        String query = "SELECT * FROM spell_data WHERE uuid = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, uuid.toString());
        try (ResultSet resultSet = statement.executeQuery()) {
            return resultSet.next();
        }
    }


    public void updateLevel(UUID uuid, int level) throws SQLException {
        String query = "UPDATE spell_data SET level = level + ? WHERE uuid = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, level);
            statement.setString(2, uuid.toString());

            statement.executeUpdate();
        }
    }

    public static String getGroup(UUID uuid) throws SQLException {
        String query = "SELECT spell_group FROM spell_data WHERE uuid = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, uuid.toString());

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getString("spell_group");
            }
        }

        return null;
    }

    public void setGroup(UUID uuid, String group) throws SQLException {
        String query = "UPDATE spell_data SET spell_group = ? WHERE uuid = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, group);
            statement.setString(2, uuid.toString());

            statement.executeUpdate();
        }
    }

    public static int getXP(UUID uuid) throws SQLException {
        String query = "SELECT xp FROM spell_data WHERE uuid = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, uuid.toString());
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt("xp");
                }
            }
        }
        return 0; // Falls keine XP gefunden werden, geben wir 0 zurück
    }

    public static void setXP(UUID uuid, int xp) throws SQLException {
        String query = "UPDATE spell_data SET xp = ? WHERE uuid = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, xp);
            statement.setString(2, uuid.toString());
            statement.executeUpdate();
        }
    }
}
