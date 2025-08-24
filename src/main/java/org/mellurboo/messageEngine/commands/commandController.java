package org.mellurboo.messageEngine.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;
import org.mellurboo.messageEngine.MessageEngine;

public class commandController implements CommandExecutor {
    public final MessageEngine plugin;
    public commandController(MessageEngine plugin) { this.plugin = plugin; }
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String @NotNull [] strings) {
        final String commandSent = strings[0];
        switch (commandSent){
            case "reload":
                if (commandSender.hasPermission("messageEngine.reload")) { plugin.reloadConfig(); }
                commandSender.sendMessage(ChatColor.GREEN + "Reloaded config, you may need to restart the interval messages");
                break;
            case "StopIntervalMessages":
                if (commandSender.hasPermission("messageEngine.scheduler.stopIntervalMessages")) {plugin.intervalBasedMessages.stopIntervalBasedMessages();}
                break;
            case "StartIntervalMessages":
                if (commandSender.hasPermission("messageEngine.scheduler.startIntervalMessages")) {plugin.intervalBasedMessages.mapIntervalBasedMessages();}
                break;
            case "RestartIntervalMessages":
                if (commandSender.hasPermission("messageEngine.scheduler.restartIntervalMessages")) {plugin.intervalBasedMessages.restartIntervalBasedMessages();}
                break;
            default:
                commandSender.sendMessage(ChatColor.RED + "[MessageEngine] Unknown command: " + commandSent);
                break;
        }
        return true;
    }
}
