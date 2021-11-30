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
 * Date: 30/11/2021
 * Time: 06:38
 */
public class RollCommand implements ICommand {

    private final Config config;

    public RollCommand() {
        config = Config.getDefault();
    }

    @Override
    public void handle(CommandContext context) {

        List<OptionMapping> args = context.getArgs();
        Member member = context.getMember();
        EmbedBuilder embedBuilder = new EmbedBuilder();

        if (args.size() > 2) {
            ErrorEmbed.sendError(context, ErrorType.COMMAND_INVALID_SYNTAX);
            return;
        }

        int sides = 6;
        int offset = 0;

        if(args.size() > 0) sides = (int) args.get(0).getAsLong();
        if(args.size() > 1) offset = (int) args.get(1).getAsLong();

        if(sides <= 0 || sides >= 1000000) {
            ErrorEmbed.sendError(context, ErrorType.ACTION_DICE_SIDES_NOT_IN_RANGE);
            return;
        }

        if(offset <= -1000000 || offset >= 1000000) {
            ErrorEmbed.sendError(context, ErrorType.ACTION_DICE_OFFSET_NOT_IN_RANGE);
            return;
        }

        int random = new Random().nextInt(sides) + 1;
        random += offset;

        embedBuilder.setTitle("**:game_die: Dice Roll**");
        embedBuilder.setDescription("You rolled a dice!\nResult: `" + random + "`");
        embedBuilder.addField("Input", "Sides: `" + sides + "`\nOffset: `" + offset + "`", false);
        embedBuilder.setColor(ColorTools.getFromRGBString(config.getString("format.color.default")));
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
    public List<OptionData> getOptions() {
        List<OptionData> optionData = new ArrayList<>();
        optionData.add(new OptionData(OptionType.INTEGER, "sides", "The number of sides the dice has. Default is 6.", false));
        optionData.add(new OptionData(OptionType.INTEGER, "offset", "A number that should be added (or subtracted if negative) to the result.", false));
        return optionData;
    }

    @Override
    public CommandCategory getCategory() {
        return CommandCategory.FUN;
    }

}
