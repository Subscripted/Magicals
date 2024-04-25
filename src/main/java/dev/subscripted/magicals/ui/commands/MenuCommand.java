package dev.subscripted.magicals.ui.commands;

import dev.subscripted.magicals.spells.database.MySQL;
import dev.subscripted.magicals.spells.group.Groups;
import dev.subscripted.magicals.spells.group.ui.Menus;
import dev.subscripted.magicals.ui.SpellMenus;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MenuCommand implements CommandExecutor, TabExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("You must be a player to use this command!");
        }
        Player player = (Player) sender;
        UUID uuid = player.getUniqueId();

        if (args.length == 0 && (MySQL.hasGroup(player.getUniqueId(), Groups.DESTRUCTOR.getDisplayName())) || MySQL.hasGroup(player.getUniqueId(), Groups.SPIRITUAL.getDisplayName()) || MySQL.hasGroup(player.getUniqueId(), Groups.PRIEST.getDisplayName())){
            Menus.openMenus(player);
            return false;
        }else {
            player.sendMessage("§cFirst you should choose a role before you can access this menu!");
        }

        if (args[0].equals("selection") && (!MySQL.hasGroup(player.getUniqueId(), Groups.DESTRUCTOR.getDisplayName())) && !MySQL.hasGroup(player.getUniqueId(), Groups.SPIRITUAL.getDisplayName()) && !MySQL.hasGroup(player.getUniqueId(), Groups.PRIEST.getDisplayName())) {
            SpellMenus.openClassChooseMenu(player);
        }
        return false;
    }


    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String s, String[] args) {
        Player player = (Player) sender;
        List<String> list = new ArrayList<>();
        if (args.length == 1 && (!MySQL.hasGroup(player.getUniqueId(), Groups.DESTRUCTOR.getDisplayName())) && !MySQL.hasGroup(player.getUniqueId(), Groups.SPIRITUAL.getDisplayName()) && !MySQL.hasGroup(player.getUniqueId(), Groups.PRIEST.getDisplayName())) {
            list.add("selection");
        }
        return list;
    }
}
