package net.toujoustudios.kazunya.util;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.toujoustudios.kazunya.config.Config;

public class EmbedUtil {

    private static final Config config = Config.getDefault();

    private EmbedUtil() {}

    public static MessageEmbed build(String title, String description, String image) {

        EmbedBuilder builder = new EmbedBuilder();
        builder.setTitle(title);
        builder.setDescription(description);
        builder.setImage(image);
        builder.setColor(ColorUtil.rgb(config.getString("format.color.error")));
        return builder.build();

    }

}
