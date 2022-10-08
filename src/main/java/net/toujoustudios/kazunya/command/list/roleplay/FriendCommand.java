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

import java.text.SimpleDateFormat;
import java.util.*;

public class FriendCommand extends ListenerAdapter implements ICommand {

    private final Config config;
    private final static HashMap<String, String> requests = new HashMap<>();

    public FriendCommand() {
        config = Config.getDefault();
    }

    @Override
    public void handle(CommandContext context) {

        List<OptionMapping> args = context.getArgs();
        Member member = context.getMember();
        Member target = args.get(1).getAsMember();
        String action = args.get(0).getAsString();
        EmbedBuilder embedBuilder = new EmbedBuilder();

        assert target != null;
        if(target.getUser().isBot()) {
            ErrorEmbed.sendError(context, ErrorType.COMMAND_INVALID_USER_BOT);
            return;
        }

        if(target.getId().equals(member.getId())) {
            ErrorEmbed.sendError(context, ErrorType.COMMAND_INVALID_USER_SELF);
            return;
        }

        UserManager memberManager = UserManager.getUser(member.getId());
        UserManager targetManager = UserManager.getUser(target.getId());

        if(action.equalsIgnoreCase("add")) {

            requests.putIfAbsent(member.getId(), target.getId());

            if(requests.containsKey(target.getId())) {

                if(requests.get(target.getId()).equals(member.getId())) {

                    requests.remove(target.getId());
                    requests.remove(member.getId());

                    Date date = new Date();
                    UserRelation memberRelation = new UserRelation(target.getId(), UserRelationType.FRIENDS, date);
                    UserRelation targetRelation = new UserRelation(member.getId(), UserRelationType.FRIENDS, date);

                    memberManager.addRelation(memberRelation);
                    targetManager.addRelation(targetRelation);

                    embedBuilder.setColor(ColorUtil.getFromRGBString(config.getString("format.color.default")));
                    embedBuilder.setTitle(":green_heart: **New Friend**");
                    embedBuilder.setThumbnail(config.getString("assets.img.icon_friends"));
                    embedBuilder.setDescription(member.getAsMention() + " and " + target.getAsMention() + " are now friends!");
                    context.getEvent().replyEmbeds(embedBuilder.build()).queue();

                }

            } else {
                embedBuilder.setColor(ColorUtil.getFromRGBString(config.getString("format.color.default")));
                embedBuilder.setTitle(":green_heart: **Friend Request**");
                embedBuilder.setThumbnail(config.getString("assets.img.icon_friend"));
                embedBuilder.setAuthor(member.getUser().getName() + "#" + member.getUser().getDiscriminator(), null, member.getEffectiveAvatarUrl());
                embedBuilder.setDescription(member.getAsMention() + " send you a friend request, " + target.getAsMention() + "!");
                context.getEvent()
                        .replyEmbeds(embedBuilder.build())
                        .addActionRow(
                                Button.success("cmd_friend_accept-" + member.getId(), "Accept"),
                                Button.danger("cmd_friend_decline-" + member.getId(), "Decline"))
                        .queue();
            }

        } else if(action.equalsIgnoreCase("remove")) {

            ErrorEmbed.sendError(context, ErrorType.GENERAL_UNFINISHED);

        } else if(action.equalsIgnoreCase("status")) {

            ErrorEmbed.sendError(context, ErrorType.GENERAL_UNFINISHED);

        } else ErrorEmbed.sendError(context, ErrorType.COMMAND_INVALID_SYNTAX);

    }

    @Override
    @SuppressWarnings("all")
    public void onButtonClick(ButtonClickEvent event) {

        String id = event.getComponentId();
        EmbedBuilder embedBuilder = new EmbedBuilder();

        if(!id.startsWith("cmd_friend_")) return;
        if(id.startsWith("cmd_friend_accept-")) {

            Member member = event.getMember();
            Member target = event.getGuild().getMemberById(id.split("-")[1]);

            if(target == null) {
                event.replyEmbeds(ErrorEmbed.buildError(ErrorType.COMMAND_INVALID_USER_NOT_FOUND)).setEphemeral(true).queue();
                return;
            }

            UserManager memberManager = UserManager.getUser(member.getId());
            UserManager targetManager = UserManager.getUser(target.getId());

            if(requests.containsKey(target.getId())) {
                if(requests.get(target.getId()).equals(member.getId())) {

                    requests.remove(target.getId());
                    requests.remove(member.getId());

                    Date date = new Date();
                    String dateString = new SimpleDateFormat("dd.MM.yy, HH:mm").format(new Date());
                    UserRelation memberRelation = new UserRelation(target.getId(), UserRelationType.FRIENDS, date);
                    UserRelation targetRelation = new UserRelation(member.getId(), UserRelationType.FRIENDS, date);

                    memberManager.addRelation(memberRelation);
                    targetManager.addRelation(targetRelation);
                    embedBuilder.setColor(ColorUtil.getFromRGBString(config.getString("format.color.default")));
                    embedBuilder.setTitle(":green_heart: **New Friend**");
                    embedBuilder.setThumbnail(config.getString("assets.img.icon_friend"));
                    embedBuilder.setDescription(member.getAsMention() + " and " + target.getAsMention() + " are now friends!");
                    embedBuilder.setAuthor(member.getUser().getName() + "#" + member.getUser().getDiscriminator(), null, member.getEffectiveAvatarUrl());
                    event.editComponents().setContent(target.getAsMention()).setEmbeds(embedBuilder.build()).queue();

                } else event.replyEmbeds(ErrorEmbed.buildError(ErrorType.ACTION_INVALID_USER)).setEphemeral(true).queue();
            } else event.replyEmbeds(ErrorEmbed.buildError(ErrorType.ACTION_INVALID_USER)).setEphemeral(true).queue();
        }
    }

    @Override
    public String getName() {
        return "friend";
    }

    @Override
    public String getDescription() {
        return "Manage your friend list.";
    }

    @Override
    public String getEmoji() {
        return "💚";
    }

    @Override
    public List<OptionData> getOptions() {
        List<OptionData> optionData = new ArrayList<>();
        optionData.add(new OptionData(OptionType.STRING, "action", "The action to perform on the friend. This can be either add, remove or status.", true));
        optionData.add(new OptionData(OptionType.USER, "user", "The person you want to perform the action on.", true));
        return optionData;
    }

    @Override
    public CommandCategory getCategory() {
        return CommandCategory.ROLEPLAY;
    }

}
