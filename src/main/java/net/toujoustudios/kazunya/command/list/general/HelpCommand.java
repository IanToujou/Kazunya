package net.toujoustudios.kazunya.command.list.general;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import net.dv8tion.jda.api.interactions.components.Button;
import net.toujoustudios.kazunya.color.ColorTools;
import net.toujoustudios.kazunya.command.CommandCategory;
import net.toujoustudios.kazunya.command.CommandContext;
import net.toujoustudios.kazunya.command.CommandManager;
import net.toujoustudios.kazunya.command.ICommand;
import net.toujoustudios.kazunya.config.Config;
import net.toujoustudios.kazunya.error.ErrorEmbed;
import net.toujoustudios.kazunya.error.ErrorType;

import java.util.ArrayList;
import java.util.List;

/**
 * This file has been created by Ian Toujou.
 * Project: Kazunya
 * Date: 26/08/2021
 * Time: 23:09
 */
public class HelpCommand implements ICommand {

    private final CommandManager manager;
    private final Config config;

    public HelpCommand(CommandManager manager) {
        this.manager = manager;
        config = Config.getDefault();
    }

    @Override
    @SuppressWarnings("all")
    public void handle(CommandContext context) {

        List<OptionMapping> args = context.getArgs();
        EmbedBuilder embedBuilder = new EmbedBuilder();

        embedBuilder.setColor(ColorTools.getFromRGBString(config.getString("format.color.default")));
        embedBuilder.setTitle(":sparkles: **Kazunya Help**");
        embedBuilder.setDescription("Here is a full list of all bot commands and features.\nYou can also type `/help [<command>]` to look up a specific command and its usage.");
        embedBuilder.setThumbnail(config.getString("assets.icon.search"));

        if (args.isEmpty()) {

            StringBuilder builderGeneral = new StringBuilder();
            StringBuilder builderRoleplay = new StringBuilder();
            StringBuilder builderFun = new StringBuilder();

            for (ICommand command : manager.getCommands()) {
                if (command.getCategory() == CommandCategory.GENERAL) {
                    builderGeneral.append("`/" + command.getName() + "` - " + command.getDescription() + "\n");
                }
                if (command.getCategory() == CommandCategory.ROLEPLAY) {
                    builderRoleplay.append("`/" + command.getName() + "` - " + command.getDescription() + "\n");
                }
                if (command.getCategory() == CommandCategory.FUN) {
                    builderFun.append("`/" + command.getName() + "` - " + command.getDescription() + "\n");
                }
            }

            embedBuilder.addField(":satellite_orbital: General:", builderGeneral.toString(), false);
            embedBuilder.addField(":heart: Roleplay:", builderRoleplay.toString(), false);
            embedBuilder.addField(":sparkles: Fun:", builderFun.toString(), false);

            boolean isOwnerOnServer = false;
            for (Member member : context.getGuild().getMembers()) {
                String id = member.getId();
                if (id.equals(config.getString("user.admin"))) {
                    isOwnerOnServer = true;
                    break;
                }
            }

            if (isOwnerOnServer) {
                embedBuilder.addField(":bookmark_tabs: Credits:", context.getGuild().getMemberById(config.getString("user.admin")).getAsMention() + " - Toujou Studios", false);
            } else {
                embedBuilder.addField(":bookmark_tabs: Credits:", "IanToujou - Toujou Studios", false);
            }

            context.getEvent().replyEmbeds(embedBuilder.build())
                    .addActionRow(Button.link(config.getString("link.invite"), "Invite"))
                    .queue();
            return;

        }

        if (args.size() > 1) {
            context.getEvent().replyEmbeds(ErrorEmbed.buildError(ErrorType.COMMAND_INVALID_SYNTAX)).addActionRow(Button.link(config.getString("link.help"), "Help")).queue();
            return;
        }

        String search = args.get(0).getAsString();
        ICommand command = manager.getCommand(search);

        if (command == null) {
            context.getEvent().replyEmbeds(ErrorEmbed.buildError(ErrorType.COMMAND_INVALID_SEARCH)).addActionRow(Button.link(config.getString("link.help"), "Help")).queue();
            return;
        }

        embedBuilder.setDescription("**Description:** " + command.getDescription() + "\n**Usage:** `/" + command.getSyntax() + "`");
        context.getEvent().replyEmbeds(embedBuilder.build()).queue();

    }

    @Override
    public String getName() {
        return "help";
    }

    @Override
    public String getDescription() {
        return "Shows a list of all the bot commands.";
    }

    @Override
    public List<OptionData> getOptions() {
        List<OptionData> optionData = new ArrayList<>();
        optionData.add(new OptionData(OptionType.STRING, "command", "The command to get more information on.", false));
        return optionData;
    }

    @Override
    public CommandCategory getCategory() {
        return CommandCategory.GENERAL;
    }

}
