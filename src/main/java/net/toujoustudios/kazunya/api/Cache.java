package net.toujoustudios.kazunya.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.experimental.Accessors;
import net.toujoustudios.kazunya.log.LogLevel;
import net.toujoustudios.kazunya.log.Logger;
import net.toujoustudios.kazunya.model.RoleplayImage;
import net.toujoustudios.kazunya.model.RoleplayInteraction;
import net.toujoustudios.kazunya.repository.RoleplayImageRepository;
import net.toujoustudios.kazunya.repository.RoleplayInteractionRepository;

import java.net.http.HttpResponse;
import java.util.Arrays;
import java.util.List;

@Data
@Accessors(fluent = true)
public class Cache {

    private final ApiClient apiClient;
    private final ObjectMapper mapper;

    private RoleplayImageRepository roleplayImageRepository;
    private RoleplayInteractionRepository roleplayInteractionRepository;

    public Cache(ApiClient apiClient) {
        this.apiClient = apiClient;
        this.mapper = new ObjectMapper();
    }

    public void register(RoleplayImageRepository repository) {
        roleplayImageRepository = repository;
        Logger.log(LogLevel.INFORMATION, "Loading roleplay-images into cache.");
        loadRoleplayImages(repository);
    }

    public void register(RoleplayInteractionRepository repository) {
        roleplayInteractionRepository = repository;
        Logger.log(LogLevel.INFORMATION, "Loading roleplay-interactions into cache.");
        loadRoleplayInteractions(repository);
    }

    private void loadRoleplayImages(RoleplayImageRepository repository) {
        try {
            HttpResponse<String> response = apiClient.get("/api/v1/roleplay-images");
            if (response == null || response.statusCode() != 200) {
                Logger.log(LogLevel.ERROR, "Failed to load roleplay-images. Status code: " +
                        (response != null ? response.statusCode() : "null"));
                return;
            }
            RoleplayImage[] result = mapper.readValue(response.body(), RoleplayImage[].class);
            List<RoleplayImage> imageList = Arrays.asList(result);
            repository.saveAll(imageList);
            Logger.log(LogLevel.INFORMATION, "Loaded " + imageList.size() + " roleplay-images into cache.");
        } catch (Exception e) {
            Logger.log(LogLevel.ERROR, "Failed to parse roleplay-images from API response.");
            Logger.log(LogLevel.ERROR, "Error: " + e.getMessage());
        }
    }

    private void loadRoleplayInteractions(RoleplayInteractionRepository repository) {
        try {
            HttpResponse<String> response = apiClient.get("/api/v1/roleplay-images");
            if (response == null || response.statusCode() != 200) {
                Logger.log(LogLevel.ERROR, "Failed to load roleplay images. Status code: " +
                        (response != null ? response.statusCode() : "null"));
                return;
            }
            RoleplayInteraction[] result = mapper.readValue(response.body(), RoleplayInteraction[].class);
            List<RoleplayInteraction> imageList = Arrays.asList(result);
            repository.saveAll(imageList);
            Logger.log(LogLevel.INFORMATION, "Loaded " + imageList.size() + " roleplay-interactions into cache.");
        } catch (Exception e) {
            Logger.log(LogLevel.ERROR, "Failed to parse roleplay-interactions from API response.");
            Logger.log(LogLevel.ERROR, "Error: " + e.getMessage());
        }
    }

}