package net.toujoustudios.kazunya.database;

import net.toujoustudios.kazunya.model.GuildManager;
import net.toujoustudios.kazunya.model.UserManager;
import net.toujoustudios.kazunya.log.LogLevel;
import net.toujoustudios.kazunya.log.Logger;

import java.util.TimerTask;

public class DatabaseTimer extends TimerTask {

    @Override
    public void run() {

        UserManager.unloadAll();
        GuildManager.unloadAll();
        DatabaseManager.disconnect();

        try {
            Thread.sleep(1000);
        } catch(InterruptedException exception) {
            exception.printStackTrace();
        }

        DatabaseManager.connect();

    }

}
