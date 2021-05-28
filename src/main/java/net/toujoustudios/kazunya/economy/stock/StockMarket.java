package net.toujoustudios.kazunya.economy.stock;

import net.toujoustudios.kazunya.log.LogLevel;
import net.toujoustudios.kazunya.log.Logger;

import java.util.*;

/**
 * This file was created by IanToujou.
 * Date: 17/05/2021
 * Time: 22:43
 * Project: Kazunya
 */
public class StockMarket {

    private static final HashMap<String, StockMarket> stockMarkets = new HashMap<>();
    private ArrayList<Stock> stocks = new ArrayList<>();
    private HashMap<Stock, Double> stockPrices = new HashMap<Stock, Double>();
    private String id;
    private boolean legal;
    private Timer timer = new Timer();
    private TimerTask timerTask;

    public StockMarket(String id, boolean legal) {

        this.id = id;
        this.legal = legal;
        this.timer = new Timer();

    }

    public static void initialize() {

        Stock.initialize();

        StockMarket defaultMarket = new StockMarket("default_market", true);
        StockMarket blackMarket = new StockMarket("black_market", false);

        defaultMarket.addStock(Stock.getStock("NEKO"));
        blackMarket.addStock(Stock.getStock("NEKO"));

        defaultMarket.initializeMarket();

        stockMarkets.put("default_market", defaultMarket);
        stockMarkets.put("black_market", blackMarket);

    }

    public void addStock(Stock stock) {
        stocks.add(stock);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public HashMap<Stock, Double> getStockPrices() {
        return stockPrices;
    }

    public void setStockPrices(HashMap<Stock, Double> stockPrices) {
        this.stockPrices = stockPrices;
    }

    public double getStockPrice(String id)  {

        Logger.log(LogLevel.DEBUG, getStockPrices().toString());
        Logger.log(LogLevel.DEBUG, Stock.getStock(id).getId());

        return getStockPrices().get(Stock.getStock(id));

    }

    public void initializeMarket() {

        Logger.log(LogLevel.DEBUG, stocks.toString());
        for(Stock currentStock : stocks) stockPrices.put(currentStock, currentStock.getBasePrice());

        timer.schedule(new TimerTask() {

            @Override
            public void run() {

                for(Stock currentStock : stocks) {

                    //Get stock stats
                    double variability = currentStock.getVariability();
                    double upperBound = currentStock.getUpperBound();
                    double lowerBound = currentStock.getLowerBound();
                    double upperProbableBound = currentStock.getUpperProbableBound();
                    double lowerProbableBound = currentStock.getLowerProbableBound();
                    double passBoundProbability = currentStock.getPassBoundProbability();

                    //Calculate randoms
                    boolean passProbableBound = false;

                    double finalOperator = stockPrices.get(currentStock) / variability;

                    //Execute operation
                    double newPrice = 0;
                    int operation = new Random().nextInt(2);
                    if(operation == 0) {
                        newPrice = stockPrices.get(currentStock) + finalOperator;
                    } else {
                        newPrice = stockPrices.get(currentStock) - finalOperator;
                    }

                    stockPrices.remove(currentStock);
                    stockPrices.put(currentStock, (double) Math.round(newPrice * 100.0) / 100.0);

                    System.out.println(stockPrices.get(currentStock));

                }

            }

        }, 0, 1000 * 3);

    }

    public static StockMarket getStockMarket(String id) {
        return stockMarkets.getOrDefault(id, null);
    }

}
