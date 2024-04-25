package dev.subscripted.magicals.spells.group.ui;

import dev.subscripted.magicals.spells.database.MySQL;
import dev.subscripted.magicals.ui.enums.InventoryLine;
import dev.subscripted.magicals.utilities.ItemBuilder;
import lombok.SneakyThrows;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.UUID;

public class Menus {

    @SneakyThrows
    public static void openMenus(Player player) {
        openChooseMenu(player);
    }

    @SneakyThrows
    protected static void openChooseMenu(Player player) {
        String playerName = player.getName();
        UUID uuid = player.getUniqueId();
        int lvl = MySQL.getLevel(uuid);
        Inventory inventory = Bukkit.createInventory(player, 5 * 9, "§8Spells Menu lvl §8§l(§e" + lvl + "§8§l)§r §c" + playerName);

        String spell1 = MySQL.getSpell1(uuid);
        String spell2 = MySQL.getSpell2(uuid);
        String spell3 = MySQL.getSpell3(uuid);

        if (spell1 == null) spell1 = "§cNo Spell Selected";
        if (spell2 == null) spell2 = "§cNo Spell Selected";
        if (spell3 == null) spell3 = "§cNo Spell Selected";

        ItemBuilder spells = new ItemBuilder(Material.BOOK).setDisplayName("§b§lSpells");
        ItemBuilder stats = new ItemBuilder(Material.PLAYER_HEAD).setDisplayName("§b§lYour stats").setSkullOwner(playerName);


        ItemBuilder spellsOnWand = new ItemBuilder(Material.STICK).setDisplayName("§e§lActive Spells").addLoreLine(" ").addLoreLine("§8[§r" + spell1 + "§8]§r").addLoreLine("§8[§r" + spell2 + "§8]§r").addLoreLine("§8[§r" + spell3 + "§8]§r").addLoreLine(" ").addLoreLine("§8§l» §e§lClick §8to edit").setGlowing(true);

        switch (lvl) {
            case 1:
                ItemBuilder lvl1 = new ItemBuilder(Material.GRAY_STAINED_GLASS_PANE).setDisplayName(" ");
                InventoryLine.fillInventoryLine(inventory, InventoryLine.LINE_1, lvl1.build());
                InventoryLine.fillInventoryLine(inventory, InventoryLine.LINE_5, lvl1.build());
                break;
            case 2:
                ItemBuilder lvl2 = new ItemBuilder(Material.LIME_STAINED_GLASS_PANE).setDisplayName(" ");
                InventoryLine.fillInventoryLine(inventory, InventoryLine.LINE_1, lvl2.build());
                InventoryLine.fillInventoryLine(inventory, InventoryLine.LINE_5, lvl2.build());
                break;
            case 3:
                ItemBuilder lvl3 = new ItemBuilder(Material.GREEN_STAINED_GLASS_PANE).setDisplayName(" ");
                InventoryLine.fillInventoryLine(inventory, InventoryLine.LINE_1, lvl3.build());
                InventoryLine.fillInventoryLine(inventory, InventoryLine.LINE_5, lvl3.build());
                break;
            case 4:
                ItemBuilder lvl4 = new ItemBuilder(Material.RED_STAINED_GLASS_PANE).setDisplayName(" ");
                InventoryLine.fillInventoryLine(inventory, InventoryLine.LINE_1, lvl4.build());
                InventoryLine.fillInventoryLine(inventory, InventoryLine.LINE_5, lvl4.build());
                break;
            case 5:
                ItemBuilder lvl5 = new ItemBuilder(Material.YELLOW_STAINED_GLASS_PANE).setDisplayName(" ");
                InventoryLine.fillInventoryLine(inventory, InventoryLine.LINE_1, lvl5.build());
                InventoryLine.fillInventoryLine(inventory, InventoryLine.LINE_5, lvl5.build());
                break;
        }


        inventory.setItem(19, stats.build());
        inventory.setItem(22, spellsOnWand.build());
        inventory.setItem(25, spells.build());

        player.playSound(player.getLocation(), Sound.BLOCK_AMETHYST_BLOCK_STEP, 1L, 1L);
        player.openInventory(inventory);
    }

}
