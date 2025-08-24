package org.mellurboo.messageEngine.commands.completer;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class commandCompleter implements TabCompleter {
    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String @NotNull [] strings) {
        List<String> completions = new ArrayList<>();
        completions.add("StopIntervalMessages");
        completions.add("StartIntervalMessages");
        completions.add("RestartIntervalMessages");
        completions.add("SeeIntervalMessages");

        if (commandSender.hasPermission("messageEngine.reload") || commandSender.isOp()) {
            completions.add("reload");
        }

        return completions;
    }
}
