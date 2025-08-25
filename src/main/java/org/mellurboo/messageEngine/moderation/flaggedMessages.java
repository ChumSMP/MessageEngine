package org.mellurboo.messageEngine.moderation;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.mellurboo.messageEngine.MessageEngine;

import java.util.ArrayList;
import java.util.List;

public class flaggedMessages{
    private final MessageEngine plugin;
    private final List<String> flaggedMessages = new ArrayList<>();

    public flaggedMessages(MessageEngine plugin) {
        this.plugin = plugin;
    }

    public void addBlockedMessage(Player player, String message) {
        flaggedMessages.add(player.getName() + ": " + message);
    }

    public void showCurrentCase(Player commandSender) {
        if (!flaggedMessages.isEmpty()) {
            commandSender.sendMessage("\n");
            commandSender.sendMessage("§c ==== Chat Review : run '/me done' when complete ==== §r");
            commandSender.sendMessage("§c > §r" + flaggedMessages.get(0) + "§r");
            commandSender.sendMessage("§7 (1 of " + flaggedMessages.size() + ")");
            commandSender.sendMessage("§c ============================================= §r");
            commandSender.sendMessage("\n");
        }else {
            commandSender.sendMessage("§a No Cases to Review! fantastic! §r");
        }
    }

    public void removeLatestCase(Player commandSender){
        if (flaggedMessages.isEmpty()) {return;}
        flaggedMessages.remove(0);
        commandSender.sendMessage("§c Done! This case has been removed, you should now run any punishment commands manually if needed. §r");
        commandSender.sendMessage("§c Run '/me review' to review the next case. The User Can now Chat!§r");
        plugin.offenderMessageHold.removeFromOffenderList();
    }
}
