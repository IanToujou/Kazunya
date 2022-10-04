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

public class DepositCommand implements ICommand {

    private final Config config;

    public DepositCommand() {
        this.config = Config.getDefault();
    }

    @Override
    public void handle(CommandContext context) {

        EmbedBuilder embedBuilder = new EmbedBuilder();
        embedBuilder.setColor(ColorUtil.getFromRGBString(config.getString("format.color.default")));

        UserManager memberManager = UserManager.getUser(context.getMember().getId());

        double amount = context.getArgs().get(0).getAsDouble();
        String currency = config.getString("format.char.currency");

        if(amount <= 0) {
            ErrorEmbed.sendError(context, ErrorType.COMMAND_INVALID_RANGE);
            return;
        }

        if(memberManager.getWalletMoney() < amount) {
            ErrorEmbed.sendError(context, ErrorType.ACTION_NOT_ENOUGH_WALLET_MONEY);
            return;
        }

        memberManager.removeWalletMoney(amount);
        memberManager.addBankMoney(amount);

        embedBuilder.setTitle("**:moneybag: Bank Deposit**");
        embedBuilder.setDescription("You successfully deposited `" + amount + currency + "` into your bank account.");
        context.getEvent().replyEmbeds(embedBuilder.build()).queue();

    }

    @Override
    public String getName() {
        return "deposit";
    }

    @Override
    public String getDescription() {
        return "Deposit money from your wallet to your bank account.";
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
