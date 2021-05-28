package net.toujoustudios.kazunya.command.list.unlisted;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.TextChannel;
import net.toujoustudios.kazunya.command.CommandCategory;
import net.toujoustudios.kazunya.command.CommandContext;
import net.toujoustudios.kazunya.command.ICommand;
import net.toujoustudios.kazunya.config.Config;

import java.util.List;

/**
 * This file was created by IanToujou.
 * Date: 06.02.2021
 * Time: 00:20
 * Project: Kazunya
 */
public class ExecuteCommand implements ICommand {

    @Override
    public void handle(CommandContext context) {

        if(!context.getMember().getId().equals(Config.DEFAULT_ADMIN_USER)) {

            return;

        }

        List<String> args = context.getArgs();
        TextChannel channel = context.getChannel();
        EmbedBuilder embedBuilder = new EmbedBuilder();

        if(args.size() != 1) {

            embedBuilder.setTitle("**__ERROR__**");
            embedBuilder.setColor(Config.ERROR_EMBED_COLOR);
            embedBuilder.setDescription(":x: The command syntax is not correct.\nPlease use **" + Config.DEFAULT_PREFIX + getUsage() + "**");
            channel.sendMessage(embedBuilder.build()).queue();
            return;

        }

        if(args.get(0).equals("notifications_games")) {

            //Executable Code
            embedBuilder.setTitle("**__NOTIFICATIONS: GAMES__**");

            StringBuilder builder;
            builder = new StringBuilder();

            builder.append("Choose what game newsletter you want to subscribe.\n\n");
            builder.append(":gift: `Free Games Newsletter`\n");
            builder.append("<:genshin_icon:821658069460844562> `Genshin Impact Newsletter`\n\n");
            builder.append("*Note: Please react with the roles listed here.*");

            embedBuilder.setDescription(builder.toString());
            embedBuilder.setColor(Config.DEFAULT_EMBED_COLOR);
            embedBuilder.setThumbnail("https://user-images.githubusercontent.com/44029196/111436198-fcbe9e80-8701-11eb-9c84-6b4647366dab.png");

        } else if(args.get(0).equals("aboutme_gender")) {

            //Executable Code
            embedBuilder.setTitle("**__ABOUT ME: GENDER__**");

            StringBuilder builder;
            builder = new StringBuilder();

            builder.append("Choose your gender.\n\n");
            builder.append(":male_sign: `Male`\n");
            builder.append(":female_sign: `Female`\n");
            builder.append(":transgender_symbol: `Transgender`\n");
            builder.append(":twisted_rightwards_arrows: `Other`\n\n");
            builder.append("*Note: Please react with the roles listed here.*");

            embedBuilder.setDescription(builder.toString());
            embedBuilder.setColor(Config.DEFAULT_EMBED_COLOR);
            embedBuilder.setThumbnail("https://user-images.githubusercontent.com/44029196/119967726-f758bc80-bfac-11eb-8812-5784022ffa54.png");

        } else if(args.get(0).equals("aboutme_age")) {

            //Executable Code
            embedBuilder.setTitle("**__ABOUT ME: AGE__**");

            StringBuilder builder;
            builder = new StringBuilder();

            builder.append("Choose your age.\n\n");
            builder.append(":yellow_circle: `13-15`\n");
            builder.append(":orange_circle: `16-18`\n");
            builder.append(":green_circle: `19-21`\n");
            builder.append(":blue_circle: `22+`\n\n");
            builder.append("*Note: Please react with the roles listed here.*");

            embedBuilder.setDescription(builder.toString());
            embedBuilder.setColor(Config.DEFAULT_EMBED_COLOR);
            embedBuilder.setThumbnail("https://user-images.githubusercontent.com/44029196/119991890-3fd2a300-bfca-11eb-8970-14f511630871.png");

        }else if(args.get(0).equals("aboutme_hobbies")) {

            //Executable Code
            embedBuilder.setTitle("**__ABOUT ME: HOBBIES__**");

            StringBuilder builder;
            builder = new StringBuilder();

            builder.append("Choose some hobbies you're interested in.\n\n");
            builder.append(":flag_jp: `Anime & Manga`\n");
            builder.append(":blue_book: `Books`\n");
            builder.append(":musical_note: `Music`\n");
            builder.append(":camera: `Photography`\n");
            builder.append(":computer: `Programming`\n");
            builder.append(":floppy_disk: `Technology`\n");
            builder.append(":video_game: `Gaming`\n");
            builder.append(":art: `Drawing`\n");
            builder.append(":clapper: `Video Editing (VFX)`\n");
            builder.append(":sparkles: `Graphic Design (GFX)`\n");
            builder.append(":soccer: `Sports`\n");
            builder.append(":shallow_pan_of_food: `Cooking`\n\n");
            builder.append("*Note: Please react with the roles listed here.*");

            embedBuilder.setDescription(builder.toString());
            embedBuilder.setColor(Config.DEFAULT_EMBED_COLOR);
            embedBuilder.setThumbnail("https://user-images.githubusercontent.com/44029196/119993938-6a256000-bfcc-11eb-8372-a5dd17878b3b.png");

        }

        context.getChannel().sendMessage(embedBuilder.build()).queue();

    }

    @Override
    public String getName() {
        return "execute";
    }

    @Override
    public String getHelp() { return "Execute a code."; }

    @Override
    public String getUsage() { return "execute"; }

    @Override
    public boolean isNSFW() { return false; }

    @Override
    public CommandCategory getCategory() { return CommandCategory.UNLISTED; }

}
