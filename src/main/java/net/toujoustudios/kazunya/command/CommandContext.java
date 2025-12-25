package net.toujoustudios.kazunya.command;

import lombok.Data;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.interactions.commands.SlashCommandInteraction;

import java.util.List;

@Data
public class CommandContext implements ICommandContext {

    private final SlashCommandInteraction interaction;
    private final List<OptionMapping> args;

    @Override
    public Guild getGuild() {
        return this.getInteraction().getGuild();
    }

    @Override
    public SlashCommandInteraction getInteraction() {
        return this.interaction;
    }

}
