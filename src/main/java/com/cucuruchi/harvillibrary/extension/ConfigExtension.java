package com.cucuruchi.harvillibrary.extension;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class ConfigExtension {

    private File file;
    private static FileConfiguration customFile;
    private final Plugin plugin;
    private final String filename;

    public ConfigExtension(String path, Plugin plugin, String filename) {
        this.plugin = plugin;
        this.filename = filename;
        this.file = new File(path, this.filename + ".yml");
        if (this.file.exists()) {
            customFile = YamlConfiguration.loadConfiguration(this.file);
        }
    }


    public void setup(String path, String config) {
        File dataFolder = plugin.getDataFolder();

        if (!dataFolder.exists()) {
            dataFolder.mkdirs();
        }

        file = new File(dataFolder, config);
        if (!file.exists()) {
            try (InputStream inputStream = plugin.getResource(config);
                 Reader reader = new InputStreamReader(inputStream, StandardCharsets.UTF_8)) {
                customFile = YamlConfiguration.loadConfiguration(reader);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            customFile = YamlConfiguration.loadConfiguration(file);
        }

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

    public static void reloadAll(String path) throws IOException {
        File folder = new File(path);
        if (!folder.exists() || !folder.isDirectory()) {
            throw new IllegalArgumentException("Invalid folder path: " + path);
        }

        File[] files = folder.listFiles();
        if (files == null) {
            throw new IOException("Failed to list files in folder: " + path);
        }

        for (File file : files) {
            customFile = YamlConfiguration.loadConfiguration(file);
        }
    }

    public void set(String value, Object key){
        customFile.set(value, key);
        save();
    }

    public void remove(String value){
        customFile.set(value, null);
        save();
    }

    public void deleteFile(String path, String filename){
        file = new File(path + "/coupon/", filename + ".yml");
        if (!file.exists()) {
            Bukkit.getLogger().warning("Could not delete config file: " + filename);
            return;
        }
        file.delete();
    }

}
