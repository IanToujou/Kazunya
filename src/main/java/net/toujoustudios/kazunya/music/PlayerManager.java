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
import net.dv8tion.jda.api.entities.TextChannel;
import net.toujoustudios.kazunya.config.Config;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

/**
 * This file was created by IanToujou.
 * Date: 24.10.2020
 * Time: 12:57
 * Project: Nyunyun
 */
public class PlayerManager {

    private static PlayerManager INSTANCE;
    private final AudioPlayerManager playerManager;
    private final Map<Long, GuildMusicManager> musicManagers;

    private PlayerManager() {

        this.musicManagers = new HashMap<>();
        this.playerManager = new DefaultAudioPlayerManager();
        AudioSourceManagers.registerRemoteSources(playerManager);
        AudioSourceManagers.registerLocalSource(playerManager);

    }

    public synchronized GuildMusicManager getGuildMusicManager(Guild guild) {

        long guildId = guild.getIdLong();
        GuildMusicManager musicManager = musicManagers.get(guildId);

        if(musicManager == null) {

            musicManager = new GuildMusicManager(playerManager);
            musicManagers.put(guildId, musicManager);

        }

        guild.getAudioManager().setSendingHandler(musicManager.getSendHandler());

        return musicManager;

    }

    public void loadAndPlay(TextChannel channel, String trackUrl) {

        GuildMusicManager musicManager = getGuildMusicManager(channel.getGuild());

        playerManager.loadItemOrdered(musicManager, trackUrl, new AudioLoadResultHandler() {

            @Override
            public void trackLoaded(AudioTrack audioTrack) {

                EmbedBuilder embedBuilder = new EmbedBuilder();
                embedBuilder.setColor(Config.DEFAULT_EMBED_COLOR);
                embedBuilder.setTitle("**__MUSIC__**");
                embedBuilder.setDescription(":arrow_forward: Now playing **" + audioTrack.getInfo().title + "**.");

                channel.sendMessage(embedBuilder.build()).queue();

                play(musicManager, audioTrack);

            }

            @Override
            public void playlistLoaded(AudioPlaylist playlist) {

                AudioTrack firstTrack = playlist.getSelectedTrack();
                EmbedBuilder embedBuilder = new EmbedBuilder();

                if(firstTrack == null) firstTrack = playlist.getTracks().remove(0);

                embedBuilder.setTitle("**__MUSIC__**");
                embedBuilder.setColor(Color.MAGENTA);
                embedBuilder.setDescription(":arrow_forward: Now playing **" + firstTrack.getInfo().title + "** and added another **" + playlist.getTracks().size() + "** songs from the playlist.");
                channel.sendMessage(embedBuilder.build()).queue();

                play(musicManager, firstTrack);
                TrackScheduler trackScheduler = musicManager.scheduler;

                playlist.getTracks().remove(0);

                for(AudioTrack audioTrack : playlist.getTracks()) {

                    trackScheduler.queue(audioTrack);

                }

            }

            @Override
            public void noMatches() {

                EmbedBuilder embedBuilder = new EmbedBuilder();

                embedBuilder.setTitle("**__ERROR__**");
                embedBuilder.setColor(Config.ERROR_EMBED_COLOR);
                embedBuilder.setDescription(":x: I could not find any matches.");
                channel.sendMessage(embedBuilder.build()).queue();

            }

            @Override
            public void loadFailed(FriendlyException e) {

                EmbedBuilder embedBuilder = new EmbedBuilder();

                embedBuilder.setTitle("**__ERROR__**");
                embedBuilder.setColor(Config.ERROR_EMBED_COLOR);
                embedBuilder.setDescription(":x: The song could not be loaded.");
                channel.sendMessage(embedBuilder.build()).queue();

            }

        });

    }

    private void play(GuildMusicManager musicManager, AudioTrack track) {

        musicManager.scheduler.queue(track);

    }

    public static synchronized PlayerManager getInstance() {

        if(INSTANCE == null) {

            INSTANCE = new PlayerManager();

        }

        return INSTANCE;

    }

}
