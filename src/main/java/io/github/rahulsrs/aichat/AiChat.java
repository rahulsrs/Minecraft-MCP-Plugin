package io.github.rahulsrs.aichat;

import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.java.JavaPlugin;


public final class AiChat extends JavaPlugin {

    private static AiChat instance;

    @Override
    public void onEnable() {
        // Plugin startup logic
        getLogger().info("AI Chat Plugin has been enabled.");
        getServer().getPluginManager().registerEvents(new ChatListener(), this);
    }

    @Override
    public void onDisable() {
        getLogger().info("Disabled Plugin");
    }

    public class ChatListener implements Listener {
//        ConsoleCommandSender console = Bukkit.getConsoleSender();
        @EventHandler
        public void onPlayerChat(AsyncPlayerChatEvent e){
            String msg = e.getMessage();
            getLogger().info("skibidi");
            getLogger().info(msg);
        }
    }

    public static AiChat getInstance() {
        return instance;
    }

}


