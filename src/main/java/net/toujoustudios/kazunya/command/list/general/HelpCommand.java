package net.toujoustudios.kazunya.command.list.general;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.components.actionrow.ActionRow;
import net.dv8tion.jda.api.components.buttons.Button;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import net.toujoustudios.kazunya.command.CommandCategory;
import net.toujoustudios.kazunya.command.CommandContext;
import net.toujoustudios.kazunya.command.CommandManager;
import net.toujoustudios.kazunya.command.ICommand;
import net.toujoustudios.kazunya.config.Config;
import net.toujoustudios.kazunya.error.ErrorEmbed;
import net.toujoustudios.kazunya.error.ErrorType;
import net.toujoustudios.kazunya.util.ColorUtil;

import java.util.ArrayList;
import java.util.List;

public class HelpCommand implements ICommand {

    private final CommandManager manager;
    private final Config config;

    public HelpCommand(CommandManager manager) {
        this.manager = manager;
        config = Config.getDefault();
    }

    @Override
    public void handle(CommandContext context) {

        List<OptionMapping> args = context.getArgs();
        EmbedBuilder embedBuilder = new EmbedBuilder();

        embedBuilder.setColor(ColorUtil.rgb(config.getString("format.color.default")));
        embedBuilder.setTitle(":sparkles: **Kazunya Help**");
        embedBuilder.setDescription("Here is a full list of all bot commands and features.\nYou can also type `/help [<command>]` to look up a specific command and its usage.");
        embedBuilder.setThumbnail(config.getString("assets.img.icon_search"));

        if(args.isEmpty()) {

            StringBuilder builderGeneral = new StringBuilder();
            StringBuilder builderRoleplay = new StringBuilder();
            StringBuilder builderFun = new StringBuilder();
            StringBuilder builderTools = new StringBuilder();

            for(ICommand command : manager.getCommands()) {
                if(command.category() == CommandCategory.GENERAL) {
                    builderGeneral.append("`/").append(command.name()).append("` - ").append(command.description()).append("\n");
                }
                if(command.category() == CommandCategory.ROLEPLAY) {
                    builderRoleplay.append("`/").append(command.name()).append("` - ").append(command.description()).append("\n");
                }
                if(command.category() == CommandCategory.FUN) {
                    builderFun.append("`/").append(command.name()).append("` - ").append(command.description()).append("\n");
                }
                if(command.category() == CommandCategory.TOOLS) {
                    builderTools.append("`/").append(command.name()).append("` - ").append(command.description()).append("\n");
                }
            }

            embedBuilder.addField(":satellite_orbital: General:", builderGeneral.toString(), false);
            embedBuilder.addField(":heart: Roleplay:", builderRoleplay.toString(), false);
            embedBuilder.addField(":sparkles: Fun:", builderFun.toString(), false);
            embedBuilder.addField(":hammer: Tools:", builderTools.toString(), false);
            embedBuilder.addField(":bookmark_tabs: Credits:", "IanToujou", false);

            context.interaction().replyEmbeds(embedBuilder.build())
                    .addComponents(ActionRow.of(Button.link(config.getString("link.help"), "Docs"), Button.link(config.getString("link.invite"), "Invite")))
                    .queue();
            return;

        }

        String search = args.getFirst().getAsString();
        ICommand command = manager.command(search);

        if(command == null) {
            ErrorEmbed.sendError(context, ErrorType.COMMAND_INVALID_SEARCH);
            return;
        }

        embedBuilder.setDescription("**Description:** " + command.description() + "\n**Usage:** `/" + command.syntax() + "`");
        context.interaction().replyEmbeds(embedBuilder.build()).queue();

    }

    @Override
    public String name() {
        return "help";
    }

    @Override
    public String description() {
        return "Shows a list of all the bot commands.";
    }

    @Override
    public String emoji() {
        return "💾";
    }

    @Override
    public List<OptionData> options() {
        List<OptionData> optionData = new ArrayList<>();
        optionData.add(new OptionData(OptionType.STRING, "command", "The command to get more information on.", false));
        return optionData;
    }

    @Override
    public CommandCategory category() {
        return CommandCategory.GENERAL;
    }

}
