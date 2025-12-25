package net.toujoustudios.kazunya.command.list.roleplay;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import net.toujoustudios.kazunya.command.CommandCategory;
import net.toujoustudios.kazunya.command.CommandContext;
import net.toujoustudios.kazunya.command.ICommand;
import net.toujoustudios.kazunya.config.Config;
import net.toujoustudios.kazunya.error.ErrorEmbed;
import net.toujoustudios.kazunya.error.ErrorType;
import net.toujoustudios.kazunya.util.ColorUtil;

import java.util.Collections;
import java.util.List;
import java.util.Random;

public class PurrCommand implements ICommand {

    private final Config config;

    public PurrCommand() {
        config = Config.getDefault();
    }

    @Override
    public void handle(CommandContext context) {

        List<OptionMapping> args = context.getArgs();
        Member member = context.getMember();
        EmbedBuilder embedBuilder = new EmbedBuilder();

        if(!args.isEmpty()) {
            ErrorEmbed.sendError(context, ErrorType.COMMAND_INVALID_SYNTAX);
            return;
        }

        List<String> images = config.getStringList("gif.command.purr");

        embedBuilder.setTitle("**:purple_heart: Purr**");
        embedBuilder.setDescription(member.getAsMention() + " purrs! Meow~");
        embedBuilder.setImage(images.get(new Random().nextInt(images.size())));
        embedBuilder.setColor(ColorUtil.rgb(config.getString("format.color.default")));
        context.getInteraction().replyEmbeds(embedBuilder.build()).queue();

    }

    @Override
    public String name() {
        return "purr";
    }

    @Override
    public String description() {
        return "Make yourself purr like a cat.";
    }

    @Override
    public String emoji() {
        return "😺";
    }

    @Override
    public List<OptionData> options() {
        return Collections.emptyList();
    }

    @Override
    public CommandCategory category() {
        return CommandCategory.ROLEPLAY;
    }

}
