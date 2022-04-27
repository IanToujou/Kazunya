package net.toujoustudios.kazunya.database;

import net.toujoustudios.kazunya.guild.GuildManager;
import net.toujoustudios.kazunya.log.LogLevel;
import net.toujoustudios.kazunya.log.Logger;
import net.toujoustudios.kazunya.user.UserManager;

import java.util.TimerTask;

/**
 * This file has been created by Ian Toujou.
 * Project: Kazunya
 * Date: 26/08/2021
 * Time: 23:22
 */
public class DatabaseTimer extends TimerTask {

    @Override
    public void run() {

        UserManager.unloadAll();
        GuildManager.unloadAll();
        Logger.log(LogLevel.INFORMATION, "Reconnecting the database...");
        DatabaseManager.disconnect();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        }

        DatabaseManager.connect();

    }

}
