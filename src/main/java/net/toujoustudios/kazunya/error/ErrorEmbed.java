package net.toujoustudios.kazunya.error;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.interactions.components.Button;
import net.toujoustudios.kazunya.command.CommandContext;
import net.toujoustudios.kazunya.config.Config;
import net.toujoustudios.kazunya.util.ColorUtil;

/**
 * This file has been created by Ian Toujou.
 * Project: Kazunya
 * Date: 26/08/2021
 * Time: 23:11
 */
public class ErrorEmbed {

    static Config config = Config.getDefault();

    public static MessageEmbed buildError(ErrorType type) {
        EmbedBuilder embedBuilder = new EmbedBuilder();
        embedBuilder.setTitle(":x: **Something went wrong**");
        embedBuilder.setColor(ColorUtil.getFromRGBString(config.getString("format.color.error")));
        embedBuilder.setThumbnail(config.getString("assets.icon.error"));
        embedBuilder.setDescription("Oops! An error occurred while attempting to perform this action. Please review the details below.\n\n**Error Code:** `" + type.getCode() + "`\n**Description:** " + type.getDescription());
        return embedBuilder.build();
    }

    public static void sendError(CommandContext context, ErrorType type) {
        context.getEvent().replyEmbeds(buildError(type)).addActionRow(Button.link(config.getString("link.help"), "Help")).setEphemeral(true).queue();
    }

    public static void sendError(SlashCommandEvent event, ErrorType type) {
        event.replyEmbeds(buildError(type)).addActionRow(Button.link(config.getString("link.help"), "Help")).setEphemeral(true).queue();
    }

}
