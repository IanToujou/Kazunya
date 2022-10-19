package net.toujoustudios.kazunya.command.list.economy;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
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

public class TransferCommand implements ICommand {

    private final Config config;

    public TransferCommand() {
        this.config = Config.getDefault();
    }

    @Override
    public void handle(CommandContext context) {

        EmbedBuilder embedBuilder = new EmbedBuilder();
        embedBuilder.setColor(ColorUtil.getFromRGBString(config.getString("format.color.default")));

        Member member = context.getMember();
        UserManager memberManager = UserManager.getUser(member);

        Member target = context.getArgs().get(0).getAsMember();
        assert target != null;

        if(target.getId().equals(member.getId())) {
            ErrorEmbed.sendError(context, ErrorType.COMMAND_INVALID_USER_SELF);
            return;
        }

        UserManager targetManager = UserManager.getUser(target);

        double amount = context.getArgs().get(1).getAsDouble();
        String currency = config.getString("format.char.currency");

        if(amount <= 0) {
            ErrorEmbed.sendError(context, ErrorType.COMMAND_INVALID_RANGE);
            return;
        }

        if(memberManager.getBankMoney() < amount) {
            ErrorEmbed.sendError(context, ErrorType.ACTION_NOT_ENOUGH_BANK_MONEY);
            return;
        }

        memberManager.removeBankMoney(amount);
        targetManager.addBankMoney(amount);

        embedBuilder.setTitle("**:credit_card: Bank Transfer**");
        embedBuilder.setDescription(":white_check_mark: Transfer successful!\nYou successfully transferred `" + amount + currency + "` to " + target.getAsMention() + ".");
        context.getEvent().replyEmbeds(embedBuilder.build()).queue();

    }

    @Override
    public String getName() {
        return "transfer";
    }

    @Override
    public String getDescription() {
        return "Transfer money from your bank account to another user.";
    }

    @Override
    public String getEmoji() {
        return "💳";
    }

    @Override
    public List<OptionData> getOptions() {
        List<OptionData> optionData = new ArrayList<>();
        optionData.add(new OptionData(OptionType.USER, "user", "The user you want to transfer money to.", true));
        optionData.add(new OptionData(OptionType.INTEGER, "amount", "The amount of money you want to transfer.", true));
        return optionData;
    }

    @Override
    public CommandCategory getCategory() {
        return CommandCategory.ECONOMY;
    }

}
