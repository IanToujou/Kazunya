package net.toujoustudios.kazunya.command.list.administration;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
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
    public void handle(CommandContext context) {

        if (!context.getMember().getId().equals(config.getString("user.admin"))) {
            ErrorEmbed.sendError(context, ErrorType.GENERAL_PERMISSION);
            return;
        }

        List<OptionMapping> args = context.getArgs();
        EmbedBuilder embedBuilder = new EmbedBuilder();

        if (args.size() != 3) {
            ErrorEmbed.sendError(context, ErrorType.COMMAND_INVALID_SYNTAX);
            return;
        }

        String action = args.get(0).getAsString().toLowerCase();
        double value;

        try {
            value = Double.parseDouble(args.get(2).getAsString());
        } catch (Exception exception) {
            ErrorEmbed.sendError(context, ErrorType.COMMAND_INVALID_NUMBER_DOUBLE);
            return;
        }

        Member target = args.get(1).getAsMember();
        assert target != null;
        UserManager targetManager = UserManager.getUser(target.getId());
        String currency = config.getString("format.char.currency");

        switch (action) {
            case "set" -> {
                targetManager.setMoney(value);
                embedBuilder.setDescription("You set the account value of " + target.getAsMention() + " to `" + value + " " + currency + "`.");
            }
            case "add" -> {
                targetManager.addAccountMoney(value);
                embedBuilder.setDescription("You added `" + value + " " + currency + "` to the account of " + target.getAsMention() + ".\nNew balance: `" + targetManager.getAccountMoney() + " " + currency + "`.");
            }
            case "remove" -> {
                targetManager.removeAccountMoney(value);
                embedBuilder.setDescription("You removed `" + value + " " + currency + "` from the account of " + target.getAsMention() + ".\nNew balance: `" + targetManager.getAccountMoney() + " " + currency + "`.");
            }
            case "get" -> embedBuilder.setDescription(target.getAsMention() + "'s account balance is currently: `" + targetManager.getAccountMoney() + " " + currency + "`.");
            default -> {
                ErrorEmbed.sendError(context, ErrorType.COMMAND_INVALID_OPERATION_BALANCE);
                return;
            }
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
        optionData.add(new OptionData(OptionType.STRING, "amount", "The desired amount. It must be a decimal number.", true));
        return optionData;
    }

    @Override
    public CommandCategory getCategory() {
        return CommandCategory.ADMINISTRATION;
    }

}
