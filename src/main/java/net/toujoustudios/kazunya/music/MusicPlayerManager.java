package net.toujoustudios.kazunya.music;

import com.sedmelluq.discord.lavaplayer.player.AudioLoadResultHandler;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.player.DefaultAudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.source.AudioSourceManagers;
import com.sedmelluq.discord.lavaplayer.tools.FriendlyException;
import com.sedmelluq.discord.lavaplayer.track.AudioPlaylist;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.toujoustudios.kazunya.util.ColorUtil;
import net.toujoustudios.kazunya.config.Config;
import net.toujoustudios.kazunya.error.ErrorEmbed;
import net.toujoustudios.kazunya.error.ErrorType;

import java.util.HashMap;
import java.util.Map;

/**
 * This file has been created by Ian Toujou.
 * Project: Kazunya
 * Date: 11/12/2021
 * Time: 12:04
 */
public class MusicPlayerManager {

    private static MusicPlayerManager INSTANCE;
    private final AudioPlayerManager playerManager;
    private final Map<Long, GuildMusicManager> musicManagers;
    private final Config config;

    private MusicPlayerManager() {

        this.musicManagers = new HashMap<>();
        this.playerManager = new DefaultAudioPlayerManager();
        AudioSourceManagers.registerRemoteSources(playerManager);
        AudioSourceManagers.registerLocalSource(playerManager);
        config = Config.getDefault();

    }

    public static synchronized MusicPlayerManager getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new MusicPlayerManager();
        }
        return INSTANCE;
    }

    public synchronized GuildMusicManager getGuildMusicManager(Guild guild) {

        long guildId = guild.getIdLong();
        GuildMusicManager musicManager = musicManagers.get(guildId);

        if (musicManager == null) {
            musicManager = new GuildMusicManager(playerManager);
            musicManagers.put(guildId, musicManager);
        }

        guild.getAudioManager().setSendingHandler(musicManager.getSendHandler());
        return musicManager;

    }

    public void loadAndPlay(SlashCommandEvent event, String trackUrl) {

        if (event.getGuild() == null) return;
        GuildMusicManager musicManager = getGuildMusicManager(event.getGuild());

        EmbedBuilder embedBuilder = new EmbedBuilder();
        embedBuilder.setColor(ColorUtil.getFromRGBString(config.getString("format.color.default")));
        embedBuilder.setTitle("**:musical_note: Music**");

        playerManager.loadItemOrdered(musicManager, trackUrl, new AudioLoadResultHandler() {

            @Override
            public void trackLoaded(AudioTrack audioTrack) {

                embedBuilder.setDescription(":arrow_forward: Now playing `" + audioTrack.getInfo().title + "`.");
                event.replyEmbeds(embedBuilder.build()).queue();
                play(musicManager, audioTrack);

            }

            @Override
            public void playlistLoaded(AudioPlaylist playlist) {

                AudioTrack firstTrack = playlist.getSelectedTrack();

                if (firstTrack == null) firstTrack = playlist.getTracks().remove(0);

                embedBuilder.setDescription(":arrow_forward: Now playing `" + firstTrack.getInfo().title + "` and added another `" + playlist.getTracks().size() + "` songs from the playlist.");
                event.replyEmbeds(embedBuilder.build()).queue();

                play(musicManager, firstTrack);
                TrackScheduler trackScheduler = musicManager.scheduler;

                playlist.getTracks().remove(0);

                for (AudioTrack audioTrack : playlist.getTracks()) {
                    trackScheduler.queue(audioTrack);
                }

            }

            @Override
            public void noMatches() {
                ErrorEmbed.sendError(event, ErrorType.COMMAND_INVALID_SEARCH);
            }

            @Override
            public void loadFailed(FriendlyException e) {
                ErrorEmbed.sendError(event, ErrorType.AUDIO_UNABLE_TO_LOAD);
            }

        });

    }

    private void play(GuildMusicManager musicManager, AudioTrack track) {

        musicManager.scheduler.queue(track);

    }

}
