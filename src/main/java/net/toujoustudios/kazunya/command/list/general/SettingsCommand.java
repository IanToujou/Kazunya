package net.toujoustudios.kazunya.command.list.general;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import net.dv8tion.jda.api.interactions.commands.build.SubcommandData;
import net.toujoustudios.kazunya.command.CommandCategory;
import net.toujoustudios.kazunya.command.CommandContext;
import net.toujoustudios.kazunya.command.ICommand;
import net.toujoustudios.kazunya.config.Config;
import net.toujoustudios.kazunya.error.ErrorEmbed;
import net.toujoustudios.kazunya.error.ErrorType;
import net.toujoustudios.kazunya.model.Gender;
import net.toujoustudios.kazunya.model.UserManager;
import net.toujoustudios.kazunya.util.ColorUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class SettingsCommand implements ICommand {

    private final Config config;

    public SettingsCommand() {
        config = Config.getDefault();
    }

    @Override
    public void handle(CommandContext context) {

        if(Objects.equals(context.getInteraction().getSubcommandName(), "gif-gender")) {

            Member member = context.getMember();
            Gender gender;

            try {
                gender = Gender.valueOf(Objects.requireNonNull(context.getInteraction().getOption("value")).getAsString().toUpperCase());
            } catch (IllegalArgumentException exception) {
                ErrorEmbed.sendError(context, ErrorType.COMMAND_INVALID_GENDER);
                return;
            }

            EmbedBuilder embedBuilder = new EmbedBuilder();
            UserManager manager = UserManager.getUser(member);
            manager.setGifGender(gender);

            embedBuilder.setColor(ColorUtil.getFromRGBString(config.getString("format.color.default")));
            embedBuilder.setTitle(":cog: **Settings Updated**");
            embedBuilder.setDescription("You updated the value of `" + context.getInteraction().getSubcommandName() + "` to `" + context.getInteraction().getOption("value") + "`");
            context.getInteraction().replyEmbeds(embedBuilder.build()).setEphemeral(true).queue();

        }

    }

    @Override
    public String getName() {
        return "settings";
    }

    @Override
    public String getDescription() {
        return "Manage personal preferences.";
    }

    @Override
    public String getEmoji() {
        return "⚙️";
    }

    @Override
    public List<OptionData> getOptions() {
        return Collections.emptyList();
    }

    @Override
    public List<SubcommandData> getSubcommandData() {
        ArrayList<SubcommandData> data = new ArrayList<>();
        data.add(new SubcommandData("gif-gender", getEmoji() + " Set the gender you want to appear as in GIFs.")
                .addOptions(new OptionData(OptionType.STRING, "value", "The value you want to change the setting to.", true))
        );
        return data;
    }

    @Override
    public CommandCategory getCategory() {
        return CommandCategory.GENERAL;
    }

}