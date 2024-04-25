package dev.subscripted.magicals.spells.group;

import lombok.Getter;

public enum Groups {
    SPIRITUAL("§b§lSpiritual"),
    DESTRUCTOR("§c§lDestructor"),
    PRIEST("§e§lPriest");

    @Getter
    private final String displayName;

    Groups(String displayName) {
        this.displayName = displayName;
    }

}
