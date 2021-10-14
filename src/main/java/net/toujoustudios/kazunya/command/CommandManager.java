package net.toujoustudios.kazunya.command;

import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import net.dv8tion.jda.api.requests.restaction.CommandListUpdateAction;
import net.toujoustudios.kazunya.command.list.general.HelpCommand;
import net.toujoustudios.kazunya.command.list.roleplay.BlushCommand;
import net.toujoustudios.kazunya.command.list.roleplay.DivorceCommand;
import net.toujoustudios.kazunya.command.list.roleplay.MarryCommand;
import net.toujoustudios.kazunya.command.list.roleplay.HugCommand;
import net.toujoustudios.kazunya.command.list.roleplay.CuddleCommand;
import net.toujoustudios.kazunya.log.LogLevel;
import net.toujoustudios.kazunya.log.Logger;
import net.toujoustudios.kazunya.main.Main;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

/**
 * This file has been created by Ian Toujou.
 * Project: Kazunya
 * Date: 26/08/2021
 * Time: 23:08
 */
public class CommandManager {

    private final List<ICommand> commands = new ArrayList<>();

    public CommandManager() {
        this.addCommand(new HelpCommand(this));
        this.addCommand(new MarryCommand());
        this.addCommand(new DivorceCommand());
        this.addCommand(new BlushCommand());
        this.addCommand(new HugCommand());
        this.addCommand(new CuddleCommand());
    }

    private void addCommand(ICommand command) {
        boolean nameFound = this.commands.stream().anyMatch((it) -> it.getName().equalsIgnoreCase(command.getName()));
        if (nameFound) throw new IllegalArgumentException("A command with this name is already present.");
        commands.add(command);
    }

    @SuppressWarnings("unused")
    public void registerCommands() {

        Logger.log(LogLevel.INFORMATION, "Registering commands. This may take a while...");
        CommandListUpdateAction updateAction = Main.getBot().getJDA().updateCommands();

        for (ICommand command : this.commands) {

            Logger.log(LogLevel.DEBUG, "--------------------------------------------------");
            Logger.log(LogLevel.DEBUG, "Started registration of a new command. More information below:");
            Logger.log(LogLevel.DEBUG, "Name: " + command.getName());
            Logger.log(LogLevel.DEBUG, "Description: " + command.getDescription());
            Logger.log(LogLevel.DEBUG, "Syntax: " + command.getSyntax());
            Logger.log(LogLevel.DEBUG, "Options:");
            for (OptionData option : command.getOptions()) {
                Logger.log(LogLevel.DEBUG, "| Option Name: " + option.getName());
                Logger.log(LogLevel.DEBUG, "| Option Description: " + option.getDescription());
                Logger.log(LogLevel.DEBUG, "| Option Type: " + option.getType());
                Logger.log(LogLevel.DEBUG, "| Option Required: " + option.isRequired());
            }
            Logger.log(LogLevel.DEBUG, "--------------------------------------------------");

            CommandData data = new CommandData(command.getName(), command.getDescription()).addOptions(command.getOptions());

            if (command.getOptions().isEmpty()) {
                Main.getBot().getJDA().upsertCommand(command.getName(), command.getDescription()).queue();
            } else {
                Main.getBot().getJDA().upsertCommand(data).queue();
            }

        }

        Logger.log(LogLevel.INFORMATION, "Successfully registered " + commands.size() + " commands.");

    }

    public List<ICommand> getCommands() {
        return commands;
    }

    @Nullable
    public ICommand getCommand(String search) {

        String searchLower = search.toLowerCase();

        for (ICommand command : this.commands) {
            if (command.getName().equals(searchLower) || command.getAliases().contains(searchLower)) return command;
        }

        return null;

    }

    public void handle(SlashCommandEvent event) {

        String invoke = event.getName();
        ICommand command = this.getCommand(invoke);

        if (command != null) {

            event.getChannel().sendTyping().queue();
            List<OptionMapping> args = event.getOptions();

            CommandContext context = new CommandContext(event, args);
            command.handle(context);

        }

    }

}
