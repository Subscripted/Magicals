package dev.subscripted.magicals.spells.events;

import dev.subscripted.magicals.spells.items.MagicWand;
import dev.subscripted.magicals.spells.group.SpellLibrary;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Interaction implements Listener {

    private static final Map<UUID, Long> cooldowns = new HashMap<>();
    private static final long COOLDOWN_TIME = 3000;

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        Action action = event.getAction();
        int spellLevel = 1; // Irgend nen kaka level
        if (MagicWand.isMagicWand(player)) {
            if (action.equals(Action.RIGHT_CLICK_AIR) || action.equals(Action.RIGHT_CLICK_BLOCK)) {
                if (!isOnCooldown(player)) {
                    SpellLibrary.castSpell(player, SpellLibrary.Spell.DEFAULT_SPELL, spellLevel);
                    setCooldown(player);
                } else {
                    player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent("§cDu kannst diesen Zauber nur alle 3 Sekunden verwenden!"));
                }
            }
            if (action.equals(Action.LEFT_CLICK_AIR) && player.isSneaking() || (action.equals(Action.LEFT_CLICK_BLOCK) && player.isSneaking())) {
                if (!isOnCooldown(player)) {
                    spellLevel = 3;
                    SpellLibrary.castSpell(player, SpellLibrary.Spell.FIRE_SPELL, spellLevel);
                    setCooldown(player);
                } else {
                    player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent("§cDu kannst diesen Zauber nur alle 3 Sekunden verwenden!"));
                }
            }
        }
    }

    private boolean isOnCooldown(Player player) {
        return cooldowns.containsKey(player.getUniqueId()) && System.currentTimeMillis() < cooldowns.get(player.getUniqueId());
    }

    private void setCooldown(Player player) {
        cooldowns.put(player.getUniqueId(), System.currentTimeMillis() + COOLDOWN_TIME);
    }
}
