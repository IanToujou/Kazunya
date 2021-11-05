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
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * This file has been created by Ian Toujou.
 * Project: Kazunya
 * Date: 05/11/2021
 * Time: 21:29
 */
public class LaughCommand implements ICommand {

    private final Config config;

    public LaughCommand() {
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
        images.add("https://c.tenor.com/10DgtkY20O8AAAAC/uzaki-uzaki-chan-wa-asobitai.gif");
        images.add("https://c.tenor.com/8nSbJK3j7EUAAAAC/laugh-anime.gif");
        images.add("https://c.tenor.com/B-expmjx5R0AAAAC/natsu-lol.gif");
        images.add("https://c.tenor.com/GpVrlLpkBEsAAAAC/haha-anime.gif");
        images.add("https://c.tenor.com/TaIzFEOO05MAAAAC/death-note-kira.gif");
        images.add("https://c.tenor.com/ibc6FwAYamEAAAAC/black-clover-anime.gif");
        images.add("https://c.tenor.com/74Win7VdWDoAAAAC/anime-laughing.gif");
        images.add("https://c.tenor.com/gzM_6h_nC_sAAAAC/nichijou-hahaha.gif");
        images.add("https://c.tenor.com/fbWCY-1exTsAAAAC/bokura-wa-minna-kawaisou-gifs-to-reaction.gif");
        images.add("https://c.tenor.com/xKvdQnAP8nwAAAAC/anime-laughing.gif");
        images.add("https://c.tenor.com/An-9HfjvNkwAAAAC/kuroo-tetsurou-haikyuu.gif");
        images.add("https://c.tenor.com/XQxOkKXFG7oAAAAC/satania-laugh.gif");
        images.add("https://c.tenor.com/uiiTHEWlNQ8AAAAC/anime-laugh.gif");
        images.add("https://c.tenor.com/_ZXFBnL1MkQAAAAC/kyouko-toshinou-yuru-yuri.gif");
        images.add("https://c.tenor.com/CAJWKDzTbFEAAAAC/hayase-nagatoro-nagatoro-hehe.gif");
        images.add("https://c.tenor.com/srps8djYMgsAAAAC/cypheral.gif");
        images.add("https://c.tenor.com/KBBH8fFA3aEAAAAC/anime-giggle.gif");
        images.add("https://c.tenor.com/fqRNsILmXHQAAAAC/anime-girl.gif");
        images.add("https://c.tenor.com/0K_Hz_9aTEUAAAAC/bokura-wa-minna-kawaisou-gifs-to-reaction.gif");
        images.add("https://c.tenor.com/Ypo7sWozoyIAAAAC/laugh-evil-laugh.gif");
        images.add("https://c.tenor.com/adZJSzcIDfEAAAAC/shinya-laugh.gif");
        images.add("https://c.tenor.com/XbGebOy9Lf0AAAAC/anime-girl.gif");
        images.add("https://c.tenor.com/lrybhFIQ0zEAAAAC/kaguya-sama-love-is-war-chika-fujiwara.gif");
        images.add("https://c.tenor.com/3SMAyDkjv6MAAAAd/akane-shinj%C5%8D-shinjou-akane.gif");
        images.add("https://c.tenor.com/Q1v5G3HatjsAAAAC/anime-laugh.gif");
        images.add("https://c.tenor.com/0VdcdocyQoQAAAAC/mha-evil-laugh.gif");
        images.add("https://c.tenor.com/S_V4Q8NA9QQAAAAC/tsugumomo-laugh.gif");
        images.add("https://c.tenor.com/VZ428SKYRGoAAAAd/laugh-anime.gif");
        images.add("https://c.tenor.com/DHhUTzmkFO8AAAAC/ha-ha-ha-laugh.gif");
        images.add("https://c.tenor.com/nqTDeAS9sL8AAAAC/fairy-tail-natsu.gif");
        images.add("https://c.tenor.com/MZXGjlCbMcwAAAAC/luffy-one-piece.gif");
        images.add("https://c.tenor.com/01L-_F-SipYAAAAC/sasuke-lol.gif");
        images.add("https://c.tenor.com/qEfogXAprQoAAAAC/nichijou-laughing.gif");
        images.add("https://c.tenor.com/5INXYo8F0bEAAAAd/takagi-laugh.gif");
        images.add("https://c.tenor.com/QdYIzpKG7DwAAAAC/laugh-senku.gif");
        images.add("https://c.tenor.com/UuD1Jq8KNU4AAAAC/anime-laugh.gif");
        images.add("https://c.tenor.com/i3LW-RDisIcAAAAC/lol-haha.gif");
        images.add("https://c.tenor.com/MMcJXAHw1wwAAAAC/redhead-girl.gif");
        images.add("https://c.tenor.com/qK8zeVLdeJAAAAAC/hehehe-laugh.gif");
        images.add("https://c.tenor.com/EZTBU8pVl9EAAAAC/lol-anime-funny.gif");
        images.add("https://c.tenor.com/HOQ5bxzACaUAAAAC/dandidave-cute.gif");
        images.add("https://c.tenor.com/2vgPNGYojFkAAAAC/anime-onepiece.gif");
        images.add("https://c.tenor.com/qbemYsp1K6IAAAAC/anime-laugh.gif");
        images.add("https://c.tenor.com/Z387ADYer2kAAAAC/laughing-laugh.gif");
        images.add("https://c.tenor.com/_3S89a6MsrQAAAAC/inuyasha-evil.gif");
        images.add("https://c.tenor.com/q0uuksIH6hAAAAAC/joshiraku-anime.gif");
        images.add("https://c.tenor.com/ti0_Sz-HQecAAAAC/kyoko-mogami-skip-beat.gif");

        embedBuilder.setTitle("**:laughing: Laugh**");
        embedBuilder.setDescription("**" + member.getEffectiveName() + " is laughing! Haha~");
        embedBuilder.setImage(images.get(new Random().nextInt(images.size())));
        embedBuilder.setColor(ColorTools.getFromRGBString(config.getString("format.color.default")));
        context.getEvent().replyEmbeds(embedBuilder.build()).queue();

    }

    @Override
    public String getName() {
        return "laugh";
    }

    @Override
    public String getDescription() {
        return "Make yourself laugh.";
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
