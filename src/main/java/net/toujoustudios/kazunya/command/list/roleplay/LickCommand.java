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
 * Date: 05/11/2021
 * Time: 21:39
 */
public class LickCommand implements ICommand {

    private final Config config;

    public LickCommand() {
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
        images.add("https://c.tenor.com/uw6-q_y4xKsAAAAd/%D0%B0%D0%BD%D0%B8%D0%BC%D0%B5-darling-in-the-franxx.gif");
        images.add("https://c.tenor.com/Yo1IUz2KJy0AAAAC/loli-lick.gif");
        images.add("https://c.tenor.com/5FOgNEcoaYMAAAAC/neck-kisses.gif");
        images.add("https://c.tenor.com/S5I9g4dPRn4AAAAC/omamori-himari-manga.gif");
        images.add("https://c.tenor.com/q8oaLRMnoq8AAAAC/lick-tongue.gif");
        images.add("https://c.tenor.com/rWtIltahEoAAAAAC/kawaii-lick.gif");
        images.add("https://c.tenor.com/0LMxPQdFBKAAAAAC/nekopara-kiss.gif");
        images.add("https://c.tenor.com/Pb1JPfqXpAIAAAAC/lick-licky.gif");
        images.add("https://c.tenor.com/XBs2LbC5QFcAAAAC/t34-squid-game.gif");
        images.add("https://c.tenor.com/zIU_JbsnMQ8AAAAC/zatch-bell-golden-gash.gif");
        images.add("https://c.tenor.com/WvjCp611y9wAAAAC/lick-intimate.gif");
        images.add("https://c.tenor.com/Qaj69b3i3-sAAAAd/hatsune-miku-lick.gif");
        images.add("https://c.tenor.com/YD8a9VOLYcoAAAAd/anime-girl.gif");
        images.add("https://c.tenor.com/eWkQ7-1c4qcAAAAC/anime-lovecraft.gif");
        images.add("https://c.tenor.com/Xb1u2Z6nLRQAAAAC/lick-anime.gif");

        embedBuilder.setTitle("**:sweat_drops: Lick**");
        embedBuilder.setDescription("**" + member.getEffectiveName() + " licks " + target.getAsMention() + "!** :3");
        embedBuilder.setImage(images.get(new Random().nextInt(images.size())));
        embedBuilder.setColor(ColorTools.getFromRGBString(config.getString("format.color.default")));
        context.getEvent().replyEmbeds(embedBuilder.build()).queue();

    }

    @Override
    public String getName() {
        return "lick";
    }

    @Override
    public String getDescription() {
        return "Lick another user.";
    }

    @Override
    public List<OptionData> getOptions() {
        List<OptionData> optionData = new ArrayList<>();
        optionData.add(new OptionData(OptionType.USER, "user", "The person you want to lick.", true));
        return optionData;
    }

    @Override
    public CommandCategory getCategory() {
        return CommandCategory.ROLEPLAY;
    }

}
