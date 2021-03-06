package net.toujoustudios.kazunya.command;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.toujoustudios.kazunya.command.list.economy.MarketinfoCommand;
import net.toujoustudios.kazunya.command.list.economy.MoneyCommand;
import net.toujoustudios.kazunya.command.list.fun.ShipCommand;
import net.toujoustudios.kazunya.command.list.general.HelpCommand;
import net.toujoustudios.kazunya.command.list.music.*;
import net.toujoustudios.kazunya.command.list.roleplay.*;
import net.toujoustudios.kazunya.command.list.space.SatelliteCommand;
import net.toujoustudios.kazunya.command.list.stats.UserinfoCommand;
import net.toujoustudios.kazunya.command.list.unlisted.ExecuteCommand;
import net.toujoustudios.kazunya.config.Config;
import net.toujoustudios.kazunya.log.LogLevel;
import net.toujoustudios.kazunya.log.Logger;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

/**
 * This file was created by IanToujou.
 * Date: 02.02.2021
 * Time: 08:45
 * Project: Kazunya
 */
public class CommandManager {

    private final List<ICommand> commands = new ArrayList<>();

    public CommandManager() {

        //Unlisted
        addCommand(new ExecuteCommand());

        //General
        addCommand(new HelpCommand(this));

        //Roleplay
        addCommand(new CryCommand());
        addCommand(new PatCommand());
        addCommand(new HugCommand());
        addCommand(new KissCommand());
        addCommand(new PurrCommand());
        addCommand(new KillCommand());
        addCommand(new CuddleCommand());
        addCommand(new BlushCommand());
        addCommand(new SlapCommand());
        addCommand(new SmileCommand());
        addCommand(new FuckCommand());
        addCommand(new MarryCommand());
        addCommand(new DivorceCommand());
        addCommand(new LickCommand());
        addCommand(new TickleCommand());
        addCommand(new LaughCommand());

        //Fun
        addCommand(new ShipCommand());

        //Music
        addCommand(new PlayCommand());
        addCommand(new StopCommand());
        addCommand(new NowPlayingCommand());
        addCommand(new SkipCommand());
        addCommand(new QueueCommand());

        //Stats
        addCommand(new UserinfoCommand());

        //Economy
        addCommand(new MarketinfoCommand());
        addCommand(new MoneyCommand());

        //Space
        addCommand(new SatelliteCommand());

        Logger.log(LogLevel.INFORMATION, "Successfully registered " + commands.size() + " commands.");

    }

    private void addCommand(ICommand command) {

        boolean nameFound = this.commands.stream().anyMatch((it) -> it.getName().equalsIgnoreCase(command.getName()));

        if(nameFound) {

            throw new IllegalArgumentException("A command with this name is already present.");

        }

        commands.add(command);

    }

    public List<ICommand> getCommands() { return commands; }

    @Nullable
    public ICommand getCommand(String search) {

        String searchLower = search.toLowerCase();

        for(ICommand command : this.commands) {

            if(command.getName().equals(searchLower) || command.getAliases().contains(searchLower)) return command;

        }

        return null;

    }

    public void handle(GuildMessageReceivedEvent event) {

        String[] split = event.getMessage().getContentRaw().replaceFirst("(?i)" + Pattern.quote(Config.DEFAULT_PREFIX), "").split("\\s+");

        String invoke = split[0].toLowerCase();
        ICommand command = this.getCommand(invoke);

        if(command != null) {

            event.getChannel().sendTyping().queue();
            List<String> args = Arrays.asList(split).subList(1, split.length);

            CommandContext context = new CommandContext(event, args);
            command.handle(context);

        }

    }

}
