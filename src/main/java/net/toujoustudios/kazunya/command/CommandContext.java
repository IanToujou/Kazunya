package net.toujoustudios.kazunya.command;

import me.duncte123.botcommons.commands.ICommandContext;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.util.List;

/**
 * This file was created by IanToujou.
 * Date: 02.02.2021
 * Time: 08:46
 * Project: Kazunya
 */
public class CommandContext implements ICommandContext {

    private final GuildMessageReceivedEvent event;
    private final List<String> args;

    public CommandContext(GuildMessageReceivedEvent event, List<String> args) {

        this.event = event;
        this.args = args;

    }

    @Override
    public Guild getGuild() { return this.getEvent().getGuild(); }

    @Override
    public GuildMessageReceivedEvent getEvent() { return this.event; }

    public List<String> getArgs() { return this.args; }

}
