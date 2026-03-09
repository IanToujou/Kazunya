package net.toujoustudios.kazunya.repository;

import net.toujoustudios.kazunya.model.RoleplayImage;
import net.toujoustudios.kazunya.model.RoleplayInteraction;
import net.toujoustudios.kazunya.model.RoleplayMessage;
import net.toujoustudios.kazunya.type.InteractionGender;
import net.toujoustudios.kazunya.type.InteractionType;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class RoleplayInteractionRepository implements IMemoryRepository<RoleplayInteraction, Integer> {

    private final Map<Integer, RoleplayInteraction> cache = new ConcurrentHashMap<>();

    @Override
    public Optional<RoleplayInteraction> findById(Integer id) {
        return Optional.ofNullable(cache.get(id));
    }

    @Override
    public List<RoleplayInteraction> findAll() {
        return new ArrayList<>(cache.values());
    }

    @Override
    public RoleplayInteraction save(RoleplayInteraction entity) {
        cache.put(entity.getId(), entity);
        return entity;
    }

    @Override
    public void saveAll(List<RoleplayInteraction> entities) {
        entities.forEach(this::save);
    }

    @Override
    public void deleteById(Integer id) {
        cache.remove(id);
    }

    @Override
    public void clear() {
        cache.clear();
    }

    @Override
    public int count() {
        return cache.size();
    }

    public Optional<RoleplayInteraction> findByName(String name) {
        return cache.values().stream()
                .filter(interaction -> interaction.getName().equalsIgnoreCase(name))
                .findFirst();
    }

    public Optional<String> findRandomImageByTypeAndGender(String name, InteractionType type, InteractionGender gender) {
        return findByName(name)
                .map(interaction -> {
                    if (interaction.getImages() == null || interaction.getImages().isEmpty())
                        return null;
                    List<RoleplayImage> matchingImages = interaction.getImages().stream()
                            .filter(img -> img.getType() == type)
                            .filter(img -> img.getGenders() != null && img.getGenders().contains(gender))
                            .toList();
                    if (matchingImages.isEmpty()) return null;
                    return matchingImages.get(new Random().nextInt(matchingImages.size())).getUrl();
                });
    }

    public Optional<String> findRandomImageByType(String name, InteractionType type) {
        return findByName(name)
                .map(interaction -> {
                    if (interaction.getImages() == null || interaction.getImages().isEmpty())
                        return null;
                    List<RoleplayImage> matchingImages = interaction.getImages().stream()
                            .filter(img -> img.getType() == type)
                            .toList();
                    if (matchingImages.isEmpty()) return null;
                    return matchingImages.get(new Random().nextInt(matchingImages.size())).getUrl();
                });
    }

    public Optional<String> findRandomMessageByType(String name, InteractionType type) {
        return findByName(name)
                .map(interaction -> {
                    if (interaction.getMessages() == null || interaction.getMessages().isEmpty())
                        return null;
                    List<RoleplayMessage> matchingMessages = interaction.getMessages().stream()
                            .filter(msg -> msg.getType() == type)
                            .toList();
                    if (matchingMessages.isEmpty()) return null;
                    return matchingMessages.get(new Random().nextInt(matchingMessages.size())).getText();
                });
    }

}
