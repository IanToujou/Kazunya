package net.toujoustudios.kazunya.command.list.administration;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import net.toujoustudios.kazunya.command.CommandCategory;
import net.toujoustudios.kazunya.command.CommandContext;
import net.toujoustudios.kazunya.command.ICommand;
import net.toujoustudios.kazunya.config.Config;
import net.toujoustudios.kazunya.error.ErrorEmbed;
import net.toujoustudios.kazunya.error.ErrorType;
import net.toujoustudios.kazunya.user.UserManager;
import net.toujoustudios.kazunya.util.ColorUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * This file has been created by Ian Toujou.
 * Project: Kazunya
 * Date: 02/11/2021
 * Time: 04:12
 */
public class AdminBalanceCommand implements ICommand {

    private final Config config;

    public AdminBalanceCommand() {
        config = Config.getDefault();
    }

    @Override
    @SuppressWarnings("all")
    public void handle(CommandContext context) {

        if(!context.getMember().getId().equals(config.getString("user.admin"))) {
            ErrorEmbed.sendError(context, ErrorType.GENERAL_PERMISSION);
            return;
        }

        EmbedBuilder embedBuilder = new EmbedBuilder();
        String action = context.getEvent().getOption("action").getAsString();
        Member target = context.getEvent().getOption("user").getAsMember();

        assert target != null;
        UserManager targetManager = UserManager.getUser(target.getId());
        String currency = config.getString("format.char.currency");

        if(context.getEvent().getOption("amount") != null) {

            if(action.equalsIgnoreCase("get")) {
                ErrorEmbed.sendError(context, ErrorType.COMMAND_INVALID_SYNTAX);
                return;
            }

            double amount = 0;
            String amountString = context.getEvent().getOption("amount").getAsString();
            try {
                amount = Double.parseDouble(amountString);
            } catch(Exception exception) {
                ErrorEmbed.sendError(context, ErrorType.COMMAND_INVALID_NUMBER_DOUBLE);
                return;
            }

            if(amount > 1000000) {
                ErrorEmbed.sendError(context, ErrorType.COMMAND_INVALID_RANGE);
                return;
            }

            switch(action) {
                case "set" -> {
                    targetManager.setAccountMoney(amount);
                    embedBuilder.setDescription("You set the account value of " + target.getAsMention() + " to `" + amount + currency + "`.");
                }
                case "add" -> {
                    targetManager.addAccountMoney(amount);
                    embedBuilder.setDescription("You added `" + amount + currency + "` to the account of " + target.getAsMention() + ".\nNew balance: `" + targetManager.getAccountMoney() + " " + currency + "`.");
                }
                case "remove" -> {
                    targetManager.removeAccountMoney(amount);
                    embedBuilder.setDescription("You removed `" + amount + currency + "` from the account of " + target.getAsMention() + ".\nNew balance: `" + targetManager.getAccountMoney() + " " + currency + "`.");
                }
                default -> {
                    ErrorEmbed.sendError(context, ErrorType.COMMAND_INVALID_OPERATION_GET_SET_ADD_REMOVE);
                    return;
                }
            }

        } else {

            if(!action.equalsIgnoreCase("get")) {
                ErrorEmbed.sendError(context, ErrorType.COMMAND_INVALID_SYNTAX);
                return;
            }

            embedBuilder.setDescription(target.getAsMention() + "'s account balance is currently: `" + targetManager.getAccountMoney() + currency + "`.");

        }

        embedBuilder.setTitle("**:credit_card: Account Modification**");
        embedBuilder.setColor(ColorUtil.getFromRGBString(config.getString("format.color.default")));
        context.getEvent().replyEmbeds(embedBuilder.build()).queue();

    }

    @Override
    public String getName() {
        return "admin-balance";
    }

    @Override
    public String getDescription() {
        return "Modify the account balance of someone.";
    }

    @Override
    public String getEmoji() {
        return "💳";
    }

    @Override
    public List<OptionData> getOptions() {
        List<OptionData> optionData = new ArrayList<>();
        optionData.add(new OptionData(OptionType.STRING, "action", "The action to perform on the account, either set, add, remove or get.", true));
        optionData.add(new OptionData(OptionType.USER, "user", "The user to whom the account belongs to.", true));
        optionData.add(new OptionData(OptionType.STRING, "amount", "The desired amount. It must be a decimal number.", false));
        return optionData;
    }

    @Override
    public CommandCategory getCategory() {
        return CommandCategory.ADMINISTRATION;
    }

}
