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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * This file has been created by Ian Toujou.
 * Project: Kazunya
 * Date: 02/11/2021
 * Time: 17:36
 */
public class CryCommand implements ICommand {

    private final Config config;

    public CryCommand() {
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

        ArrayList<String> images = new ArrayList<>();
        images.add("https://c.tenor.com/OhuSWqAsQH4AAAAC/anime-girl-sad-sad.gif");
        images.add("https://c.tenor.com/q0nNfTktQ7wAAAAC/crying-anime.gif");
        images.add("https://c.tenor.com/OfYt0T0tgCYAAAAC/anime-cry.gif");
        images.add("https://c.tenor.com/xb3lLfpZTCkAAAAC/akame-crying-akame-cry.gif");
        images.add("https://c.tenor.com/FXq5be_Q7oUAAAAC/anime-anime-cry.gif");
        images.add("https://c.tenor.com/q9V98YHPZX4AAAAC/anime-umaru.gif");
        images.add("https://c.tenor.com/gDk49oAcW9QAAAAd/anime-cry-cry.gif");
        images.add("https://c.tenor.com/Q0HUwg81A_0AAAAd/anime-cry.gif");
        images.add("https://c.tenor.com/NNij18kYIOIAAAAC/anime-cry.gif");
        images.add("https://c.tenor.com/dTgqu4CcSKEAAAAC/cry-sad.gif");
        images.add("https://c.tenor.com/thw0X00MurYAAAAC/anime-crying.gif");
        images.add("https://c.tenor.com/fBNK66X1CWwAAAAC/cry-anime.gif");
        images.add("https://c.tenor.com/cpDRqZxQvYQAAAAC/sorry-anime.gif");
        images.add("https://c.tenor.com/Mlgi6bkVkG8AAAAC/emotional-cry.gif");
        images.add("https://c.tenor.com/NSxEb1YvXQQAAAAC/deku-cry.gif");
        images.add("https://c.tenor.com/bMSZQ4j3CQkAAAAC/anime-cry.gif");
        images.add("https://c.tenor.com/FgETH4kGKRgAAAAC/rin-sad.gif");
        images.add("https://c.tenor.com/NMiID29TUvIAAAAC/hunter-x-hunter-gon-freecs.gif");
        images.add("https://c.tenor.com/2pawKZu4h_oAAAAC/sad-anime.gif");
        images.add("https://c.tenor.com/zfmbNj3IpAgAAAAC/sorry-crying.gif");
        images.add("https://c.tenor.com/iQ_mMnFEJc0AAAAd/anime-girl.gif");
        images.add("https://c.tenor.com/fSlWHQyaxRkAAAAC/sadness-sad.gif");
        images.add("https://c.tenor.com/zPGH1GeElyAAAAAd/anime-anime-panic.gif");
        images.add("https://c.tenor.com/hge_M0aodP4AAAAC/free-iwatobi.gif");
        images.add("https://c.tenor.com/ZK3DOFspBAUAAAAC/akko-qq.gif");
        images.add("https://c.tenor.com/SgjbrdojHZwAAAAC/anohana-tears.gif");
        images.add("https://c.tenor.com/HUewq2uQi30AAAAC/anime-crying.gif");
        images.add("https://c.tenor.com/AawIsMGnR88AAAAC/llorar1-cry.gif");
        images.add("https://c.tenor.com/a-j9Ldg2FEcAAAAC/anime-cry.gif");
        images.add("https://c.tenor.com/ghi1gmzEv94AAAAC/crying-anime.gif");
        images.add("https://c.tenor.com/O6mJ5XEY5tQAAAAC/cry-pleure.gif");
        images.add("https://c.tenor.com/6EpIy39TAtwAAAAC/bedtime-sad-story.gif");
        images.add("https://c.tenor.com/7cpM0BTTbm0AAAAC/sad-face.gif");
        images.add("https://c.tenor.com/EZsmE8l33TcAAAAd/anime-anime-cry.gif");
        images.add("https://c.tenor.com/QI1bpPc2icsAAAAC/anime-cry.gif");
        images.add("https://c.tenor.com/MmaQIlUsAlwAAAAC/anime-sad.gif");
        images.add("https://c.tenor.com/jchX5Rs6kP4AAAAC/anime-sad.gif");
        images.add("https://c.tenor.com/v_FOnNyYuGcAAAAC/cry-k-on.gif");
        images.add("https://c.tenor.com/7NzyFQ0tcm8AAAAC/anime-emotions.gif");
        images.add("https://c.tenor.com/_lAM9RDkCfYAAAAC/anime-girl.gif");
        images.add("https://c.tenor.com/tgVGh4R71MEAAAAC/anime-cry.gif");
        images.add("https://c.tenor.com/T9VlMPLfpCQAAAAC/sad-life-crying.gif");
        images.add("https://c.tenor.com/k2SKe36YwIUAAAAC/crying-girl.gif");
        images.add("https://c.tenor.com/YwWCe3T4uCwAAAAC/anime-cry.gif");
        images.add("https://c.tenor.com/iH5x-r7ozCwAAAAC/crybaby-cry.gif");
        images.add("https://c.tenor.com/00Ljqy2Iv9QAAAAC/shintaro-kisaragi-shintaro.gif");
        images.add("https://c.tenor.com/P6A-13V_BLwAAAAC/aikatsu-aikatsu-stars.gif");
        images.add("https://c.tenor.com/rI6L9q1-7V0AAAAC/glri-anime.gif");
        images.add("https://c.tenor.com/JMZnvTfGVdsAAAAC/cry.gif");
        images.add("https://c.tenor.com/Vs9QNG3lQZUAAAAC/luffy-one-piece.gif");
        images.add("https://c.tenor.com/FdSU_Xoy25cAAAAC/crying-anime.gif");

        embedBuilder.setTitle("**:cry: Cry**");
        embedBuilder.setDescription(member.getAsMention() + " is crying! :c");
        embedBuilder.setImage(images.get(new Random().nextInt(images.size())));
        embedBuilder.setColor(ColorUtil.getFromRGBString(config.getString("format.color.default")));
        context.getEvent().replyEmbeds(embedBuilder.build()).queue();

    }

    @Override
    public String getName() {
        return "cry";
    }

    @Override
    public String getDescription() {
        return "Make yourself cry.";
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
