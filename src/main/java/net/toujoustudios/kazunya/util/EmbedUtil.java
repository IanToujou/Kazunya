package net.toujoustudios.kazunya.util;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.toujoustudios.kazunya.config.Config;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class EmbedUtil {

    private static final Config config = Config.getDefault();

    private EmbedUtil() {}

    public static MessageEmbed build(@NotNull String title, @Nullable String description, @Nullable String image) {

        EmbedBuilder builder = new EmbedBuilder();
        builder.setTitle(title);
        if (description != null) builder.setDescription(description);
        if (image != null) builder.setImage(image);
        builder.setColor(ColorUtil.rgb(config.getString("format.color.default")));
        return builder.build();

    }

}
