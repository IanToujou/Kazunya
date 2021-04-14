package net.toujoustudios.kazunya.command.list.music;

import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import com.sedmelluq.discord.lavaplayer.track.AudioTrackInfo;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.TextChannel;
import net.toujoustudios.kazunya.command.CommandCategory;
import net.toujoustudios.kazunya.command.CommandContext;
import net.toujoustudios.kazunya.command.ICommand;
import net.toujoustudios.kazunya.config.Config;
import net.toujoustudios.kazunya.music.GuildMusicManager;
import net.toujoustudios.kazunya.music.PlayerManager;
import net.toujoustudios.kazunya.string.StringTools;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;

/**
 * This file was created by IanToujou.
 * Date: 24.10.2020
 * Time: 12:57
 * Project: Kazunya
 */
public class QueueCommand implements ICommand {

    @Override
    public void handle(CommandContext context) {

        EmbedBuilder embedBuilder = new EmbedBuilder();
        TextChannel channel = context.getChannel();
        PlayerManager playerManager = PlayerManager.getInstance();
        GuildMusicManager musicManager = playerManager.getGuildMusicManager(context.getGuild());
        BlockingQueue<AudioTrack> queue = musicManager.scheduler.getQueue();
        List<String> args = context.getArgs();

        embedBuilder.setTitle("**__MUSIC QUEUE__**");
        embedBuilder.setColor(Config.DEFAULT_EMBED_COLOR);

        if(args.size() != 0) {

            embedBuilder.setTitle("**__ERROR__**");
            embedBuilder.setColor(Config.ERROR_EMBED_COLOR);
            embedBuilder.setDescription(":x: The command syntax is not correct.\nPlease use **" + Config.DEFAULT_PREFIX + getUsage() + "**");
            channel.sendMessage(embedBuilder.build()).queue();
            return;

        }

        if(queue.isEmpty()) {

            embedBuilder.setDescription("*The queue is empty.*");
            channel.sendMessage(embedBuilder.build()).queue();
            return;

        }

        int trackCount = Math.max(queue.size(), 20);
        List<AudioTrack> tracks = new ArrayList<>(queue);
        embedBuilder.addField(":information_source: Queue Information: ", "**Songs:** " + queue.size(), false);
        StringBuilder builder = new StringBuilder();

        for(int i = 0; i < trackCount; i++) {

            if(i > 9) break;

            if(tracks.get(i) == null) return;

            AudioTrack track = tracks.get(i);
            AudioTrackInfo info = track.getInfo();

            builder.append(String.format(

                    "**(" + (i + 1) + ")** %s [%s]\n\n", info.title, StringTools.formatTime(info.length)

            ));

        }

        embedBuilder.addField(":musical_note: Songs:", builder.toString(), false);
        channel.sendMessage(embedBuilder.build()).queue();

    }

    @Override
    public String getName() { return "queue"; }

    @Override
    public String getHelp() { return "Shows the current track queue."; }

    @Override
    public String getUsage() { return "queue"; }

    @Override
    public CommandCategory getCategory() { return CommandCategory.MUSIC; }

    @Override
    public boolean isNSFW() {
        return false;
    }

}
