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
 * Date: 10/04/2021
 * Time: 14:42
 * Project: Kazunya
 */

public class PatCommand implements ICommand {

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
        images.add("https://media1.tenor.com/images/da8f0e8dd1a7f7db5298bda9cc648a9a/tenor.gif?itemid=12018819");
        images.add("https://media1.tenor.com/images/e5fff7bc2fc641f8ed0cba92475ea741/tenor.gif?itemid=18243417");
        images.add("https://media1.tenor.com/images/55df4c5fb33f3cd05b2f1ac417e050d9/tenor.gif?itemid=6238142");
        images.add("https://media1.tenor.com/images/d9b480bcd392d05426ae6150e986bbf0/tenor.gif?itemid=9332926");
        images.add("https://media1.tenor.com/images/f5176d4c5cbb776e85af5dcc5eea59be/tenor.gif?itemid=5081286");
        images.add("https://media1.tenor.com/images/90712ed3a99db973ec92383a3c6a8767/tenor.gif?itemid=14043105");
        images.add("https://media1.tenor.com/images/6151c42c94df654b1c7de2fdebaa6bd1/tenor.gif?itemid=16456868");
        images.add("https://media1.tenor.com/images/116fe7ede5b7976920fac3bf8067d42b/tenor.gif?itemid=9200932");
        images.add("https://media1.tenor.com/images/daa885ec8a9cfa4107eb966df05ba260/tenor.gif?itemid=13792462");
        images.add("https://media1.tenor.com/images/bfdeb9ec7f89aad86170d06fe4189bec/tenor.gif?itemid=16085314");
        images.add("https://media1.tenor.com/images/2b3ddd79058842ccb9c1fc6acea0bcaa/tenor.gif?itemid=16243971");
        images.add("https://media1.tenor.com/images/6ee188a109975a825f53e0dfa56d497d/tenor.gif?itemid=17747839");
        images.add("https://media1.tenor.com/images/857aef7553857b812808a355f31bbd1f/tenor.gif?itemid=13576017");
        images.add("https://media1.tenor.com/images/183ff4514cbe90609e3f286adaa3d0b4/tenor.gif?itemid=5518321");
        images.add("https://media1.tenor.com/images/0d2fb6ad9a6d71c3a018c0b37ffca50b/tenor.gif?itemid=16121044");
        images.add("https://media1.tenor.com/images/71e74263a48a6e9a2c53f3bc1439c3ac/tenor.gif?itemid=12434286");
        images.add("https://media1.tenor.com/images/5466adf348239fba04c838639525c28a/tenor.gif?itemid=13284057");
        images.add("https://media1.tenor.com/images/c0bcaeaa785a6bdf1fae82ecac65d0cc/tenor.gif?itemid=7453915");
        images.add("https://media1.tenor.com/images/54722063c802bac30d928db3bf3cc3b4/tenor.gif?itemid=8841561");
        images.add("https://media1.tenor.com/images/f330c520a8dfa461130a799faca13c7e/tenor.gif?itemid=13911345");
        images.add("https://media1.tenor.com/images/01a97fee428982b325269207ca22866b/tenor.gif?itemid=16085328");
        images.add("https://media1.tenor.com/images/291ea37382e1d6cd33349c50a398b6b9/tenor.gif?itemid=10204936");
        images.add("https://media1.tenor.com/images/005e8df693c0f59e442b0bf95c22d0f5/tenor.gif?itemid=10947495");
        images.add("https://media1.tenor.com/images/28f4f29de42f03f66fb17c5621e7bedf/tenor.gif?itemid=8659513");
        images.add("https://media1.tenor.com/images/93ccdbf8da129c4fdf8a74a73fb34f17/tenor.gif?itemid=14809730");
        images.add("https://media1.tenor.com/images/d3c117054fb924d66c75169ff158c811/tenor.gif?itemid=15471762");
        images.add("https://media1.tenor.com/images/1e92c03121c0bd6688d17eef8d275ea7/tenor.gif?itemid=9920853");
        images.add("https://media1.tenor.com/images/fe69e3583343f4e707ad6c5584ec581a/tenor.gif?itemid=16085272");
        images.add("https://media1.tenor.com/images/46bab773fdf4c340b59b89655abcda79/tenor.gif?itemid=18776498");
        images.add("https://media1.tenor.com/images/61187dd8c7985c443bf9cd39bc310c02/tenor.gif?itemid=12018805");
        images.add("https://media1.tenor.com/images/b518909216fd793247753ba69d8e94b7/tenor.gif?itemid=16898838");
        images.add("https://media1.tenor.com/images/6050e5640a4bb058fb1ba9a19e12d997/tenor.gif?itemid=4977531");
        images.add("https://media1.tenor.com/images/8b5711095b0ba786c43b617bf9c675dd/tenor.gif?itemid=15735895");
        images.add("https://media1.tenor.com/images/c0c1c5d15f8ad65a9f0aaf6c91a3811e/tenor.gif?itemid=13410974");
        images.add("https://media1.tenor.com/images/63924d378cf9dbd6f78c2927dde89107/tenor.gif?itemid=15049549");
        images.add("https://media1.tenor.com/images/a7de089b219dfdb945f28cc62063dbf2/tenor.gif?itemid=20663103");
        images.add("https://media1.tenor.com/images/6c08993a59a7b250cc710fc124cb1708/tenor.gif?itemid=16085501");
        images.add("https://media1.tenor.com/images/398c9c832335a13be124914c23e88fdf/tenor.gif?itemid=9939761");
        images.add("https://media1.tenor.com/images/8c1f6874db27c8227755a08b2b07740b/tenor.gif?itemid=10789367");
        images.add("https://media1.tenor.com/images/37b0ba8252f8698d23c83d889768540b/tenor.gif?itemid=19580650");

        embedBuilder.setDescription("**" + member.getEffectiveName() + "** is patting **" + target.getEffectiveName() + "**! :purple_heart: Nya~");
        embedBuilder.setImage(images.get(new Random().nextInt(images.size())));
        channel.sendMessage(embedBuilder.build()).queue();

    }

    @Override
    public String getName() {
        return "pat";
    }

    @Override
    public String getHelp() {
        return "Headpat another user.";
    }

    @Override
    public String getUsage() {
        return "pat [@user]";
    }

    @Override
    public boolean isNSFW() { return false; }

    @Override
    public CommandCategory getCategory() {
        return CommandCategory.ROLEPLAY;
    }

}
