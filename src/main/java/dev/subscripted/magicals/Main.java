package dev.subscripted.magicals;

import dev.subscripted.magicals.registery.CommandsRegistery;
import dev.subscripted.magicals.registery.ListenerRegistery;
import dev.subscripted.magicals.spells.database.MySQL;
import lombok.SneakyThrows;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.SQLException;

public final class Main extends JavaPlugin {

    public static Main instance;

    @Override
    public void onEnable() {
        instance = this;
        registerListener();
        registerCommands();
        startMySQL();
    }

    @SneakyThrows
    @Override
    public void onDisable() {
        MySQL.disconnect();
    }

    public static void registerListener() {
        ListenerRegistery.registerListener(instance);
    }

    public static void registerCommands() {
        CommandsRegistery.registerCommands(instance);
    }

    @SneakyThrows
    public static void startMySQL() {
        MySQL.connect();
        MySQL.createTable();
    }

    //TODO: Zauberklassen
    //TODO: Klassen Level


}
