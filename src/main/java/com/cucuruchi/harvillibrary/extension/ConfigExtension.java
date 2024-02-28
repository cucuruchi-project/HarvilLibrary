package com.cucuruchi.harvillibrary.extension;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.IOException;

public class ConfigExtension {

    private static File file;

    private static FileConfiguration customFile;

    public ConfigExtension(Plugin plugin, String configName) {
        setup(plugin, configName);
    }

    public void setup(Plugin plugin, String configName) {
        file = new File(plugin.getDataFolder(), configName + ".yml");
        if (!file.exists())
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        customFile = YamlConfiguration.loadConfiguration(file);
        customFile.options().copyDefaults(true);
        save();
    }

    public FileConfiguration get() {
        return customFile;
    }

    public void save() {
        try {
            customFile.save(file);
        } catch (IOException e) {
            Bukkit.getLogger().warning("Could not save config file: " + e.getMessage());
        }
    }

    public void reload() {
        customFile = YamlConfiguration.loadConfiguration(file);
    }
}
