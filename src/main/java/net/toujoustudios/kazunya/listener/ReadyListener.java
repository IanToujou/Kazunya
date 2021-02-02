package net.toujoustudios.kazunya.listener;

import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.toujoustudios.kazunya.log.LogLevel;
import net.toujoustudios.kazunya.log.Logger;

import javax.annotation.Nonnull;

/**
 * This file was created by IanToujou.
 * Date: 01.01.2021
 * Time: 03:09
 * Project: Kazunya
 */
public class ReadyListener extends ListenerAdapter {

    @Override
    public void onReady(@Nonnull ReadyEvent event) {

        Logger.log(LogLevel.INFORMATION, "The bot has been started.");

    }

}
