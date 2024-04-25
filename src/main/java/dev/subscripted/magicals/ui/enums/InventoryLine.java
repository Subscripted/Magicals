package dev.subscripted.magicals.ui.enums;

import lombok.Getter;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;


    @Getter
    public enum InventoryLine {
        LINE_1(0), LINE_2(9), LINE_3(18), LINE_4(27), LINE_5(36), LINE_6(45);

        private final int startingIndex;

        InventoryLine(int startingIndex) {
            this.startingIndex = startingIndex;
        }



    public static void fillInventoryLine(Inventory inventory, InventoryLine line, ItemStack item) {
        int startingIndex = line.getStartingIndex();
        for (int i = startingIndex; i < startingIndex + 9; i++) {
            inventory.setItem(i, item);
        }
    }
}