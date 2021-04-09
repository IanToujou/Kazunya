package net.toujoustudios.kazunya.command.list.moderation;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.TextChannel;
import net.toujoustudios.kazunya.command.CommandCategory;
import net.toujoustudios.kazunya.command.CommandContext;
import net.toujoustudios.kazunya.command.ICommand;
import net.toujoustudios.kazunya.config.Config;
import net.toujoustudios.kazunya.guild.GuildManager;

import java.util.Collections;
import java.util.List;

/**
 * This file was created by IanToujou.
 * Date: 05.03.2021
 * Time: 07:59
 * Project: Kazunya
 */
public class GuildSettingsCommand implements ICommand {

    @Override
    public void handle(CommandContext context) {

        List<String> args = context.getArgs();
        TextChannel channel = context.getChannel();
        EmbedBuilder embedBuilder = new EmbedBuilder();

        embedBuilder.setColor(Config.DEFAULT_EMBED_COLOR);
        embedBuilder.setTitle("**__GUILD SETTINGS__**");

        if(args.size() == 0) {

            GuildManager guildManager = GuildManager.getGuild(context.getGuild().getId());

            embedBuilder.setDescription("Here is a list of all available settings.");
            embedBuilder.addField(":flag_white: Roles:", "**keepRoles [boolean]** - Currently: `" + guildManager.isRestoreRoles() + "`", false);

        } else if(args.size() == 1) {

            String entry = args.get(0);

        } else if(args.size() == 2) {

            String entry = args.get(0);
            String value = args.get(1);

        } else {

            embedBuilder.setTitle("**__ERROR__**");
            embedBuilder.setColor(Config.ERROR_EMBED_COLOR);
            embedBuilder.setDescription(":x: The command syntax is not correct.\nPlease use **" + Config.DEFAULT_PREFIX + getUsage() + "**");
            channel.sendMessage(embedBuilder.build()).queue();
            return;

        }

        channel.sendMessage(embedBuilder.build()).queue();

    }

    @Override
    public String getName() {
        return "guildsettings";
    }

    @Override
    public String getHelp() {
        return "Configure the guild settings.";
    }

    @Override
    public String getUsage() {
        return "guildsettings [setting] [value]";
    }

    @Override
    public CommandCategory getCategory() {
        return CommandCategory.MODERATION;
    }

    @Override
    public List<String> getAliases() {
        return Collections.singletonList("gs");
    }

}
