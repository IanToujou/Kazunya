package net.toujoustudios.kazunya.command.list.roleplay;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.interaction.ButtonClickEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import net.dv8tion.jda.api.interactions.commands.build.SubcommandData;
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
import net.toujoustudios.kazunya.log.LogLevel;
import net.toujoustudios.kazunya.log.Logger;
import net.toujoustudios.kazunya.main.Main;
import net.toujoustudios.kazunya.util.ColorUtil;

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
        EmbedBuilder embedBuilder = new EmbedBuilder();

        if(Objects.equals(context.getEvent().getSubcommandName(), "add")) {

            Member member = context.getMember();
            Member target = args.get(0).getAsMember();

            assert target != null;
            if(target.getUser().isBot()) {
                ErrorEmbed.sendError(context, ErrorType.COMMAND_INVALID_USER_BOT);
                return;
            }

            UserManager memberManager = UserManager.getUser(member);
            UserManager.createAccount(target);

            if(target.getId().equals(member.getId())) {
                ErrorEmbed.sendError(context, ErrorType.COMMAND_INVALID_USER_SELF);
                return;
            }

            if(memberManager.getRelation(target.getId()) != null) {
                if(memberManager.getRelation(target.getId()).getType().getValue() >= UserRelationType.FRIENDS.getValue()) {
                    ErrorEmbed.sendError(context, ErrorType.ACTION_ALREADY_FRIENDS);
                    return;
                }
            }

            if(memberManager.getRelationsOfType(UserRelationType.FRIENDS).size() >= UserRelationType.FRIENDS.getMaxUsers()) {
                ErrorEmbed.sendError(context, ErrorType.ACTION_MAX_FRIENDS);
                return;
            }

            requests.putIfAbsent(member.getId(), target.getId());
            embedBuilder.setColor(ColorUtil.getFromRGBString(config.getString("format.color.default")));
            embedBuilder.setTitle(":green_heart: **Friend Request**");
            embedBuilder.setThumbnail(config.getString("assets.img.icon_friend_request"));
            embedBuilder.setAuthor(member.getUser().getName() + "#" + member.getUser().getDiscriminator(), null, member.getEffectiveAvatarUrl());
            embedBuilder.setDescription(member.getAsMention() + " sent you a friend request, " + target.getAsMention() + "!");
            context.getEvent().reply(target.getAsMention())
                    .addEmbeds(embedBuilder.build())
                    .addActionRow(
                            Button.success("friend_a-" + member.getId(), "Accept"),
                            Button.danger("friend_d-" + member.getId(), "Decline"))
                    .queue();
            Logger.log(LogLevel.DEBUG, "Creating buttons for target user " + member.getId());

        } else if(Objects.equals(context.getEvent().getSubcommandName(), "remove")) {

            Member member = context.getMember();
            Member target = args.get(0).getAsMember();

            assert target != null;
            if(target.getUser().isBot()) {
                ErrorEmbed.sendError(context, ErrorType.COMMAND_INVALID_USER_BOT);
                return;
            }

            UserManager memberManager = UserManager.getUser(member);
            UserManager targetManager = UserManager.getUser(target);

            if(target.getId().equals(member.getId())) {
                ErrorEmbed.sendError(context, ErrorType.COMMAND_INVALID_USER_SELF);
                return;
            }

            if(memberManager.getRelation(target.getId()) == null) {
                ErrorEmbed.sendError(context, ErrorType.ACTION_NOT_FRIENDS);
                return;
            }

            if(memberManager.getRelation(target.getId()).getType().getValue() > UserRelationType.FRIENDS.getValue()) {
                ErrorEmbed.sendError(context, ErrorType.ACTION_RELATION_TOO_HIGH);
                return;
            }

            memberManager.removeRelationWith(target.getId());
            targetManager.removeRelationWith(member.getId());

            embedBuilder.setColor(ColorUtil.getFromRGBString(config.getString("format.color.default")));
            embedBuilder.setTitle(":broken_heart: **Friend Removed**");
            embedBuilder.setAuthor(member.getUser().getName() + "#" + member.getUser().getDiscriminator(), null, member.getEffectiveAvatarUrl());
            embedBuilder.setDescription("You removed " + target.getAsMention() + " from your friends...");
            context.getEvent().replyEmbeds(embedBuilder.build()).setEphemeral(true).queue();

        } else if(Objects.equals(context.getEvent().getSubcommandName(), "list")) {

            Member member = context.getMember();
            UserManager memberManager = UserManager.getUser(member);

            ArrayList<UserRelation> relations = memberManager.getRelations();
            ArrayList<UserRelation> friends = new ArrayList<>();

            for(UserRelation all : relations) {
                if(all.getType() == UserRelationType.FRIENDS) friends.add(all);
            }

            StringBuilder stringBuilder = new StringBuilder();
            int hiddenFriends = 0;
            for(UserRelation all : friends) {
                UserManager friendManager = UserManager.getUser(all.getTarget());
                if(friendManager.getAccount() != null)
                    stringBuilder.append("\n").append("• `").append(friendManager.getAccount().getName()).append("`");
                else hiddenFriends++;
            }

            if(hiddenFriends > 0)
                stringBuilder.append("\n\n").append("*You have ").append(hiddenFriends).append(" hidden friends.*");

            embedBuilder.setColor(ColorUtil.getFromRGBString(config.getString("format.color.default")));
            embedBuilder.setTitle(":green_heart: **Friend List**");
            embedBuilder.setAuthor(member.getUser().getName() + "#" + member.getUser().getDiscriminator(), null, member.getEffectiveAvatarUrl());
            if(friends.size() > 0) embedBuilder.setDescription("Your current friends are: " + stringBuilder);
            else embedBuilder.setDescription("*You don't have any friends.*");
            context.getEvent().replyEmbeds(embedBuilder.build()).queue();

        }

    }

    @Override
    @SuppressWarnings("all")
    public void onButtonClick(ButtonClickEvent event) {

        String id = event.getComponentId();
        EmbedBuilder embedBuilder = new EmbedBuilder();

        if(!id.startsWith("friend_")) return;
        if(id.startsWith("friend_a-")) {

            Member member = event.getMember();
            Main.getBot().getJDA().retrieveUserById(id.split("-")[1]).queue(target -> {

                if(target == null) {
                    event.replyEmbeds(ErrorEmbed.buildError(ErrorType.COMMAND_INVALID_USER_NOT_FOUND)).setEphemeral(true).queue();
                    return;
                }

                UserManager memberManager = UserManager.getUser(member);
                UserManager targetManager = UserManager.getUser(target.getId());

                if(requests.containsKey(target.getId())) {
                    if(requests.get(target.getId()).equals(member.getId())) {

                        requests.remove(target.getId());
                        requests.remove(member.getId());

                        Date date = new Date();
                        UserRelation memberRelation = new UserRelation(UUID.randomUUID().toString(), target.getId(), UserRelationType.FRIENDS, date);
                        UserRelation targetRelation = new UserRelation(UUID.randomUUID().toString(), member.getId(), UserRelationType.FRIENDS, date);

                        memberManager.addRelation(memberRelation);
                        targetManager.addRelation(targetRelation);
                        embedBuilder.setColor(ColorUtil.getFromRGBString(config.getString("format.color.default")));
                        embedBuilder.setTitle(":green_heart: **New Friend**");
                        embedBuilder.setThumbnail(config.getString("assets.img.icon_friend"));
                        embedBuilder.setDescription(member.getAsMention() + " and " + target.getAsMention() + " are now friends!");
                        embedBuilder.setAuthor(member.getUser().getName() + "#" + member.getUser().getDiscriminator(), null, member.getEffectiveAvatarUrl());
                        event.getChannel().sendMessage(target.getAsMention()).setEmbeds(embedBuilder.build()).queue();
                        event.getMessage().delete().queue();

                    } else
                        event.replyEmbeds(ErrorEmbed.buildError(ErrorType.ACTION_INVALID_USER)).setEphemeral(true).queue();
                } else
                    event.replyEmbeds(ErrorEmbed.buildError(ErrorType.ACTION_INVALID_FRIEND_REQUEST)).setEphemeral(true).queue();
            });

        } else if(id.startsWith("friend_d-")) {

            Member member = event.getMember();
            Main.getBot().getJDA().retrieveUserById(id.split("-")[1]).queue(target -> {

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
                        embedBuilder.setDescription(member.getAsMention() + " rejected your friend request.");
                        embedBuilder.setAuthor(member.getUser().getName() + "#" + member.getUser().getDiscriminator(), null, member.getEffectiveAvatarUrl());
                        event.getChannel().sendMessage(target.getAsMention()).setEmbeds(embedBuilder.build()).queue();
                        event.getMessage().delete().queue();

                    } else
                        event.replyEmbeds(ErrorEmbed.buildError(ErrorType.ACTION_INVALID_USER)).setEphemeral(true).queue();
                } else
                    event.replyEmbeds(ErrorEmbed.buildError(ErrorType.ACTION_INVALID_FRIEND_REQUEST)).setEphemeral(true).queue();
            });

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
        return Collections.emptyList();
    }

    @Override
    public CommandCategory getCategory() {
        return CommandCategory.ROLEPLAY;
    }

    @Override
    public List<SubcommandData> getSubcommandData() {
        ArrayList<SubcommandData> data = new ArrayList<>();
        data.add(new SubcommandData("add", getEmoji() + " Add someone to your friend list.")
                .addOptions(new OptionData(OptionType.USER, "user", "The person you want to perform the action on."))
        );
        data.add(new SubcommandData("remove", getEmoji() + " Remove someone from your friend list.")
                .addOptions(new OptionData(OptionType.USER, "user", "The person you want to perform the action on."))
        );
        data.add(new SubcommandData("list", getEmoji() + " List all your friends."));
        return data;
    }

}
