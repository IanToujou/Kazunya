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
 * Date: 05/11/2021
 * Time: 21:48
 */
public class SmileCommand implements ICommand {

    private final Config config;

    public SmileCommand() {
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
        images.add("https://c.tenor.com/3fAZZncIHDQAAAAC/smile-anime.gif");
        images.add("https://c.tenor.com/SEZ_uWSewwsAAAAC/anime-girl-smile.gif");
        images.add("https://c.tenor.com/nBWlYPbKxzwAAAAC/anime-happy.gif");
        images.add("https://c.tenor.com/nuKmYDgaDpAAAAAC/anime-smile.gif");
        images.add("https://c.tenor.com/WFxzA4PQTiEAAAAC/smile-anime.gif");
        images.add("https://c.tenor.com/VrUxJZFdmIsAAAAC/anime-excited.gif");
        images.add("https://c.tenor.com/9Gc5ja3GlzYAAAAC/happy-smile.gif");
        images.add("https://c.tenor.com/AG-IPfazCnQAAAAC/hyouka-h%C5%8Dtar%C5%8Doreki.gif");
        images.add("https://c.tenor.com/BA2eLg42RbEAAAAC/isitwrongtotryandpickupgirlsinadungeon-bellcranel.gif");
        images.add("https://c.tenor.com/ivEOWR55wBEAAAAC/anime-happy.gif");
        images.add("https://c.tenor.com/B5mG_MXzno0AAAAC/anime-taiga-aisaka.gif");
        images.add("https://c.tenor.com/s3g8xVGdjyEAAAAC/jibaku-shounen-hanako-kun-handsome.gif");
        images.add("https://c.tenor.com/X4tchZoJLIsAAAAC/anime-smile-hanako.gif");
        images.add("https://c.tenor.com/U1p83COiAPYAAAAC/anime-happy-anime-smile.gif");
        images.add("https://c.tenor.com/Ev7Wy4W-bpQAAAAC/anime-konata.gif");
        images.add("https://c.tenor.com/Wzymbgijw7gAAAAC/anime-smile.gif");
        images.add("https://c.tenor.com/EVRZfOqUJ4wAAAAC/chitose-karasuma-girlish-number.gif");
        images.add("https://c.tenor.com/FUeNgq8G6tAAAAAC/haru-yoshida-tonari-no-kaibutsu-kun.gif");
        images.add("https://c.tenor.com/xuavd045hkoAAAAC/anime-love-anime-smile.gif");
        images.add("https://c.tenor.com/W12sevEG_I0AAAAC/anime-smiling.gif");
        images.add("https://c.tenor.com/9BvbcMZFCCMAAAAC/anime-cute.gif");
        images.add("https://c.tenor.com/Pjwr4KQt2MUAAAAC/my-little-monster-anime.gif");
        images.add("https://c.tenor.com/EHIkianDEOEAAAAd/hayase-nagatoro-smug-nagatoro-smug.gif");
        images.add("https://c.tenor.com/Ur_pBB1YBlwAAAAC/himouto-umaru-chan-smile.gif");
        images.add("https://c.tenor.com/EnFI5gdE3_IAAAAC/haru-yoshida-tonari-no-kaibutsu-kun.gif");
        images.add("https://c.tenor.com/KHrW6-V4HCoAAAAC/koushi-nagumo-shota.gif");
        images.add("https://c.tenor.com/Q--iyrFnBw8AAAAC/anime-smile.gif");
        images.add("https://c.tenor.com/kEw4mqKPRDYAAAAC/anime-smile.gif");
        images.add("https://c.tenor.com/iysyjzqfXVMAAAAC/anime-anime-blush.gif");
        images.add("https://c.tenor.com/MngVjni-BNsAAAAC/yuuki-anime-smile.gif");
        images.add("https://c.tenor.com/--PYOLkrn8EAAAAC/headpat-smile.gif");
        images.add("https://c.tenor.com/MRIkRK5l_coAAAAC/chitoge.gif");
        images.add("https://c.tenor.com/Vtjibw10S4AAAAAC/cute-smiling.gif");
        images.add("https://c.tenor.com/3EcCzBCBXbYAAAAC/yato-anime.gif");
        images.add("https://c.tenor.com/0G2pfkTTP0gAAAAC/happy-loli.gif");
        images.add("https://c.tenor.com/7X0QACRXAN8AAAAC/amaama-to-inazuma-anime.gif");
        images.add("https://c.tenor.com/W4deQTUzHswAAAAC/anime-smile.gif");
        images.add("https://c.tenor.com/aUskYSGIMFAAAAAC/anime-smile-smile.gif");
        images.add("https://c.tenor.com/Ul15t3k6ueIAAAAC/love-live-anime.gif");
        images.add("https://c.tenor.com/BjoLwqByDzMAAAAC/fakesmile-anime.gif");
        images.add("https://c.tenor.com/ShE4dl0Z59IAAAAC/smile-anime.gif");
        images.add("https://c.tenor.com/D91rZB7NPAwAAAAC/joy.gif");
        images.add("https://c.tenor.com/Eok6d6MQhg4AAAAC/cute-smile.gif");
        images.add("https://c.tenor.com/6j5bybtXFMEAAAAC/anime-smile-anime.gif");
        images.add("https://c.tenor.com/AOPVAMTmFfQAAAAC/mochizou-mochizou-ooji.gif");

        embedBuilder.setTitle("**:purple_heart: Smile**");
        embedBuilder.setDescription("**" + member.getEffectiveName() + " is smiling! Yay~");
        embedBuilder.setImage(images.get(new Random().nextInt(images.size())));
        embedBuilder.setColor(ColorUtil.getFromRGBString(config.getString("format.color.default")));
        context.getEvent().replyEmbeds(embedBuilder.build()).queue();

    }

    @Override
    public String getName() {
        return "smile";
    }

    @Override
    public String getDescription() {
        return "Make yourself smile.";
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
