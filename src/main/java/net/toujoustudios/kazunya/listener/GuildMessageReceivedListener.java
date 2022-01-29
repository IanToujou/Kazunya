package net.toujoustudios.kazunya.listener;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.toujoustudios.kazunya.color.ColorTools;
import net.toujoustudios.kazunya.config.Config;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

/**
 * This file has been created by Ian Toujou.
 * Project: Kazunya
 * Date: 09/01/2022
 * Time: 17:20
 */
public class GuildMessageReceivedListener extends ListenerAdapter {

    private final Config config = Config.getDefault();

    @Override
    public void onGuildMessageReceived(@NotNull GuildMessageReceivedEvent event) {

        String rawMessage = event.getMessage().getContentRaw();
        String baseMessage = rawMessage.toLowerCase();

        if (baseMessage.contains("http://") || baseMessage.contains("https://")) {

            if (baseMessage.startsWith("https://discord.com/") || baseMessage.startsWith("https://discord.gift/") || baseMessage.startsWith("https://discord.gg/")) {
                return;
            }

            ArrayList<String> bannedWords = new ArrayList<>();
            bannedWords.add("discord");
            bannedWords.add("gift");
            bannedWords.add("nitro");
            bannedWords.add("nitr0");
            bannedWords.add("g1ft");
            bannedWords.add("discrd");
            bannedWords.add("disc0rd");

            EmbedBuilder embedBuilder = new EmbedBuilder();
            embedBuilder.setColor(ColorTools.getFromRGBString(config.getString("format.color.default")));
            embedBuilder.setTitle(":warning: **Message Deleted**");
            embedBuilder.setDescription("Your message has been deleted for the following reason:\n`Potentially malicious or dangerous links.`\n\n*This message has been automatically reported to administrators.*");

            for (String all : bannedWords) {

                if (baseMessage.contains(all)) {
                    event.getChannel().sendMessage(embedBuilder.build()).queue();
                    event.getMessage().delete().queue();
                    break;
                }

            }

        }

    }

}
