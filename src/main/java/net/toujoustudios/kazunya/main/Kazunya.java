package net.toujoustudios.kazunya.main;

import lombok.Getter;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.toujoustudios.kazunya.command.CommandManager;
import net.toujoustudios.kazunya.command.list.roleplay.*;
import net.toujoustudios.kazunya.config.Config;
import net.toujoustudios.kazunya.model.GuildManager;
import net.toujoustudios.kazunya.model.UserManager;
import net.toujoustudios.kazunya.database.DatabaseManager;
import net.toujoustudios.kazunya.database.DatabaseTimer;
import net.toujoustudios.kazunya.listener.SlashCommandListener;
import net.toujoustudios.kazunya.listener.MessageReceivedListener;
import net.toujoustudios.kazunya.loader.Loader;
import net.toujoustudios.kazunya.log.LogLevel;
import net.toujoustudios.kazunya.log.Logger;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Objects;
import java.util.Scanner;
import java.util.Timer;


public class Kazunya {

    @Getter
    private JDABuilder builder;
    private JDA jda;
    @Getter
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
        builder.addEventListeners(new MessageReceivedListener());

        //Add event listeners for command buttons.
        builder.addEventListeners(new BonkCommand());
        builder.addEventListeners(new CuddleCommand());
        builder.addEventListeners(new HugCommand());
        builder.addEventListeners(new KissCommand());
        builder.addEventListeners(new LickCommand());
        builder.addEventListeners(new NomCommand());
        builder.addEventListeners(new PatCommand());
        builder.addEventListeners(new PokeCommand());
        builder.addEventListeners(new SlapCommand());
        builder.addEventListeners(new TickleCommand());
        builder.addEventListeners(new YeetCommand());

    }

    public void start() {
        Loader.ensureLoad();
        jda = builder.build();
        commandManager.registerCommands();
        startConsole();
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
            } else if(input.startsWith("stop") || input.startsWith("exit") || input.startsWith("shutdown")) {
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
            } else if(input.startsWith("pmsg")) {
                String[] args = input.split(" ");
                String user = args[1];
                StringBuilder message = new StringBuilder();
                for(int i = 2; i < args.length; i++) {
                    if(i != 2) message.append(" ").append(args[i]);
                    else message.append(args[i]);
                }
                try {
                    Objects.requireNonNull(jda.getUserById(user)).openPrivateChannel().flatMap(channel -> channel.sendMessage(message)).queue();
                    Logger.log(LogLevel.INFORMATION, "The message has been sent.");
                } catch(Exception exception) {
                    Logger.log(LogLevel.ERROR, "Could not send message to channel.");
                }
            } else if(input.startsWith("info")) {
                Logger.log(LogLevel.INFORMATION, "The bot is currently on " + jda.getGuilds().size() + " servers.");
            } else {
                Logger.log(LogLevel.ERROR, "The specified command does not exist.");
            }

        }

    }

    public JDA getJDA() {
        return jda;
    }

}
