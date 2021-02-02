package net.toujoustudios.kazunya.main;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.toujoustudios.kazunya.config.Config;
import net.toujoustudios.kazunya.database.DatabaseManager;
import net.toujoustudios.kazunya.database.DatabaseTimer;
import net.toujoustudios.kazunya.listener.ReadyListener;
import net.toujoustudios.kazunya.log.LogLevel;
import net.toujoustudios.kazunya.log.Logger;
import net.toujoustudios.kazunya.user.UserManager;

import javax.security.auth.login.LoginException;
import java.util.Timer;

/**
 * This file was created by IanToujou.
 * Date: 31.12.2020
 * Time: 18:20
 * Project: Kazunya
 */
public class Kazunya {

    public static final CommandManager COMMAND_MANAGER = new CommandManager();
    public static JDA jda;

    private Kazunya() {

        try {

            JDABuilder builder = JDABuilder.createDefault(Config.TOKEN);
            builder.enableIntents(GatewayIntent.GUILD_MEMBERS);
            builder.enableIntents(GatewayIntent.GUILD_PRESENCES);
            builder.addEventListeners(new ReadyListener());
            builder.addEventListeners(new GuildMessageReceiveListener());
            jda = builder.build();

        } catch(LoginException exception) {

            exception.printStackTrace();

        }

        DatabaseManager.connect();

        DatabaseTimer databaseTimer = new DatabaseTimer();
        Timer timer = new Timer();

    }

    public static void main(String[] args) {

        new Kazunya();

    }

    public static JDA getJDA() {
        return jda;
    }

    public static void shutdown() {

        UserManager.unloadAll();
        DatabaseManager.disconnect();
        Logger.log(LogLevel.INFORMATION, "Shutting down the bot...");
        System.exit(0);

    }

}
