package net.toujoustudios.kazunya.command.list.roleplay;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import net.toujoustudios.kazunya.util.ColorUtil;
import net.toujoustudios.kazunya.command.CommandCategory;
import net.toujoustudios.kazunya.command.CommandContext;
import net.toujoustudios.kazunya.command.ICommand;
import net.toujoustudios.kazunya.config.Config;
import net.toujoustudios.kazunya.error.ErrorEmbed;
import net.toujoustudios.kazunya.error.ErrorType;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * This file has been created by Ian Toujou.
 * Project: Kazunya
 * Date: 14/10/2021
 * Time: 09:20
 */
public class CuddleCommand implements ICommand {

    private final Config config;

    public CuddleCommand() {
        config = Config.getDefault();
    }

    @Override
    public void handle(CommandContext context) {

        List<OptionMapping> args = context.getArgs();
        Member member = context.getMember();
        EmbedBuilder embedBuilder = new EmbedBuilder();

        if (args.size() == 0) {
            ErrorEmbed.sendError(context, ErrorType.COMMAND_INVALID_SYNTAX);
            return;
        }

        Member target = args.get(0).getAsMember();
        assert target != null;

        if (target.getId().equals(member.getId())) {
            ErrorEmbed.sendError(context, ErrorType.COMMAND_INVALID_USER_SELF);
            return;
        }

        ArrayList<String> images = new ArrayList<>();
        images.add("https://c.tenor.com/08vDStcjoGAAAAAd/cuddle-anime-hug-anime.gif");
        images.add("https://c.tenor.com/dbIbtIyByEwAAAAC/cuddle-anime.gif");
        images.add("https://c.tenor.com/BmbTYhCZ5UsAAAAC/yuri-sleeping-yuri-sleep.gif");
        images.add("https://c.tenor.com/okeP090NK1cAAAAC/anime-couples.gif");
        images.add("https://c.tenor.com/sK9icjg3xm4AAAAC/strugglesnuggle-annoyed.gif");
        images.add("https://c.tenor.com/ItpTQW2UKPYAAAAC/cuddle-hug.gif");
        images.add("https://c.tenor.com/ch1kq7TOxlkAAAAC/anime-cuddle.gif");
        images.add("https://c.tenor.com/hsnYxyxQbRoAAAAC/hug-anime.gif");
        images.add("https://c.tenor.com/0PIj7XctFr4AAAAC/a-whisker-away-hug.gif");
        images.add("https://c.tenor.com/X54vC9bzK6MAAAAC/cute-cuddle.gif");
        images.add("https://c.tenor.com/fklZNDaU9NMAAAAC/hideri-hideri-kanzaki.gif");
        images.add("https://c.tenor.com/wwd7R-pi7DIAAAAC/anime-cuddle.gif");
        images.add("https://c.tenor.com/DVOTqLcB2jUAAAAC/anime-hug-love.gif");
        images.add("https://c.tenor.com/GuHHnDT6quYAAAAd/anime-couples.gif");
        images.add("https://c.tenor.com/gowinK__PvAAAAAC/anime-cuddle.gif");
        images.add("https://c.tenor.com/ABT86RpJUMUAAAAC/love-asuna.gif");
        images.add("https://c.tenor.com/A5ZuMAZ44l8AAAAC/anime-cuddle.gif");
        images.add("https://c.tenor.com/sBFE3GeNpJ4AAAAC/tackle-hug-couple.gif");
        images.add("https://c.tenor.com/5UwhB5xQSTEAAAAC/anime-hug.gif");
        images.add("https://c.tenor.com/0gCYBXXb2toAAAAC/hug-hugs-and-love.gif");

        embedBuilder.setTitle("**:purple_heart: Cuddles**");
        embedBuilder.setDescription(member.getAsMention() + " cuddles " + target.getAsMention() + "! :3");
        embedBuilder.setImage(images.get(new Random().nextInt(images.size())));
        embedBuilder.setColor(ColorUtil.getFromRGBString(config.getString("format.color.default")));
        context.getEvent().replyEmbeds(embedBuilder.build()).queue();

    }

    @Override
    public String getName() {
        return "cuddle";
    }

    @Override
    public String getDescription() {
        return "Cuddle another person.";
    }

    @Override
    public List<OptionData> getOptions() {
        List<OptionData> optionData = new ArrayList<>();
        optionData.add(new OptionData(OptionType.USER, "user", "The person you want to cuddle.", true));
        return optionData;
    }

    @Override
    public CommandCategory getCategory() {
        return CommandCategory.ROLEPLAY;
    }

}