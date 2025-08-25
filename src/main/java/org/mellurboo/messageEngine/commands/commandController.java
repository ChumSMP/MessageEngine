package org.mellurboo.messageEngine.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.mellurboo.messageEngine.MessageEngine;

public class commandController implements CommandExecutor {
    public final MessageEngine plugin;
    public commandController(MessageEngine plugin) { this.plugin = plugin; }
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String @NotNull [] strings) {
        if (strings.length < 1) { commandSender.sendMessage(ChatColor.RED + "[MessageEngine] Command sent with bad no arguments."); return true; }

        final String commandSent = strings[0];
        switch (commandSent){
            case "reload":
                if (commandSender.hasPermission("messageEngine.reload")) { plugin.reloadConfig(); }
                commandSender.sendMessage(ChatColor.GREEN + "Reloaded config, you may need to restart the interval messages");
                break;
            case "StopIntervalMessages":
                if (commandSender.hasPermission("messageEngine.scheduler.stopIntervalMessages")) {plugin.intervalBasedMessages.stopIntervalBasedMessages((Player) commandSender);}
                break;
            case "StartIntervalMessages":
                if (commandSender.hasPermission("messageEngine.scheduler.startIntervalMessages")) {plugin.intervalBasedMessages.mapIntervalBasedMessages((Player) commandSender);}
                break;
            case "RestartIntervalMessages":
                if (commandSender.hasPermission("messageEngine.scheduler.restartIntervalMessages")) {plugin.intervalBasedMessages.restartIntervalBasedMessages((Player) commandSender);}
                break;
            case "SeeIntervalMessages":
                if (commandSender.hasPermission("seeIntervalMessages")) {plugin.intervalBasedMessages.seeIntervalBasedMessages((Player) commandSender);}
                break;
            case "review":
                if (commandSender.hasPermission("messageEngine.moderation.reviewer")) { plugin.flaggedMessages.showCurrentCase((Player) commandSender); }
                break;
            case "done":
                if (commandSender.hasPermission("messageEngine.moderation.reviewer")) { plugin.flaggedMessages.removeLatestCase((Player) commandSender); }
                break;
            default:
                commandSender.sendMessage(ChatColor.RED + "[MessageEngine] Unknown command: " + commandSent);
                break;
        }
        return true;
    }
}
