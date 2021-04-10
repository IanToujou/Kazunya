package net.toujoustudios.kazunya.command.list.general;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.TextChannel;
import net.toujoustudios.kazunya.command.CommandCategory;
import net.toujoustudios.kazunya.command.CommandContext;
import net.toujoustudios.kazunya.command.CommandManager;
import net.toujoustudios.kazunya.command.ICommand;
import net.toujoustudios.kazunya.config.Config;

import java.util.List;

/**
 * This file was created by IanToujou.
 * Date: 02.02.2021
 * Time: 08:50
 * Project: Kazunya
 */
public class HelpCommand implements ICommand {

    private final CommandManager manager;

    public HelpCommand(CommandManager manager) {

        this.manager = manager;

    }

    @Override
    public void handle(CommandContext context) {

        List<String> args = context.getArgs();
        TextChannel channel = context.getChannel();
        EmbedBuilder embedBuilder = new EmbedBuilder();

        embedBuilder.setColor(Config.DEFAULT_EMBED_COLOR);
        embedBuilder.setTitle("**__HELP__**");

        if(args.isEmpty()) {

            StringBuilder builderGeneral = new StringBuilder();
            StringBuilder builderRoleplay = new StringBuilder();
            StringBuilder builderFun = new StringBuilder();
            StringBuilder builderModeration = new StringBuilder();

            for(ICommand command : manager.getCommands()) {
                if(command.getCategory() == CommandCategory.GENERAL) {
                    builderGeneral.append("**" + Config.DEFAULT_PREFIX + command.getName() + "** - " + command.getHelp() + "\n");
                }
                if(command.getCategory() == CommandCategory.ROLEPLAY) {
                    builderRoleplay.append("**" + Config.DEFAULT_PREFIX + command.getName() + "** - " + command.getHelp() + "\n");
                }
                if(command.getCategory() == CommandCategory.FUN) {
                    builderFun.append("**" + Config.DEFAULT_PREFIX + command.getName() + "** - " + command.getHelp() + "\n");
                }
                if(command.getCategory() == CommandCategory.MODERATION) {
                    builderModeration.append("**" + Config.DEFAULT_PREFIX + command.getName() + "** - " + command.getHelp() + "\n");
                }
            }

            embedBuilder.addField(":satellite_orbital: General:", builderGeneral.toString(), false);
            embedBuilder.addField(":smile: Roleplay:", builderRoleplay.toString(), false);
            embedBuilder.addField(":tada: Fun:", builderFun.toString(), false);
            embedBuilder.addField(":shield: Moderation:", builderModeration.toString(), false);
            embedBuilder.addField(":bookmark_tabs: Credits:", "IanToujou", false);

            channel.sendMessage(embedBuilder.build()).queue();

            return;

        }

        if(args.size() > 1) {

            embedBuilder.setTitle("**__ERROR__**");
            embedBuilder.setColor(Config.ERROR_EMBED_COLOR);
            embedBuilder.setDescription(":x: The command syntax is not correct.\nPlease use **" + Config.DEFAULT_PREFIX + getUsage() + "**");
            channel.sendMessage(embedBuilder.build()).queue();
            return;

        }

        String search = args.get(0);
        ICommand command = manager.getCommand(search);

        if(command == null) {

            embedBuilder.setTitle("**__ERROR__**");
            embedBuilder.setColor(Config.ERROR_EMBED_COLOR);
            embedBuilder.setDescription(":x: Nothing found for `" + search + "`.");
            channel.sendMessage(embedBuilder.build()).queue();
            return;

        }

        embedBuilder.setDescription("**Description:** " + command.getHelp() + "\n**Usage:** " + Config.DEFAULT_PREFIX + command.getUsage());
        channel.sendMessage(embedBuilder.build()).queue();

    }

    @Override
    public String getName() {
        return "help";
    }

    @Override
    public String getHelp() { return "Shows a list with all my features and commands."; }

    @Override
    public String getUsage() { return "help [(command)]"; }

    @Override
    public CommandCategory getCategory() { return CommandCategory.GENERAL; }

}
