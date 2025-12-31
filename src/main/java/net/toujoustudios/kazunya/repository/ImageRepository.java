package net.toujoustudios.kazunya.repository;

import lombok.Data;
import lombok.experimental.Accessors;
import net.toujoustudios.kazunya.model.Image;

import java.util.*;

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

    public List<Image> findByTypeAndGenders(String type, List<Character> genders) {
        List<Image> result = new ArrayList<>();
        for (Image image : images) {
            if (image.type().equalsIgnoreCase(type) &&
                    image.genders() != null &&
                    matchesGenders(image.genders(), genders))
                result.add(image);
        }
        return result;
    }

    public String randomByTypeAndGenders(String type, String genders) {
        List<Character> characters = genders.chars()
                .mapToObj(c -> (char) c)
                .toList();
        return randomByTypeAndGenders(type, characters);
    }

    public String randomByTypeAndGenders(String type, List<Character> genders) {
        List<Image> result = findByTypeAndGenders(type, genders);
        if (result.isEmpty())
            return null;
        return result.get(new Random().nextInt(result.size())).url();
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

    private boolean matchesGenders(List<Character> imageGenders, List<Character> searchGenders) {

        if (imageGenders == null || searchGenders == null)
            return false;
        if (imageGenders.size() != searchGenders.size())
            return false;

        boolean forwardMatch = true;
        for (int i = 0; i < imageGenders.size(); i++) {
            char imageGender = Character.toUpperCase(imageGenders.get(i));
            char searchGender = Character.toUpperCase(searchGenders.get(i));
            if (searchGender != 'X' && imageGender != 'X' && imageGender != searchGender) {
                forwardMatch = false;
                break;
            }
        }

        if (forwardMatch)
            return true;

        boolean reverseMatch = true;
        for (int i = 0; i < imageGenders.size(); i++) {
            char imageGender = Character.toUpperCase(imageGenders.get(i));
            char searchGender = Character.toUpperCase(searchGenders.get(searchGenders.size() - 1 - i));
            if (searchGender != 'X' && imageGender != 'X' && imageGender != searchGender) {
                reverseMatch = false;
                break;
            }
        }

        return reverseMatch;

    }

}
