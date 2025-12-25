package net.toujoustudios.kazunya.config;

import net.toujoustudios.kazunya.log.LogLevel;
import net.toujoustudios.kazunya.log.Logger;
import org.yaml.snakeyaml.Yaml;

import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;


public class Config {

    private static final HashMap<String, Config> files = new HashMap<>();
    private HashMap<String, Object> content;

    public Config(String filename) {

        try {
            InputStream inputStream = new FileInputStream(filename);
            Yaml yaml = new Yaml();
            content = yaml.load(inputStream);
        } catch(FileNotFoundException ignored) {}

        files.put(filename, this);
        Logger.log(LogLevel.INFORMATION, "Configuration file " + filename + " loaded.");

    }

    public static Config getFile(String filename) {
        return files.getOrDefault(filename, null);
    }

    public static Config getDefault() {
        return getFile("config.yml");
    }

    public String getString(String key) {
        return (String) content.getOrDefault(key, null);
    }

    @SuppressWarnings("BooleanMethodIsAlwaysInverted")
    public boolean getBoolean(String key) {
        return (boolean) content.getOrDefault(key, false);
    }

    public List<String> getStringList(String key) {
        String[] stringArray = getString(key).split(", ");
        if(stringArray.length >= 1) return Arrays.asList(stringArray);
        else return null;
    }

}
