package net.toujoustudios.kazunya.command.list.roleplay;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import net.toujoustudios.kazunya.color.ColorTools;
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
 * Date: 03/11/2021
 * Time: 14:55
 */
public class KissCommand implements ICommand {

    private final Config config;

    public KissCommand() {
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
        images.add("https://c.tenor.com/F02Ep3b2jJgAAAAC/cute-kawai.gif");
        images.add("https://c.tenor.com/D5H2Hp89wwsAAAAd/kiss-cute.gif");
        images.add("https://c.tenor.com/I8kWjuAtX-QAAAAC/anime-ano.gif");
        images.add("https://c.tenor.com/dJU8aKmPKAgAAAAd/anime-kiss.gif");
        images.add("https://c.tenor.com/G954PGQ7OX8AAAAd/cute-urara-shiraishi-anime.gif");
        images.add("https://c.tenor.com/TWbZjCy8iN4AAAAC/girl-anime.gif");
        images.add("https://c.tenor.com/7T1cuiOtJvQAAAAC/anime-kiss.gif");
        images.add("https://c.tenor.com/JQ9jjb_JTqEAAAAC/anime-kiss.gif");
        images.add("https://c.tenor.com/GKgJqkC7yGAAAAAC/kiss-anime.gif");
        images.add("https://c.tenor.com/ErAPuiWY46QAAAAC/kiss-anime.gif");
        images.add("https://c.tenor.com/uZbyY_n5VZoAAAAC/kiss-anime.gif");
        images.add("https://c.tenor.com/wDYWzpOTKgQAAAAC/anime-kiss.gif");
        images.add("https://c.tenor.com/etSTc3aWspcAAAAC/yuri-kiss.gif");
        images.add("https://c.tenor.com/AtcFtesvEcEAAAAd/kissing-anime.gif");
        images.add("https://c.tenor.com/03wlqWILqpEAAAAC/highschool-dxd-asia.gif");
        images.add("https://c.tenor.com/V0nBQduEYb8AAAAC/anime-kiss-making-out.gif");
        images.add("https://c.tenor.com/4ofp_xCUBxcAAAAC/eden-of-the-east-akira-takizawa.gif");
        images.add("https://c.tenor.com/9vycr5sUYBMAAAAC/eden-of-the-east-anime.gif");
        images.add("https://c.tenor.com/VTvkMN6P648AAAAC/anime-kiss.gif");
        images.add("https://c.tenor.com/e6cYiAPPCq4AAAAC/anime-kissing.gif");
        images.add("https://c.tenor.com/SqpFZQfcyEgAAAAC/anime-kiss.gif");
        images.add("https://c.tenor.com/dp6A2wF5EKYAAAAC/anime-love.gif");
        images.add("https://c.tenor.com/hK8IUmweJWAAAAAC/kiss-me-%D0%BB%D1%8E%D0%B1%D0%BB%D1%8E.gif");
        images.add("https://c.tenor.com/3zdH2jC6qCcAAAAC/love-anime.gif");
        images.add("https://c.tenor.com/0mdCwkmGD1oAAAAC/kiss-love.gif");
        images.add("https://c.tenor.com/MXAKtm5-klUAAAAC/love-anime.gif");
        images.add("https://c.tenor.com/Ze6FyEgy4WAAAAAC/kiss-anime.gif");
        images.add("https://c.tenor.com/3wE3JNW0fswAAAAC/anime-kiss-love.gif");
        images.add("https://c.tenor.com/lA5I6yI1qogAAAAC/kuzu-no-honkai-kissing.gif");
        images.add("https://c.tenor.com/TnjL6WcdkkwAAAAd/anime-kiss.gif");
        images.add("https://c.tenor.com/9rN8nw7pVcEAAAAC/anime-kiss.gif");
        images.add("https://c.tenor.com/HgV0doOr_YoAAAAC/golden-time-anime.gif");
        images.add("https://c.tenor.com/YTsHLAJdOT4AAAAC/anime-kiss.gif");
        images.add("https://c.tenor.com/hjhEoMvMTOIAAAAC/girls-anime.gif");
        images.add("https://c.tenor.com/h9A4bnxJys8AAAAC/cheek-kiss.gif");
        images.add("https://c.tenor.com/hc2ZCXLcH5AAAAAC/hakuoki-hakuouki.gif");
        images.add("https://c.tenor.com/Yu2a1FmxFK8AAAAC/love-sweet.gif");
        images.add("https://c.tenor.com/hayBS0l13xcAAAAd/anime-kiss.gif");
        images.add("https://c.tenor.com/VxNAxTOeT3YAAAAC/anime-kiss.gif");

        embedBuilder.setTitle("**:heart: Kiss**");
        embedBuilder.setDescription(member.getAsMention() + " gives " + target.getAsMention() + " a kiss! Nawww~");
        embedBuilder.setImage(images.get(new Random().nextInt(images.size())));
        embedBuilder.setColor(ColorTools.getFromRGBString(config.getString("format.color.default")));
        context.getEvent().replyEmbeds(embedBuilder.build()).queue();

    }

    @Override
    public String getName() {
        return "kiss";
    }

    @Override
    public String getDescription() {
        return "Kiss another person.";
    }

    @Override
    public List<OptionData> getOptions() {
        List<OptionData> optionData = new ArrayList<>();
        optionData.add(new OptionData(OptionType.USER, "user", "The person you want to kiss.", true));
        return optionData;
    }

    @Override
    public CommandCategory getCategory() {
        return CommandCategory.ROLEPLAY;
    }

}
