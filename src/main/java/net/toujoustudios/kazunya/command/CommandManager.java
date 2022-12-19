package net.toujoustudios.kazunya.command;

import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.SubcommandData;
import net.dv8tion.jda.api.requests.restaction.CommandListUpdateAction;
import net.toujoustudios.kazunya.command.list.economy.*;
import net.toujoustudios.kazunya.command.list.fun.PeePeeCommand;
import net.toujoustudios.kazunya.command.list.fun.RollCommand;
import net.toujoustudios.kazunya.command.list.fun.ShipCommand;
import net.toujoustudios.kazunya.command.list.general.HelpCommand;
import net.toujoustudios.kazunya.command.list.general.InfoCommand;
import net.toujoustudios.kazunya.command.list.roleplay.*;
import net.toujoustudios.kazunya.command.list.tools.AvatarCommand;
import net.toujoustudios.kazunya.command.list.tools.UserInfoCommand;
import net.toujoustudios.kazunya.error.ErrorEmbed;
import net.toujoustudios.kazunya.error.ErrorType;
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

        //Register general commands
        this.addCommand(new HelpCommand(this));
        this.addCommand(new InfoCommand());

        //Register role play commands
        this.addCommand(new MarryCommand());
        this.addCommand(new DivorceCommand());
        this.addCommand(new BlushCommand());
        this.addCommand(new HugCommand());
        this.addCommand(new KissCommand());
        this.addCommand(new CuddleCommand());
        this.addCommand(new CryCommand());
        this.addCommand(new PatCommand());
        this.addCommand(new TickleCommand());
        this.addCommand(new LaughCommand());
        this.addCommand(new LickCommand());
        this.addCommand(new PurrCommand());
        this.addCommand(new SlapCommand());
        this.addCommand(new SmileCommand());
        this.addCommand(new KillCommand());
        this.addCommand(new FuckCommand());
        this.addCommand(new BonkCommand());
        this.addCommand(new NomCommand());
        this.addCommand(new FriendCommand());
        this.addCommand(new PartnerCommand());
        this.addCommand(new YeetCommand());

        //Register economy commands
        this.addCommand(new MarketInfoCommand());
        this.addCommand(new WalletCommand());
        this.addCommand(new BankCommand());
        this.addCommand(new DepositCommand());
        this.addCommand(new WithdrawCommand());
        this.addCommand(new TransferCommand());

        //Register fun commands
        this.addCommand(new PeePeeCommand());
        this.addCommand(new RollCommand());
        this.addCommand(new ShipCommand());

        //Register tool commands
        this.addCommand(new AvatarCommand());
        this.addCommand(new UserInfoCommand());

    }

    private void addCommand(ICommand command) {
        boolean nameFound = this.commands.stream().anyMatch((it) -> it.getName().equalsIgnoreCase(command.getName()));
        if(nameFound) throw new IllegalArgumentException("A command with this name is already present.");
        commands.add(command);
    }

    public void registerCommands() {

        Logger.log(LogLevel.INFORMATION, "Registering commands. This may take a while...");
        CommandListUpdateAction updateAction = Main.getBot().getJDA().updateCommands();
        List<CommandData> commands = new ArrayList<>();

        for(ICommand command : this.commands) {
            Logger.log(LogLevel.DEBUG, "Started registration of the following commands: /" + command.getName());
            CommandData data = new CommandData(command.getName(), command.getEmoji() + " " + command.getDescription());
            if(command.getSubcommandData().size() > 0) {
                for(int i = 1; i <= command.getSubcommandData().size(); i++) {
                    SubcommandData subcommandData = command.getSubcommandData().get(i - 1);
                    if(i == command.getSubcommandData().size())
                        Logger.log(LogLevel.DEBUG, "└── /" + data.getName() + " " + subcommandData.getName());
                    else
                        Logger.log(LogLevel.DEBUG, "├── /" + data.getName() + " " + subcommandData.getName());
                    data.addSubcommands(subcommandData);
                }
            } else data.addOptions(command.getOptions());
            commands.add(data);
        }

        updateAction.addCommands(commands).queue();
        Logger.log(LogLevel.INFORMATION, "Successfully registered " + commands.size() + " commands.");

    }

    public List<ICommand> getCommands() {
        return commands;
    }

    @Nullable
    public ICommand getCommand(String search) {

        String searchLower = search.toLowerCase();

        for(ICommand command : this.commands) {
            if(command.getName().equals(searchLower)) return command;
        }

        return null;

    }

    public void handle(SlashCommandEvent event) {

        String invoke = event.getName();
        ICommand command = this.getCommand(invoke);

        if(command != null) {

            event.getChannel().sendTyping().queue();

            if(command.getCategory() == CommandCategory.NSFW && !event.getTextChannel().isNSFW()) {
                ErrorEmbed.sendError(event, ErrorType.GENERAL_NSFW);
                return;
            }

            List<OptionMapping> args = event.getOptions();
            CommandContext context = new CommandContext(event, args);

            command.handle(context);

        }

    }

}
