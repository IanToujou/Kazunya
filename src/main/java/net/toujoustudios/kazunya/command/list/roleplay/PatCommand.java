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
public class PatCommand implements ICommand {

    private final Config config;

    public PatCommand() {
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
        images.add("https://c.tenor.com/N41zKEDABuUAAAAC/anime-head-pat-anime-pat.gif");
        images.add("https://c.tenor.com/8DaE6qzF0DwAAAAC/neet-anime.gif");
        images.add("https://c.tenor.com/wLqFGYigJuIAAAAC/mai-sakurajima.gif");
        images.add("https://c.tenor.com/E6fMkQRZBdIAAAAC/kanna-kamui-pat.gif");
        images.add("https://c.tenor.com/jEfC8cztigIAAAAC/anime-pat.gif");
        images.add("https://c.tenor.com/dmYhPDHbbI4AAAAC/misha-misha-necron-anos-voldigoad-the-misfit-of-demon-king-academy-headpat-pat.gif");
        images.add("https://c.tenor.com/rZRQ6gSf128AAAAC/anime-good-girl.gif");
        images.add("https://c.tenor.com/tYS5DBIos-UAAAAd/kyo-ani-musaigen.gif");
        images.add("https://c.tenor.com/JWx5wmF6bqAAAAAC/charlotte-pat.gif");
        images.add("https://c.tenor.com/0mdj-zud0RAAAAAC/tohru-kobayashi.gif");
        images.add("https://c.tenor.com/6dLDH0npv6IAAAAC/nogamenolife-shiro.gif");
        images.add("https://c.tenor.com/Fxku5ndWrN8AAAAC/head-pat-anime.gif");
        images.add("https://c.tenor.com/AYEu-gdwHD8AAAAd/love-love-ryant.gif");
        images.add("https://c.tenor.com/YroVxwiL2dcAAAAC/ao-haru-ride-anime-boy.gif");
        images.add("https://c.tenor.com/TDqVQaQWcFMAAAAC/anime-pat.gif");
        images.add("https://c.tenor.com/o0re0DQzkd8AAAAC/anime-head-rub.gif");
        images.add("https://c.tenor.com/dLdNYQrLsp4AAAAC/umaru-frown.gif");
        images.add("https://c.tenor.com/EYhRCNjiyIYAAAAC/momokuri-anime-pat.gif");
        images.add("https://c.tenor.com/oZ-sZLLPf0QAAAAC/pat-anime.gif");
        images.add("https://c.tenor.com/muVzMQS6mW0AAAAC/pat-anime.gif");
        images.add("https://c.tenor.com/tVwc20r-GwQAAAAd/rascal-does-not-dream-of-bunny-girl-senpai-seishun-buta-yar%C5%8D.gif");
        images.add("https://c.tenor.com/TRxNL32jtEIAAAAC/anime-pat.gif");
        images.add("https://c.tenor.com/HNZMnQj1DDUAAAAd/tate-no-yuusha-no-nariagari-the-rising-of-the-shield-hero.gif");
        images.add("https://c.tenor.com/G14pV-tr0NAAAAAC/anime-head.gif");
        images.add("https://c.tenor.com/QAIyvivjoB4AAAAC/anime-anime-head-rub.gif");
        images.add("https://c.tenor.com/YDuiSAaax_cAAAAC/anime-pat-head-pats.gif");
        images.add("https://c.tenor.com/S3pfBHXIDYQAAAAC/ijiranaide-nagatoro-anime-pat.gif");
        images.add("https://c.tenor.com/YaFzR7EkabYAAAAC/head-pat-anime.gif");
        images.add("https://c.tenor.com/kM1mVaXE8Y8AAAAC/kaede-azusagawa-kaede.gif");
        images.add("https://c.tenor.com/pvYCizt4_W8AAAAC/rachnera-monster-musume.gif");
        images.add("https://c.tenor.com/y0gEAhy4ZYIAAAAC/chihya-chihaya.gif");
        images.add("https://c.tenor.com/VEIcuNYenJ0AAAAC/anime-pat.gif");
        images.add("https://c.tenor.com/1dO-1j18C-sAAAAC/pet-pat.gif");
        images.add("https://c.tenor.com/smeOhSMHr9QAAAAC/behave-anime.gif");
        images.add("https://c.tenor.com/hCh_kg6bS2YAAAAC/anime-pat.gif");
        images.add("https://c.tenor.com/5dezHjhjBucAAAAC/nagi-no-asukara-manaka-mukaido.gif");
        images.add("https://c.tenor.com/mjzPtcvwO2cAAAAC/pat-head-good-job.gif");
        images.add("https://c.tenor.com/zBPha3hhm7QAAAAC/anime-girl.gif");
        images.add("https://c.tenor.com/gP_BvlhIa0oAAAAC/anime-cute.gif");
        images.add("https://c.tenor.com/8w4TYd2tsKcAAAAC/anime-pat.gif");
        images.add("https://c.tenor.com/5xPqFgBA0XwAAAAC/anime.gif");
        images.add("https://c.tenor.com/pB5LKEouppgAAAAC/pat-pat-on-head.gif");
        images.add("https://c.tenor.com/-hkJYNs7tUkAAAAC/anime-pat.gif");

        embedBuilder.setTitle("**:purple_heart: Headpat**");
        embedBuilder.setDescription("**" + member.getEffectiveName() + " pats " + target.getAsMention() + "!** :3");
        embedBuilder.setImage(images.get(new Random().nextInt(images.size())));
        embedBuilder.setColor(ColorTools.getFromRGBString(config.getString("format.color.default")));
        context.getEvent().replyEmbeds(embedBuilder.build()).queue();

    }

    @Override
    public String getName() {
        return "pat";
    }

    @Override
    public String getDescription() {
        return "Headpat another person.";
    }

    @Override
    public List<OptionData> getOptions() {
        List<OptionData> optionData = new ArrayList<>();
        optionData.add(new OptionData(OptionType.USER, "user", "The person you want to headpat.", true));
        return optionData;
    }

    @Override
    public CommandCategory getCategory() {
        return CommandCategory.ROLEPLAY;
    }

}
