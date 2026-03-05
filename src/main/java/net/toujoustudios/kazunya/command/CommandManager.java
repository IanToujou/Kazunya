package net.toujoustudios.kazunya.command;

import lombok.Getter;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.interactions.commands.SlashCommandInteraction;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.SlashCommandData;
import net.dv8tion.jda.api.requests.restaction.CommandListUpdateAction;
import net.toujoustudios.kazunya.command.list.fun.PeePeeCommand;
import net.toujoustudios.kazunya.command.list.fun.RollCommand;
import net.toujoustudios.kazunya.command.list.fun.ShipCommand;
import net.toujoustudios.kazunya.command.list.fun.ShipListCommand;
import net.toujoustudios.kazunya.command.list.general.HelpCommand;
import net.toujoustudios.kazunya.command.list.general.InfoCommand;
import net.toujoustudios.kazunya.command.list.general.LinkCommand;
import net.toujoustudios.kazunya.command.list.roleplay.*;
import net.toujoustudios.kazunya.command.list.tools.AvatarCommand;
import net.toujoustudios.kazunya.command.list.tools.UserInfoCommand;
import net.toujoustudios.kazunya.error.ErrorEmbed;
import net.toujoustudios.kazunya.error.ErrorType;
import net.toujoustudios.kazunya.log.LogLevel;
import net.toujoustudios.kazunya.log.Logger;
import net.toujoustudios.kazunya.main.Main;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

@Getter
public class CommandManager {

    private final List<ICommand> commands = new ArrayList<>();

    public CommandManager() {

        //Register general commands
        this.add(new HelpCommand(this));
        this.add(new InfoCommand());
        this.add(new LinkCommand());

        //Register role play commands
        this.add(new BlushCommand());
        this.add(new HugCommand());
        this.add(new KissCommand());
        this.add(new CuddleCommand());
        this.add(new CryCommand());
        this.add(new PatCommand());
        this.add(new PokeCommand());
        this.add(new TickleCommand());
        this.add(new LaughCommand());
        this.add(new LickCommand());
        this.add(new PurrCommand());
        this.add(new SlapCommand());
        this.add(new SmileCommand());
        this.add(new KillCommand());
        this.add(new BonkCommand());
        this.add(new NomCommand());
        this.add(new YeetCommand());

        //Register fun commands
        this.add(new PeePeeCommand());
        this.add(new RollCommand());
        this.add(new ShipCommand());
        this.add(new ShipListCommand());

        //Register tool commands
        this.add(new AvatarCommand());
        this.add(new UserInfoCommand());

    }

    private void add(ICommand command) {
        boolean nameFound = this.commands.stream().anyMatch((it) -> it.name().equalsIgnoreCase(command.name()));
        if(nameFound) throw new IllegalArgumentException("A command with this name is already present.");
        commands.add(command);
    }

    public void registerCommands() {

        Logger.log(LogLevel.INFORMATION, "Registering commands. This may take a while...");
        CommandListUpdateAction updateAction = Main.getBot().getJDA().updateCommands();
        List<CommandData> commands = new ArrayList<>();

        for(ICommand command : this.commands) {
            Logger.log(LogLevel.DEBUG, "Started registration of the following commands: /" + command.name());
            SlashCommandData data = Commands.slash(command.name(), command.emoji() + " " + command.description()).addSubcommands();
            if(!command.subCommandData().isEmpty()) {
                command.subCommandData().forEach(data::addSubcommands);
                Logger.log(LogLevel.DEBUG, "Registered " + command.subCommandData().size() + " subcommands.");
            } else data.addOptions(command.options());
            commands.add(data);
        }

        updateAction.addCommands(commands).queue();
        Logger.log(LogLevel.INFORMATION, "Successfully registered " + commands.size() + " commands.");

    }

    @Nullable
    public ICommand command(String search) {
        String searchLower = search.toLowerCase();
        for(ICommand command : this.commands) {
            if(command.name().equals(searchLower)) return command;
        }
        return null;
    }

    public void handle(SlashCommandInteraction interaction) {

        String invoke = interaction.getName();
        ICommand command = this.command(invoke);

        if(command != null) {
            interaction.getChannel().sendTyping().queue();
            if(command.category() == CommandCategory.NSFW && !interaction.getChannel().asTextChannel().isNSFW()) {
                ErrorEmbed.sendError(interaction, ErrorType.GENERAL_NSFW);
                return;
            }

            List<OptionMapping> args = interaction.getOptions();
            CommandContext context = new CommandContext(interaction, args);
            command.handle(context);
        }

    }

}
