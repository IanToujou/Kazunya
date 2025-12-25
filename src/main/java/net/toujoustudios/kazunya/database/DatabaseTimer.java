package net.toujoustudios.kazunya.database;

import java.util.TimerTask;

public class DatabaseTimer extends TimerTask {

    @Override
    public void run() {

        DatabaseManager.disconnect();

        try {
            Thread.sleep(1000);
        } catch(InterruptedException ignored) {}

        DatabaseManager.connect();

    }

}
