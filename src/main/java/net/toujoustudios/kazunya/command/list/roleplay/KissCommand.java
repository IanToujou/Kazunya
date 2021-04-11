package net.toujoustudios.kazunya.command.list.roleplay;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;
import net.toujoustudios.kazunya.command.CommandCategory;
import net.toujoustudios.kazunya.command.CommandContext;
import net.toujoustudios.kazunya.command.ICommand;
import net.toujoustudios.kazunya.config.Config;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * This file was created by IanToujou.
 * Date: 11/04/2021
 * Time: 02:12
 * Project: Kazunya
 */
public class KissCommand implements ICommand {

    @Override
    public void handle(CommandContext context) {

        List<String> args = context.getArgs();
        TextChannel channel = context.getChannel();
        EmbedBuilder embedBuilder = new EmbedBuilder();
        Member member = context.getMember();

        embedBuilder.setColor(Config.DEFAULT_EMBED_COLOR);

        if(args.size() == 0) {

            embedBuilder.setTitle("**__ERROR__**");
            embedBuilder.setColor(Config.ERROR_EMBED_COLOR);
            embedBuilder.setDescription(":x: The command syntax is not correct.\nPlease use **" + Config.DEFAULT_PREFIX + getUsage() + "**");
            channel.sendMessage(embedBuilder.build()).queue();
            return;

        }

        if(context.getMessage().getMentionedMembers().size() != 1) {

            embedBuilder.setTitle("**__ERROR__**");
            embedBuilder.setColor(Config.ERROR_EMBED_COLOR);
            embedBuilder.setDescription(":x: The command syntax is not correct.\nPlease use **" + Config.DEFAULT_PREFIX + getUsage() + "**");
            channel.sendMessage(embedBuilder.build()).queue();
            return;

        }

        Member target = context.getMessage().getMentionedMembers().get(0);

        ArrayList<String> images = new ArrayList<>();
        images.add("https://media1.tenor.com/images/78095c007974aceb72b91aeb7ee54a71/tenor.gif?itemid=5095865");
        images.add("https://media1.tenor.com/images/ea9a07318bd8400fbfbd658e9f5ecd5d/tenor.gif?itemid=12612515");
        images.add("https://media1.tenor.com/images/f102a57842e7325873dd980327d39b39/tenor.gif?itemid=12392648");
        images.add("https://media1.tenor.com/images/7fd98defeb5fd901afe6ace0dffce96e/tenor.gif?itemid=9670722");
        images.add("https://media1.tenor.com/images/bc5e143ab33084961904240f431ca0b1/tenor.gif?itemid=9838409");
        images.add("https://media1.tenor.com/images/3d56f6ef81e5c01241ff17c364b72529/tenor.gif?itemid=13843260");
        images.add("https://media1.tenor.com/images/02d9cae34993e48ab5bb27763d5ca2fa/tenor.gif?itemid=4874618");
        images.add("https://media1.tenor.com/images/e76e640bbbd4161345f551bb42e6eb13/tenor.gif?itemid=4829336");
        images.add("https://media1.tenor.com/images/1306732d3351afe642c9a7f6d46f548e/tenor.gif?itemid=6155670");
        images.add("https://media1.tenor.com/images/a1f7d43752168b3c1dbdfb925bda8a33/tenor.gif?itemid=10356314");
        images.add("https://media1.tenor.com/images/621ceac89636fc46ecaf81824f9fee0e/tenor.gif?itemid=4958649");
        images.add("https://media1.tenor.com/images/6f455ef36a0eb011a60fad110a44ce68/tenor.gif?itemid=13658106");
        images.add("https://media1.tenor.com/images/b8d0152fbe9ecc061f9ad7ff74533396/tenor.gif?itemid=5372258");
        images.add("https://media1.tenor.com/images/a390476cc2773898ae75090429fb1d3b/tenor.gif?itemid=12837192");
        images.add("https://media1.tenor.com/images/4b5d5afd747fe053ed79317628aac106/tenor.gif?itemid=5649376");
        images.add("https://media1.tenor.com/images/d307db89f181813e0d05937b5feb4254/tenor.gif?itemid=16371489");
        images.add("https://media1.tenor.com/images/4b56464510f4c9cfbec9eda5a25c3a72/tenor.gif?itemid=17193768");
        images.add("https://media1.tenor.com/images/ba1841e4aeb5328e41530d3289616f46/tenor.gif?itemid=14240425");
        images.add("https://media1.tenor.com/images/4c66d14c58838d05376b5d2712655d91/tenor.gif?itemid=15009390");
        images.add("https://media1.tenor.com/images/9fac3eab2f619789b88fdf9aa5ca7b8f/tenor.gif?itemid=12925177");
        images.add("https://media1.tenor.com/images/ef4a0bcb6e42189dc12ee55e0d479c54/tenor.gif?itemid=12143127");
        images.add("https://media1.tenor.com/images/e858678426357728038c277598871d6d/tenor.gif?itemid=9903014");
        images.add("https://media1.tenor.com/images/e00f3104927ae27d7d6a32393d163176/tenor.gif?itemid=12192866");
        images.add("https://media1.tenor.com/images/230e9fd40cd15e3f27fc891bac04248e/tenor.gif?itemid=14751754");
        images.add("https://media1.tenor.com/images/f34f73decaef049f9354b27984dfb09c/tenor.gif?itemid=14958166");
        images.add("https://media1.tenor.com/images/558f63303a303abfdddaa71dc7b3d6ae/tenor.gif?itemid=12879850");
        images.add("https://media1.tenor.com/images/632a3db90c6ecd87f1242605f92120c7/tenor.gif?itemid=5608449");
        images.add("https://media1.tenor.com/images/68a37a5a1b86f227b8e1169f33a6a6bb/tenor.gif?itemid=13344389");
        images.add("https://media1.tenor.com/images/2c8b6aa48be83de7b505d1d48bb4aa6d/tenor.gif?itemid=18234023");
        images.add("https://media1.tenor.com/images/61dba0b61a2647a0663b7bde896c966c/tenor.gif?itemid=5262571");
        images.add("https://media1.tenor.com/images/d7bddc2032a53da99f9a3e5bfadf3cf2/tenor.gif?itemid=17708192");
        images.add("https://media1.tenor.com/images/7ea0b8822e5390c2393ef6f18a40893d/tenor.gif?itemid=16687888");
        images.add("https://media1.tenor.com/images/34ecc943dd11f0c55689e25f1bacddfb/tenor.gif?itemid=14816388");
        images.add("https://media1.tenor.com/images/37633f0b8d39daf70a50f69293e303fc/tenor.gif?itemid=13344412");
        images.add("https://media1.tenor.com/images/ae9747f76a390d9ce192c8082f61df85/tenor.gif?itemid=15997716");
        images.add("https://media1.tenor.com/images/ad514e809adc14f0b7722a324c2eb36e/tenor.gif?itemid=14375355");
        images.add("https://media1.tenor.com/images/d93c9a9c201ec1fe3c8011718b18a83c/tenor.gif?itemid=16317577");

        embedBuilder.setDescription("**" + member.getEffectiveName() + "** is kissing **" + target.getEffectiveName() + "**! :purple_heart: Nya~");
        embedBuilder.setImage(images.get(new Random().nextInt(images.size())));
        channel.sendMessage(embedBuilder.build()).queue();

    }

    @Override
    public String getName() {
        return "kiss";
    }

    @Override
    public String getHelp() {
        return "Kiss another user.";
    }

    @Override
    public String getUsage() {
        return "kiss [user]";
    }

    @Override
    public CommandCategory getCategory() {
        return CommandCategory.ROLEPLAY;
    }

}
