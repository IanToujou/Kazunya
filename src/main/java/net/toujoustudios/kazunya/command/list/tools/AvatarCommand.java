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

public class AvatarCommand implements ICommand {

    private final Config config;

    public AvatarCommand() {
        config = Config.getDefault();
    }

    @Override
    public void handle(CommandContext context) {

        List<OptionMapping> args = context.getArgs();
        EmbedBuilder embedBuilder = new EmbedBuilder();

        Member target = args.getFirst().getAsMember();
        if(target == null) return;
        User targetUser = target.getUser();

        embedBuilder.setTitle("**:frame_photo: Avatar**");
        embedBuilder.setDescription("Here is " + targetUser.getAsMention() + "'s Discord avatar. It looks very cool! :3");
        embedBuilder.setImage(targetUser.getEffectiveAvatarUrl() + "?size=4096");

        embedBuilder.setColor(ColorUtil.rgb(config.getString("format.color.default")));
        context.interaction().replyEmbeds(embedBuilder.build()).queue();

    }

    @Override
    public String name() {
        return "avatar";
    }

    @Override
    public String description() {
        return "Displays the user's Discord avatar.";
    }

    @Override
    public String emoji() {
        return "🪟";
    }

    @Override
    public List<OptionData> options() {
        List<OptionData> optionData = new ArrayList<>();
        optionData.add(new OptionData(OptionType.USER, "user", "The user you want to get the Discord avatar from.", true));
        return optionData;
    }

    @Override
    public CommandCategory category() {
        return CommandCategory.TOOLS;
    }
}
