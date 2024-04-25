package dev.subscripted.magicals.spells.items;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class MagicWand {
    public static boolean isMagicWand(Player player) {
        ItemStack mainHandItem = player.getInventory().getItemInMainHand();
        if (mainHandItem.getType() == Material.STICK) {
            ItemMeta mainHandMeta = mainHandItem.getItemMeta();
            if (mainHandMeta != null && mainHandMeta.hasDisplayName() && mainHandMeta.getDisplayName().equals("Magic Wand")) {
                return true;
            }
        }
        return false;
    }
}
