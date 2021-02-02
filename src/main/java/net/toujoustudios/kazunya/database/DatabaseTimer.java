package net.toujoustudios.kazunya.database;

import net.toujoustudios.kazunya.log.LogLevel;
import net.toujoustudios.kazunya.log.Logger;
import net.toujoustudios.kazunya.user.UserManager;

import java.util.TimerTask;

/**
 * This file was created by IanToujou.
 * Date: 02.02.2021
 * Time: 08:38
 * Project: Kazunya
 */
public class DatabaseTimer extends TimerTask {

    @Override
    public void run() {

        UserManager.unloadAll();
        Logger.log(LogLevel.INFORMATION, "Reconnecting the database...");
        DatabaseManager.disconnect();

        try {

            Thread.sleep(1000);

        } catch(InterruptedException e) {

            e.printStackTrace();

        }

        DatabaseManager.connect();

    }

}
