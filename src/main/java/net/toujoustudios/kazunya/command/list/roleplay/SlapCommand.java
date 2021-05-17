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
 * Date: 14/04/2021
 * Time: 22:55
 * Project: Kazunya
 */
public class SlapCommand implements ICommand {

    @Override
    public void handle(CommandContext context) {

        List<String> args = context.getArgs();
        TextChannel channel = context.getChannel();
        EmbedBuilder embedBuilder = new EmbedBuilder();
        Member member = context.getMember();

        embedBuilder.setColor(Config.DEFAULT_EMBED_COLOR);
        embedBuilder.setTitle("**__ROLEPLAY__**");

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
        images.add("https://media1.tenor.com/images/d14969a21a96ec46f61770c50fccf24f/tenor.gif?itemid=5509136");
        images.add("https://media1.tenor.com/images/74db8b0b64e8d539aebebfbb2094ae84/tenor.gif?itemid=15144612");
        images.add("https://media1.tenor.com/images/528ff731635b64037fab0ba6b76d8830/tenor.gif?itemid=17078255");
        images.add("https://media1.tenor.com/images/9ea4fb41d066737c0e3f2d626c13f230/tenor.gif?itemid=7355956");
        images.add("https://media1.tenor.com/images/358986720d4b533a49bdb67cbc4fe3e5/tenor.gif?itemid=14179582");
        images.add("https://media1.tenor.com/images/b7a844cc66ca1c6a4f06c266646d070f/tenor.gif?itemid=17423278");
        images.add("https://media1.tenor.com/images/3fd96f4dcba48de453f2ab3acd657b53/tenor.gif?itemid=14358509");
        images.add("https://media1.tenor.com/images/af36628688f5f50f297c5e4bce61a35c/tenor.gif?itemid=17314633");
        images.add("https://media1.tenor.com/images/dcd359a74e32bca7197de46a58ec7b72/tenor.gif?itemid=12396060");
        images.add("https://media1.tenor.com/images/2915aef3da681c2361ee9c4dcc9dbfa4/tenor.gif?itemid=14694312");
        images.add("https://media1.tenor.com/images/a9b8bd2060d76ec286ec8b4c61ec1f5a/tenor.gif?itemid=17784858");
        images.add("https://media1.tenor.com/images/6885c7676d8645bf2891138564159713/tenor.gif?itemid=4436362");
        images.add("https://media1.tenor.com/images/89309d227081132425e5931fbbd7f59b/tenor.gif?itemid=4880762");
        images.add("https://media1.tenor.com/images/7437caf9fb0bea289a5bb163b90163c7/tenor.gif?itemid=13595529");
        images.add("https://media1.tenor.com/images/92ec42af8364dcc44816a4f2bb1dd0da/tenor.gif?itemid=16881889");
        images.add("https://media1.tenor.com/images/4a6b15b8d111255c77da57c735c79b44/tenor.gif?itemid=10937039");
        images.add("https://media1.tenor.com/images/b221fb3f50f0e15b3ace6a2b87ad0ffa/tenor.gif?itemid=8576304");
        images.add("https://media1.tenor.com/images/477821d58203a6786abea01d8cf1030e/tenor.gif?itemid=7958720");
        images.add("https://media1.tenor.com/images/37c72d1a4a4ad8108897642f7bebc4be/tenor.gif?itemid=17845941");
        images.add("https://media1.tenor.com/images/c159cd1d7e7424cf9fd6fbdb09919146/tenor.gif?itemid=14179570");
        images.add("https://media1.tenor.com/images/e8f880b13c17d61810ac381b2f6a93c3/tenor.gif?itemid=17897236");
        images.add("https://media1.tenor.com/images/07b4516d50406b4a320269d514876170/tenor.gif?itemid=17897216");
        images.add("https://media1.tenor.com/images/153b2f1bfd3c595c920ce60f1553c5f7/tenor.gif?itemid=10936993");
        images.add("https://media1.tenor.com/images/0860d681fbe7ad04a2f39735ab939176/tenor.gif?itemid=13642334");
        images.add("https://media1.tenor.com/images/fb17a25b86d80e55ceb5153f08e79385/tenor.gif?itemid=7919028");
        images.add("https://media1.tenor.com/images/85722c3e51d390e11a0493696f32fb69/tenor.gif?itemid=5463215");
        images.add("https://media1.tenor.com/images/f47da420684e9912025448c44b543d26/tenor.gif?itemid=20055427");
        images.add("https://media1.tenor.com/images/1ae0a42059d8ad64a0345e93313dfc91/tenor.gif?itemid=21281337");

        embedBuilder.setDescription("**" + member.getEffectiveName() + "** is slapping **" + target.getEffectiveName() + "**! :anger: Ouch!");
        embedBuilder.setImage(images.get(new Random().nextInt(images.size())));
        channel.sendMessage(embedBuilder.build()).queue();

    }

    @Override
    public String getName() {
        return "slap";
    }

    @Override
    public String getHelp() {
        return "Slap another user.";
    }

    @Override
    public String getUsage() {
        return "slap [user]";
    }

    @Override
    public boolean isNSFW() { return false; }

    @Override
    public CommandCategory getCategory() {
        return CommandCategory.ROLEPLAY;
    }

}
