package net.toujoustudios.kazunya.command.list.economy;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.TextChannel;
import net.toujoustudios.kazunya.command.CommandCategory;
import net.toujoustudios.kazunya.command.CommandContext;
import net.toujoustudios.kazunya.command.ICommand;
import net.toujoustudios.kazunya.config.Config;
import net.toujoustudios.kazunya.economy.stock.Stock;
import net.toujoustudios.kazunya.economy.stock.StockMarket;

import java.util.List;

/**
 * This file was created by IanToujou.
 * Date: 17/05/2021
 * Time: 23:23
 * Project: Kazunya
 */
public class MarketinfoCommand implements ICommand {

    @Override
    public void handle(CommandContext context) {

        List<String> args = context.getArgs();
        TextChannel channel = context.getChannel();
        EmbedBuilder embedBuilder = new EmbedBuilder();

        embedBuilder.setColor(Config.DEFAULT_EMBED_COLOR);

        if(args.size() > 1) {

            embedBuilder.setTitle("**__ERROR__**");
            embedBuilder.setColor(Config.ERROR_EMBED_COLOR);
            embedBuilder.setDescription(":x: The command syntax is not correct.\nPlease use **" + Config.DEFAULT_PREFIX + getUsage() + "**");
            channel.sendMessage(embedBuilder.build()).queue();
            return;

        }

        if(args.size() == 0) {

            embedBuilder.setTitle("**__STOCK MARKET__**");
            StringBuilder stockBuilder = new StringBuilder();

            for(Stock stock : StockMarket.getStockMarket("default_market").getStocks()) {

                stockBuilder.append("`" + stock.getDiscriminator() + "` | :chart: Price: " + stock.getBasePrice() + Config.CURRENCY_CHAR);

            }

            embedBuilder.setDescription(stockBuilder.toString());

        } else {

            String discriminator = args.get(0).toUpperCase();

        }

        channel.sendMessage(embedBuilder.build()).queue();

    }

    @Override
    public String getName() {
        return "marketinfo";
    }

    @Override
    public String getHelp() {
        return "Shows a list of the currently available stock markets and detailed information.";
    }

    @Override
    public String getUsage() {
        return "marketlist [(discriminator)]";
    }

    @Override
    public boolean isNSFW() {
        return false;
    }

    @Override
    public CommandCategory getCategory() {
        return CommandCategory.ECONOMY;
    }

}
