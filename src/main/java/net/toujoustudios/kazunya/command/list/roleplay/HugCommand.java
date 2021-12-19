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
 * Date: 14/10/2021
 * Time: 09:20
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

        if (args.size() == 0) {
            ErrorEmbed.sendError(context, ErrorType.COMMAND_INVALID_SYNTAX);
            return;
        }

        Member target = args.get(0).getAsMember();
        assert target != null;

        if(target.getId().equals(member.getId())) {
            ErrorEmbed.sendError(context, ErrorType.COMMAND_INVALID_USER_SELF);
            return;
        }

        ArrayList<String> images = new ArrayList<>();
        images.add("https://c.tenor.com/9e1aE_xBLCsAAAAC/anime-hug.gif");
        images.add("https://c.tenor.com/c8M8yU1q6c4AAAAC/hug-anime.gif");
        images.add("https://c.tenor.com/DVOTqLcB2jUAAAAC/anime-hug-love.gif");
        images.add("https://c.tenor.com/0vl21YIsGvgAAAAC/hug-anime.gif");
        images.add("https://c.tenor.com/kPgR6UH6AXcAAAAC/anime-hug.gif");
        images.add("https://c.tenor.com/Ct4bdr2ZGeAAAAAC/teria-wang-kishuku-gakkou-no-juliet.gif");
        images.add("https://c.tenor.com/stLEkL3l0NAAAAAC/anime-acchi-kocchi.gif");
        images.add("https://c.tenor.com/dIvoDyyk5LIAAAAC/anime-hug-sweet.gif");
        images.add("https://c.tenor.com/2bWwi8DhDsAAAAAC/hugs-and-love.gif");
        images.add("https://c.tenor.com/pcULC09CfkgAAAAC/hug-anime.gif");
        images.add("https://c.tenor.com/F1VUry86n7kAAAAC/hug-anime.gif");
        images.add("https://c.tenor.com/xgVPw2QK5n8AAAAC/sakura-quest-anime.gif");
        images.add("https://c.tenor.com/KD__SewDxK0AAAAC/horimiya-izumi-miyamura.gif");
        images.add("https://c.tenor.com/z2QaiBZCLCQAAAAC/hug-anime.gif");
        images.add("https://c.tenor.com/Pd2sMiVr09YAAAAC/hug-anime-hug.gif");
        images.add("https://c.tenor.com/SPs0Rpt7HAcAAAAC/chiya-urara.gif");
        images.add("https://c.tenor.com/nHkiUCkS04gAAAAC/anime-hug-hearts.gif");
        images.add("https://c.tenor.com/xIuXbMtA38sAAAAd/toilet-bound-hanakokun.gif");
        images.add("https://c.tenor.com/EnfEuWDXthkAAAAC/hug-couple.gif");
        images.add("https://c.tenor.com/X5nBTYuoKpoAAAAC/anime-cheeks.gif");
        images.add("https://c.tenor.com/nmzZIEFv8nkAAAAC/hug-anime.gif");
        images.add("https://c.tenor.com/mmQyXP3JvKwAAAAC/anime-cute.gif");
        images.add("https://c.tenor.com/fLxZt7jo1YEAAAAC/loli-dragon.gif");
        images.add("https://c.tenor.com/vkiqyZJWJ4wAAAAC/hug-cat.gif");

        assert target != null;
        embedBuilder.setTitle("**:purple_heart: Hugs**");
        embedBuilder.setDescription(member.getAsMention() + " hugs " + target.getAsMention() + "! :3");
        embedBuilder.setImage(images.get(new Random().nextInt(images.size())));
        embedBuilder.setColor(ColorTools.getFromRGBString(config.getString("format.color.default")));
        context.getEvent().replyEmbeds(embedBuilder.build()).queue();

    }

    @Override
    public String getName() {
        return "hug";
    }

    @Override
    public String getDescription() {
        return "Hug another person.";
    }

    @Override
    public List<OptionData> getOptions() {
        List<OptionData> optionData = new ArrayList<>();
        optionData.add(new OptionData(OptionType.USER, "user", "The person you want to hug.", true));
        return optionData;
    }

    @Override
    public CommandCategory getCategory() {
        return CommandCategory.ROLEPLAY;
    }

}
