package net.toujoustudios.kazunya.command;

import lombok.Getter;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.interactions.commands.SlashCommandInteraction;

import java.util.List;

public class CommandContext implements ICommandContext {

    private final SlashCommandInteraction interaction;
    @Getter
    private final List<OptionMapping> args;

    public CommandContext(SlashCommandInteraction interaction, List<OptionMapping> args) {
        this.interaction = interaction;
        this.args = args;
    }

    @Override
    public Guild getGuild() {
        return this.getInteraction().getGuild();
    }

    @Override
    public SlashCommandInteraction getInteraction() {
        return this.interaction;
    }

}
