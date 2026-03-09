package net.toujoustudios.kazunya.repository;

import net.toujoustudios.kazunya.model.RoleplayImage;
import net.toujoustudios.kazunya.type.InteractionGender;
import net.toujoustudios.kazunya.type.InteractionType;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class RoleplayImageRepository implements IMemoryRepository<RoleplayImage, Integer> {

    private final Map<Integer, RoleplayImage> cache = new ConcurrentHashMap<>();

    @Override
    public Optional<RoleplayImage> findById(Integer id) {
        return Optional.ofNullable(cache.get(id));
    }

    @Override
    public List<RoleplayImage> findAll() {
        return new ArrayList<>(cache.values());
    }

    @Override
    public RoleplayImage save(RoleplayImage entity) {
        cache.put(entity.getId(), entity);
        return entity;
    }

    @Override
    public void saveAll(List<RoleplayImage> entities) {
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

    public List<RoleplayImage> findByType(InteractionType type) {
        return cache.values().stream()
                .filter(img -> img.getType() == type)
                .collect(Collectors.toList());
    }

    public List<RoleplayImage> findByGender(InteractionGender gender) {
        return cache.values().stream()
                .filter(img -> img.getGenders() != null && img.getGenders().contains(gender))
                .collect(Collectors.toList());
    }

    public List<RoleplayImage> findByTypeAndGender(InteractionType type, InteractionGender gender) {
        return cache.values().stream()
                .filter(img -> img.getType() == type)
                .filter(img -> img.getGenders() != null && img.getGenders().contains(gender))
                .collect(Collectors.toList());
    }

    public Optional<RoleplayImage> findRandomByType(InteractionType type) {
        List<RoleplayImage> images = findByType(type);
        if (images.isEmpty()) return Optional.empty();
        return Optional.of(images.get(new Random().nextInt(images.size())));
    }

    public Optional<RoleplayImage> findRandomByTypeAndGender(InteractionType type, InteractionGender gender) {
        List<RoleplayImage> images = findByTypeAndGender(type, gender);
        if (images.isEmpty()) return Optional.empty();
        return Optional.of(images.get(new Random().nextInt(images.size())));
    }

}