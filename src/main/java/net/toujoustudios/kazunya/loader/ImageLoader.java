package net.toujoustudios.kazunya.loader;

import net.toujoustudios.kazunya.config.Config;
import net.toujoustudios.kazunya.model.Image;
import net.toujoustudios.kazunya.log.LogLevel;
import net.toujoustudios.kazunya.log.Logger;
import net.toujoustudios.kazunya.repository.ImageRepository;

import java.util.List;

public class ImageLoader {

    private static final Config config = Config.getFile("images.yml");

    public static void initialize() {

        int amount = 0;
        String[] keys = {
                "interaction.hug"
        };

        for (String key : keys) {
            List<Image> list = config.getImageList(key);
            ImageRepository.add(new ImageRepository(key, list));
            amount += list.size();
        }

        Logger.log(LogLevel.INFORMATION, "Loaded a total of " + amount + " images.");

    }

}
