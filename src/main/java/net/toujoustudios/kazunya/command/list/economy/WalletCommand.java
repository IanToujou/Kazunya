package net.toujoustudios.kazunya.command.list.economy;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import net.toujoustudios.kazunya.command.CommandCategory;
import net.toujoustudios.kazunya.command.CommandContext;
import net.toujoustudios.kazunya.command.ICommand;
import net.toujoustudios.kazunya.config.Config;
import net.toujoustudios.kazunya.user.UserManager;
import net.toujoustudios.kazunya.util.ColorUtil;

import java.util.Collections;
import java.util.List;

public class WalletCommand implements ICommand {

    private final Config config;

    public WalletCommand() {
        config = Config.getDefault();
    }

    @Override
    public void handle(CommandContext context) {

        EmbedBuilder embedBuilder = new EmbedBuilder();

        UserManager memberManager = UserManager.getUser(context.getMember().getId());
        String currency = config.getString("format.char.currency");
        double amount = memberManager.getAccountMoney();

        embedBuilder.setColor(ColorUtil.getFromRGBString(config.getString("format.color.default")));
        embedBuilder.setTitle("**:dollar: Your Wallet**");
        embedBuilder.setDescription("You currently have `" + amount + currency + "` in your wallet.");

        context.getEvent().replyEmbeds(embedBuilder.build()).queue();

    }

    @Override
    public String getName() {
        return "wallet";
    }

    @Override
    public String getDescription() {
        return "Display or manage your wallet.";
    }

    @Override
    public String getEmoji() {
        return "💵";
    }

    @Override
    public List<OptionData> getOptions() {
        return Collections.emptyList();
    }

    @Override
    public CommandCategory getCategory() {
        return CommandCategory.ECONOMY;
    }

}
