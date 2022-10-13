package net.toujoustudios.kazunya.main;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.toujoustudios.kazunya.command.CommandManager;
import net.toujoustudios.kazunya.command.list.roleplay.FriendCommand;
import net.toujoustudios.kazunya.command.list.roleplay.MarryCommand;
import net.toujoustudios.kazunya.command.list.roleplay.PartnerCommand;
import net.toujoustudios.kazunya.config.Config;
import net.toujoustudios.kazunya.data.guild.GuildManager;
import net.toujoustudios.kazunya.data.user.UserManager;
import net.toujoustudios.kazunya.database.DatabaseManager;
import net.toujoustudios.kazunya.database.DatabaseTimer;
import net.toujoustudios.kazunya.listener.SlashCommandListener;
import net.toujoustudios.kazunya.loader.Loader;
import net.toujoustudios.kazunya.log.LogLevel;
import net.toujoustudios.kazunya.log.Logger;

import javax.security.auth.login.LoginException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Objects;
import java.util.Scanner;
import java.util.Timer;


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

        //Enable gateway intents.
        builder.enableIntents(GatewayIntent.GUILD_MEMBERS);
        builder.enableIntents(GatewayIntent.GUILD_PRESENCES);

        //Add event listeners for general events.
        builder.addEventListeners(new SlashCommandListener());

        //Add event listeners for command buttons.
        builder.addEventListeners(new FriendCommand());
        builder.addEventListeners(new PartnerCommand());
        builder.addEventListeners(new MarryCommand());

    }

    public void start() {
        try {
            Loader.ensureLoad();
            jda = builder.build();
            commandManager.registerCommands();
            startConsole();
        } catch(LoginException exception) {
            Logger.log(LogLevel.FATAL, "Failed to log the bot in. Please review the error below:");
            Logger.log(LogLevel.FATAL, exception.getMessage());
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
        while(true) {

            String input = scanner.nextLine();

            if(input.startsWith("save")) {
                UserManager.unloadAll();
                GuildManager.unloadAll();
                Logger.log(LogLevel.INFORMATION, "Successfully saved all data in the cache.");
            } else if(input.startsWith("ban")) {
                String[] args = input.split(" ");
                String user = args[1];
                String reason = args[2];
                String until = args[3];
                try {
                    UserManager userManager = UserManager.getUser(user);
                    userManager.ban(reason, new SimpleDateFormat("dd.MM.yyyy").parse(until));
                    Logger.log(LogLevel.INFORMATION, "Successfully banned the user from using the bot.");
                } catch(ParseException exception) {
                    Logger.log(LogLevel.ERROR, "Please enter a valid date.");
                }
            } else if(input.startsWith("help")) {
                Logger.log(LogLevel.INFORMATION, "Here is a list of all available commands: ban, help, save, shutdown, msg, pmsg");
            } else if(input.startsWith("shutdown")) {
                UserManager.unloadAll();
                GuildManager.unloadAll();
                Logger.log(LogLevel.INFORMATION, "Successfully saved all data in the cache.");
                System.exit(0);
            } else if(input.startsWith("msg")) {
                String[] args = input.split(" ");
                String channel = args[1];
                StringBuilder message = new StringBuilder();
                for(int i = 2; i < args.length; i++) {
                    if(i != 2) message.append(" ").append(args[i]);
                    else message.append(args[i]);
                }
                try {
                    Objects.requireNonNull(jda.getTextChannelById(channel)).sendMessage(message).queue();
                    Logger.log(LogLevel.INFORMATION, "The message has been sent.");
                } catch(Exception exception) {
                    Logger.log(LogLevel.ERROR, "Could not send message to channel.");
                }
            } else {
                Logger.log(LogLevel.ERROR, "The specified command does not exist.");
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
