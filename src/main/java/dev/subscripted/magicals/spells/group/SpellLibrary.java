package dev.subscripted.magicals.spells.group;

import dev.subscripted.magicals.spells.group.ui.Menus;
import dev.subscripted.magicals.spells.items.MagicWand;
import dev.subscripted.magicals.spells.level.MonsterXP;
import dev.subscripted.magicals.spells.level.XPSystem;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.util.Vector;

import java.util.HashMap;
import java.util.Map;

public class SpellLibrary implements Listener {

    private static final Map<Integer, Integer> damageLevels = new HashMap<>();
    private static final Map<Player, Boolean> spellDeathMap = new HashMap<>();

    public static void castSpell(Player player, Spell spell, int level) {
        spellDeathMap.put(player, false); // Reset spell death flag
        spell.cast(player, level);
    }

    static {
        damageLevels.put(1, 10); // Level 1 damage
        damageLevels.put(2, 12); // Level 2 damage
        damageLevels.put(3, 15); // Level 3 damage
        damageLevels.put(4, 16); // Level 4 damage
        damageLevels.put(5, 17); // Level 5 damage
        damageLevels.put(6, (int) 17.5); // Level 6 damage
        damageLevels.put(7, 18); // Level 7 damage
        damageLevels.put(8, 20); // Level 8 damage
        damageLevels.put(9, 21); // Level 9 damage
        damageLevels.put(10, 30); // Level 10 damage
        // Add more levels and damage levels as needed
    }

    public enum Spell {
        DEFAULT_SPELL,
        FIRE_SPELL;

        public void cast(Player player, int level) {
            int damage = damageLevels.getOrDefault(level, 0);
            switch (this) {
                case DEFAULT_SPELL:
                    if (MagicWand.isMagicWand(player)) {
                        player.playSound(player.getLocation(), Sound.ENTITY_ENDER_DRAGON_AMBIENT, 10.0f, 5.0f);
                        shootPinkParticles(player, damage);
                    }
                    break;
                case FIRE_SPELL:
                    if (MagicWand.isMagicWand(player)) {
                        player.playSound(player.getLocation(), Sound.ENTITY_FIREWORK_ROCKET_BLAST, 10.5f, 5.0f);
                        shootFireParticle(player, damage);
                    }
            }
        }
    }

    public static void shootPinkParticles(Player player, int damage) {
        Location playerLocation = player.getEyeLocation();
        Vector direction = playerLocation.getDirection().normalize();

        double interval = 0.1;
        double offset = 1.5;

        Location particleLocation = playerLocation.clone().add(direction.clone().multiply(offset));
        for (double distance = 0; distance <= 20.0; distance += interval) {
            particleLocation.add(direction.clone().multiply(interval));

            Block block = particleLocation.getBlock();
            if (block.getType() == Material.END_CRYSTAL) {
                block.setType(Material.AIR);
                player.getWorld().createExplosion(particleLocation, 4.0f, false, false);
                return;
            }
            if (!block.isPassable() && !block.isLiquid() && !isOpenDoor(block.getType())) {
                return;
            }

            player.getWorld().spawnParticle(Particle.REDSTONE, particleLocation, 1, new Particle.DustOptions(Color.PURPLE, 1));

            for (Entity entity : player.getWorld().getNearbyEntities(particleLocation, 0.2, 0.2, 0.2)) {
                if (entity instanceof LivingEntity && !(entity instanceof Player)) {
                    LivingEntity livingEntity = (LivingEntity) entity;
                    livingEntity.damage(damage);
                    spellDeathMap.put(player, true); // Set spell death flag
                }
            }
        }
    }

    public static void shootFireParticle(Player player, int damage) {
        Location playerLocation = player.getEyeLocation();
        Vector direction = playerLocation.getDirection().normalize();

        double interval = 0.1;
        double offset = 1.5;

        Location particleLocation = playerLocation.clone().add(direction.clone().multiply(offset));
        for (double distance = 0; distance <= 20.0; distance += interval) {
            particleLocation.add(direction.clone().multiply(interval));

            Block block = particleLocation.getBlock();
            if (!block.isPassable() && !block.isLiquid() && !isOpenDoor(block.getType())) {
                return;
            }

            player.getWorld().spawnParticle(Particle.REDSTONE, particleLocation, 1, new Particle.DustOptions(Color.WHITE, 1));

            for (Entity entity : player.getWorld().getNearbyEntities(particleLocation, 0.2, 0.2, 0.2)) {
                if (entity instanceof LivingEntity && !(entity instanceof Player)) {
                    LivingEntity livingEntity = (LivingEntity) entity;
                    livingEntity.damage(damage);
                    spellDeathMap.put(player, true); // Set spell death flag
                }
            }
        }
    }

    private static boolean isOpenDoor(Material material) {
        return material == Material.OAK_DOOR || material == Material.IRON_DOOR || material == Material.DARK_OAK_DOOR ||
                material == Material.ACACIA_DOOR || material == Material.BIRCH_DOOR || material == Material.JUNGLE_DOOR ||
                material == Material.SPRUCE_DOOR || material == Material.CRIMSON_DOOR || material == Material.WARPED_DOOR;
    }

    @EventHandler
    public void onEntityDeath(EntityDeathEvent event) {
        LivingEntity entity = event.getEntity();
        Player player = entity.getKiller();
        if (player != null && spellDeathMap.getOrDefault(player, false)) { // Check if player is the killer with a spell
            grantXP(player, entity);
            spellDeathMap.put(player, false); // Reset spell death flag after granting XP
        }
    }

    private static void grantXP(Player player, LivingEntity target) {
        EntityType entityType = target.getType();
        for (MonsterXP monsterXP : MonsterXP.values()) {
            if (monsterXP.getEntityType() == entityType) {
                int xp = monsterXP.getXP();
                XPSystem.addXP(player.getUniqueId(), xp);
                break;
            }
        }
    }
}
