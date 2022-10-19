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
import net.toujoustudios.kazunya.data.relation.UserRelation;
import net.toujoustudios.kazunya.data.relation.UserRelationType;
import net.toujoustudios.kazunya.data.user.UserManager;
import net.toujoustudios.kazunya.error.ErrorEmbed;
import net.toujoustudios.kazunya.error.ErrorType;
import net.toujoustudios.kazunya.util.ColorUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * A command to marry other people on Discord using the bot. The marriage is valid on all Discord servers. A user cannot have multiple partners in different servers, but are rather limited to one single person.
 *
 * @author Ian Toujou
 * @see net.toujoustudios.kazunya.command.list.roleplay.DivorceCommand
 * @since 1.0.0
 */
public class MarryCommand extends ListenerAdapter implements ICommand {

    private final Config config;
    private final static HashMap<String, String> requests = new HashMap<>();

    public MarryCommand() {
        config = Config.getDefault();
    }

    @Override
    public void handle(CommandContext context) {

        List<OptionMapping> args = context.getArgs();
        Member member = context.getMember();
        EmbedBuilder embedBuilder = new EmbedBuilder();

        Member target = args.get(0).getAsMember();
        assert target != null;

        if(target.getUser().isBot()) {
            ErrorEmbed.sendError(context, ErrorType.COMMAND_INVALID_USER_BOT);
            return;
        }

        if(target.getId().equals(member.getId())) {
            ErrorEmbed.sendError(context, ErrorType.COMMAND_INVALID_USER_SELF);
            return;
        }

        UserManager memberManager = UserManager.getUser(member);
        UserManager.createAccount(target);

        if(memberManager.getRelationsOfType(UserRelationType.MARRIED).size() >= UserRelationType.MARRIED.getMaxUsers()) {
            ErrorEmbed.sendError(context, ErrorType.ACTION_MAX_MARRIED);
            return;
        }

        if(memberManager.getRelation(target.getId()) != null) {
            if(memberManager.getRelation(target.getId()).getType().getValue() < UserRelationType.COUPLE.getValue()) {
                ErrorEmbed.sendError(context, ErrorType.ACTION_RELATION_TOO_LOW);
                return;
            }
        }

        if(memberManager.getRelation(target.getId()) != null) {
            if(memberManager.getRelation(target.getId()).getType().getValue() >= UserRelationType.MARRIED.getValue()) {
                ErrorEmbed.sendError(context, ErrorType.ACTION_ALREADY_PARTNERS);
                return;
            }
        }

        requests.putIfAbsent(member.getId(), target.getId());

        embedBuilder.setColor(ColorUtil.getFromRGBString(config.getString("format.color.default")));
        embedBuilder.setTitle(":ring: **Proposal**");
        embedBuilder.setThumbnail(config.getString("assets.img.icon_marry_request"));
        embedBuilder.setAuthor(member.getUser().getName() + "#" + member.getUser().getDiscriminator(), null, member.getEffectiveAvatarUrl());
        embedBuilder.setDescription(member.getAsMention() + " is proposing to you, " + target.getAsMention() + "!\nWhat will you do?~");
        context.getEvent().reply(target.getAsMention())
                .addEmbeds(embedBuilder.build())
                .addActionRow(
                        Button.success("cmd_marry_accept-" + member.getId(), "Accept"),
                        Button.danger("cmd_marry_decline-" + member.getId(), "Decline"))
                .queue();

    }

    @Override
    @SuppressWarnings("all")
    public void onButtonClick(ButtonClickEvent event) {

        String id = event.getComponentId();
        EmbedBuilder embedBuilder = new EmbedBuilder();

        if(!id.startsWith("cmd_marry_")) return;
        if(id.startsWith("cmd_marry_accept-")) {

            Member member = event.getMember();
            Member target = event.getGuild().getMemberById(id.split("-")[1]);

            if(target == null) {
                event.replyEmbeds(ErrorEmbed.buildError(ErrorType.COMMAND_INVALID_USER_NOT_FOUND)).setEphemeral(true).queue();
                return;
            }

            UserManager memberManager = UserManager.getUser(member);
            UserManager targetManager = UserManager.getUser(target);

            if(requests.containsKey(target.getId())) {
                if(requests.get(target.getId()).equals(member.getId())) {

                    requests.remove(target.getId());
                    requests.remove(member.getId());

                    Date date = new Date();
                    UserRelation memberRelation = new UserRelation(target.getId(), UserRelationType.MARRIED, date);
                    UserRelation targetRelation = new UserRelation(member.getId(), UserRelationType.MARRIED, date);

                    memberManager.addRelation(memberRelation);
                    targetManager.addRelation(targetRelation);
                    embedBuilder.setColor(ColorUtil.getFromRGBString(config.getString("format.color.default")));
                    embedBuilder.setTitle(":ring: **Marriage**");
                    embedBuilder.setThumbnail(config.getString("assets.img.icon_marry"));
                    embedBuilder.setDescription(member.getAsMention() + " and " + target.getAsMention() + " are now happily married!");
                    embedBuilder.setAuthor(member.getUser().getName() + "#" + member.getUser().getDiscriminator(), null, member.getEffectiveAvatarUrl());
                    event.getChannel().sendMessage(target.getAsMention()).setEmbeds(embedBuilder.build()).queue();
                    event.getMessage().delete().queue();

                } else
                    event.replyEmbeds(ErrorEmbed.buildError(ErrorType.ACTION_INVALID_MARRY_REQUEST)).setEphemeral(true).queue();
            } else
                event.replyEmbeds(ErrorEmbed.buildError(ErrorType.ACTION_INVALID_MARRY_REQUEST)).setEphemeral(true).queue();
        } else if(id.startsWith("cmd_marry_decline-")) {

            Member member = event.getMember();
            Member target = event.getGuild().getMemberById(id.split("-")[1]);

            if(target == null) {
                event.replyEmbeds(ErrorEmbed.buildError(ErrorType.COMMAND_INVALID_USER_NOT_FOUND)).setEphemeral(true).queue();
                return;
            }

            if(requests.containsKey(target.getId())) {
                if(requests.get(target.getId()).equals(member.getId())) {

                    requests.remove(target.getId());
                    requests.remove(member.getId());

                    embedBuilder.setColor(ColorUtil.getFromRGBString(config.getString("format.color.default")));
                    embedBuilder.setTitle(":no_entry: **Request Rejected**");
                    embedBuilder.setDescription(member.getAsMention() + " rejected your proposal. Maybe they need more time...");
                    embedBuilder.setAuthor(member.getUser().getName() + "#" + member.getUser().getDiscriminator(), null, member.getEffectiveAvatarUrl());
                    event.getChannel().sendMessage(target.getAsMention()).setEmbeds(embedBuilder.build()).queue();
                    event.getMessage().delete().queue();

                } else
                    event.replyEmbeds(ErrorEmbed.buildError(ErrorType.ACTION_INVALID_MARRY_REQUEST)).setEphemeral(true).queue();
            } else
                event.replyEmbeds(ErrorEmbed.buildError(ErrorType.ACTION_INVALID_MARRY_REQUEST)).setEphemeral(true).queue();

        }
    }

    @Override
    public String getName() {
        return "marry";
    }

    @Override
    public String getDescription() {
        return "Ask someone to be your wife or husband.";
    }

    @Override
    public String getEmoji() {
        return "💍";
    }

    @Override
    public List<OptionData> getOptions() {
        List<OptionData> optionData = new ArrayList<>();
        optionData.add(new OptionData(OptionType.USER, "user", "The person you want to marry.", true));
        return optionData;
    }

    @Override
    public CommandCategory getCategory() {
        return CommandCategory.ROLEPLAY;
    }

}
