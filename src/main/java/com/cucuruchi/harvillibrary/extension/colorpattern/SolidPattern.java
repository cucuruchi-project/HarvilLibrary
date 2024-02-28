package com.cucuruchi.harvillibrary.extension.colorpattern;

import com.cucuruchi.harvillibrary.extension.ColorExtension;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SolidPattern implements Patterns{

    Pattern pattern = Pattern.compile("<SOLID:([0-9A-Fa-f]{6})>");

    @Override
    public String process(String string) {
        Matcher matcher = this.pattern.matcher(string);
        while (matcher.find()) {
            String color = matcher.group(1);
            string = string.replace(matcher.group(), ColorExtension.getColor(color) + "");
        }
        return string;
    }
}
