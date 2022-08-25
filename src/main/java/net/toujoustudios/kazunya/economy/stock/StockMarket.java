package net.toujoustudios.kazunya.economy.stock;

import net.toujoustudios.kazunya.config.Config;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

/**
 * This file has been created by Ian Toujou.
 * Project: Kazunya
 * Date: 04/11/2021
 * Time: 23:59
 */
public class StockMarket {

    private static final HashMap<String, StockMarket> stockMarkets = new HashMap<>();
    private ArrayList<Stock> stocks = new ArrayList<>();
    private HashMap<Stock, Double> stockPrices = new HashMap<>();
    private String id;
    private boolean legal;
    private Timer timer = new Timer();
    private TimerTask timerTask;
    private Config config;

    public StockMarket(String id, boolean legal) {

        this.id = id;
        this.legal = legal;
        this.timer = new Timer();
        this.config = Config.getDefault();

    }

    public static void initialize() {

        Stock.initialize();

        StockMarket defaultMarket = new StockMarket("default_market", true);

        defaultMarket.addStock(Stock.getStock("NEKO"));
        defaultMarket.addStock(Stock.getStock("BUTT"));
        defaultMarket.addStock(Stock.getStock("SUS"));
        defaultMarket.addStock(Stock.getStock("CAT"));

        defaultMarket.initializeMarket();

        stockMarkets.put("default_market", defaultMarket);

    }

    public static StockMarket getStockMarket(String id) {
        return stockMarkets.getOrDefault(id, null);
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

    public double getStockPrice(String id) {

        return getStockPrices().get(Stock.getStock(id));

    }

    public void initializeMarket() {

        for(Stock currentStock : stocks) stockPrices.put(currentStock, currentStock.getBasePrice());

        timer.schedule(new TimerTask() {

            @Override
            public void run() {

                for(Stock currentStock : stocks) {

                    double variability = currentStock.getVariability();
                    double upperBound = currentStock.getUpperBound();
                    double lowerBound = currentStock.getLowerBound();
                    double upperProbableBound = currentStock.getUpperProbableBound();
                    double lowerProbableBound = currentStock.getLowerProbableBound();
                    double passBoundProbability = currentStock.getPassBoundProbability();

                    double oldPrice = stockPrices.get(currentStock);
                    double maxOperator = oldPrice / 100 * variability;
                    boolean passProbableBound = false;
                    double operator = ThreadLocalRandom.current().nextDouble(maxOperator);
                    int operation = new Random().nextInt(2);
                    double predictedPrice = 0;
                    int passProbableBoundRandom = new Random().nextInt(101);
                    double finalPrice;

                    switch(operation) {
                        case 0:
                            predictedPrice = oldPrice + operator;
                        case 1:
                            predictedPrice = oldPrice - operator;
                    }

                    if(passProbableBoundRandom <= passBoundProbability) passProbableBound = true;

                    if(predictedPrice > upperBound || predictedPrice < lowerBound) {
                        if(predictedPrice > upperBound) {
                            finalPrice = oldPrice - operator;
                        } else {
                            finalPrice = oldPrice + operator;
                        }
                    } else {
                        if(predictedPrice > upperProbableBound || predictedPrice < lowerProbableBound) {
                            if(passProbableBound) {
                                finalPrice = predictedPrice;
                            } else {
                                if(predictedPrice > upperBound) {
                                    finalPrice = oldPrice - operator;
                                } else {
                                    finalPrice = oldPrice + operator;
                                }
                            }
                        } else {
                            finalPrice = predictedPrice;
                        }
                    }

                    double roundedPrice = (double) Math.round(finalPrice * 100.0) / 100.0;

                    stockPrices.remove(currentStock);
                    stockPrices.put(currentStock, roundedPrice);

                }

            }

        }, 0, 1000L * config.getInteger("economy.stockmarket.interval"));

    }

}
