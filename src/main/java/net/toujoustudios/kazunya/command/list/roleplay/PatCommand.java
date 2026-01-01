package net.toujoustudios.kazunya.command.list.roleplay;

import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import net.toujoustudios.kazunya.command.CommandCategory;
import net.toujoustudios.kazunya.command.CommandContext;
import net.toujoustudios.kazunya.command.ICommand;
import net.toujoustudios.kazunya.error.ErrorEmbed;
import net.toujoustudios.kazunya.error.ErrorType;
import net.toujoustudios.kazunya.repository.ImageRepository;
import net.toujoustudios.kazunya.repository.MessageRepository;
import net.toujoustudios.kazunya.util.EmbedUtil;

import java.util.ArrayList;
import java.util.List;

public class PatCommand extends ListenerAdapter implements ICommand {

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

        String image = ImageRepository.get("interaction." + name()).randomByTypeAndGenders("friendly", "MF");
        String description = MessageRepository.get("interaction." + name()).randomByType("friendly")
                .replace("{member}", member.getAsMention())
                .replace("{target}", target.getAsMention());

        context.interaction().reply(target.getAsMention())
                .addEmbeds(EmbedUtil.build("**:blue_heart: Headpats**", description, image))
                .queue();

    }

    @Override
    public String name() {
        return "pat";
    }

    @Override
    public String description() {
        return "Interact with another user by giving them headpats.";
    }

    @Override
    public String emoji() {
        return "💙";
    }

    @Override
    public List<OptionData> options() {
        List<OptionData> optionData = new ArrayList<>();
        optionData.add(new OptionData(OptionType.USER, "user", "The person you want to give headpats to.", true));
        return optionData;
    }

    @Override
    public CommandCategory category() {
        return CommandCategory.ROLEPLAY;
    }

}
