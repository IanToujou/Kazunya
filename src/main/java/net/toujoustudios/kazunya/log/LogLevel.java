package net.toujoustudios.kazunya.log;

import lombok.Getter;

@Getter
public enum LogLevel {

    SILENT(0, "\u001B[30;1m"), DEBUG(1, "\u001B[36m"), INFORMATION(1, "\u001B[32m"), WARNING(2, "\u001B[33m"), ERROR(3, "\u001b[31;1m"), FATAL(4, "\u001B[31m");

    private final int level;
    private final String color;

    LogLevel(int level, String color) {
        this.level = level;
        this.color = color;
    }

}
