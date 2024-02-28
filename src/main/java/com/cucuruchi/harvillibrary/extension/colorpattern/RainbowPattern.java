package com.cucuruchi.harvillibrary.extension.colorpattern;

import com.cucuruchi.harvillibrary.extension.ColorExtension;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RainbowPattern implements Patterns {

    Pattern pattern = Pattern.compile("<RAINBOW([0-9]{1,3})>(.*?)</RAINBOW>");
    @Override
    public String process(String string) {
        Matcher matcher = pattern.matcher(string);
        while (matcher.find()){
            String saturation = matcher.group(1);
            String content = matcher.group(2);
            string = string.replace(matcher.group(), ColorExtension.rainbow(content, Float.parseFloat(saturation)));
        }
        return string;
    }
}
