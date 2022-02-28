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
import net.toujoustudios.kazunya.error.ErrorEmbed;
import net.toujoustudios.kazunya.error.ErrorType;
import net.toujoustudios.kazunya.user.UserManager;
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

        if (args.size() != 1) {
            ErrorEmbed.sendError(context, ErrorType.COMMAND_INVALID_SYNTAX);
            return;
        }

        Member target = args.get(0).getAsMember();
        if (target == null) return;
        User targetUser = target.getUser();
        UserManager targetManager = UserManager.getUser(targetUser.getId());

        embedBuilder.setTitle("**:frame_photo: Avatar**");
        embedBuilder.setDescription("Here is " + targetUser.getAsMention() + "'s avatar. It looks very cool! :3");
        embedBuilder.setImage(targetUser.getEffectiveAvatarUrl());

        embedBuilder.setColor(ColorUtil.getFromRGBString(config.getString("format.color.default")));
        context.getEvent().replyEmbeds(embedBuilder.build()).queue();

    }

    @Override
    public String getName() {
        return "userinfo";
    }

    @Override
    public String getDescription() {
        return "Displays general information about a discord user.";
    }

    @Override
    public List<OptionData> getOptions() {
        List<OptionData> optionData = new ArrayList<>();
        optionData.add(new OptionData(OptionType.USER, "user", "The user you want to get information on.", true));
        return optionData;
    }

    @Override
    public CommandCategory getCategory() {
        return CommandCategory.TOOLS;
    }
}
