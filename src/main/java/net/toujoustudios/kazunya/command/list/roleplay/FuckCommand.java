package net.toujoustudios.kazunya.command.list.roleplay;

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
import java.util.List;
import java.util.Random;

/**
 * This file has been created by Ian Toujou.
 * Project: Kazunya
 * Date: 03/11/2021
 * Time: 15:47
 */
public class FuckCommand implements ICommand {

    private final Config config;

    public FuckCommand() {
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
        assert target != null;

        if (target.getId().equals(member.getId())) {
            ErrorEmbed.sendError(context, ErrorType.COMMAND_INVALID_USER_SELF);
            return;
        }

        ArrayList<String> images = new ArrayList<>();
        images.add("https://wimg.rule34.xxx//images/3591/835ceb4c8a166c81b585640ee7824eb6.gif");
        images.add("https://sexxxgif.com/content/2021/07/hentai-gif-67_001.gif");
        images.add("https://static.hentai-img.com/upload/20111002/6/5861/10.gif");
        images.add("https://uncensoredhentaigif.com/content/_gifs/hentai/sexcom/18280640_min.gif");
        images.add("http://x3vid.com/images/17112/https:__ep1.xhcdn.com_000_158_121_375_1000.gif");
        images.add("https://bobsvagene.club/wp-content/uploads/2018/05/uncensored-8465.gif");

        embedBuilder.setTitle("**:sweat_drops: Fucky Wucky**");
        embedBuilder.setDescription(member.getAsMention() + " is giving " + target.getAsMention() + " a sexy time! o//w//o");
        embedBuilder.setImage(images.get(new Random().nextInt(images.size())));
        embedBuilder.setColor(ColorUtil.getFromRGBString(config.getString("format.color.default")));
        context.getEvent().replyEmbeds(embedBuilder.build()).queue();

    }

    @Override
    public String getName() {
        return "fuck";
    }

    @Override
    public String getDescription() {
        return "Fuck another person.";
    }

    @Override
    public List<OptionData> getOptions() {
        List<OptionData> optionData = new ArrayList<>();
        optionData.add(new OptionData(OptionType.USER, "user", "The person you want to cuddle.", true));
        return optionData;
    }

    @Override
    public CommandCategory getCategory() {
        return CommandCategory.NSFW;
    }

}