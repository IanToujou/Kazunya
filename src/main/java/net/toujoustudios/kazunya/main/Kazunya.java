package net.toujoustudios.kazunya.main;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.toujoustudios.kazunya.command.CommandManager;
import net.toujoustudios.kazunya.config.Config;
import net.toujoustudios.kazunya.database.DatabaseManager;
import net.toujoustudios.kazunya.database.DatabaseTimer;
import net.toujoustudios.kazunya.guild.GuildManager;
import net.toujoustudios.kazunya.listener.GuildMessageReceiveListener;
import net.toujoustudios.kazunya.listener.ReadyListener;
import net.toujoustudios.kazunya.log.LogLevel;
import net.toujoustudios.kazunya.log.Logger;
import net.toujoustudios.kazunya.user.UserManager;

import javax.security.auth.login.LoginException;
import java.util.Objects;
import java.util.Scanner;
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
            builder.setActivity(Activity.streaming("\u2728 " + Config.DEFAULT_PREFIX + "help - Running Version " + Config.CURRENT_VERSION, "https://twitch.tv/iantoujou"));
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

        DatabaseManager.setup();

        timer.schedule(databaseTimer, 3600000, 3600000);

        Scanner scanner = new Scanner(System.in);

        while(true) {

            String input = scanner.nextLine();

            if(input.startsWith("/save")) {

                UserManager.unloadAll();
                GuildManager.unloadAll();
                Logger.log(LogLevel.INFORMATION, "Successfully saved all data in the cache.");

            } else if(input.startsWith("/shutdown")) {

                UserManager.unloadAll();
                GuildManager.unloadAll();
                Logger.log(LogLevel.INFORMATION, "Successfully saved all data in the cache.");
                System.exit(0);

            } else if(input.startsWith("/msg")) {

                String[] args = input.split(" ");
                String channel = args[1];
                StringBuilder message = new StringBuilder();

                for(int i = 2; i < args.length; i++) {

                    if(i != 2) {
                        message.append(" ").append(args[i]);
                    } else {
                        message.append(args[i]);
                    }

                }

                try {

                    Objects.requireNonNull(jda.getTextChannelById(channel)).sendMessage(message).queue();
                    Logger.log(LogLevel.INFORMATION, "The message has been sent.");

                } catch(Exception exception) {

                    Logger.log(LogLevel.ERROR, "Could not send message to channel.");

                }

            } else if(input.startsWith("/pmsg")) {

                String[] args = input.split(" ");
                String user = args[1];
                StringBuilder message = new StringBuilder();

                for(int i = 2; i < args.length; i++) {

                    if(i != 2) {
                        message.append(" ").append(args[i]);
                    } else {
                        message.append(args[i]);
                    }

                }

                try {

                    Objects.requireNonNull(jda.getUserById(user)).openPrivateChannel().queue((channel) -> channel.sendMessage(message.toString()).queue());

                    Logger.log(LogLevel.INFORMATION, "The message has been sent.");

                } catch(Exception exception) {

                    Logger.log(LogLevel.ERROR, "Could not send message to user.");

                }

            }

        }

    }

    @SuppressWarnings("all")
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
