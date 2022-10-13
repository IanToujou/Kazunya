package net.toujoustudios.kazunya.command.list.roleplay;

public class PartnerCommand extends ListenerAdapter implements ICommand {

    private final Config config;
    private final static HashMap<String, String> requests = new HashMap<>();

    public FriendCommand() {
        config = Config.getDefault();
    }

    @Override
    public void handle(CommandContext context) {

        List<OptionMapping> args = context.getArgs();
        EmbedBuilder embedBuilder = new EmbedBuilder();

        if(context.getEvent().getSubcommandName().equals("add")) {

            Member member = context.getMember();
            Member target = args.get(0).getAsMember();

            assert target != null;
            if(target.getUser().isBot()) {
                ErrorEmbed.sendError(context, ErrorType.COMMAND_INVALID_USER_BOT);
                return;
            }

            UserManager memberManager = UserManager.getUser(member.getId());
            UserManager targetManager = UserManager.getUser(target.getId());

            if(target.getId().equals(member.getId())) {
                ErrorEmbed.sendError(context, ErrorType.COMMAND_INVALID_USER_SELF);
                return;
            }

            if(memberManager.getRelation(target.getId()) != null) {
                ErrorEmbed.sendError(context, ErrorType.ACTION_ALREADY_PARTNERS);
                return;
            }

            requests.putIfAbsent(member.getId(), target.getId());
            embedBuilder.setColor(ColorUtil.getFromRGBString(config.getString("format.color.default")));
            embedBuilder.setTitle(":heart: **Partner Request**");
            embedBuilder.setThumbnail(config.getString("assets.img.icon_partner"));
            embedBuilder.setAuthor(member.getUser().getName() + "#" + member.getUser().getDiscriminator(), null, member.getEffectiveAvatarUrl());
            embedBuilder.setDescription(member.getAsMention() + " is asking you to be their partner, " + target.getAsMention() + "!");
            context.getEvent().reply(target.getAsMention())
                    .addEmbeds(embedBuilder.build())
                    .addActionRow(
                            Button.success("cmd_partner_accept-" + member.getId(), "Accept"),
                            Button.danger("cmd_partner_decline-" + member.getId(), "Decline"))
                    .queue();

        } else if(context.getEvent().getSubcommandName().equals("remove")) {

            Member member = context.getMember();
            Member target = args.get(0).getAsMember();

            assert target != null;
            if(target.getUser().isBot()) {
                ErrorEmbed.sendError(context, ErrorType.COMMAND_INVALID_USER_BOT);
                return;
            }

            UserManager memberManager = UserManager.getUser(member.getId());
            UserManager targetManager = UserManager.getUser(target.getId());

            if(target.getId().equals(member.getId())) {
                ErrorEmbed.sendError(context, ErrorType.COMMAND_INVALID_USER_SELF);
                return;
            }

            if(memberManager.getRelation(target.getId()) == null) {
                ErrorEmbed.sendError(context, ErrorType.ACTION_NOT_PARTNERS);
                return;
            }

            if(memberManager.getRelation(target.getId()).getType().getValue() > UserRelationType.PARTNERS.getValue()) {
                ErrorEmbed.sendError(context, ErrorType.ACTION_RELATION_TOO_HIGH);
                return;
            }

            memberManager.removeRelationWith(target.getId());
            targetManager.removeRelationWith(member.getId());

            embedBuilder.setColor(ColorUtil.getFromRGBString(config.getString("format.color.default")));
            embedBuilder.setTitle(":broken_heart: **Break Up**");
            embedBuilder.setAuthor(member.getUser().getName() + "#" + member.getUser().getDiscriminator(), null, member.getEffectiveAvatarUrl());
            embedBuilder.setDescription("You broke up with " + target.getAsMention() + "...");
            context.getEvent().replyEmbeds(embedBuilder.build()).setEphemeral(true).queue();

        } else if(context.getEvent().getSubcommandName().equals("list")) {

            Member member = context.getMember();
            UserManager memberManager = UserManager.getUser(member.getId());

            ArrayList<UserRelation> relations = memberManager.getRelations();
            ArrayList<UserRelation> partners = new ArrayList<>();

            for(UserRelation all : relations) {
                if(all.getType() == UserRelationType.PARTNERS) partners.add(all);
            }

            StringBuilder stringBuilder = new StringBuilder();
            int hiddenPartners = 0;
            for(UserRelation all : partners) {
                UserManager partnerManager = UserManager.getUser(all.getTarget());
                if(friendManager.getAccount() != null) {
                    stringBuilder.append("\n").append("• `").append(partnerManager.getAccount().getName()).append("`");
                } else hiddenPartners++;
            }

            if(hiddenPartners > 0) stringBuilder.append("\n\n").append("*You have ").append(hiddenPartners).append(" hidden partners.*");

            embedBuilder.setColor(ColorUtil.getFromRGBString(config.getString("format.color.default")));
            embedBuilder.setTitle(":heart: **Partner List**");
            embedBuilder.setAuthor(member.getUser().getName() + "#" + member.getUser().getDiscriminator(), null, member.getEffectiveAvatarUrl());
            embedBuilder.setDescription("Your current partners are: " + stringBuilder);
            context.getEvent().replyEmbeds(embedBuilder.build()).queue();

        }

    }

    @Override
    @SuppressWarnings("all")
    public void onButtonClick(ButtonClickEvent event) {

        String id = event.getComponentId();
        EmbedBuilder embedBuilder = new EmbedBuilder();

        if(!id.startsWith("cmd_partner_")) return;
        if(id.startsWith("cmd_partner_accept-")) {

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
                    UserRelation memberRelation = new UserRelation(target.getId(), UserRelationType.FRIENDS, date);
                    UserRelation targetRelation = new UserRelation(member.getId(), UserRelationType.FRIENDS, date);

                    memberManager.addRelation(memberRelation);
                    targetManager.addRelation(targetRelation);
                    embedBuilder.setColor(ColorUtil.getFromRGBString(config.getString("format.color.default")));
                    embedBuilder.setTitle(":_heart: **New Partner**");
                    embedBuilder.setThumbnail(config.getString("assets.img.icon_partner"));
                    embedBuilder.setDescription(member.getAsMention() + " and " + target.getAsMention() + " are now partners!");
                    embedBuilder.setAuthor(member.getUser().getName() + "#" + member.getUser().getDiscriminator(), null, member.getEffectiveAvatarUrl());
                    event.getChannel().sendMessage(target.getAsMention()).setEmbeds(embedBuilder.build()).queue();
                    event.getMessage().delete().queue();

                } else
                    event.replyEmbeds(ErrorEmbed.buildError(ErrorType.ACTION_INVALID_USER)).setEphemeral(true).queue();
            } else event.replyEmbeds(ErrorEmbed.buildError(ErrorType.ACTION_INVALID_USER)).setEphemeral(true).queue();
        }
    }

    @Override
    public String getName() {
        return "partner";
    }

    @Override
    public String getDescription() {
        return "Manage your partners.";
    }

    @Override
    public String getEmoji() {
        return "❤️";
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
        data.add(new SubcommandData("add", getEmoji() + " Add someone as your partner.")
                .addOptions(new OptionData(OptionType.USER, "user", "The person you want to perform the action on."))
        );
        data.add(new SubcommandData("remove", getEmoji() + " Remove someone as your partner.")
                .addOptions(new OptionData(OptionType.USER, "user", "The person you want to perform the action on."))
        );
        data.add(new SubcommandData("list", getEmoji() + " List all your current partners."));
        return data;
    }

}
