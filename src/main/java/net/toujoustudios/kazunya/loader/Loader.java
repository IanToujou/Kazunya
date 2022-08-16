package net.toujoustudios.kazunya.loader;

import net.toujoustudios.kazunya.config.Config;
import net.toujoustudios.kazunya.economy.stock.StockMarket;
import net.toujoustudios.kazunya.main.Main;

@SuppressWarnings("unused")
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
        StockMarket.initialize();
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
