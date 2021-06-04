package net.toujoustudios.kazunya.economy.stock;

import net.toujoustudios.kazunya.config.Config;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

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
        defaultMarket.addStock(Stock.getStock("BUTT"));
        defaultMarket.addStock(Stock.getStock("SUS"));
        defaultMarket.addStock(Stock.getStock("CAT"));
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

        return getStockPrices().get(Stock.getStock(id));

    }

    public void initializeMarket() {

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

                    //Variables
                    double oldPrice = stockPrices.get(currentStock);
                    double maxOperator = oldPrice / 100 * variability;
                    boolean passProbableBound = false;
                    double operator = ThreadLocalRandom.current().nextDouble(maxOperator);
                    int operation = new Random().nextInt(2);
                    double predictedPrice = 0;
                    int passProbableBoundRandom = new Random().nextInt(101);
                    double finalPrice;

                    switch(operation) {
                        case 0: predictedPrice = oldPrice + operator;
                        case 1: predictedPrice = oldPrice - operator;
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

                    stockPrices.remove(currentStock);
                    stockPrices.put(currentStock, (double) Math.round(finalPrice * 100.0) / 100.0);

                }

            }

        }, 0, 1000 * Config.STOCK_CALCULATION_INTERVAL);

    }

    public static StockMarket getStockMarket(String id) {
        return stockMarkets.getOrDefault(id, null);
    }

}
