package net.toujoustudios.kazunya.economy.stock;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * This file was created by IanToujou.
 * Date: 17/05/2021
 * Time: 22:43
 * Project: Kazunya
 */
public class StockMarket {

    private static HashMap<String, StockMarket> stockMarkets = new HashMap<>();
    private ArrayList<Stock> stocks = new ArrayList<>();
    private String id;
    private boolean legal;

    public StockMarket(String id, boolean legal) {

        this.id = id;
        this.legal = legal;

    }

    public static void initialize() {

        Stock.initialize();

        StockMarket defaultMarket = new StockMarket("default_market", true);
        StockMarket blackMarket = new StockMarket("black_market", false);

        defaultMarket.addStock(Stock.getStock("neko"));

        stockMarkets.put("default_market", defaultMarket);
        stockMarkets.put("black_market", blackMarket);

        stockMarkets.get("default_market");

    }

    public void addStock(Stock stock) {
        stocks.add(stock);
    }

    public String getID() {
        return id;
    }

    public void setID(String id) {
        this.id = id;
    }

    public boolean isLegal() {
        return legal;
    }

    public void setLegal(boolean legal) {
        this.legal = legal;
    }

    public ArrayList<Stock> getStocks() {
        return stocks;
    }

    public void setStocks(ArrayList<Stock> stocks) {
        this.stocks = stocks;
    }

    public static StockMarket getStockMarket(String id) {
        return stockMarkets.getOrDefault(id, null);
    }

}
