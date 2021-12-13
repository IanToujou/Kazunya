package net.toujoustudios.kazunya.command.list.music;

import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import net.toujoustudios.kazunya.command.CommandCategory;
import net.toujoustudios.kazunya.command.CommandContext;
import net.toujoustudios.kazunya.command.ICommand;

import java.util.List;

/**
 * This file has been created by Ian Toujou.
 * Project: Kazunya
 * Date: 13/12/2021
 * Time: 21:02
 */
public class PlayCommand implements ICommand {


    @Override
    public void handle(CommandContext context) {

    }

    @Override
    public String getName() {
        return "play";
    }

    @Override
    public String getDescription() {
        return "Play a song from SoundCloud, YouTube, Bandcamp, Vimeo, Twitch or via a direct link.Play a song from YouTube, Bandcamp, Soundcloud, ";
    }

    @Override
    public List<OptionData> getOptions() {
        return null;
    }

    @Override
    public CommandCategory getCategory() {
        return CommandCategory.MUSIC;
    }
}
