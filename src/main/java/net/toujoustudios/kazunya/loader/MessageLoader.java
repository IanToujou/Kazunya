package net.toujoustudios.kazunya.loader;

import net.toujoustudios.kazunya.config.Config;
import net.toujoustudios.kazunya.log.LogLevel;
import net.toujoustudios.kazunya.log.Logger;
import net.toujoustudios.kazunya.model.Message;
import net.toujoustudios.kazunya.repository.MessageRepository;

import java.util.List;

public class MessageLoader {

    private static final Config config = Config.getFile("messages.yml");

    public static void initialize() {

        int amount = 0;
        String[] keys = {
                "interaction.hug"
        };

        for (String key : keys) {
            List<Message> list = config.getMessageList(key);
            MessageRepository.add(new MessageRepository(key, list));
            amount += list.size();
        }

        Logger.log(LogLevel.INFORMATION, "Loaded a total of " + amount + " messages.");

    }

}
