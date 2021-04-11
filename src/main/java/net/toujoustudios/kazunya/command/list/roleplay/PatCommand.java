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
        images.add("https://media1.tenor.com/images/da8f0e8dd1a7f7db5298bda9cc648a9a/tenor.gif");
        images.add("https://media1.tenor.com/images/e5fff7bc2fc641f8ed0cba92475ea741/tenor.gif");
        images.add("https://media1.tenor.com/images/55df4c5fb33f3cd05b2f1ac417e050d9/tenor.gif");
        images.add("https://media1.tenor.com/images/d9b480bcd392d05426ae6150e986bbf0/tenor.gif");
        images.add("https://media1.tenor.com/images/116fe7ede5b7976920fac3bf8067d42b/tenor.gif");
        images.add("https://media1.tenor.com/images/6151c42c94df654b1c7de2fdebaa6bd1/tenor.gif");
        images.add("https://media1.tenor.com/images/90712ed3a99db973ec92383a3c6a8767/tenor.gif");
        images.add("https://media1.tenor.com/images/f5176d4c5cbb776e85af5dcc5eea59be/tenor.gif");
        images.add("https://media1.tenor.com/images/daa885ec8a9cfa4107eb966df05ba260/tenor.gif");
        images.add("https://media1.tenor.com/images/a58b340308475e34e324ea437bb40641/tenor.gif");
        images.add("https://media1.tenor.com/images/fdf1766623d123680a3db459aa63a408/tenor.gif");
        images.add("https://media1.tenor.com/images/943a52d38d896bda734a6396b1ffca89/tenor.gif");
        images.add("https://media1.tenor.com/images/fa9ad7f4ecfad744aec37241cce2cecc/tenor.gif");
        images.add("https://media1.tenor.com/images/5466adf348239fba04c838639525c28a/tenor.gif");
        images.add("https://media1.tenor.com/images/dc61bf036b96b9a321943493c55ad8a4/tenor.gif");
        images.add("https://media1.tenor.com/images/291ea37382e1d6cd33349c50a398b6b9/tenor.gif");
        images.add("https://media1.tenor.com/images/1d37a873edfeb81a1f5403f4a3bfa185/tenor.gif");
        images.add("https://media1.tenor.com/images/d3c117054fb924d66c75169ff158c811/tenor.gif");
        images.add("https://media1.tenor.com/images/a5bc0631e178956d3f0c5419ddd7e9c7/tenor.gif");
        images.add("https://media1.tenor.com/images/4989f50281ecd19a4480b81b5cf75f90/tenor.gif");
        images.add("https://media1.tenor.com/images/61187dd8c7985c443bf9cd39bc310c02/tenor.gif");
        images.add("https://media1.tenor.com/images/89f52641cbe7f4644efeee383c73942b/tenor.gif");
        images.add("https://media1.tenor.com/images/c0c1c5d15f8ad65a9f0aaf6c91a3811e/tenor.gif");
        images.add("https://media1.tenor.com/images/8b5711095b0ba786c43b617bf9c675dd/tenor.gif");
        images.add("https://media1.tenor.com/images/66d2feb19f872fe3d284855cb5ec94b9/tenor.gif");
        images.add("https://media1.tenor.com/images/ec5a0df5fd3d93efa362f7a883bc82ae/tenor.gif");
        images.add("https://media1.tenor.com/images/398c9c832335a13be124914c23e88fdf/tenor.gif");
        images.add("https://media1.tenor.com/images/565253d431d73c67615120037bade555/tenor.gif");
        images.add("https://media1.tenor.com/images/e4fdebb36ec312dc9e10df21dca7154e/tenor.gif");
        images.add("https://media1.tenor.com/images/0a144a04199941c2e265d41fc23f88af/tenor.gif");
        images.add("https://media1.tenor.com/images/835cfeb78a9ddf1988969431f8368e4b/tenor.gif");

        embedBuilder.setDescription("**" + context.getMember().getEffectiveName() + "** is patting **" + target.getEffectiveName() + "**! :purple_heart: Nya~");
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
        return "pat";
    }

    @Override
    public CommandCategory getCategory() {
        return CommandCategory.ROLEPLAY;
    }

}
