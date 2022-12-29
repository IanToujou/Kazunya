package net.toujoustudios.kazunya.main;

import net.toujoustudios.kazunya.loader.Loader;

public class Main {

    private static Kazunya bot;

    public static void main(String[] args) {
        bot = new Kazunya();
        Loader.startLoading();
    }

    public static Kazunya getBot() {
        return bot;
    }

}
