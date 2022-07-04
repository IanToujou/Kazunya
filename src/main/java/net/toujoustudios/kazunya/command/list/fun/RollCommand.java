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
import java.util.Random;

/**
 * This file has been created by Ian Toujou.
 * Project: Kazunya
 * Date: 30/11/2021
 * Time: 06:38
 */
public class RollCommand implements ICommand {

    private final Config config;

    public RollCommand() {
        config = Config.getDefault();
    }

    @Override
    @SuppressWarnings("all")
    public void handle(CommandContext context) {
        
        EmbedBuilder embedBuilder = new EmbedBuilder();

        int sides = 6;
        int times = 1;
        int offset = 0;

        if (context.getEvent().getOption("sides") != null) sides = (int) context.getEvent().getOption("sides").getAsDouble();
        if (context.getEvent().getOption("times") != null) times = (int) context.getEvent().getOption("times").getAsDouble();
        if (context.getEvent().getOption("offset") != null) offset = (int) context.getEvent().getOption("offset").getAsDouble();

        if (sides < 2 || sides > 1000) {
            ErrorEmbed.sendError(context, ErrorType.COMMAND_INVALID_RANGE);
            return;
        }

        if (offset < -1000 || offset > 1000) {
            ErrorEmbed.sendError(context, ErrorType.COMMAND_INVALID_RANGE);
            return;
        }

        if (times < 1 || times > 50) {
            ErrorEmbed.sendError(context, ErrorType.COMMAND_INVALID_RANGE);
            return;
        }

        int[] results = new int[times];

        for (int i = 0; i < times; i++) {
            int random = new Random().nextInt(sides) + 1;
            results[i] = random;
        }

        if (times == 1) {
            embedBuilder.setDescription("You rolled `1` dice!\nResult: `" + results[0] + "`");
            embedBuilder.addField("Input", "Sides: `" + sides + "`\nOffset: `" + offset + "`\nTimes: `" + times + "`", false);
        } else {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("You rolled `").append(times).append("` dice!\n");
            stringBuilder.append("Result: `(");

            int sum = 0;
            for (int i = 0; i < results.length; i++) {
                if (i != 0) stringBuilder.append(" + ");
                stringBuilder.append(results[i]);
                sum += results[i];
            }

            int finalResult = sum + offset;

            stringBuilder.append(") + ");
            stringBuilder.append(offset);
            stringBuilder.append(" = ");
            stringBuilder.append(finalResult);
            stringBuilder.append("`");

            embedBuilder.setDescription(stringBuilder.toString());
            embedBuilder.addField(":level_slider: Input", "Sides: `" + sides + "`\nOffset: `" + offset + "`\nTimes: `" + times + "`", false);
        }

        embedBuilder.setTitle("**:game_die: Dice Roll**");
        embedBuilder.setColor(ColorUtil.getFromRGBString(config.getString("format.color.default")));
        context.getEvent().replyEmbeds(embedBuilder.build()).queue();

    }

    @Override
    public String getName() {
        return "roll";
    }

    @Override
    public String getDescription() {
        return "Roll a dice and get a random result.";
    }

    @Override
    public String getEmoji() {
        return "🎲";
    }

    @Override
    public List<OptionData> getOptions() {
        List<OptionData> optionData = new ArrayList<>();
        optionData.add(new OptionData(OptionType.INTEGER, "sides", "The number of sides the dice has. Default is 6.", false));
        optionData.add(new OptionData(OptionType.INTEGER, "times", "The number of times that you want to roll the dice. Default is 1.", false));
        optionData.add(new OptionData(OptionType.INTEGER, "offset", "A number that should be added (or subtracted if negative) to the result.", false));
        return optionData;
    }

    @Override
    public CommandCategory getCategory() {
        return CommandCategory.FUN;
    }

}
