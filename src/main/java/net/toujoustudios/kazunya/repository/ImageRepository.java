package net.toujoustudios.kazunya.repository;

import lombok.Data;
import lombok.experimental.Accessors;
import net.toujoustudios.kazunya.model.Image;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

@Data
@Accessors(fluent = true)
public class ImageRepository {

    private static HashMap<String, ImageRepository> instances = new HashMap<>();

    private final String key;
    private final List<Image> images;

    public static void add(ImageRepository repository) {
        instances.put(repository.key, repository);
    }

    public static ImageRepository get(String key) {
        return instances.get(key);
    }

    public List<Image> findAll() {
        return images;
    }

    public List<Image> findByType(String type) {
        List<Image> result = new ArrayList<>();
        for (Image image : images) {
            if (image.type().equalsIgnoreCase(type))
                result.add(image);
        }
        return result;
    }

    public String random() {
        List<Image> result = findAll();
        if (result.isEmpty())
            return null;
        return result.get(new Random().nextInt(result.size())).url();
    }

    public String randomByType(String type) {
        List<Image> result = findByType(type);
        if (result.isEmpty())
            return null;
        return result.get(new Random().nextInt(result.size())).url();
    }

}
