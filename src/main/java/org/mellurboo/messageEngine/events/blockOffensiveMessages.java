package org.mellurboo.messageEngine.events;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.mellurboo.messageEngine.MessageEngine;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class blockOffensiveMessages implements Listener {
    public final MessageEngine plugin;
    public blockOffensiveMessages(MessageEngine plugin) { this.plugin = plugin; }

    private List<Pattern> blacklistedWords = new ArrayList<>();

    @EventHandler
    public void evaluatePlayerChat(AsyncPlayerChatEvent event) {
        Player chatter = event.getPlayer();
        String message = event.getMessage().toLowerCase();
        if (blacklistedWords.isEmpty()) return;

        for (Pattern pattern : blacklistedWords) {
            Matcher matcher = pattern.matcher(message);

            if (matcher.find()) {
                event.setCancelled(true);
                chatter.sendMessage(plugin.getConfig().getString("PlayerFlaggedMessage"));
                plugin.flaggedMessages.addBlockedMessage(chatter, message);
                plugin.offenderMessageHold.addToOffenderList(chatter);
                return;
            }
        }
    }

    public void loadBlacklistedWords(){
        blacklistedWords.clear();

        for (String regex : plugin.getConfig().getStringList("blacklisted")) {
            try {
                blacklistedWords.add(Pattern.compile(regex, Pattern.CASE_INSENSITIVE));
            } catch (Exception e) {
                plugin.getLogger().warning("Invalid regex in blacklist: " + regex);
            }
        }

        if (blacklistedWords.isEmpty()) {
            blacklistedWords = List.of();
        }
    }
}
