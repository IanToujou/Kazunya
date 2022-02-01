package net.toujoustudios.kazunya.command.list.economy;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import net.toujoustudios.kazunya.command.CommandCategory;
import net.toujoustudios.kazunya.command.CommandContext;
import net.toujoustudios.kazunya.command.ICommand;
import net.toujoustudios.kazunya.config.Config;
import net.toujoustudios.kazunya.economy.stock.Stock;
import net.toujoustudios.kazunya.economy.stock.StockMarket;
import net.toujoustudios.kazunya.error.ErrorEmbed;
import net.toujoustudios.kazunya.error.ErrorType;
import net.toujoustudios.kazunya.util.ColorUtil;
import net.toujoustudios.kazunya.util.LinkUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * This file has been created by Ian Toujou.
 * Project: Kazunya
 * Date: 05/11/2021
 * Time: 00:05
 */
public class MarketInfoCommand implements ICommand {

    private final Config config;

    public MarketInfoCommand() {
        config = Config.getDefault();
    }

    @Override
    public void handle(CommandContext context) {

        List<OptionMapping> args = context.getArgs();
        Member member = context.getMember();
        EmbedBuilder embedBuilder = new EmbedBuilder();
        StockMarket stockMarket = StockMarket.getStockMarket("default_market");

        embedBuilder.setColor(ColorUtil.getFromRGBString(config.getString("format.color.default")));

        if (args.size() > 1) {
            ErrorEmbed.sendError(context, ErrorType.COMMAND_INVALID_SYNTAX);
            return;
        }

        if (args.size() == 0) {

            embedBuilder.setTitle(":chart_with_upwards_trend: **Stock Market**");
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("*Here is a full list of the available stocks or currencies.\nPlease type `/marketinfo [id]` to get more information on a stock.*\n");

            for (Stock stock : StockMarket.getStockMarket("default_market").getStocks()) stringBuilder.append("\n`").append(stock.getId()).append("` | :chart: Price: ").append(stockMarket.getStockPrice(stock.getId())).append(config.getString("format.char.currency"));

            embedBuilder.setDescription(stringBuilder.toString());
            embedBuilder.setThumbnail(config.getString("assets.img.icon_stock_market"));
            context.getEvent().replyEmbeds(embedBuilder.build()).queue();
            return;

        } else {

            String discriminator = args.get(0).getAsString().toUpperCase();
            StringBuilder stringBuilder = new StringBuilder();

            embedBuilder.setTitle("**:chart_with_upwards_trend: Stock Market: " + discriminator + "**");

            for (Stock stock : StockMarket.getStockMarket("default_market").getStocks()) {

                if (stock.getId().equalsIgnoreCase(discriminator)) {

                    stringBuilder.append(":regional_indicator_a: **Name:** ").append(stock.getName()).append("\n");
                    stringBuilder.append(":hash: **ID:** ").append(stock.getId()).append("\n");
                    stringBuilder.append(":chart: **Current Price:** ").append(stockMarket.getStockPrice(stock.getId())).append(config.getString("format.char.currency")).append("\n");
                    embedBuilder.setDescription(stringBuilder.toString());
                    embedBuilder.setThumbnail(config.getString("assets.img.icon_stock_single"));
                    context.getEvent().replyEmbeds(embedBuilder.build()).queue();
                    return;

                }

            }

        }

        ErrorEmbed.sendError(context, ErrorType.COMMAND_INVALID_STOCK);

    }

    @Override
    public String getName() {
        return "marketinfo";
    }

    @Override
    public String getDescription() {
        return "Retrieve information from the virtual stock market.";
    }

    @Override
    public List<OptionData> getOptions() {
        List<OptionData> optionData = new ArrayList<>();
        optionData.add(new OptionData(OptionType.STRING, "stock", "The stock you want to retrieve information from.", false));
        return optionData;
    }

    @Override
    public CommandCategory getCategory() {
        return CommandCategory.ECONOMY;
    }

}
