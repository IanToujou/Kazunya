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
 * Date: 05/11/2021
 * Time: 22:21
 */
public class KillCommand implements ICommand {

    private final Config config;

    public KillCommand() {
        config = Config.getDefault();
    }

    @Override
    public void handle(CommandContext context) {

        List<OptionMapping> args = context.getArgs();
        Member member = context.getMember();
        EmbedBuilder embedBuilder = new EmbedBuilder();

        if (args.size() == 0) {
            ErrorEmbed.sendError(context, ErrorType.COMMAND_INVALID_SYNTAX);
            return;
        }

        Member target = args.get(0).getAsMember();

        ArrayList<String> images = new ArrayList<>();
        images.add("https://c.tenor.com/hDFU7nFDFhcAAAAd/wasted-anime.gif");
        images.add("https://c.tenor.com/Re9dglY0sCwAAAAC/anime-wasted.gif");
        images.add("https://c.tenor.com/FJmJM5jRVp4AAAAd/wasted-anime.gif");
        images.add("https://c.tenor.com/PJbU0yjG3BUAAAAd/anime-girl.gif");

        ArrayList<String> messages = new ArrayList<>();
        messages.add("{Victim} got killed by {Killer}!");
        messages.add("{Killer} used a nuke on {Victim}!");
        messages.add("{Victim} stepped on a landmine and died.");
        messages.add("{Killer} hugged {Victim} a little too hard...");
        messages.add("");

        assert target != null;
        embedBuilder.setTitle("**:skull: Kill**");
        embedBuilder.setDescription(messages.get(new Random().nextInt(messages.size())).replace("{Victim}", target.getAsMention()).replace("{Killer}", member.getAsMention()));
        embedBuilder.setImage(images.get(new Random().nextInt(images.size())));
        embedBuilder.setColor(ColorTools.getFromRGBString(config.getString("format.color.default")));
        context.getEvent().replyEmbeds(embedBuilder.build()).queue();

    }

    @Override
    public String getName() {
        return "kill";
    }

    @Override
    public String getDescription() {
        return "Kill someone else and commit a very bad thing.";
    }

    @Override
    public List<OptionData> getOptions() {
        List<OptionData> optionData = new ArrayList<>();
        optionData.add(new OptionData(OptionType.USER, "user", "The person you want to kill.", true));
        return optionData;
    }

    @Override
    public CommandCategory getCategory() {
        return CommandCategory.ROLEPLAY;
    }

}
