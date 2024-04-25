package dev.subscripted.magicals.registery;

import dev.subscripted.magicals.Main;
import dev.subscripted.magicals.spells.events.Interaction;
import dev.subscripted.magicals.spells.group.SpellLibrary;
import dev.subscripted.magicals.ui.events.ClickEvent;
import org.bukkit.Bukkit;

public class ListenerRegistery {

    public static void registerListener(Main instance){
        Bukkit.getPluginManager().registerEvents(new Interaction(), instance);
        Bukkit.getPluginManager().registerEvents(new ClickEvent(), instance);
        Bukkit.getPluginManager().registerEvents(new SpellLibrary(), instance);
    }

}
