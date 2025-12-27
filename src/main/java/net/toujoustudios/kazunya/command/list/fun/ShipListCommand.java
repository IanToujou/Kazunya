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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShipListCommand implements ICommand {

    private final Config config;

    public ShipListCommand() {
        config = Config.getDefault();
    }

    @Override
    public void handle(CommandContext context) {

        List<OptionMapping> args = context.getArgs();
        EmbedBuilder embedBuilder = new EmbedBuilder();

        if(args.size() != 1) {
            ErrorEmbed.sendError(context, ErrorType.COMMAND_INVALID_SYNTAX);
            return;
        }

        Member member = args.getFirst().getAsMember();
        if (member == null) return;

        String id = member.getId();
        HashMap<Member, Integer> ratings = new HashMap<>();

        context.guild().loadMembers().onSuccess(members -> {

            members.forEach(m -> {
                if (m.getId().equals(member.getId())) return;
                int rating;
                String mId = m.getId();
                int num1 = Integer.parseInt(id.substring(id.length() - 2));
                int num2 = Integer.parseInt(mId.substring(mId.length() - 2));
                rating = num1 + num2;
                if (rating > 100) rating -= 100;
                ratings.put(m, rating);
            });

            embedBuilder.setThumbnail(member.getAvatarUrl());
            embedBuilder.setTitle("**:purple_heart: Ship Rankings**");

            StringBuilder builder = new StringBuilder();
            ratings.entrySet().stream()
                    .sorted(Map.Entry.<Member, Integer>comparingByValue().reversed())
                    .limit(10)
                    .forEach(entry -> {
                        builder.append("\n");
                        if (entry.getValue() == 100) builder.append(":ring:");
                        else if (entry.getValue() >= 90) builder.append(":heart:");
                        else if (entry.getValue() >= 80) builder.append(":heartpulse:");
                        else if (entry.getValue() >= 70) builder.append(":pink_heart:");
                        else if (entry.getValue() == 69) builder.append(":thumbsup:");
                        else if (entry.getValue() >= 50) builder.append(":green_heart:");
                        else if (entry.getValue() >= 30) builder.append(":mending_heart:");
                        else builder.append(":broken_heart:");
                        builder.append(entry.getKey().getAsMention()).append(": **").append(entry.getValue()).append("%").append("**");
                    });

            embedBuilder.setDescription(member.getAsMention() + "'s ship rank list is:\n" + builder);
            embedBuilder.setColor(ColorUtil.rgb(config.getString("format.color.default")));
            context.interaction().replyEmbeds(embedBuilder.build()).queue();

        }).onError(Throwable::printStackTrace);

    }

    @Override
    public String name() {
        return "shiplist";
    }

    @Override
    public String description() {
        return "Show the most compatible ship ratings for one user.";
    }

    @Override
    public String emoji() {
        return "💞";
    }

    @Override
    public List<OptionData> options() {
        List<OptionData> optionData = new ArrayList<>();
        optionData.add(new OptionData(OptionType.USER, "user", "The user you want to see the rating for.", true));
        return optionData;
    }

    @Override
    public CommandCategory category() {
        return CommandCategory.FUN;
    }

}
