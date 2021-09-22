package net.toujoustudios.kazunya.command.list.roleplay;

public class DivorceCommand implements ICommand {

    private ArrayList<String> list = new ArrayList<>();

    @Override
    public void handle(CommandContext context) {
    
        Member member = context.getMember();
        List<OptionMapping> args = context.getArgs();
        EmbedBuilder embedBuilder = new EmbedBuilder();
        
        if(args.length > 1) {
        context.getEvent().replyEmbeds(ErrorEmbed.buildError(ErrorType.COMMAND_INVALID_SYNTAX)).addActionRow(Button.link(config.getString("link.help"), "Help")).queue();
            return;
        }
        
        if(!list.contains(member.getId()) {
        
            embedBuilder.setColor(config.getString("format.color.default");
            embedBuilder.setTitle("");
            embedBuilder.setDescription("Do you really want to divorce?\nPlease type `/divorce` again to confirm this action.");
            list.add(member.getId());
            context.getEvent().replyEmbeds(embedBuilder.build()).queue();
            return;
            
        }

}