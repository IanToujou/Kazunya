package net.toujoustudios.kazunya.repository;

import lombok.Data;
import lombok.experimental.Accessors;
import net.toujoustudios.kazunya.model.Message;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

@Data
@Accessors(fluent = true)
public class MessageRepository {

    private static HashMap<String, MessageRepository> instances = new HashMap<>();

    private final String key;
    private final List<Message> messages;

    public static void add(MessageRepository repository) {
        instances.put(repository.key, repository);
    }

    public static MessageRepository get(String key) {
        return instances.get(key);
    }

    public List<Message> findAll() {
        return messages;
    }

    public List<Message> findByType(String type) {
        List<Message> result = new ArrayList<>();
        for (Message image : messages) {
            if (image.type().equalsIgnoreCase(type))
                result.add(image);
        }
        return result;
    }

    public String random() {
        List<Message> result = findAll();
        if (result.isEmpty())
            return null;
        return result.get(new Random().nextInt(result.size())).message();
    }

    public String randomByType(String type) {
        List<Message> result = findByType(type);
        if (result.isEmpty())
            return null;
        return result.get(new Random().nextInt(result.size())).message();
    }

}
