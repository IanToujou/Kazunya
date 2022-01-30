package net.toujoustudios.kazunya.listener;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.toujoustudios.kazunya.color.ColorTools;
import net.toujoustudios.kazunya.config.Config;
import net.toujoustudios.kazunya.main.Main;
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

            String checkString = baseMessage.split("http")[1];

            if (baseMessage.contains("discord.com/") || baseMessage.contains("discord.gift/") || baseMessage.contains("discord.gg/") || baseMessage.contains("tenor.com/")) {
                return;
            }

            ArrayList<String> bannedWords = new ArrayList<>();
            bannedWords.add("nitro");
            bannedWords.add("nirto");
            bannedWords.add("nitr0");
            bannedWords.add("discrd");
            bannedWords.add("disc0rd");

            EmbedBuilder embedBuilder = new EmbedBuilder();
            embedBuilder.setColor(ColorTools.getFromRGBString(config.getString("format.color.default")));
            embedBuilder.setTitle(":warning: **Message Deleted**");
            embedBuilder.setDescription("Your message has been deleted for the following reason:\n`Potentially malicious or dangerous links.`\n\n*This message has been automatically reported to administrators.*");

            for (String all : bannedWords) {

                if (checkString.contains(all)) {

                    event.getMessage().delete().queue();
                    event.getChannel().sendMessage(embedBuilder.build()).queue();

                    embedBuilder.setTitle(":warning: **Scam Link Detection:**");
                    embedBuilder.setDescription("A message was flagged as scam and removed from a server.```" + rawMessage + "```");
                    User user = Main.getBot().getJDA().getUserById(config.getString("user.admin"));
                    if (user != null)
                        user.openPrivateChannel().flatMap(channel -> channel.sendMessage(embedBuilder.build())).queue();
                    break;

                }

            }

        }

    }

}
