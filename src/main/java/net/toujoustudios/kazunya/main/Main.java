package net.toujoustudios.kazunya.main;

import net.toujoustudios.kazunya.loader.Loader;

/**
 * This file has been created by Ian Toujou.
 * Project: Kazunya
 * Date: 26/08/2021
 * Time: 23:01
 */
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
