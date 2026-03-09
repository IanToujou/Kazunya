package net.toujoustudios.kazunya.loader;

import lombok.Getter;
import net.toujoustudios.kazunya.api.ApiClient;
import net.toujoustudios.kazunya.api.Cache;
import net.toujoustudios.kazunya.log.LogLevel;
import net.toujoustudios.kazunya.log.Logger;
import net.toujoustudios.kazunya.main.Main;
import net.toujoustudios.kazunya.repository.RoleplayImageRepository;
import net.toujoustudios.kazunya.repository.RoleplayInteractionRepository;

import java.util.Scanner;

public class Loader {

    @Getter
    private static LoaderState state;

    public static void startLoading() {
        preInitialize();
        initialize();
        postInitialize();
    }

    private static void preInitialize() {

        String url = System.getenv("API_URL");
        String username = System.getenv("API_USERNAME");
        String password = System.getenv("API_PASSWORD");
        if (url == null || username == null || password == null) {
            Logger.log(LogLevel.FATAL, "Failed to establish API connection.");
            Logger.log(LogLevel.FATAL, "Error: API_URL, API_USERNAME, API_PASSWORD must be set.");
            cancel();
            return;
        }

        Main.getBot().apiClient(new ApiClient(url, username, password));
        if (!Main.getBot().apiClient().authenticate()) {
            Logger.log(LogLevel.FATAL, "Failed to authenticate and fetch token. Are the credentials correct?");
            cancel();
        }

    }

    private static void initialize() {
        Main.getBot().cache(new Cache(Main.getBot().apiClient()));
        Main.getBot().cache().register(new RoleplayImageRepository());
        Main.getBot().cache().register(new RoleplayInteractionRepository());
        Main.getBot().build();
    }

    private static void postInitialize() {
        Main.getBot().start();
    }

    public static void cancel() {
        Loader.state = LoaderState.CANCELLED;
    }

    public static void ensureLoad() {
        if(Loader.getState() == LoaderState.CANCELLED) {
            Logger.log(LogLevel.WARNING, "Loader state is set to cancelled. Aborting startup. Please press any key.");
            Scanner scanner = new Scanner(System.in);
            scanner.nextLine();
            System.exit(0);
        }
    }

}
