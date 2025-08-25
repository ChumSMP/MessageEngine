package org.mellurboo.messageEngine.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.mellurboo.messageEngine.MessageEngine;

import java.util.ArrayList;
import java.util.List;

public class offenderMessageHold implements Listener {
    public final MessageEngine plugin;
    public offenderMessageHold(MessageEngine plugin) { this.plugin = plugin; }

    public List<Player>SuspectList = new ArrayList<>();

    public void addToOffenderList(Player p) {
        SuspectList.add(p);
    }

    public void removeFromOffenderList() {
        SuspectList.removeFirst();
    }

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        if (SuspectList.contains(event.getPlayer())) {
            event.getPlayer().sendMessage("\n§c====== You are currently Chat Blocked ======");
            event.getPlayer().sendMessage("§cYou are unable to chat until your recent chats\nare reviewed by an administrator.");
            event.getPlayer().sendMessage("§c========================================");
            event.setCancelled(true);
        }
    }
}
