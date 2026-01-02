package net.toujoustudios.kazunya.command.list.roleplay;

import net.dv8tion.jda.api.components.actionrow.ActionRow;
import net.dv8tion.jda.api.components.buttons.Button;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import net.toujoustudios.kazunya.command.CommandCategory;
import net.toujoustudios.kazunya.command.CommandContext;
import net.toujoustudios.kazunya.command.ICommand;
import net.toujoustudios.kazunya.error.ErrorEmbed;
import net.toujoustudios.kazunya.error.ErrorType;
import net.toujoustudios.kazunya.main.Main;
import net.toujoustudios.kazunya.repository.ImageRepository;
import net.toujoustudios.kazunya.repository.MessageRepository;
import net.toujoustudios.kazunya.util.EmbedUtil;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class KissCommand extends ListenerAdapter implements ICommand {

    private static final List<String> VALID_MODES = List.of(
            "cheek",
            "mouth",
            "intense"
    );

    @Override
    public void handle(CommandContext context) {

        List<OptionMapping> args = context.getArgs();
        Member member = context.member();
        Member target = args.getFirst().getAsMember();

        if (target == null) {
            ErrorEmbed.sendError(context, ErrorType.COMMAND_INVALID_USER_NOT_FOUND);
            return;
        }

        if(target.getId().equals(member.getId())) {
            ErrorEmbed.sendError(context, ErrorType.COMMAND_INVALID_USER_SELF);
            return;
        }

        String mode = "cheek";
        if (args.size() == 2) {
            if (!VALID_MODES.contains(args.get(1).getAsString())) {
                ErrorEmbed.sendError(context, ErrorType.COMMAND_INVALID_MODE);
                return;
            }
            mode = args.get(1).getAsString().toLowerCase();
        }

        String image = ImageRepository.get("interaction." + name()).randomByTypeAndGenders(mode, "MF");
        String description = MessageRepository.get("interaction." + name()).randomByType(mode)
                .replace("{member}", member.getAsMention())
                .replace("{target}", target.getAsMention());

        context.interaction().reply(target.getAsMention())
                .addEmbeds(EmbedUtil.build("**:heart: Kiss**", description, image))
                .addComponents(ActionRow.of(Button.secondary("kiss-" + member.getId() + "-" + target.getId(), "😚 Kiss Back")))
                .queue();

    }

    @Override
    public void onButtonInteraction(@NotNull ButtonInteractionEvent event) {

        String id = event.getComponentId();
        if(!id.startsWith("kiss")) return;
        Member member = event.getMember();

        Main.getBot().getJDA().retrieveUserById(id.split("-")[1]).queue(target -> {

            if(target == null || member == null) {
                event.replyEmbeds(ErrorEmbed.buildError(ErrorType.COMMAND_INVALID_USER_NOT_FOUND)).setEphemeral(true).queue();
                return;
            }

            if(!member.getId().equals(id.split("-")[2])) {
                event.replyEmbeds(ErrorEmbed.buildError(ErrorType.ACTION_INVALID_USER)).setEphemeral(true).queue();
                return;
            }

            event.reply(target.getAsMention()).addEmbeds(
                    EmbedUtil.build("**:purple_heart: Kiss**", member.getAsMention() + " kissed " + target.getAsMention() + " back! :3", ImageRepository.get("interaction." + name()).randomByType("cheek"))
            ).queue();

        });

    }

    @Override
    public String name() {
        return "kiss";
    }

    @Override
    public String description() {
        return "Interact with another user by kissing them.";
    }

    @Override
    public String emoji() {
        return "😚";
    }

    @Override
    public List<OptionData> options() {
        List<OptionData> optionData = new ArrayList<>();
        optionData.add(new OptionData(OptionType.USER, "user", "The person you want to kiss.", true));
        optionData.add(new OptionData(OptionType.STRING, "mode", "Mode of the kiss. Options: Cheek, Mouth, Intense", false));
        return optionData;
    }

    @Override
    public CommandCategory category() {
        return CommandCategory.ROLEPLAY;
    }

}
