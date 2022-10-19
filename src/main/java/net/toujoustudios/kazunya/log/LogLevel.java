package net.toujoustudios.kazunya.log;

/**
 * This file has been created by Ian Toujou.
 * Project: Kazunya
 * Date: 26/08/2021
 * Time: 23:05
 */
public enum LogLevel {

    SILENT(0, "\u001B[30;1m"), DEBUG(1, "\u001B[36m"), INFORMATION(1, "\u001B[32m"), WARNING(2, "\u001B[33m"), ERROR(3, "\u001b[31;1m"), FATAL(4, "\u001B[31m");

    private final int level;
    private final String color;

    LogLevel(int level, String color) {
        this.level = level;
        this.color = color;
    }

    public int getLevel() {
        return this.level;
    }

    public String getColor() {
        return this.color;
    }

}
