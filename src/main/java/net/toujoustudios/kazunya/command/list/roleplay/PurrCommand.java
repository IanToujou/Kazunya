package net.toujoustudios.kazunya.command.list.roleplay;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import net.toujoustudios.kazunya.color.ColorTools;
import net.toujoustudios.kazunya.command.CommandCategory;
import net.toujoustudios.kazunya.command.CommandContext;
import net.toujoustudios.kazunya.command.ICommand;
import net.toujoustudios.kazunya.config.Config;
import net.toujoustudios.kazunya.error.ErrorEmbed;
import net.toujoustudios.kazunya.error.ErrorType;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * This file has been created by Ian Toujou.
 * Project: Kazunya
 * Date: 05/11/2021
 * Time: 21:44
 */
public class PurrCommand implements ICommand {

    private final Config config;

    public PurrCommand() {
        config = Config.getDefault();
    }

    @Override
    public void handle(CommandContext context) {

        List<OptionMapping> args = context.getArgs();
        Member member = context.getMember();
        EmbedBuilder embedBuilder = new EmbedBuilder();

        if (args.size() != 0) {
            ErrorEmbed.sendError(context, ErrorType.COMMAND_INVALID_SYNTAX);
            return;
        }

        ArrayList<String> images = new ArrayList<>();
        images.add("https://c.tenor.com/gWf8TwO0BKcAAAAC/purr-kitten.gif");
        images.add("https://c.tenor.com/LhGIR486YogAAAAC/neko-anime.gif");
        images.add("https://c.tenor.com/G57GjEpd4E8AAAAC/nya-leateq.gif");
        images.add("https://c.tenor.com/dkX3A1YEDSwAAAAC/shy-anime.gif");
        images.add("https://c.tenor.com/gjavgtxeEjQAAAAC/chiya-anime.gif");
        images.add("https://c.tenor.com/V6dzSIT-iEcAAAAC/kawaii-neko.gif");
        images.add("https://c.tenor.com/O6r43C_Pb0gAAAAd/neko-nekomimi.gif");
        images.add("https://c.tenor.com/agReLT91Aw8AAAAC/nyan-nya.gif");

        embedBuilder.setTitle("**:purple_heart: Purr**");
        embedBuilder.setDescription("**" + member.getEffectiveName() + "** purrs! Meow~");
        embedBuilder.setImage(images.get(new Random().nextInt(images.size())));
        embedBuilder.setColor(ColorTools.getFromRGBString(config.getString("format.color.default")));
        context.getEvent().replyEmbeds(embedBuilder.build()).queue();

    }

    @Override
    public String getName() {
        return "purr";
    }

    @Override
    public String getDescription() {
        return "Make yourself purr like a cat.";
    }

    @Override
    public List<OptionData> getOptions() {
        return Collections.emptyList();
    }

    @Override
    public CommandCategory getCategory() {
        return CommandCategory.ROLEPLAY;
    }

}
