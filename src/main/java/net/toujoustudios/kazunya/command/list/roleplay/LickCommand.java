package net.toujoustudios.kazunya.command.list.roleplay;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.interaction.ButtonClickEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import net.dv8tion.jda.api.interactions.components.Button;
import net.toujoustudios.kazunya.command.CommandCategory;
import net.toujoustudios.kazunya.command.CommandContext;
import net.toujoustudios.kazunya.command.ICommand;
import net.toujoustudios.kazunya.config.Config;
import net.toujoustudios.kazunya.error.ErrorEmbed;
import net.toujoustudios.kazunya.error.ErrorType;
import net.toujoustudios.kazunya.main.Main;
import net.toujoustudios.kazunya.util.ColorUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * This file has been created by Ian Toujou.
 * Project: Kazunya
 * Date: 05/11/2021
 * Time: 21:39
 */
public class LickCommand extends ListenerAdapter implements ICommand {

    private final Config config;

    public LickCommand() {
        config = Config.getDefault();
    }

    @Override
    public void handle(CommandContext context) {

        List<OptionMapping> args = context.getArgs();
        Member member = context.getMember();
        EmbedBuilder embedBuilder = new EmbedBuilder();

        if(args.size() == 0) {
            ErrorEmbed.sendError(context, ErrorType.COMMAND_INVALID_SYNTAX);
            return;
        }

        Member target = args.get(0).getAsMember();
        assert target != null;

        if(target.getId().equals(member.getId())) {
            ErrorEmbed.sendError(context, ErrorType.COMMAND_INVALID_USER_SELF);
            return;
        }

        List<String> images = config.getStringList("gif.command.lick");

        embedBuilder.setTitle("**:sweat_drops: Lick**");
        embedBuilder.setDescription(member.getAsMention() + " licks " + target.getAsMention() + "! :3");
        embedBuilder.setImage(images.get(new Random().nextInt(images.size())));
        embedBuilder.setColor(ColorUtil.getFromRGBString(config.getString("format.color.default")));
        context.getEvent().reply(target.getAsMention())
                .addEmbeds(embedBuilder.build())
                .addActionRow(
                        Button.secondary("lick-" + member.getId() + "-" + target.getId(), "👅 Lick Back"))
                .queue();

    }

    @Override
    public void onButtonClick(ButtonClickEvent event) {

        String id = event.getComponentId();
        EmbedBuilder embedBuilder = new EmbedBuilder();

        if(!id.startsWith("lick")) return;
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

            List<String> images = config.getStringList("gif.command.lick");

            embedBuilder.setTitle("**:sweat_drops: Lick**");
            embedBuilder.setDescription(member.getAsMention() + " licks " + target.getAsMention() + "! :3");
            embedBuilder.setImage(images.get(new Random().nextInt(images.size())));
            embedBuilder.setColor(ColorUtil.getFromRGBString(config.getString("format.color.default")));
            event.reply(target.getAsMention()).addEmbeds(embedBuilder.build()).queue();

        });

    }

    @Override
    public String getName() {
        return "lick";
    }

    @Override
    public String getDescription() {
        return "Lick another user.";
    }

    @Override
    public String getEmoji() {
        return "👅";
    }

    @Override
    public List<OptionData> getOptions() {
        List<OptionData> optionData = new ArrayList<>();
        optionData.add(new OptionData(OptionType.USER, "user", "The person you want to lick.", true));
        return optionData;
    }

    @Override
    public CommandCategory getCategory() {
        return CommandCategory.ROLEPLAY;
    }

}
