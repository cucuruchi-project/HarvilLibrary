package com.cucuruchi.harvillibrary.extension;

import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
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

    public static TextComponent HoverClickableMessage(Player player, String message, String hoverText, String clickAction, String clickActionData) {
        TextComponent textComponent = new TextComponent(message);
        textComponent.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new BaseComponent[] {
                new TextComponent(hoverText)
        }));
        textComponent.setClickEvent(new ClickEvent(ClickEvent.Action.valueOf(clickAction.toUpperCase()), clickActionData));
        return textComponent;
    }
}
