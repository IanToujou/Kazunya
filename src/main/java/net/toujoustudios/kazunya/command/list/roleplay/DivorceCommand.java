package net.toujoustudios.kazunya.command.list.roleplay;

public class DivorceCommand implements ICommand {

    @Override
    public void handle(CommandContext context) {
    
        Member member = context.getMember();
        List<OptionMapping> args = context.getArgs();
        EmbedBuilder embedBuilder = new EmbedBuilder();
        
        if(args.length > 1) {
            context.getEvent().reply("").queue();
            return;
        }
        
        if(args.length == 0) {
        
            
        
        }
    
    }

}