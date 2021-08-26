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
import net.toujoustudios.kazunya.listener.SlashCommandListener;
import net.toujoustudios.kazunya.log.LogLevel;
import net.toujoustudios.kazunya.log.Logger;
import net.toujoustudios.kazunya.user.UserManager;

import javax.security.auth.login.LoginException;
import java.util.Objects;
import java.util.Scanner;
import java.util.Timer;

/**
 * This file has been created by Ian Toujou.
 * Project: Kazunya
 * Date: 26/08/2021
 * Time: 23:01
 */
public class Kazunya {

    private JDABuilder builder;
    private JDA jda;
    private CommandManager commandManager;

    public void build() {

        Config config = Config.getDefault();
        Config keysConfig = Config.getFile("keys.yml");
        commandManager = new CommandManager();

        builder = JDABuilder.createDefault(keysConfig.getString("keys.token"));

        builder.setBulkDeleteSplittingEnabled(false);
        builder.setActivity(Activity.streaming("/help - Running " + config.getString("general.name") + " " + config.getString("general.version"), "https://twitch.tv/iantoujou"));

        builder.enableIntents(GatewayIntent.GUILD_MEMBERS);
        builder.enableIntents(GatewayIntent.GUILD_PRESENCES);
        builder.addEventListeners(new SlashCommandListener());

    }

    public void start() {
        try {
            jda = builder.build();
            commandManager.registerCommands();
            startConsole();
        } catch (LoginException exception) {
            exception.printStackTrace();
        }
    }

    public void initializeDatabase() {

        DatabaseManager.connect();
        DatabaseTimer databaseTimer = new DatabaseTimer();
        Timer timer = new Timer();
        DatabaseManager.setup();
        timer.schedule(databaseTimer, 3600000, 3600000);

    }

    public void startConsole() {

        Scanner scanner = new Scanner(System.in);
        while (true) {

            String input = scanner.nextLine();

            if (input.startsWith("/save")) {

                UserManager.unloadAll();
                GuildManager.unloadAll();
                Logger.log(LogLevel.INFORMATION, "Successfully saved all data in the cache.");

            } else if (input.startsWith("/shutdown")) {

                UserManager.unloadAll();
                GuildManager.unloadAll();
                Logger.log(LogLevel.INFORMATION, "Successfully saved all data in the cache.");
                System.exit(0);

            } else if (input.startsWith("/msg")) {

                String[] args = input.split(" ");
                String channel = args[1];
                StringBuilder message = new StringBuilder();

                for (int i = 2; i < args.length; i++) {

                    if (i != 2) {
                        message.append(" ").append(args[i]);
                    } else {
                        message.append(args[i]);
                    }

                }

                try {

                    Objects.requireNonNull(jda.getTextChannelById(channel)).sendMessage(message).queue();
                    Logger.log(LogLevel.INFORMATION, "The message has been sent.");

                } catch (Exception exception) {

                    Logger.log(LogLevel.ERROR, "Could not send message to channel.");

                }

            } else if (input.startsWith("/pmsg")) {

                String[] args = input.split(" ");
                String user = args[1];
                StringBuilder message = new StringBuilder();

                for (int i = 2; i < args.length; i++) {

                    if (i != 2) {
                        message.append(" ").append(args[i]);
                    } else {
                        message.append(args[i]);
                    }

                }

                try {

                    Objects.requireNonNull(jda.getUserById(user)).openPrivateChannel().queue((channel) -> channel.sendMessage(message.toString()).queue());

                    Logger.log(LogLevel.INFORMATION, "The message has been sent.");

                } catch (Exception exception) {

                    Logger.log(LogLevel.ERROR, "Could not send message to user.");

                }

            }

        }

    }

    public JDABuilder getBuilder() {
        return builder;
    }

    public JDA getJDA() {
        return jda;
    }

    public CommandManager getCommandManager() {
        return commandManager;
    }

}
