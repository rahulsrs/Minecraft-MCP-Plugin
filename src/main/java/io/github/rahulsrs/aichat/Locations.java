package io.github.rahulsrs.aichat;

import org.bukkit.Bukkit;

import java.io.File;

public class Locations {
    String serverDirectory = "";
    public Locations(){
       File server = Bukkit.getWorldContainer();
       serverDirectory = server.getAbsolutePath();
       AiChat instance = AiChat.getInstance();
       instance.getLogger().info("Got Server Location, It's at " + serverDirectory);
    }


}
