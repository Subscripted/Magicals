package dev.subscripted.magicals.spells.level;

import lombok.Getter;
import org.bukkit.entity.EntityType;

public enum MonsterXP {
    ZOMBIE(EntityType.ZOMBIE, 5),
    SKELETON(EntityType.SKELETON, 7),
    SPIDER(EntityType.SPIDER, 8),
    CREEPER(EntityType.CREEPER, 10),
    CAVE_SPIDER(EntityType.CAVE_SPIDER, 9),
    SILVERFISH(EntityType.SILVERFISH, 6),
    ENDERMAN(EntityType.ENDERMAN, 12),
    WITCH(EntityType.WITCH, 15),
    BLAZE(EntityType.BLAZE, 18),
    GHAST(EntityType.GHAST, 20),
    MAGMA_CUBE(EntityType.MAGMA_CUBE, 25),
    SLIME(EntityType.SLIME, 8),
    VINDICATOR(EntityType.VINDICATOR, 11),
    ILLUSIONER(EntityType.ILLUSIONER, 14),
    PILLAGER(EntityType.PILLAGER, 13),
    RAVAGER(EntityType.RAVAGER, 30),
    EVOKER(EntityType.EVOKER, 22),
    WITHER_SKELETON(EntityType.WITHER_SKELETON, 28),
    PHANTOM(EntityType.PHANTOM, 17),
    DROWNED(EntityType.DROWNED, 10),
    HOGLIN(EntityType.HOGLIN, 12),
    PIGLIN(EntityType.PIGLIN, 11),
    ZOGLIN(EntityType.ZOGLIN, 13),
    STRAY(EntityType.STRAY, 9),
    HUSK(EntityType.HUSK, 6),
    ELDER_GUARDIAN(EntityType.ELDER_GUARDIAN, 40),
    WITHER(EntityType.WITHER, 100),
    ENDER_DRAGON(EntityType.ENDER_DRAGON, 200),
    GUARDIAN(EntityType.GUARDIAN, 25),
    SHULKER(EntityType.SHULKER, 20),
    WANDERING_TRADER(EntityType.WANDERING_TRADER, 1),
    ENDERMITE(EntityType.ENDERMITE, 1),
    GIANT(EntityType.GIANT, 1),
    IRON_GOLEM(EntityType.IRON_GOLEM, 1),
    SNOWMAN(EntityType.SNOWMAN, 1),
    WOLF(EntityType.WOLF, 1),
    VILLAGER(EntityType.VILLAGER, 1),
    PLAYER(EntityType.PLAYER, 1),
    HORSE(EntityType.HORSE, 1),
    LLAMA(EntityType.LLAMA, 1),
    TRADER_LLAMA(EntityType.TRADER_LLAMA, 1),
    TROPICAL_FISH(EntityType.TROPICAL_FISH, 1),
    PARROT(EntityType.PARROT, 1),
    CAT(EntityType.CAT, 1),
    RABBIT(EntityType.RABBIT, 1),
    FOX(EntityType.FOX, 1),
    PANDA(EntityType.PANDA, 1),
    POLAR_BEAR(EntityType.POLAR_BEAR, 1),
    BAT(EntityType.BAT, 1),
    CHICKEN(EntityType.CHICKEN, 1),
    COD(EntityType.COD, 1),
    COW(EntityType.COW, 1),
    DONKEY(EntityType.DONKEY, 1),
    DOLPHIN(EntityType.DOLPHIN, 1),
    SQUID(EntityType.SQUID, 1),
    TURTLE(EntityType.TURTLE, 1),
    PUFFERFISH(EntityType.PUFFERFISH, 1),
    MULE(EntityType.MULE, 1),
    MUSHROOM_COW(EntityType.MUSHROOM_COW, 1),
    OCELOT(EntityType.OCELOT, 1),
    PIG(EntityType.PIG, 1),
    SALMON(EntityType.SALMON, 1),
    SHEEP(EntityType.SHEEP, 1),
    STRIDER(EntityType.STRIDER, 1),
    VEX(EntityType.VEX, 1);

    @Getter
    private final EntityType entityType;
    private final int xp;

    MonsterXP(EntityType entityType, int xp) {
        this.entityType = entityType;
        this.xp = xp;
    }

    public int getXP() {
        return xp;
    }
}
