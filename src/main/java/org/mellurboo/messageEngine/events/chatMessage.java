package org.mellurboo.messageEngine.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.mellurboo.messageEngine.MessageEngine;

public class chatMessage implements Listener {
    public final MessageEngine plugin;
    public chatMessage(MessageEngine plugin) { this.plugin = plugin; }
    public int messagesSinceLastBroadcast = 0;

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        messagesSinceLastBroadcast++;
    }

    public void resetMessagesSinceLastBroadcast() {
        messagesSinceLastBroadcast = 0;
    }

    public int getMessagesSinceLastBroadcast() {
        return messagesSinceLastBroadcast;
    }

    public int getMinimumChatMessagesToBroadcast() {
        return plugin.getConfig().getInt("minimumChatMessagesForBroadcast");
    }
}
