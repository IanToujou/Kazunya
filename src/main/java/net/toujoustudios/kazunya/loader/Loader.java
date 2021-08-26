package net.toujoustudios.kazunya.loader;

import net.toujoustudios.kazunya.config.Config;
import net.toujoustudios.kazunya.main.Main;

/**
 * This file has been created by Ian Toujou.
 * Project: Kazunya
 * Date: 26/08/2021
 * Time: 23:02
 */
public class Loader {

    private static LoaderState state;

    public static void startLoading() {

        preInitialize();
        initialize();
        postInitialize();

    }

    private static void preInitialize() {
        new Config("config.yml");
        new Config("keys.yml");
        new Config("database.yml");
        Main.getBot().initializeDatabase();
    }

    private static void initialize() {
        Main.getBot().build();
    }

    private static void postInitialize() {
        Main.getBot().start();
    }

    public static LoaderState getState() {
        return state;
    }

    public static void setState(LoaderState state) {
        Loader.state = state;
    }

    public static void cancel() {
        Loader.state = LoaderState.CANCELLED;
    }

}
