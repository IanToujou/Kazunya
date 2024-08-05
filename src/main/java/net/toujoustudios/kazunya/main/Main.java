package net.toujoustudios.kazunya.main;

import lombok.Getter;
import net.toujoustudios.kazunya.loader.Loader;

public class Main {

    @Getter
    private static Kazunya bot;

    public static void main(String[] args) {
        bot = new Kazunya();
        Loader.startLoading();
    }

}
