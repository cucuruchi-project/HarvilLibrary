package com.cucuruchi.harvillibrary.extension;

import org.bukkit.entity.Player;

import java.util.Arrays;

import static com.cucuruchi.harvillibrary.extension.StringExtension.*;

public class MessageExtension {

    public static void sendMessages(Player player, String... messages){
        for (String message : messages) {
            player.sendMessage(transChatColor(message));
        }
    }

    public static void sendMessage(Player player, String message){
        player.sendMessage(transChatColor(message));
    }

    public static void helpMessage(Player player, String prefix, String label, String... subcommands){
        sendMessage(player, prefix);
        Arrays.stream(subcommands).toList().forEach(
            subcommand -> sendMessages(player, "┗   " + label + " " + subcommand));
    }
}
