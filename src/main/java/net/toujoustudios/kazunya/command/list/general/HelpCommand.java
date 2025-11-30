package net.toujoustudios.kazunya.command.list.general;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.components.actionrow.ActionRow;
import net.dv8tion.jda.api.components.buttons.Button;
import net.dv8tion.jda.api.entities.Member;
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
    @SuppressWarnings("all")
    public void handle(CommandContext context) {

        List<OptionMapping> args = context.getArgs();
        EmbedBuilder embedBuilder = new EmbedBuilder();

        embedBuilder.setColor(ColorUtil.getFromRGBString(config.getString("format.color.default")));
        embedBuilder.setTitle(":sparkles: **Kazunya Help**");
        embedBuilder.setDescription("Here is a full list of all bot commands and features.\nYou can also type `/help [<command>]` to look up a specific command and its usage.");
        embedBuilder.setThumbnail(config.getString("assets.img.icon_search"));

        if(args.isEmpty()) {

            StringBuilder builderGeneral = new StringBuilder();
            StringBuilder builderRoleplay = new StringBuilder();
            StringBuilder builderFun = new StringBuilder();
            StringBuilder builderStats = new StringBuilder();
            StringBuilder builderTools = new StringBuilder();
            StringBuilder builderEconomy = new StringBuilder();
            StringBuilder builderNSFW = new StringBuilder();
            StringBuilder builderAdministration = new StringBuilder();

            for(ICommand command : manager.getCommands()) {
                if(command.getCategory() == CommandCategory.GENERAL) {
                    builderGeneral.append("`/" + command.getName() + "` - " + command.getDescription() + "\n");
                }
                if(command.getCategory() == CommandCategory.ROLEPLAY) {
                    builderRoleplay.append("`/" + command.getName() + "` - " + command.getDescription() + "\n");
                }
                if(command.getCategory() == CommandCategory.FUN) {
                    builderFun.append("`/" + command.getName() + "` - " + command.getDescription() + "\n");
                }
                if(command.getCategory() == CommandCategory.TOOLS) {
                    builderTools.append("`/" + command.getName() + "` - " + command.getDescription() + "\n");
                }
                if(command.getCategory() == CommandCategory.ECONOMY) {
                    builderEconomy.append("`/" + command.getName() + "` - " + command.getDescription() + "\n");
                }
                if(command.getCategory() == CommandCategory.NSFW) {
                    builderNSFW.append("`/" + command.getName() + "` - " + command.getDescription() + "\n");
                }
            }

            embedBuilder.addField(":satellite_orbital: General:", builderGeneral.toString(), false);
            embedBuilder.addField(":heart: Roleplay:", builderRoleplay.toString(), false);
            embedBuilder.addField(":sparkles: Fun:", builderFun.toString(), false);
            embedBuilder.addField(":hammer: Tools:", builderTools.toString(), false);
            embedBuilder.addField(":credit_card: Economy:", builderEconomy.toString(), false);
            if(context.getInteraction().getChannel().asTextChannel().isNSFW())
                embedBuilder.addField(":no_entry_sign: NSFW:", builderNSFW.toString(), false);
            if(!context.getMember().getId().equals(config.getString("user.admin")))
                embedBuilder.addField(":tools: Administration:", builderAdministration.toString(), false);

            boolean isOwnerOnServer = false;
            for(Member member : context.getGuild().getMembers()) {
                String id = member.getId();
                if(id.equals(config.getString("user.admin"))) {
                    isOwnerOnServer = true;
                    break;
                }
            }

            if(isOwnerOnServer) {
                embedBuilder.addField(":bookmark_tabs: Credits:", context.getGuild().getMemberById(config.getString("user.admin")).getAsMention() + " - Toujou Studios", false);
            } else {
                embedBuilder.addField(":bookmark_tabs: Credits:", "IanToujou - Toujou Studios", false);
            }

            context.getInteraction().replyEmbeds(embedBuilder.build())
                    .addComponents(ActionRow.of(Button.link(config.getString("link.help"), "Docs"), Button.link(config.getString("link.invite"), "Invite")))
                    .queue();
            return;

        }

        String search = args.get(0).getAsString();
        ICommand command = manager.getCommand(search);

        if(command == null) {
            ErrorEmbed.sendError(context, ErrorType.COMMAND_INVALID_SEARCH);
            return;
        }

        embedBuilder.setDescription("**Description:** " + command.getDescription() + "\n**Usage:** `/" + command.getSyntax() + "`");
        context.getInteraction().replyEmbeds(embedBuilder.build()).queue();

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
    public String getEmoji() {
        return "💾";
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
