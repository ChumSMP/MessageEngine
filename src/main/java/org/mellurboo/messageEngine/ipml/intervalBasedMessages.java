package org.mellurboo.messageEngine.ipml;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import org.mellurboo.messageEngine.MessageEngine;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class intervalBasedMessages {
    public final MessageEngine plugin;
    public intervalBasedMessages(MessageEngine plugin) { this.plugin = plugin; }
    public List<BukkitTask> scheduledIntervalTasks = new ArrayList<>();

    /// maps the interval based messages and starts them as a task timer
    public void mapIntervalBasedMessages(){
        List<Map<?, ?>> messages = plugin.getConfig().getMapList("messages");

        for (Map<?, ?> mdata : messages) {
            String message = (String) mdata.get("text");
            int interval = (Integer) mdata.get("interval");

            // initiate the task
            BukkitTask task = new BukkitRunnable(){
                @Override
                public void run(){
                    for (Player p : Bukkit.getOnlinePlayers()){
                        p.sendMessage(message);
                    }
                }
            }.runTaskTimer(plugin, interval * 20L, interval * 20L); // it's not random it accounts for tickrate

            scheduledIntervalTasks.add(task);

            Bukkit.getLogger().info("Scheduled '" + message + "' to be sent to players every " + interval + " Seconds");
        }
    }

    public void stopIntervalBasedMessages(){
        for (BukkitTask task : scheduledIntervalTasks){
            task.cancel();
        }

        Bukkit.getLogger().info("Stopped " + scheduledIntervalTasks.size() + " tasks");
        scheduledIntervalTasks.clear();
    }

    public void restartIntervalBasedMessages(){
        stopIntervalBasedMessages();
        mapIntervalBasedMessages();
    }
}
