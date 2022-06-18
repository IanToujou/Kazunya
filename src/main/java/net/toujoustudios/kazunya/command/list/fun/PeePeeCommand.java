package net.toujoustudios.kazunya.command.list.fun;

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

/**
 * This file has been created by Ian Toujou.
 * Project: Kazunya
 * Date: 01/05/2022
 * Time: 23:21
 */
public class PeePeeCommand implements ICommand {

    private final Config config;

    public PeePeeCommand() {
        this.config = Config.getDefault();
    }

    @Override
    public void handle(CommandContext context) {

        List<OptionMapping> args = context.getArgs();
        EmbedBuilder embedBuilder = new EmbedBuilder();
        Member member = context.getMember();

        if (args.size() == 1) {
            member = args.get(0).getAsMember();
        }

        if (member == null) {
            ErrorEmbed.sendError(context, ErrorType.COMMAND_INVALID_USER_NOT_FOUND);
            return;
        }

        String memberId = member.getId();
        int num = Integer.parseInt(memberId.substring(memberId.length() - 2));
        int size = Math.round(num) / 3;
        if (memberId.equals("369381378703228928")) size = 50;
        if (memberId.equals("453998477232570368")) size = 500;
        String pp = "8" + "=".repeat(Math.max(0, size)) + "D";

        String comment;

        if (size <= 1) {
            comment = ":knife: Oh no, did someone chop it off?! I can't see it.";
        } else if (size <= 5) {
            comment = ":microscope: Hold up. I need an electron microscope for this one.";
        } else if (size <= 10) {
            comment = ":arrow_down_small: Below average but still fine.";
        } else if (size <= 20) {
            comment = ":sparkles: Pretty average, very nice tip though. A solid 7/10. Now onto the taste test.";
        } else if (size <= 29) {
            comment = ":muscle: That's a nice one bro! A solid 9/10.";
        } else if (size >= 33) {
            comment = ":moyai: That one is max-size. What a gigachad.";
        } else {
            comment = ":flushed: This one is huuuuge...";
        }

        embedBuilder.setTitle("**:eggplant: PP Size**");
        embedBuilder.setColor(ColorUtil.getFromRGBString(config.getString("format.color.default")));
        embedBuilder.setDescription(member.getAsMention() + " has the following PP size:\n`" + pp + "`\n" + comment);

        context.getEvent().replyEmbeds(embedBuilder.build()).queue();

    }

    @Override
    public String getName() {
        return "pp";
    }

    @Override
    public String getDescription() {
        return "Shows the size of a persons pp.";
    }

    @Override
    public String getEmoji() {
        return "🍆";
    }

    @Override
    public List<OptionData> getOptions() {
        List<OptionData> optionData = new ArrayList<>();
        optionData.add(new OptionData(OptionType.USER, "user", "The user you want to get the pp size from.", false));
        return optionData;
    }

    @Override
    public CommandCategory getCategory() {
        return CommandCategory.FUN;
    }

}
