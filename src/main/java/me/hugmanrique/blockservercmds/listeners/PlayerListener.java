package me.hugmanrique.blockservercmds.listeners;

import me.hugmanrique.blockservercmds.Main;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.ChatEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.event.EventHandler;
import net.md_5.bungee.event.EventPriority;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Hugmanrique
 * @since 30/11/2016
 */
public class PlayerListener implements Listener {
    private Main main;

    private Set<ServerInfo> servers;

    private List<String> commands;
    private BaseComponent[] message;
    private boolean chat;

    public PlayerListener(Main main) {
        this.main = main;

        Configuration config = main.getConfig();

        commands = config.getStringList("whitelist");
        chat = config.getBoolean("block-chat");

        String msgString = config.getString("msg");
        message = TextComponent.fromLegacyText(ChatColor.translateAlternateColorCodes('&', msgString));

        servers = config.getStringList("servers").stream().map(str -> main.getProxy().getServerInfo(str)).collect(Collectors.toSet());

        register();
    }

    private void register() {
        main.getProxy().getPluginManager().registerListener(main, this);
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onMessage(ChatEvent e) {
        if (!(e.getSender() instanceof ProxiedPlayer) || e.isCancelled()) {
            return;
        }

        ProxiedPlayer player = (ProxiedPlayer) e.getSender();

        if (!servers.contains(player.getServer().getInfo())) {
            return;
        }

        String message = e.getMessage().toLowerCase();

        boolean success = true;

        if (e.isCommand()) {
            String[] cmd = message.substring(1).split(" ", 2);

            if (!commands.contains(cmd[0])) {
                success = false;
            }
        } else if (chat) {
            success = false;
        }

        if (success) {
            return;
        }

        e.setCancelled(true);
        player.sendMessage();
    }
}
