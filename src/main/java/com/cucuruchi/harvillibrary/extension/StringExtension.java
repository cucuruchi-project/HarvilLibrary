package com.cucuruchi.harvillibrary.extension;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.regex.Pattern;

public class StringExtension {

    public static String transChatColor(String text){
        return ChatColor.translateAlternateColorCodes('&', text);
    }

    private static final Pattern STRIP_COLOR_PATTERN = Pattern.compile("(?i)" + String.valueOf('&') + "[0-9A-FK-OR]");
    public static String stripcolor(String input) {
        return input == null?null:STRIP_COLOR_PATTERN.matcher(input).replaceAll("");
    }
}
