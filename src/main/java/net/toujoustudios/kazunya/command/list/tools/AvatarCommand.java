package net.toujoustudios.kazunya.command.list.tools;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import net.toujoustudios.kazunya.command.CommandCategory;
import net.toujoustudios.kazunya.command.CommandContext;
import net.toujoustudios.kazunya.command.ICommand;
import net.toujoustudios.kazunya.config.Config;
import net.toujoustudios.kazunya.util.ColorUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * This file has been created by Ian Toujou.
 * Project: Kazunya
 * Date: 28/02/2022
 * Time: 06:42
 */
public class AvatarCommand implements ICommand {

    private final Config config;

    public AvatarCommand() {
        config = Config.getDefault();
    }

    @Override
    public void handle(CommandContext context) {

        List<OptionMapping> args = context.getArgs();
        EmbedBuilder embedBuilder = new EmbedBuilder();

        Member target = args.get(0).getAsMember();
        if (target == null) return;
        User targetUser = target.getUser();

        embedBuilder.setTitle("**:frame_photo: Avatar**");
        embedBuilder.setDescription("Here is " + targetUser.getAsMention() + "'s Discord avatar. It looks very cool! :3");
        embedBuilder.setImage(targetUser.getEffectiveAvatarUrl() + "?size=4096");

        embedBuilder.setColor(ColorUtil.getFromRGBString(config.getString("format.color.default")));
        context.getEvent().replyEmbeds(embedBuilder.build()).queue();

    }

    @Override
    public String getName() {
        return "avatar";
    }

    @Override
    public String getDescription() {
        return "Displays the user's Discord avatar.";
    }

    @Override
    public String getEmoji() {
        return "🪟";
    }

    @Override
    public List<OptionData> getOptions() {
        List<OptionData> optionData = new ArrayList<>();
        optionData.add(new OptionData(OptionType.USER, "user", "The user you want to get the Discord avatar from.", true));
        return optionData;
    }

    @Override
    public CommandCategory getCategory() {
        return CommandCategory.TOOLS;
    }
}
