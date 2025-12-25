package net.toujoustudios.kazunya.command.list.general;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.components.actionrow.ActionRow;
import net.dv8tion.jda.api.components.buttons.Button;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import net.toujoustudios.kazunya.command.CommandCategory;
import net.toujoustudios.kazunya.command.CommandContext;
import net.toujoustudios.kazunya.command.ICommand;
import net.toujoustudios.kazunya.config.Config;
import net.toujoustudios.kazunya.util.ColorUtil;

import java.util.Collections;
import java.util.List;

public class InfoCommand implements ICommand {

    private final Config config;

    public InfoCommand() {
        config = Config.getDefault();
    }

    @Override
    public void handle(CommandContext context) {

        EmbedBuilder embedBuilder = new EmbedBuilder();

        embedBuilder.setColor(ColorUtil.rgb(config.getString("format.color.default")));
        embedBuilder.setTitle(":purple_heart: **Kazunya Information**");
        embedBuilder.setDescription("Here is all the information you need to know about the **Kazunya Bot**.");
        embedBuilder.addField(":information_source: Description:", "Kazunya is a general purpose bot that mainly focuses on roleplay and Anime!", false);
        embedBuilder.addField(":test_tube: Development:", "**GitHub:** https://github.com/IanToujou/Kazunya\n**Credits:** Made by `Toujou Studios`", false);
        embedBuilder.setThumbnail(config.getString("assets.img.icon_information"));
        context.getInteraction().replyEmbeds(embedBuilder.build()).addComponents(ActionRow.of(Button.link(config.getString("link.invite"), "Invite"))).queue();

    }

    @Override
    public String name() {
        return "info";
    }

    @Override
    public String description() {
        return "Get general information about the bot.";
    }

    @Override
    public String emoji() {
        return "💜";
    }

    @Override
    public List<OptionData> options() {
        return Collections.emptyList();
    }

    @Override
    public CommandCategory category() {
        return CommandCategory.GENERAL;
    }

}