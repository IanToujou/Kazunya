package net.toujoustudios.kazunya.economy.stock;

import java.util.HashMap;

/**
 * This file has been created by Ian Toujou.
 * Project: Kazunya
 * Date: 04/11/2021
 * Time: 23:58
 */
public class Stock {

    private static final HashMap<String, Stock> stocks = new HashMap<>();
    private String id;
    private String name;
    private double basePrice;
    private double variability;
    private double upperBound;
    private double lowerBound;
    private double upperProbableBound;
    private double lowerProbableBound;
    private int passBoundProbability;

    /**
     * @param id                   The id for the stock. This is used to create a new stock and will be used whenever you need to refer to the given stock. For examle: BTC, ETH, XMR, etc.
     * @param name                 The full name of the stock.
     * @param basePrice            The base price for the stock. This will be taken into account when calculating the price of a stock.
     * @param variability          A variability value in percent, this can range from 0 to 100. 0 is no variability at all, so the price stays always the same. A variability of 100 means that every price between 0 and the double of the base price can be reached.
     * @param upperBound           The absolute upper bound for the stock. The price will never reach this point and it will always be below the given value.
     * @param lowerBound           The absolute lower bound for the stock. The price will never reach this point and it will always be above the given value.
     * @param upperProbableBound   The price will likely stay below this value. However, it may pass this value with a given probability.
     * @param lowerProbableBound   The price will likely stay above this value. However, it may pass this value with a given probability.
     * @param passBoundProbability The probability (%), which will be used to calculate if the price exceeds the upper or lower probable bounds.
     */
    public Stock(String id, String name, int basePrice, double variability, double upperBound, double lowerBound, double upperProbableBound, double lowerProbableBound, int passBoundProbability) {

        this.id = id;
        this.name = name;
        this.basePrice = basePrice;
        this.variability = variability;
        this.upperBound = upperBound;
        this.lowerBound = lowerBound;
        this.upperProbableBound = upperProbableBound;
        this.lowerProbableBound = lowerProbableBound;
        this.passBoundProbability = passBoundProbability;

    }

    /**
     * Initializes all the stocks by creating and adding new stocks.
     */
    public static void initialize() {

        stocks.put("NEKO", new Stock("NEKO", "Neko Inc.", 420, 2.5, 1000, 200, 600, 400, 60));
        stocks.put("BUTT", new Stock("BUTT", "Buttcoin", 1000, 5, 2000, 200, 1500, 500, 20));
        stocks.put("SUS", new Stock("SUS", "Amogus", 80, 1.5, 200, 10, 160, 30, 75));
        stocks.put("CAT", new Stock("CAT", "Catgirl Research", 800, 3.7, 1200, 500, 1000, 650, 65));

    }

    public static Stock getStock(String id) {
        return stocks.getOrDefault(id, null);
    }

    public static HashMap<String, Stock> getStocks() {
        return stocks;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(double basePrice) {
        this.basePrice = basePrice;
    }

    public double getVariability() {
        return variability;
    }

    public void setVariability(double variability) {
        this.variability = variability;
    }

    public double getUpperBound() {
        return upperBound;
    }

    public void setUpperBound(double upperBound) {
        this.upperBound = upperBound;
    }

    public double getLowerBound() {
        return lowerBound;
    }

    public void setLowerBound(double lowerBound) {
        this.lowerBound = lowerBound;
    }

    public double getUpperProbableBound() {
        return upperProbableBound;
    }

    public void setUpperProbableBound(double upperProbableBound) {
        this.upperProbableBound = upperProbableBound;
    }

    public double getLowerProbableBound() {
        return lowerProbableBound;
    }

    public void setLowerProbableBound(double lowerProbableBound) {
        this.lowerProbableBound = lowerProbableBound;
    }

    public int getPassBoundProbability() {
        return passBoundProbability;
    }

    public void setPassBoundProbability(int passBoundProbability) {
        this.passBoundProbability = passBoundProbability;
    }

}
