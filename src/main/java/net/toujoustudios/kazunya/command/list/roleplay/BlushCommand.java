package net.toujoustudios.kazunya.command.list.roleplay;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import net.toujoustudios.kazunya.util.ColorUtil;
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
 * Date: 22/09/2021
 * Time: 06:41
 */
public class BlushCommand implements ICommand {

    private final Config config;

    public BlushCommand() {
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
        images.add("https://c.tenor.com/lDz32d62I0sAAAAC/aikatsu-stars.gif");
        images.add("https://c.tenor.com/T51BLj_Cj8cAAAAC/blush.gif");
        images.add("https://c.tenor.com/HBjElxuQjuIAAAAC/anime-blush.gif");
        images.add("https://c.tenor.com/KKLSi3AgXuoAAAAC/anime-girl.gif");
        images.add("https://c.tenor.com/HAWlr1X00Y8AAAAC/anime-love.gif");
        images.add("https://c.tenor.com/VrfSZUjiWn4AAAAC/shy-anime.gif");
        images.add("https://c.tenor.com/eBtzvs6BwisAAAAC/anime-girl.gif");
        images.add("https://c.tenor.com/cjFjOFwZODIAAAAC/anime-girl.gif");
        images.add("https://c.tenor.com/JhO1fYhvP14AAAAC/face-blush.gif");
        images.add("https://c.tenor.com/d3AEjdxSfawAAAAC/anime-blush.gif");
        images.add("https://c.tenor.com/QRtXsJdPJsoAAAAC/sad-eyes-anime.gif");
        images.add("https://c.tenor.com/M7wcdD0eujYAAAAd/anime-looking.gif");
        images.add("https://c.tenor.com/FHjoCc579pAAAAAC/blush-blushing.gif");
        images.add("https://c.tenor.com/wwxHnJqUNEMAAAAC/anime-blush.gif");
        images.add("https://c.tenor.com/bEes0xCurvMAAAAC/anime-blush-dizzy.gif");
        images.add("https://c.tenor.com/hCqcNUuWCf0AAAAC/blush-anime.gif");
        images.add("https://c.tenor.com/2cWyWrf4ucAAAAAC/cyan-hijirikawa-show-by-rock.gif");
        images.add("https://c.tenor.com/8-F6-nB1L84AAAAC/anime-blush.gif");
        images.add("https://c.tenor.com/4KLTabq5i8cAAAAC/bell-blush.gif");
        images.add("https://c.tenor.com/PYOlVtPLnwoAAAAC/the-quintessential-quintuplets.gif");
        images.add("https://c.tenor.com/QKuVXBYQ--sAAAAC/blushing-anime.gif");
        images.add("https://c.tenor.com/yY7EhaDIy8wAAAAd/girl-blush.gif");
        images.add("https://c.tenor.com/OmrEdg-gq0UAAAAC/anime-blushing.gif");
        images.add("https://c.tenor.com/4y2F6WB7Z9wAAAAC/tonikaku-kawaii-yuzaki-tsukasa.gif");
        images.add("https://c.tenor.com/Pyhna-FV8dwAAAAC/blush-kawaii.gif");

        embedBuilder.setTitle("**:purple_heart: Blush**");
        embedBuilder.setDescription(member.getAsMention() + " is blushing! :3");
        embedBuilder.setImage(images.get(new Random().nextInt(images.size())));
        embedBuilder.setColor(ColorUtil.getFromRGBString(config.getString("format.color.default")));
        context.getEvent().replyEmbeds(embedBuilder.build()).queue();

    }

    @Override
    public String getName() {
        return "blush";
    }

    @Override
    public String getDescription() {
        return "Make yourself blush.";
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
