package net.toujoustudios.kazunya.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.experimental.Accessors;
import net.toujoustudios.kazunya.loader.Loader;
import net.toujoustudios.kazunya.log.LogLevel;
import net.toujoustudios.kazunya.log.Logger;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

/**
 * The API client manages authentication for the Discord bot and makes
 * API requests to the backend.
 * <p>
 * Making any kind of API request is only allowed after requesting a JWT
 * token, using the proper authentication method.
 *
 * @since 1.3.0
 * @author Toujou Studios
 */
@Data
@Accessors(fluent = true)
public class ApiClient {

    private final String url;
    private final String username;
    private final String password;

    private HttpClient client = HttpClient.newHttpClient();
    private ObjectMapper mapper = new ObjectMapper();
    private String token;

    /**
     * Sets the JWT token for the Discord bot. This method makes a request
     * to the authentication endpoint of the backend and uses the provided
     * username and password to login.
     *
     * @return Whether authentication was successful or not.
     */
    public boolean authenticate() {

        String body = String.format(
                "{\"username\":\"%s\",\"password\":\"%s\"}",
                username,
                password
        );

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url + "/auth/authenticate"))
                .POST(HttpRequest.BodyPublishers.ofString(body))
                .header("Content-Type", "application/json")
                .build();

        try {

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200) {
                token = mapper.readTree(response.body()).get("token").asText();
                Logger.log(LogLevel.INFORMATION, "Established API connection and authenticated bot user.");
                return true;
            }

            return false;

        } catch (Exception exception) {
            Logger.log(LogLevel.FATAL, "Failed to fetch initial API token.");
            Loader.cancel();
            return false;
        }

    }

    /**
     * Sends an arbitrary request to the backend with proper authorization headers.
     * <p>
     * This method is marked as private since the request should be done using the separate
     * get, post, delete, and put methods.
     *
     * @param method The HTTP method to use.
     * @param endpoint The endpoint after the defined API url. Must start with a slash.
     * @param body The body to send. This may be null.
     * @return The body of the response from the backend.
     */
    private HttpResponse<String> sendRequest(HttpMethod method, @NotNull String endpoint, @Nullable String body) {

        if (token == null) {
            Logger.log(LogLevel.ERROR, "Failed to send request to API. Please set a token first.");
            return null;
        }

        HttpRequest.Builder builder = HttpRequest.newBuilder()
                .uri(URI.create(url + endpoint))
                .header("Authorization", "Bearer " + token)
                .header("Content-Type", "application/json");

        if (method == HttpMethod.GET)
            builder.GET();
        else if (method == HttpMethod.POST)
            builder.POST(HttpRequest.BodyPublishers.ofString(body != null ? body : ""));
        else if (method == HttpMethod.PUT)
            builder.PUT(HttpRequest.BodyPublishers.ofString(body != null ? body : ""));
        else if (method == HttpMethod.DELETE)
            builder.DELETE();

        HttpRequest request = builder.build();

        try {
            return client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (Exception exception) {
            Logger.log(LogLevel.ERROR, "Failed to send request to API.");
            Logger.log(LogLevel.ERROR, "Error: " + exception.getMessage());
            return null;
        }

    }

    /**
     * Sends a GET request to a given endpoint.
     *
     * @param endpoint The endpoint after the defined API url. Must start with a slash.
     * @return The response as HTTP response object.
     */
    public HttpResponse<String> get(@NotNull String endpoint) {
        return sendRequest(HttpMethod.GET, endpoint, null);
    }

    /**
     * Sends a POST request to a given endpoint.
     *
     * @param endpoint The endpoint after the defined API url. Must start with a slash.
     * @param body The body to send. Can be null.
     * @return The response as HTTP response object.
     */
    public HttpResponse<String> post(@NotNull String endpoint, @Nullable String body) {
        return sendRequest(HttpMethod.POST, endpoint, body);
    }

    /**
     * Sends a PUT request to a given endpoint.
     *
     * @param endpoint The endpoint after the defined API url. Must start with a slash.
     * @param body The body to send. Can be null.
     * @return The response as HTTP response object.
     */
    public HttpResponse<String> put(@NotNull String endpoint, @Nullable String body) {
        return sendRequest(HttpMethod.PUT, endpoint, body);
    }

    /**
     * Sends a DELETE request to a given endpoint.
     *
     * @param endpoint The endpoint after the defined API url. Must start with a slash.
     * @return The response as HTTP response object.
     */
    public HttpResponse<String> delete(@NotNull String endpoint) {
        return sendRequest(HttpMethod.DELETE, endpoint, null);
    }

    /**
     * Fetches a random roleplay image URL from the backend.
     *
     * @param name The name of the roleplay interaction (e.g., "cuddle").
     * @param type The type of interaction (FRIENDLY or ROMANTIC).
     * @param gender The gender filter (ANY, MALE, FEMALE).
     * @return The URL of a random image matching the criteria, or null if not found.
     */
    public String getRandomRoleplayImage(@NotNull String name, @NotNull String type, @NotNull String gender) {
        try {
            String endpoint = String.format("/roleplay-interactions?name=%s&type=%s&gender=%s", name, type, gender);
            Logger.log(LogLevel.INFORMATION, "Fetching roleplay image from API: " + endpoint);
            HttpResponse<String> response = get(endpoint);

            if (response == null || response.statusCode() != 200) {
                Logger.log(LogLevel.ERROR, "Failed to fetch roleplay image from API.");
                return null;
            }

            var root = mapper.readTree(response.body());
            if (!root.isArray() || root.isEmpty()) {
                Logger.log(LogLevel.ERROR, "API response is not an array or is empty.");
                return null;
            }

            var interaction = root.get(0);
            var images = interaction.get("images");

            if (images == null || !images.isArray() || images.isEmpty()) {
                Logger.log(LogLevel.ERROR, "No images found in the interaction.");
                return null;
            }

            // Select a random image from the array
            int randomIndex = (int) (Math.random() * images.size());
            return images.get(randomIndex).get("url").asText();
        } catch (Exception exception) {
            Logger.log(LogLevel.ERROR, "Error parsing roleplay image response: " + exception.getMessage());
            return null;
        }
    }

    /**
     * Fetches a random roleplay message from the backend.
     *
     * @param name The name of the roleplay interaction (e.g., "cuddle").
     * @param type The type of interaction (FRIENDLY or ROMANTIC).
     * @return The message text, or null if not found.
     */
    public String getRandomRoleplayMessage(@NotNull String name, @NotNull String type) {
        try {
            String endpoint = String.format("/roleplay-interactions?name=%s&type=%s", name, type);
            HttpResponse<String> response = get(endpoint);

            if (response == null || response.statusCode() != 200) {
                Logger.log(LogLevel.ERROR, "Failed to fetch roleplay message from API.");
                return null;
            }

            return mapper.readTree(response.body()).get("message").asText();
        } catch (Exception exception) {
            Logger.log(LogLevel.ERROR, "Error parsing roleplay message response: " + exception.getMessage());
            return null;
        }
    }

}
