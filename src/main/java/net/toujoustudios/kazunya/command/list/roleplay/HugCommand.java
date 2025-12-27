package net.toujoustudios.kazunya.command.list.roleplay;

import net.dv8tion.jda.api.EmbedBuilder;
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
import net.toujoustudios.kazunya.config.Config;
import net.toujoustudios.kazunya.error.ErrorEmbed;
import net.toujoustudios.kazunya.error.ErrorType;
import net.toujoustudios.kazunya.main.Main;
import net.toujoustudios.kazunya.repository.ImageRepository;
import net.toujoustudios.kazunya.repository.MessageRepository;
import net.toujoustudios.kazunya.util.ColorUtil;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class HugCommand extends ListenerAdapter implements ICommand {

    private final Config config;

    public HugCommand() {
        config = Config.getDefault();
    }

    @Override
    public void handle(CommandContext context) {

        List<OptionMapping> args = context.getArgs();
        Member member = context.member();
        EmbedBuilder embedBuilder = new EmbedBuilder();
        Member target = args.getFirst().getAsMember();
        String mode = "casual";

        if (target == null) {
            ErrorEmbed.sendError(context, ErrorType.COMMAND_INVALID_USER_NOT_FOUND);
            return;
        }

        if(target.getId().equals(member.getId())) {
            ErrorEmbed.sendError(context, ErrorType.COMMAND_INVALID_USER_SELF);
            return;
        }

        if (args.size() == 2)
            mode = args.get(1).getAsString().toLowerCase();

        String image = ImageRepository.get("interaction." + name()).randomByType(mode);
        String description = MessageRepository.get("interaction." + name()).randomByType(mode)
                .replace("{member}", member.getAsMention())
                .replace("{target}", target.getAsMention());

        embedBuilder.setTitle("**:purple_heart: Hugs**");
        embedBuilder.setDescription(description);
        embedBuilder.setImage(image);
        embedBuilder.setColor(ColorUtil.rgb(config.getString("format.color.default")));

        context.interaction().reply(target.getAsMention())
                .addEmbeds(embedBuilder.build())
                .addComponents(ActionRow.of(Button.secondary("hug-" + member.getId() + "-" + target.getId(), "🫂 Hug Back")))
                .queue();

    }

    @Override
    public void onButtonInteraction(@NotNull ButtonInteractionEvent event) {

        String id = event.getComponentId();
        EmbedBuilder embedBuilder = new EmbedBuilder();

        if(!id.startsWith("hug")) return;
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

            String image = ImageRepository.get("interaction." + name()).randomByType("casual");

            embedBuilder.setTitle("**:purple_heart: Hugs**");
            embedBuilder.setDescription(member.getAsMention() + " hugs " + target.getAsMention() + "! :3");
            embedBuilder.setImage(image);
            embedBuilder.setColor(ColorUtil.rgb(config.getString("format.color.default")));
            event.reply(target.getAsMention()).addEmbeds(embedBuilder.build()).queue();

        });

    }

    @Override
    public String name() {
        return "hug";
    }

    @Override
    public String description() {
        return "Hug another person.";
    }

    @Override
    public String emoji() {
        return "🫂";
    }

    @Override
    public List<OptionData> options() {
        List<OptionData> optionData = new ArrayList<>();
        optionData.add(new OptionData(OptionType.USER, "user", "The person you want to hug.", true));
        optionData.add(new OptionData(OptionType.STRING, "mode", "Mode of the hug. Options: Casual, Romantic, Comforting", false));
        return optionData;
    }

    @Override
    public CommandCategory category() {
        return CommandCategory.ROLEPLAY;
    }

}
