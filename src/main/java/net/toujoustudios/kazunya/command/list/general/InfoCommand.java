package net.toujoustudios.kazunya.command.list.general;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import net.dv8tion.jda.api.interactions.components.Button;
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

/**
 * This file has been created by Ian Toujou.
 * Project: Kazunya
 * Date: 28/02/2022
 * Time: 23:09
 */
public class HelpCommand implements ICommand {

    private final CommandManager manager;
    private final Config config;

    @Override
    public void handle(CommandContext context) {

        List<OptionMapping> args = context.getArgs();
        EmbedBuilder embedBuilder = new EmbedBuilder();

        embedBuilder.setColor(ColorUtil.getFromRGBString(config.getString("format.color.default")));
        embedBuilder.setTitle(":information: **Kazunya Help**");
        embedBuilder.setDescription("Here are all the informations you need to know about the **Kazunya Bot**");
        embedBuilder.setThumbnail(config.getString("assets.img.icon_information"));
        context.getEvent().replyEmbeds(embedBuilder.build()).queue();

    }

    @Override
    public String getName() {
        return "info";
    }

    @Override
    public String getDescription() {
        return "Get general indormation about the bot.";
    }

    @Override
    public List<OptionData> getOptions() {
        return Collections.emptyList();
    }

    @Override
    public CommandCategory getCategory() {
        return CommandCategory.GENERAL;
    }

}