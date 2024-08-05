package net.toujoustudios.kazunya.loader;

import lombok.Getter;
import net.toujoustudios.kazunya.config.Config;
import net.toujoustudios.kazunya.economy.stock.StockMarket;
import net.toujoustudios.kazunya.log.LogLevel;
import net.toujoustudios.kazunya.log.Logger;
import net.toujoustudios.kazunya.main.Main;

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

    public static void setState(LoaderState state) {
        Loader.state = state;
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
