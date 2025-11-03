package io.github.rahulsrs.aichat;


import org.bukkit.event.EventHandler;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import org.bukkit.event.Listener;

public class ChatListener implements Listener {

    private final HttpClient httpClient = HttpClient.newHttpClient();

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent e) {
        String msg = e.getMessage();
        String playerName = e.getPlayer().getName();

        AiChat.getInstance().getLogger().info("Received chat: " + msg);

        // Send to MCP server asynchronously
        sendToMcpServerAsync(playerName, msg)
                .thenAccept(response -> {
                    // Send response back to player (on main thread)
                    AiChat.getInstance().getServer().getScheduler().runTask(
                            AiChat.getInstance(),
                            () -> e.getPlayer().sendMessage("AI says: " + response)
                    );
                })
                .exceptionally(ex -> {
                    AiChat.getInstance().getLogger().warning("Failed to reach MCP server: " + ex.getMessage());
                    return null;
                });
    }

    private java.util.concurrent.CompletableFuture<String> sendToMcpServerAsync(String playerName, String message) {
        try {
            String json = String.format("{\"player\":\"%s\", \"message\":\"%s\"}", playerName, message);

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://dummyjson.com/test")) // Your MCP endpoint
                    .header("Content-Type", "application/json")
                    .GET()
                    .build();

            return httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                    .thenApply(HttpResponse::body);
        } catch (Exception e) {
            AiChat.getInstance().getLogger().warning("Error preparing request: " + e.getMessage());
            return java.util.concurrent.CompletableFuture.completedFuture("Error sending message.");
        }
    }
}