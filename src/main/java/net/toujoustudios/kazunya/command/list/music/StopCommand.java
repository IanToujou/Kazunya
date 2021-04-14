package net.toujoustudios.kazunya.command.list.music;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.VoiceChannel;
import net.dv8tion.jda.api.managers.AudioManager;
import net.toujoustudios.kazunya.command.CommandCategory;
import net.toujoustudios.kazunya.command.CommandContext;
import net.toujoustudios.kazunya.command.ICommand;
import net.toujoustudios.kazunya.config.Config;
import net.toujoustudios.kazunya.music.GuildMusicManager;
import net.toujoustudios.kazunya.music.PlayerManager;

import java.awt.*;
import java.util.List;

/**
 * This file was created by IanToujou.
 * Date: 24.10.2020
 * Time: 12:57
 * Project: Kazunya
 */
public class StopCommand implements ICommand {

    @Override
    public void handle(CommandContext context) {

        EmbedBuilder embedBuilder = new EmbedBuilder();
        TextChannel channel = context.getChannel();
        PlayerManager playerManager = PlayerManager.getInstance();
        Guild guild = context.getGuild();
        AudioManager audioManager = guild.getAudioManager();
        Member member = context.getMember();
        List<String> args = context.getArgs();
        GuildMusicManager musicManager = playerManager.getGuildMusicManager(guild);

        embedBuilder.setColor(Config.DEFAULT_EMBED_COLOR);
        embedBuilder.setTitle("**__MUSIC__**");

        if(args.size() != 0) {

            embedBuilder.setTitle("**__ERROR__**");
            embedBuilder.setColor(Config.ERROR_EMBED_COLOR);
            embedBuilder.setDescription(":x: The command syntax is not correct.\nPlease use **" + Config.DEFAULT_PREFIX + getUsage() + "**");
            channel.sendMessage(embedBuilder.build()).queue();
            return;

        }

        if(!audioManager.isConnected()) {

            embedBuilder.setTitle("**__ERROR__**");
            embedBuilder.setColor(Color.RED);
            embedBuilder.setDescription(":x: The bot is not connected to any voice channel.");
            channel.sendMessage(embedBuilder.build()).queue();
            return;

        }

        VoiceChannel voiceChannel = audioManager.getConnectedChannel();

        if(!voiceChannel.getMembers().contains(member)) {

            embedBuilder.setTitle("**__ERROR__**");
            embedBuilder.setColor(Color.RED);
            embedBuilder.setDescription(":x: You are not connected to any voice channel.");
            channel.sendMessage(embedBuilder.build()).queue();
            return;

        }

        embedBuilder.setDescription(":stop_button: Stopped playing songs and cleared the queue.");
        audioManager.closeAudioConnection();
        musicManager.scheduler.getQueue().clear();
        musicManager.player.stopTrack();
        musicManager.player.setPaused(false);

        channel.sendMessage(embedBuilder.build()).queue();

    }

    @Override
    public String getName() { return "stop"; }

    @Override
    public String getHelp() { return "Stop the current track."; }

    @Override
    public String getUsage() { return "stop"; }

    @Override
    public CommandCategory getCategory() { return CommandCategory.MUSIC; }

    @Override
    public boolean isNSFW() {
        return false;
    }

}
