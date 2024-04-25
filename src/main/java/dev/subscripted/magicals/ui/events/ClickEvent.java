package dev.subscripted.magicals.ui.events;

import dev.subscripted.magicals.spells.database.MySQL;
import dev.subscripted.magicals.ui.SpellMenus;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.sql.SQLException;
import java.util.UUID;

public class ClickEvent implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) throws SQLException {
        if (event.getCurrentItem() == null || event.getCurrentItem().getType() == Material.AIR) {
            return;
        }

        Player player = (Player) event.getWhoClicked();
        String playerName = player.getName();
        UUID uuid = player.getUniqueId();
        int lvl = MySQL.getLevel(uuid);

        String inventoryTitle = event.getView().getTitle();
        switch (inventoryTitle) {
            case "§d§lClass Menu":
                event.setCancelled(true);
                switch (event.getCurrentItem().getType()) {
                    case KNOWLEDGE_BOOK:
                        String itemName = event.getCurrentItem().getItemMeta().getDisplayName();
                        player.closeInventory();
                        player.sendMessage("Du hast dich für die Rolle " + itemName + "§r entschieden!");
                        if (!MySQL.hasPlayerData(uuid)) {
                            MySQL.insertPlayerData(uuid, 1, 0,null, null, null, itemName);
                        }

                        //setSpellClass
                        break;

                    case RED_BANNER:
                        player.closeInventory();
                        player.playSound(player.getLocation(), Sound.BLOCK_CHEST_CLOSE, 1L, 1L);
                        break;
                }
                break;

            case "//Confirmation//":

                break;
        }
        if (inventoryTitle.contains("§8Spells Menu lvl §8§l(§e" + lvl + "§8§l)§r §c" + playerName)) {
            event.setCancelled(true);
            switch (event.getCurrentItem().getType()) {
                case PLAYER_HEAD:
                    SpellMenus.openStatsMenu(player);

                    break;
            }
        }
    }
}
