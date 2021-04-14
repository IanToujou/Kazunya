package net.toujoustudios.kazunya.command.list.music;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.GuildVoiceState;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.VoiceChannel;
import net.dv8tion.jda.api.managers.AudioManager;
import net.toujoustudios.kazunya.command.CommandCategory;
import net.toujoustudios.kazunya.command.CommandContext;
import net.toujoustudios.kazunya.command.ICommand;
import net.toujoustudios.kazunya.config.Config;
import net.toujoustudios.kazunya.music.PlayerManager;

import java.awt.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

/**
 * This file was created by IanToujou.
 * Date: 24.10.2020
 * Time: 12:57
 * Project: Kazunya
 */
public class PlayCommand implements ICommand {

    @Override
    public void handle(CommandContext context) {

        EmbedBuilder embedBuilder = new EmbedBuilder();
        TextChannel channel = context.getChannel();
        Member member = context.getMember();
        Member selfMember = context.getSelfMember();
        List<String> args = context.getArgs();
        PlayerManager playerManager = PlayerManager.getInstance();
        AudioManager audioManager = context.getGuild().getAudioManager();

        embedBuilder.setTitle("**__MUSIC__**");
        embedBuilder.setColor(Config.DEFAULT_EMBED_COLOR);

        if(args.isEmpty()) {

            embedBuilder.setTitle("**__ERROR__**");
            embedBuilder.setColor(Color.RED);
            embedBuilder.setDescription(":x: The command syntax is not correct.\nPlease use **" + Config.DEFAULT_PREFIX + getUsage() + "**");
            channel.sendMessage(embedBuilder.build()).queue();
            return;

        }

        String input = String.join(" ", args);

        if(!isURL(input)) {

            embedBuilder.setTitle("**__ERROR__**");
            embedBuilder.setColor(Color.RED);
            embedBuilder.setDescription(":x: Please enter a valid SoundCloud, YouTube, Bandcamp, Vimeo, Twitch or direct link.");
            channel.sendMessage(embedBuilder.build()).queue();
            return;

        }

        GuildVoiceState memberVoiceState = member.getVoiceState();

        if(!memberVoiceState.inVoiceChannel()) {

            embedBuilder.setTitle("**__ERROR__**");
            embedBuilder.setColor(Config.ERROR_EMBED_COLOR);
            embedBuilder.setDescription(":x: You are not in a voice channel.");
            channel.sendMessage(embedBuilder.build()).queue();
            return;

        }

        VoiceChannel voiceChannel = memberVoiceState.getChannel();

        if(!selfMember.hasPermission(voiceChannel, Permission.VOICE_CONNECT)) {

            embedBuilder.setTitle("**__ERROR__**");
            embedBuilder.setColor(Config.ERROR_EMBED_COLOR);
            embedBuilder.setDescription(":x: I don't have the permission to join your voice channel.");
            channel.sendMessage(embedBuilder.build()).queue();
            return;

        }

        if(!audioManager.isConnected()) audioManager.openAudioConnection(voiceChannel);

        playerManager.loadAndPlay(channel, input);

    }

    @Override
    public String getName() { return "play"; }

    @Override
    public String getHelp() { return "Play a song from SoundCloud, YouTube, Bandcamp, Vimeo, Twitch or via a direct link."; }

    @Override
    public String getUsage() { return "play [link]"; }

    @Override
    public CommandCategory getCategory() { return CommandCategory.MUSIC; }

    @Override
    public boolean isNSFW() {
        return false;
    }

    private boolean isURL(String input) {

        try {

            new URL(input);
            return true;

        } catch(MalformedURLException exception) {

            return false;

        }

    }

}
