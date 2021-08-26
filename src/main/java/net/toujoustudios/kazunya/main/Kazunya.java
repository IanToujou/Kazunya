package net.toujoustudios.kazunya.main;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.toujoustudios.kazunya.command.CommandManager;
import net.toujoustudios.kazunya.config.Config;
import net.toujoustudios.kazunya.listener.SlashCommandListener;

import javax.security.auth.login.LoginException;

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
        commandManager = new CommandManager();

        builder = JDABuilder.createDefault(config.getString("keys.token"));

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
        } catch (LoginException exception) {
            exception.printStackTrace();
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
