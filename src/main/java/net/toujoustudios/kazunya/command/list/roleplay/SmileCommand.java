package net.toujoustudios.kazunya.command.list.roleplay;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import net.toujoustudios.kazunya.command.CommandCategory;
import net.toujoustudios.kazunya.command.CommandContext;
import net.toujoustudios.kazunya.command.ICommand;
import net.toujoustudios.kazunya.config.Config;
import net.toujoustudios.kazunya.util.ColorUtil;

import java.util.Collections;
import java.util.List;
import java.util.Random;

public class SmileCommand implements ICommand {

    private final Config config;

    public SmileCommand() {
        config = Config.getDefault();
    }

    @Override
    public void handle(CommandContext context) {

        Member member = context.member();
        EmbedBuilder embedBuilder = new EmbedBuilder();

        List<String> images = config.getStringList("gif.command.smile");

        embedBuilder.setTitle("**:purple_heart: Smile**");
        embedBuilder.setDescription(member.getAsMention() + " is smiling! Yay~");
        embedBuilder.setImage(images.get(new Random().nextInt(images.size())));
        embedBuilder.setColor(ColorUtil.rgb(config.getString("format.color.default")));
        context.interaction().replyEmbeds(embedBuilder.build()).queue();

    }

    @Override
    public String name() {
        return "smile";
    }

    @Override
    public String description() {
        return "Make yourself smile.";
    }

    @Override
    public String emoji() {
        return "😁";
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
