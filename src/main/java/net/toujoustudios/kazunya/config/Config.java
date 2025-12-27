package net.toujoustudios.kazunya.config;

import net.toujoustudios.kazunya.model.Image;
import net.toujoustudios.kazunya.log.LogLevel;
import net.toujoustudios.kazunya.log.Logger;
import net.toujoustudios.kazunya.model.Message;
import org.yaml.snakeyaml.Yaml;

import java.io.*;
import java.util.*;

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

        Object value = content.get(key);
        if (value == null) return null;

        if (value instanceof List) {
            @SuppressWarnings("unchecked")
            List<String> list = (List<String>) value;
            return list;
        }

        if (value instanceof String) {
            String[] stringArray = ((String) value).split(", ");
            return stringArray.length > 0 ? Arrays.asList(stringArray) : null;
        }

        return null;
    }

    public List<Image> getImageList(String key) {

        Object value = content.get(key);
        if (value == null) return null;
        if (!(value instanceof List<?> rawList)) return null;

        List<Image> result = new ArrayList<>();
        for (Object item : rawList) {
            if (item instanceof Map) {
                @SuppressWarnings("unchecked")
                Map<String, Object> map = (Map<String, Object>) item;
                String type = (String) map.get("type");
                String url = (String) map.get("url");
                if (type != null && url != null) {
                    result.add(new Image(type, url));
                }
            }
        }

        return result;

    }

    public List<Message> getMessageList(String key) {

        Object value = content.get(key);
        if (value == null) return null;
        if (!(value instanceof List<?> rawList)) return null;

        List<Message> result = new ArrayList<>();
        for (Object item : rawList) {
            if (item instanceof Map) {
                @SuppressWarnings("unchecked")
                Map<String, Object> map = (Map<String, Object>) item;
                String type = (String) map.get("type");
                String url = (String) map.get("message");
                if (type != null && url != null) {
                    result.add(new Message(type, url));
                }
            }
        }

        return result;

    }

}
