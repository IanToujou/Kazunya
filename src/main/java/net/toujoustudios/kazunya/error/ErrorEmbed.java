package net.toujoustudios.kazunya.error;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.interactions.commands.SlashCommandInteraction;
import net.dv8tion.jda.api.interactions.components.buttons.Button;
import net.toujoustudios.kazunya.command.CommandContext;
import net.toujoustudios.kazunya.config.Config;
import net.toujoustudios.kazunya.util.ColorUtil;

public class ErrorEmbed {

    static Config config = Config.getDefault();

    public static MessageEmbed buildError(ErrorType type) {
        EmbedBuilder embedBuilder = new EmbedBuilder();
        embedBuilder.setTitle(":x: **Something went wrong**");
        embedBuilder.setColor(ColorUtil.getFromRGBString(config.getString("format.color.error")));
        embedBuilder.setThumbnail(config.getString("assets.img.icon_error"));
        embedBuilder.setDescription("Oops! An error occurred while attempting to perform this action. Please review the details below.\n\n**Error Code:** `" + type.getCode() + "`\n**Description:** " + type.getDescription());
        return embedBuilder.build();
    }

    public static void sendError(CommandContext context, ErrorType type) {
        context.getInteraction().replyEmbeds(buildError(type)).addActionRow(Button.link(config.getString("link.help"), "Help")).setEphemeral(true).queue();
    }

    public static void sendError(SlashCommandInteraction interaction, ErrorType type) {
        interaction.replyEmbeds(buildError(type)).addActionRow(Button.link(config.getString("link.help"), "Help")).setEphemeral(true).queue();
    }

    public static void sendError(TextChannel channel, ErrorType type) {
        channel.sendMessageEmbeds(buildError(type)).queue();
    }

}
