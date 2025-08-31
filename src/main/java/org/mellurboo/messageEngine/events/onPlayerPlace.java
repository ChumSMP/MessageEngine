package org.mellurboo.messageEngine.events;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class onPlayerPlace implements Listener {
    @EventHandler
    public void onPlayerPlaceSign(BlockPlaceEvent event) {
        Block placed = event.getBlockPlaced();
        if (placed.getType().name().endsWith("_SIGN") || placed.getType().name().endsWith("_WALL_SIGN")) {
            event.setCancelled(true);
            event.getPlayer().sendMessage("\n§c====== You are currently Chat Blocked ======");
            event.getPlayer().sendMessage("§cYou are unable to use signs until your recent chats\nare reviewed by an administrator.");
            event.getPlayer().sendMessage("§c========================================");
        }
    }
}
