package dev.subscripted.magicals.spells.level;

import dev.subscripted.magicals.spells.database.MySQL;
import lombok.SneakyThrows;

import java.util.UUID;

public class XPSystem {

    @SneakyThrows
    public static int getXP(UUID uuid) {
        return MySQL.getXP(uuid);
    }

    @SneakyThrows
    public static void setXP(UUID uuid, int xp) {
        MySQL.setXP(uuid, xp);
    }

    @SneakyThrows
    public static void addXP(UUID uuid, int xp) {
        int currentXP = getXP(uuid);
        setXP(uuid, currentXP + xp);
    }

    @SneakyThrows
    public static void removeXP(UUID uuid, int xp) {
        int currentXP = getXP(uuid);
        setXP(uuid, Math.max(0, currentXP - xp));
    }
}
