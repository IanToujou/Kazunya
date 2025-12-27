package net.toujoustudios.kazunya.main;

import lombok.Data;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.toujoustudios.kazunya.command.CommandManager;
import net.toujoustudios.kazunya.command.list.roleplay.*;
import net.toujoustudios.kazunya.config.Config;
import net.toujoustudios.kazunya.event.SlashCommandListener;
import net.toujoustudios.kazunya.loader.Loader;
import net.toujoustudios.kazunya.log.LogLevel;
import net.toujoustudios.kazunya.log.Logger;

import java.util.Objects;
import java.util.Scanner;

@Data
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

        initializeIntends();
        initializeEvents();
        initializeCommandEvents();

    }

    public void start() {
        Loader.ensureLoad();
        jda = builder.build();
        commandManager.registerCommands();
        startConsole();
    }

    public void initializeIntends() {
        builder.enableIntents(GatewayIntent.GUILD_MEMBERS);
        builder.enableIntents(GatewayIntent.GUILD_PRESENCES);
    }

    public void initializeEvents() {
        builder.addEventListeners(new SlashCommandListener());
    }

    public void initializeCommandEvents() {
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

    public void startConsole() {

        Scanner scanner = new Scanner(System.in);
        while(true) {

            String input = scanner.nextLine();

            if(input.startsWith("save")) {
                Logger.log(LogLevel.INFORMATION, "Successfully saved all data in the cache.");
            } else if(input.startsWith("help")) {
                Logger.log(LogLevel.INFORMATION, "Here is a list of all available commands: ban, help, save, shutdown, msg, pmsg");
            } else if(input.startsWith("stop") || input.startsWith("exit") || input.startsWith("shutdown")) {
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
