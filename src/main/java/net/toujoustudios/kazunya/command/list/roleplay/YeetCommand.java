package net.toujoustudios.kazunya.command.list.roleplay;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
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

public class YeetCommand extends ListenerAdapter implements ICommand {

    private final Config config = Config.getDefault();

    @Override
    public void handle(CommandContext context) {

        List<OptionMapping> args = context.getArgs();
        Member member = context.member();
        EmbedBuilder embedBuilder = new EmbedBuilder();

        Member target = args.getFirst().getAsMember();
        assert target != null;

        if(target.getId().equals(member.getId())) {
            ErrorEmbed.sendError(context, ErrorType.COMMAND_INVALID_USER_SELF);
            return;
        }

        List<String> images = config.getStringList("gif.command.yeet");

        embedBuilder.setTitle("**:man_golfing: Yeet**");
        embedBuilder.setDescription(member.getAsMention() + " yeets " + target.getAsMention() + " away! Oh nooo~");
        embedBuilder.setImage(images.get(new Random().nextInt(images.size())));
        embedBuilder.setColor(ColorUtil.rgb(config.getString("format.color.default")));
        context.interaction().reply(target.getAsMention()).addEmbeds(embedBuilder.build()).queue();

    }

    @Override
    public String name() {
        return "yeet";
    }

    @Override
    public String description() {
        return "Yeet someone else on the server.";
    }

    @Override
    public String emoji() {
        return "🏌️";
    }

    @Override
    public List<OptionData> options() {
        List<OptionData> optionData = new ArrayList<>();
        optionData.add(new OptionData(OptionType.USER, "user", "The person you want to yeet.", true));
        return optionData;
    }

    @Override
    public CommandCategory category() {
        return CommandCategory.ROLEPLAY;
    }

}
