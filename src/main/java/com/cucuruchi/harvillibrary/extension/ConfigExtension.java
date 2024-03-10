package com.cucuruchi.harvillibrary.extension;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class ConfigExtension {

    private File file;
    private static FileConfiguration customFile;
    private final Plugin plugin;

    public ConfigExtension(Plugin plugin, String path, String filename) {
        this.plugin = plugin;
        setup(path, filename);
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
        file = new File(path, filename + ".yml");
        if (!file.exists()) {
            Bukkit.getLogger().warning("Could not delete config file: " + filename);
            return;
        }
        file.delete();
    }

    public String getString(String key) {
        return customFile.getString(key);
    }

    public int getInt(String key) {
        return customFile.getInt(key);
    }

    public double getDouble(String key) {
        return customFile.getDouble(key);
    }

    public boolean getBoolean(String key) {
        return customFile.getBoolean(key);
    }

    public List<?> getList(String key) {
        return customFile.getList(key);
    }

    public List<String> getStringList(String key) {
        return customFile.getStringList(key);
    }

    public List<Integer> getIntList(String key) {
        return customFile.getIntegerList(key);
    }

    public List<Double> getDoubleList(String key) {
        return customFile.getDoubleList(key);
    }

    public List<Boolean> getBooleanList(String key) {
        return customFile.getBooleanList(key);
    }

    public FileConfiguration get(String key){
        return customFile;
    }
}
