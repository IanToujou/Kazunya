package net.toujoustudios.kazunya.command.list.roleplay;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import net.toujoustudios.kazunya.command.CommandCategory;
import net.toujoustudios.kazunya.command.CommandContext;
import net.toujoustudios.kazunya.command.ICommand;
import net.toujoustudios.kazunya.config.Config;
import net.toujoustudios.kazunya.error.ErrorEmbed;
import net.toujoustudios.kazunya.error.ErrorType;
import net.toujoustudios.kazunya.util.ColorUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * This file has been created by Ian Toujou.
 * Project: Kazunya
 * Date: 05/11/2021
 * Time: 21:23
 */
public class TickleCommand implements ICommand {

    private final Config config;

    public TickleCommand() {
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
        images.add("https://c.tenor.com/PXL1ONAO9CEAAAAC/tickle-laugh.gif");
        images.add("https://c.tenor.com/9KCaFFBc_lkAAAAC/anime-tickle.gif");
        images.add("https://c.tenor.com/0UY84zQWda8AAAAC/laugh-droll.gif");
        images.add("https://c.tenor.com/3OduUUYND64AAAAd/bang-dream-bandori.gif");
        images.add("https://c.tenor.com/L5-ABrIwrksAAAAC/tickle-anime.gif");
        images.add("https://c.tenor.com/jT65qUu0eeEAAAAC/anime-boy.gif");
        images.add("https://c.tenor.com/lX4VUs86-q4AAAAC/ijiranaide-nagatoro-nagataro.gif");
        images.add("https://c.tenor.com/ymMtVnW-TrYAAAAd/nekopara-anime.gif");
        images.add("https://c.tenor.com/sa1QuA9GFaoAAAAC/anime-tickle.gif");

        embedBuilder.setTitle("**:yellow_heart: Tickle**");
        embedBuilder.setDescription("**" + member.getEffectiveName() + " tickles " + target.getAsMention() + "!** :3");
        embedBuilder.setImage(images.get(new Random().nextInt(images.size())));
        embedBuilder.setColor(ColorUtil.getFromRGBString(config.getString("format.color.default")));
        context.getEvent().replyEmbeds(embedBuilder.build()).queue();

    }

    @Override
    public String getName() {
        return "tickle";
    }

    @Override
    public String getDescription() {
        return "Tickle another user.";
    }

    @Override
    public List<OptionData> getOptions() {
        List<OptionData> optionData = new ArrayList<>();
        optionData.add(new OptionData(OptionType.USER, "user", "The person you want to tickle.", true));
        return optionData;
    }

    @Override
    public CommandCategory getCategory() {
        return CommandCategory.ROLEPLAY;
    }

}
