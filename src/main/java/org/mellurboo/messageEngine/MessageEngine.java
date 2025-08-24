package org.mellurboo.messageEngine;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.mellurboo.messageEngine.commands.commandController;
import org.mellurboo.messageEngine.commands.completer.commandCompleter;
import org.mellurboo.messageEngine.ipml.intervalBasedMessages;

public final class MessageEngine extends JavaPlugin {
    public intervalBasedMessages intervalBasedMessages = new intervalBasedMessages(this);
    public FileConfiguration config;

    @Override
    public void onEnable() {
        loadFileConfiguration();
        intervalBasedMessages.mapIntervalBasedMessages(null);

        getCommand("me").setExecutor(new commandController(this));
        getCommand("me").setTabCompleter(new commandCompleter());

        Bukkit.getLogger().info("[MessageEngine] Plugin Enabled]");
    }

    @Override
    public void onDisable() {
        Bukkit.getScheduler().cancelTasks(this); // cancel the intervalBasedMessages
        intervalBasedMessages.scheduledIntervalTasks.clear();
        Bukkit.getLogger().info("[MessageEngine] Plugin Disabled]");
    }

    public void loadFileConfiguration() {
        saveDefaultConfig();
        config = getConfig();
    }
}
