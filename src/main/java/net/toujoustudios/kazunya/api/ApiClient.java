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

@Data
@Accessors(fluent = true)
public class ApiClient {

    private final String url;
    private final String username;
    private final String password;

    private HttpClient client = HttpClient.newHttpClient();
    private ObjectMapper mapper = new ObjectMapper();
    private String token;

    public boolean authenticate() {

        String body = String.format(
                "{\"username\":\"%s\",\"password\":\"%s\"}",
                username,
                password
        );

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url + "/authenticate"))
                .setHeader("Authorization", "Bearer " + token)
                .POST(HttpRequest.BodyPublishers.ofString(body))
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
            Logger.log(LogLevel.FATAL, "Failed to establish API connection.");
            Logger.log(LogLevel.FATAL, "Error: " + exception.getMessage());
            Loader.cancel();
            return false;
        }

    }

    private String sendRequest(HttpMethod method, @NotNull String endpoint, @Nullable String body) {

        if (token == null) {
            Logger.log(LogLevel.ERROR, "Failed to send request to API.");
            Logger.log(LogLevel.ERROR, "Error: Token has not been set.");
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
            return client.send(request, HttpResponse.BodyHandlers.ofString()).body();
        } catch (Exception exception) {
            Logger.log(LogLevel.ERROR, "Failed to send request to API.");
            Logger.log(LogLevel.ERROR, "Error: " + exception.getMessage());
            return null;
        }

    }

    public String get(@NotNull String endpoint) {
        return sendRequest(HttpMethod.GET, endpoint, null);
    }

    public String post(@NotNull String endpoint, @Nullable String body) {
        return sendRequest(HttpMethod.POST, endpoint, body);
    }

    public String put(@NotNull String endpoint, @Nullable String body) {
        return sendRequest(HttpMethod.PUT, endpoint, body);
    }

    public String delete(@NotNull String endpoint) {
        return sendRequest(HttpMethod.DELETE, endpoint, null);
    }

}
