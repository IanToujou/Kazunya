package net.toujoustudios.kazunya.log;

/**
 * This file was created by IanToujou.
 * Date: 02.01.2021
 * Time: 20:00
 * Project: Kazunya
 */
public enum LogLevel {

    DEBUG(0, "DEBUG"), INFORMATION(1, "INFORMATION"), WARNING(2, "WARNING"), ERROR(3, "ERROR"), FATAL(4, "FATAL");

    private final int level;
    private final String name;

    LogLevel(int level, String name) {

        this.level = level;
        this.name = name;

    }

    public int getLevel() {

        return this.level;

    }

    public String getName() {

        return this.name;

    }

}
