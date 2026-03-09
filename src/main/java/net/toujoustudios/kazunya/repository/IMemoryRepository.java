package net.toujoustudios.kazunya.repository;

import java.util.List;
import java.util.Optional;

/**
 * Generic interface for in-memory repositories.
 * Provides basic CRUD operations without database persistence.
 *
 * @param <T> The entity type
 * @param <ID> The ID type
 * @since 1.3.0
 * @author Toujou Studios
 */
public interface IMemoryRepository<T, ID> {

    /**
     * Finds an entity by its ID.
     *
     * @param id The ID to search for
     * @return An Optional containing the entity if found
     */
    Optional<T> findById(ID id);

    /**
     * Retrieves all entities.
     *
     * @return A list of all entities
     */
    List<T> findAll();

    /**
     * Saves an entity to the repository.
     *
     * @param entity The entity to save
     * @return The saved entity
     */
    T save(T entity);

    /**
     * Saves multiple entities to the repository.
     *
     * @param entities The entities to save
     */
    void saveAll(List<T> entities);

    /**
     * Deletes an entity by its ID.
     *
     * @param id The ID of the entity to delete
     */
    void deleteById(ID id);

    /**
     * Clears all entities from the repository.
     */
    void clear();

    /**
     * Returns the number of entities in the repository.
     *
     * @return The count of entities
     */
    int count();

}
