package net.toujoustudios.kazunya.command.list.fun;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import net.toujoustudios.kazunya.command.CommandCategory;
import net.toujoustudios.kazunya.command.CommandContext;
import net.toujoustudios.kazunya.command.ICommand;
import net.toujoustudios.kazunya.error.ErrorEmbed;
import net.toujoustudios.kazunya.error.ErrorType;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ShipCommand implements ICommand {

    @Override
    @SuppressWarnings("all")
    public void handle(CommandContext context) {

        List<OptionMapping> args = context.getArgs();
        EmbedBuilder embedBuilder = new EmbedBuilder();

        if(args.size() != 2) {
            ErrorEmbed.sendError(context, ErrorType.COMMAND_INVALID_SYNTAX);
            return;
        }

        Member first = args.get(0).getAsMember();
        Member second = args.get(1).getAsMember();

        if(first == null || second == null) return;

        int rating = 0;
        String firstId = first.getId();
        String secondId = second.getId();
        int num1 = Integer.parseInt(firstId.substring(firstId.length() - 2));
        int num2 = Integer.parseInt(secondId.substring(secondId.length() - 2));
        rating = num1 + num2;
        if(rating > 100) rating -= 100;

        String firstName = first.getEffectiveName();
        String secondName = second.getEffectiveName();
        firstName = firstName.substring(0, firstName.length() / 2);
        secondName = secondName.substring(secondName.length() / 2);

        String shipName = firstName + secondName.toLowerCase();

        int avatarNumber = new Random().nextInt(2);

        embedBuilder.setThumbnail(args.get(avatarNumber).getAsUser().getAvatarUrl());

        String commentary = "";

        if(rating < 10) {

            commentary = "The relationship would be truly horrible...";
            embedBuilder.setColor(Color.RED);

        } else if(rating < 30) {

            commentary = "This wouldn't work that great...";
            embedBuilder.setColor(Color.ORANGE);

        } else if(rating < 50) {

            commentary = "It is fine.";
            embedBuilder.setColor(Color.YELLOW);

        } else if(rating == 69) {

            commentary = "69? Nice.";
            embedBuilder.setColor(Color.GREEN);

        } else if(rating < 70) {

            commentary = "This could work nya~";
            embedBuilder.setColor(Color.GREEN);

        } else if(rating < 90) {

            commentary = "This would be a wholesome relationship! Nya~";
            embedBuilder.setColor(Color.PINK);

        } else if(rating < 100) {

            commentary = "The two should instantly get married! Nyaaa~";
            embedBuilder.setColor(Color.MAGENTA);

        } else {

            commentary = "I WANT THEM TO GET MARRIED! NOW!";
            embedBuilder.setColor(Color.MAGENTA);

        }

        embedBuilder.setTitle("**:purple_heart: Ship Rating**");
        embedBuilder.setDescription(first.getAsMention() + " and " + second.getAsMention() + "'s ship results:\n" + commentary + "\n\n:chart_with_upwards_trend: Ship Rating: **" + rating + "%**\n:heart: Ship Name: **" + shipName + "**");
        context.interaction().replyEmbeds(embedBuilder.build()).queue();

    }

    @Override
    public String name() {
        return "ship";
    }

    @Override
    public String description() {
        return "Ship two users and get a compatibility score.";
    }

    @Override
    public String emoji() {
        return "💞";
    }

    @Override
    public List<OptionData> options() {
        List<OptionData> optionData = new ArrayList<>();
        optionData.add(new OptionData(OptionType.USER, "first-user", "The first user you want to ship with someone else.", true));
        optionData.add(new OptionData(OptionType.USER, "second-user", "The second user you want to ship with the first user.", true));
        return optionData;
    }

    @Override
    public CommandCategory category() {
        return CommandCategory.FUN;
    }

}
