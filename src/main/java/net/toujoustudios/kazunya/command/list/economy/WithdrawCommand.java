package net.toujoustudios.kazunya.command.list.economy;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import net.toujoustudios.kazunya.command.CommandCategory;
import net.toujoustudios.kazunya.command.CommandContext;
import net.toujoustudios.kazunya.command.ICommand;
import net.toujoustudios.kazunya.config.Config;
import net.toujoustudios.kazunya.data.user.UserManager;
import net.toujoustudios.kazunya.error.ErrorEmbed;
import net.toujoustudios.kazunya.error.ErrorType;
import net.toujoustudios.kazunya.util.ColorUtil;

import java.util.ArrayList;
import java.util.List;

public class WithdrawCommand implements ICommand {

    private final Config config;

    public WithdrawCommand() {
        this.config = Config.getDefault();
    }

    @Override
    public void handle(CommandContext context) {

        EmbedBuilder embedBuilder = new EmbedBuilder();
        embedBuilder.setColor(ColorUtil.getFromRGBString(config.getString("format.color.default")));

        UserManager memberManager = UserManager.getUser(context.getMember());

        double amount = context.getArgs().get(0).getAsDouble();
        String currency = config.getString("format.char.currency");

        if(amount <= 0) {
            ErrorEmbed.sendError(context, ErrorType.COMMAND_INVALID_RANGE);
            return;
        }

        if(memberManager.getBankMoney() < amount) {
            ErrorEmbed.sendError(context, ErrorType.ACTION_NOT_ENOUGH_BANK_MONEY);
            return;
        }

        memberManager.addWalletMoney(amount);
        memberManager.removeBankMoney(amount);

        embedBuilder.setTitle("**:moneybag: Bank Withdrawal**");
        embedBuilder.setDescription("You successfully withdrew `" + amount + currency + "` from your bank account.");
        context.getEvent().replyEmbeds(embedBuilder.build()).queue();

    }

    @Override
    public String getName() {
        return "withdraw";
    }

    @Override
    public String getDescription() {
        return "Withdraw money from your bank account to your wallet.";
    }

    @Override
    public String getEmoji() {
        return "💰";
    }

    @Override
    public List<OptionData> getOptions() {
        List<OptionData> optionData = new ArrayList<>();
        optionData.add(new OptionData(OptionType.INTEGER, "amount", "The amount of cash you want to deposit.", true));
        return optionData;
    }

    @Override
    public CommandCategory getCategory() {
        return CommandCategory.ECONOMY;
    }

}
