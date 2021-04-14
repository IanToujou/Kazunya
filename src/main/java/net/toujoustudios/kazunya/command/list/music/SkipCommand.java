package net.toujoustudios.kazunya.command.list.music;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.TextChannel;
import net.toujoustudios.kazunya.command.CommandCategory;
import net.toujoustudios.kazunya.command.CommandContext;
import net.toujoustudios.kazunya.command.ICommand;
import net.toujoustudios.kazunya.config.Config;
import net.toujoustudios.kazunya.music.GuildMusicManager;
import net.toujoustudios.kazunya.music.PlayerManager;
import net.toujoustudios.kazunya.music.TrackScheduler;

import java.util.List;

/**
 * This file was created by IanToujou.
 * Date: 24.10.2020
 * Time: 12:57
 * Project: Kazunya
 */
public class SkipCommand implements ICommand {

    @Override
    public void handle(CommandContext context) {

        EmbedBuilder embedBuilder = new EmbedBuilder();
        TextChannel channel = context.getChannel();
        PlayerManager playerManager = PlayerManager.getInstance();
        GuildMusicManager musicManager = playerManager.getGuildMusicManager(context.getGuild());
        TrackScheduler trackScheduler = musicManager.scheduler;
        AudioPlayer player = musicManager.player;
        List<String> args = context.getArgs();

        embedBuilder.setTitle("**__MUSIC__**");
        embedBuilder.setColor(Config.DEFAULT_EMBED_COLOR);

        if(args.size() != 0) {

            embedBuilder.setTitle("**__ERROR__**");
            embedBuilder.setColor(Config.ERROR_EMBED_COLOR);
            embedBuilder.setDescription(":x: The command syntax is not correct.\nPlease use **" + Config.DEFAULT_PREFIX + getUsage() + "**");
            channel.sendMessage(embedBuilder.build()).queue();
            return;

        }

        if(player.getPlayingTrack() == null) {

            embedBuilder.setTitle("**__ERROR__**");
            embedBuilder.setColor(Config.ERROR_EMBED_COLOR);
            embedBuilder.setDescription(":x: I'm not playing any music right now.");
            channel.sendMessage(embedBuilder.build()).queue();
            return;

        }

        trackScheduler.nextTrack();
        embedBuilder.setDescription(":track_next: Skipped the current track.");
        channel.sendMessage(embedBuilder.build()).queue();

    }

    @Override
    public String getName() { return "skip"; }

    @Override
    public String getHelp() { return "Skips the current song."; }

    @Override
    public String getUsage() { return "skip"; }

    @Override
    public CommandCategory getCategory() { return CommandCategory.MUSIC; }

    @Override
    public boolean isNSFW() {
        return false;
    }

}
