package net.toujoustudios.kazunya.command.list.roleplay;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import net.toujoustudios.kazunya.color.ColorTools;
import net.toujoustudios.kazunya.command.CommandCategory;
import net.toujoustudios.kazunya.command.CommandContext;
import net.toujoustudios.kazunya.command.ICommand;
import net.toujoustudios.kazunya.config.Config;
import net.toujoustudios.kazunya.error.ErrorEmbed;
import net.toujoustudios.kazunya.error.ErrorType;

import java.util.Collections;
import java.util.List;

/**
 * This file has been created by Ian Toujou.
 * Project: Kazunya
 * Date: 24/09/2021
 * Time: 08:13
 */
public class HugCommand implements ICommand {

    private final Config config;

    public HugCommand() {
        config = Config.getDefault();
    }

    @Override
    public void handle(CommandContext context) {

        List<OptionMapping> args = context.getArgs();
        Member member = context.getMember();
        EmbedBuilder embedBuilder = new EmbedBuilder();

        if (args.size() != 0) {
            ErrorEmbed.sendError(context, ErrorType.COMMAND_INVALID_SYNTAX);
            return;
        }
        
        Member target = args.get(0).getAsMember();
        assert target != null;

        //ArrayList<String> images = new ArrayList<>();
        //images.add("");

        embedBuilder.setTitle("**" + member.getNickname() + "is hugging" + target.getNickname() "!  :3");
        //embedBuilder.setImage(images.get(new Random().nextInt(images.size())));
        embedBuilder.setColor(ColorTools.getFromRGBString(config.getString("format.color.default")));
        context.getEvent().replyEmbeds(embedBuilder.build()).queue();

    }

    @Override
    public String getName() {
        return "hug";
    }

    @Override
    public String getDescription() {
        return "Hug another user.";
    }

    @Override
    public List<OptionData> getOptions() {
        return Collections.emptyList();
    }

    @Override
    public CommandCategory getCategory() {
        return CommandCategory.ROLEPLAY;
    }

}
