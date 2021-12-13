package net.toujoustudios.kazunya.command.list.music;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.GuildVoiceState;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.VoiceChannel;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import net.dv8tion.jda.api.managers.AudioManager;
import net.toujoustudios.kazunya.command.CommandCategory;
import net.toujoustudios.kazunya.command.CommandContext;
import net.toujoustudios.kazunya.command.ICommand;
import net.toujoustudios.kazunya.config.Config;
import net.toujoustudios.kazunya.error.ErrorEmbed;
import net.toujoustudios.kazunya.error.ErrorType;
import net.toujoustudios.kazunya.music.MusicPlayerManager;
import net.toujoustudios.kazunya.network.URLManager;

import java.util.ArrayList;
import java.util.List;

/**
 * This file has been created by Ian Toujou.
 * Project: Kazunya
 * Date: 13/12/2021
 * Time: 21:02
 */
public class PlayCommand implements ICommand {


    private final Config config;

    public PlayCommand() {
        config = Config.getDefault();
    }

    @Override
    public void handle(CommandContext context) {

        List<OptionMapping> args = context.getArgs();
        Member member = context.getMember();
        EmbedBuilder embedBuilder = new EmbedBuilder();

        if (args.size() != 1) {
            ErrorEmbed.sendError(context, ErrorType.COMMAND_INVALID_SYNTAX);
            return;
        }

        MusicPlayerManager musicPlayerManager = MusicPlayerManager.getInstance();
        AudioManager audioManager = context.getGuild().getAudioManager();
        String input = args.get(0).getAsString();

        if(!URLManager.isURL(input)) {
            ErrorEmbed.sendError(context, ErrorType.COMMAND_INVALID_URL);
            return;
        }

        GuildVoiceState memberVoiceState = member.getVoiceState();

        if (memberVoiceState == null || !memberVoiceState.inVoiceChannel()) {
            ErrorEmbed.sendError(context, ErrorType.AUDIO_USER_NOT_IN_CHANNEL);
            return;
        }

        VoiceChannel voiceChannel = memberVoiceState.getChannel();

        if (voiceChannel == null || !context.getSelfMember().hasPermission(voiceChannel, Permission.VOICE_CONNECT)) {
            ErrorEmbed.sendError(context, ErrorType.PERMISSION_VOICE_CHANNEL_NOT_AVAILABLE);
            return;
        }

        if (!audioManager.isConnected()) audioManager.openAudioConnection(voiceChannel);
        musicPlayerManager.loadAndPlay(context.getEvent(), input);

    }

    @Override
    public String getName() {
        return "play";
    }

    @Override
    public String getDescription() {
        return "Play a song from SoundCloud, YouTube, Bandcamp, Vimeo, Twitch or via a direct link.";
    }

    @Override
    public List<OptionData> getOptions() {
        List<OptionData> optionData = new ArrayList<>();
        optionData.add(new OptionData(OptionType.USER, "link", "The link of the song. It can be either SoundCloud, YouTube, Bandcamp, Vimeo, Twitch or a direct link.", true));
        return optionData;
    }

    @Override
    public CommandCategory getCategory() {
        return CommandCategory.MUSIC;
    }

}
