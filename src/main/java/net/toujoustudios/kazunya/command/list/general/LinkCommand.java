package net.toujoustudios.kazunya.command.list.general;

import net.dv8tion.jda.api.components.actionrow.ActionRow;
import net.dv8tion.jda.api.components.buttons.Button;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import net.toujoustudios.kazunya.command.CommandCategory;
import net.toujoustudios.kazunya.command.CommandContext;
import net.toujoustudios.kazunya.command.ICommand;
import net.toujoustudios.kazunya.util.EmbedUtil;

import java.util.Collections;
import java.util.List;

public class LinkCommand implements ICommand {

    @Override
    public void handle(CommandContext context) {

        MessageEmbed embed = EmbedUtil.build(":link: Link Discord Account", "Click the button below to link your account with the Kazunya Dashboard. Do not share your link!", null);
        context.interaction().replyEmbeds(embed)
                .addComponents(ActionRow.of(Button.link("https://kazunya.toujoustudios.net/link?key=", "Link Account")))
                .setEphemeral(true)
                .queue();

    }

    @Override
    public String name() {
        return "link";
    }

    @Override
    public String description() {
        return "Link a Discord account with the Kazunya dashboard.";
    }

    @Override
    public String emoji() {
        return "\uD83D\uDD17";
    }

    @Override
    public List<OptionData> options() {
        return Collections.emptyList();
    }

    @Override
    public CommandCategory category() {
        return CommandCategory.GENERAL;
    }

}
