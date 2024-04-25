package dev.subscripted.magicals.ui;

import dev.subscripted.magicals.spells.database.MySQL;
import dev.subscripted.magicals.spells.group.Groups;
import dev.subscripted.magicals.ui.enums.InventoryLine;
import dev.subscripted.magicals.utilities.ItemBuilder;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;

import java.sql.SQLException;
import java.util.UUID;

public class SpellMenus {

    @Getter
    private static String GUI_NAME = "§d§lClass Menu";

    @Getter
    private static String STATS_GUI_NAME = "§a§lClass Menu";

    public static void openClassChooseMenu(Player p) {
        Inventory inventory = Bukkit.createInventory(p, 3 * 9, GUI_NAME);

        ItemBuilder gray = new ItemBuilder(Material.GRAY_STAINED_GLASS_PANE).setDisplayName(" ");
        ItemBuilder classes1 = new ItemBuilder(Material.KNOWLEDGE_BOOK).setDisplayName(Groups.SPIRITUAL.getDisplayName()).addEnchant(Enchantment.ARROW_DAMAGE, 1).addItemFlag(ItemFlag.HIDE_ENCHANTS);
        ItemBuilder classes2 = new ItemBuilder(Material.KNOWLEDGE_BOOK).setDisplayName(Groups.DESTRUCTOR.getDisplayName()).addEnchant(Enchantment.ARROW_DAMAGE, 1).addItemFlag(ItemFlag.HIDE_ENCHANTS);
        ItemBuilder classes3 = new ItemBuilder(Material.KNOWLEDGE_BOOK).setDisplayName(Groups.PRIEST.getDisplayName()).addEnchant(Enchantment.ARROW_DAMAGE, 1).addItemFlag(ItemFlag.HIDE_ENCHANTS);
        ItemBuilder close = new ItemBuilder(Material.RED_BANNER).setDisplayName("§c§lClose");

        InventoryLine.fillInventoryLine(inventory, InventoryLine.LINE_1, gray.build());
        InventoryLine.fillInventoryLine(inventory, InventoryLine.LINE_3, gray.build());
        inventory.setItem(10, classes1.build());
        inventory.setItem(13, classes2.build());
        inventory.setItem(16, classes3.build());

        p.playSound(p.getLocation(), Sound.BLOCK_AMETHYST_BLOCK_STEP, 1L, 1L);
        p.openInventory(inventory);
    }

    public static void openStatsMenu(Player p) throws SQLException {
        String playername = p.getName();
        UUID uuid = p.getUniqueId();
        int level = MySQL.getLevel(uuid);
        String group = MySQL.getGroup(uuid);

        Inventory inventory = Bukkit.createInventory(p, 3 * 9, STATS_GUI_NAME);

        ItemBuilder filler = new ItemBuilder(Material.GRAY_STAINED_GLASS_PANE).setDisplayName(" ");
        ItemBuilder stats = new ItemBuilder(Material.PLAYER_HEAD).setDisplayName("§7Stats §8| §a" + playername).setSkullOwner(playername).addLoreLine("§8§l » §7Group | " + group).addLoreLine("§8§l » §7Level | §e" + level);
        ItemBuilder close = new ItemBuilder(Material.RED_BANNER).setDisplayName("§c§lClose");


        InventoryLine.fillInventoryLine(inventory, InventoryLine.LINE_1, filler.build());
        InventoryLine.fillInventoryLine(inventory, InventoryLine.LINE_3, filler.build());

        inventory.setItem(13, stats.build());
        inventory.setItem(9, filler.build());
        inventory.setItem(17, filler.build());
        inventory.setItem(26, close.build());

        p.playSound(p.getLocation(), Sound.BLOCK_AMETHYST_BLOCK_STEP, 1L, 1L);
        p.openInventory(inventory);
    }

}
