package dev.subscripted.magicals.registery;

import dev.subscripted.magicals.Main;
import dev.subscripted.magicals.ui.commands.MenuCommand;

public class CommandsRegistery {

    public static void registerCommands(Main instance) {
        instance.getCommand("spell").setExecutor(new MenuCommand());
        instance.getCommand("spell").setTabCompleter(new MenuCommand());
    }

}
