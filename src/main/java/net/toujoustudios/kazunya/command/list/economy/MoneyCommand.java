package net.toujoustudios.kazunya.command.list.economy;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;
import net.toujoustudios.kazunya.command.CommandCategory;
import net.toujoustudios.kazunya.command.CommandContext;
import net.toujoustudios.kazunya.command.ICommand;
import net.toujoustudios.kazunya.config.Config;
import net.toujoustudios.kazunya.user.UserManager;

import java.util.List;

/**
 * This file was created by IanToujou.
 * Date: 24/05/2021
 * Time: 14:34
 * Project: Kazunya
 */
public class MoneyCommand implements ICommand {

    @Override
    public void handle(CommandContext context) {

        if(!context.getMember().getId().equals(Config.DEFAULT_ADMIN_USER)) {

            return;

        }

        List<String> args = context.getArgs();
        TextChannel channel = context.getChannel();
        EmbedBuilder embedBuilder = new EmbedBuilder();

        embedBuilder.setColor(Config.DEFAULT_EMBED_COLOR);

        if(args.size() < 3) {

            embedBuilder.setTitle("**__ERROR__**");
            embedBuilder.setColor(Config.ERROR_EMBED_COLOR);
            embedBuilder.setDescription(":x: The command syntax is not correct.\nPlease use **" + Config.DEFAULT_PREFIX + getUsage() + "**");
            channel.sendMessage(embedBuilder.build()).queue();
            return;

        }

        String action = args.get(0).toLowerCase();
        double value = 0;

        try {

            value = Double.parseDouble(args.get(1));

        } catch(Exception exception) {

            embedBuilder.setTitle("**__ERROR__**");
            embedBuilder.setColor(Config.ERROR_EMBED_COLOR);
            embedBuilder.setDescription(":x: The given value is invalid.");
            channel.sendMessage(embedBuilder.build()).queue();
            return;

        }

        if(context.getMessage().getMentionedMembers().size() != 1) {

            embedBuilder.setTitle("**__ERROR__**");
            embedBuilder.setColor(Config.ERROR_EMBED_COLOR);
            embedBuilder.setDescription(":x: The command syntax is not correct.\nPlease use **" + Config.DEFAULT_PREFIX + getUsage() + "**");
            channel.sendMessage(embedBuilder.build()).queue();
            return;

        }

        Member target = context.getMessage().getMentionedMembers().get(0);
        UserManager targetManager = UserManager.getUser(target.getId());

        if(action.equals("set")) {

            targetManager.setMoney(value);
            embedBuilder.setDescription(":credit_card: You set the account value of " + target.getAsMention() + " to **" + value + "$**.");

        } else if(action.equals("add")) {

            targetManager.addMoney(value);
            embedBuilder.setDescription(":credit_card: You added  **" + value + "$** to the account of " + target.getAsMention() + ". New balance: **" + targetManager.getMoney() + "$**.");

        } else if(action.equals("remove")) {

            targetManager.removeMoney(value);
            embedBuilder.setDescription(":credit_card: You removed  **" + value + "$** from the account of " + target.getAsMention() + ". New balance: **" + targetManager.getMoney() + "$**.");

        } else {

            embedBuilder.setTitle("**__ERROR__**");
            embedBuilder.setColor(Config.ERROR_EMBED_COLOR);
            embedBuilder.setDescription(":x: The command syntax is not correct.\nPlease use **" + Config.DEFAULT_PREFIX + getUsage() + "**");
            channel.sendMessage(embedBuilder.build()).queue();
            return;

        }

        channel.sendMessage(embedBuilder.build()).queue();

    }

    @Override
    public String getName() {
        return "money";
    }

    @Override
    public String getHelp() {
        return "Set, remove or add money to a user.";
    }

    @Override
    public String getUsage() {
        return "money [add/remove/set] [value] [@user]";
    }

    @Override
    public boolean isNSFW() {
        return false;
    }

    @Override
    public CommandCategory getCategory() {
        return CommandCategory.UNLISTED;
    }

}
