package net.toujoustudios.kazunya.command.list.roleplay;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import net.dv8tion.jda.api.interactions.components.Button;
import net.toujoustudios.kazunya.command.CommandCategory;
import net.toujoustudios.kazunya.command.CommandContext;
import net.toujoustudios.kazunya.command.CommandManager;
import net.toujoustudios.kazunya.command.ICommand;
import net.toujoustudios.kazunya.config.Config;
import net.toujoustudios.kazunya.error.ErrorEmbed;
import net.toujoustudios.kazunya.error.ErrorType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * This file has been created by Ian Toujou.
 * Project: Kazunya
 * Date: 22/09/2021
 * Time: 06:41
 */
public class BlushCommand implements ICommand {

    private final CommandManager manager;
    private final Config config;
    private final HashMap<Member, Member> requests = new HashMap<>();

    public BlushCommand(CommandManager manager) {
        this.manager = manager;
        config = Config.getDefault();
    }

    @Override
    public void handle(CommandContext context) {

        List<OptionMapping> args = context.getArgs();
        Member member = context.getMember();
        EmbedBuilder embedBuilder = new EmbedBuilder();

        if (args.size() != 0) {
            context.getEvent().replyEmbeds(ErrorEmbed.buildError(ErrorType.COMMAND_INVALID_SYNTAX)).addActionRow(Button.link(config.getString("link.help"), "Help")).queue();
            return;
        }

        embedBuilder.setTitle("**" + member.getEffectiveName() + "is blushing! :3");
        embedBuilder.setColor(Config.getDefault().getString("format.color.default"));

    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public String getDescription() {
        return null;
    }

    @Override
    public List<OptionData> getOptions() {
        return new ArrayList<>();
    }

    @Override
    public CommandCategory getCategory() {
        return CommandCategory.ROLEPLAY;
    }

}
