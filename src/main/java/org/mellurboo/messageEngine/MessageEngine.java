package org.mellurboo.messageEngine;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.mellurboo.messageEngine.ipml.intervalBasedMessages;

public final class MessageEngine extends JavaPlugin {
    public intervalBasedMessages intervalBasedMessages = new intervalBasedMessages(this);

    @Override
    public void onEnable() {
        saveDefaultConfig();
        intervalBasedMessages.mapIntervalBasedMessages();

        Bukkit.getLogger().info("[MessageEngine] Plugin Enabled]");
    }

    @Override
    public void onDisable() {
        Bukkit.getScheduler().cancelTasks(this); // cancel the intervalBasedMessages
        Bukkit.getLogger().info("[MessageEngine] Plugin Disabled]");
    }
}
