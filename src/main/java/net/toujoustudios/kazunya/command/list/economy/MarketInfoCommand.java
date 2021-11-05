package net.toujoustudios.kazunya.command.list.economy;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import net.toujoustudios.kazunya.color.ColorTools;
import net.toujoustudios.kazunya.command.CommandCategory;
import net.toujoustudios.kazunya.command.CommandContext;
import net.toujoustudios.kazunya.command.ICommand;
import net.toujoustudios.kazunya.config.Config;
import net.toujoustudios.kazunya.economy.stock.Stock;
import net.toujoustudios.kazunya.economy.stock.StockMarket;
import net.toujoustudios.kazunya.error.ErrorEmbed;
import net.toujoustudios.kazunya.error.ErrorType;

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

        embedBuilder.setColor(ColorTools.getFromRGBString(config.getString("format.color.default")));

        if (args.size() > 1) {
            ErrorEmbed.sendError(context, ErrorType.COMMAND_INVALID_SYNTAX);
            return;
        }

        if (args.size() == 0) {

            embedBuilder.setTitle("**__STOCK MARKET__**");
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("*Here is a full list of the available stocks or currencies.\nPlease type `nya marketinfo [id]` to get more information on a stock.*\n");

            for (Stock stock : StockMarket.getStockMarket("default_market").getStocks()) {

                stringBuilder.append("\n`" + stock.getId() + "` | :chart: Price: " + stockMarket.getStockPrice(stock.getId()) + config.getString("format.char.currency"));

            }

            embedBuilder.setDescription(stringBuilder.toString());
            embedBuilder.setThumbnail("https://github.com/IanToujou/Kazunya/blob/master/src/main/resources/chart_icon.png?raw=true");
            context.getEvent().replyEmbeds(embedBuilder.build()).queue();
            return;

        } else {

            String discriminator = args.get(0).getAsString().toUpperCase();
            StringBuilder stringBuilder = new StringBuilder();

            embedBuilder.setTitle("**__STOCK MARKET: " + discriminator + "__**");

            for (Stock stock : StockMarket.getStockMarket("default_market").getStocks()) {

                if (stock.getId().equalsIgnoreCase(discriminator)) {

                    stringBuilder.append(":regional_indicator_a: **Name:** " + stock.getName() + "\n");
                    stringBuilder.append(":hash: **ID:** " + stock.getId() + "\n");
                    stringBuilder.append(":chart: **Current Price:** " + stockMarket.getStockPrice(stock.getId()) + config.getString("format.char.currency") + "\n");
                    embedBuilder.setDescription(stringBuilder.toString());
                    embedBuilder.setThumbnail("https://github.com/IanToujou/Kazunya/blob/master/src/main/resources/chart_icon.png?raw=true");
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
