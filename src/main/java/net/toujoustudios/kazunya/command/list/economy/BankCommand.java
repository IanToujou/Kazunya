package net.toujoustudios.kazunya.command.list.economy;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import net.toujoustudios.kazunya.command.CommandCategory;
import net.toujoustudios.kazunya.command.CommandContext;
import net.toujoustudios.kazunya.command.ICommand;
import net.toujoustudios.kazunya.config.Config;
import net.toujoustudios.kazunya.model.UserManager;
import net.toujoustudios.kazunya.util.ColorUtil;

import java.util.Collections;
import java.util.List;

public class BankCommand implements ICommand {

    public final Config config;

    public BankCommand() {
        this.config = Config.getDefault();
    }

    @Override
    public void handle(CommandContext context) {

        EmbedBuilder embedBuilder = new EmbedBuilder();
        Member member = context.getMember();
        UserManager memberManager = UserManager.getUser(member);
        String currency = config.getString("format.char.currency");
        double accountMoney = memberManager.getBankMoney();

        String ibanBegin = "NYA" + member.getId().substring(0, 2);
        String processId = member.getId().substring(2, 18);

        String iban = ibanBegin + java.util.Arrays.toString(processId.split("(?<=\\G....)"));
        iban = iban.replace("[", " ");
        iban = iban.replace("]", "");
        iban = iban.replace(",", "");

        embedBuilder.setColor(ColorUtil.getFromRGBString(config.getString("format.color.default")));
        embedBuilder.setTitle("**:bank: Bank Account**");
        embedBuilder.setThumbnail(config.getString("assets.img.icon_money"));
        embedBuilder.setDescription("Here is your current bank account status.");
        embedBuilder.addField("**:credit_card: Client Information:**", "Name: `" + member.getEffectiveName() + "`", false);
        embedBuilder.addField("**:euro: Current Account:**", "IBAN: `" + iban + "`\nAmount: `" + accountMoney + currency + "`", false);

        context.getInteraction().replyEmbeds(embedBuilder.build()).queue();

    }

    @Override
    public String getName() {
        return "bank";
    }

    @Override
    public String getDescription() {
        return "Retrieve information about your current bank account.";
    }

    @Override
    public String getEmoji() {
        return "🏦";
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
