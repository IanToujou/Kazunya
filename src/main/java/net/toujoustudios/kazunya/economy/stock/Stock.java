package net.toujoustudios.kazunya.economy.stock;

import java.util.HashMap;

/**
 * This file was created by IanToujou.
 * Date: 17/05/2021
 * Time: 22:44
 * Project: Kazunya
 */
public class Stock {

    private static HashMap<String, Stock> stocks = new HashMap<>();
    private String id;
    private String name;
    private String discriminator;
    private int basePrice;
    private double variability;

    /**
     * @param id The id for the stock. This is used to create a new stock and will be used whenever you need to refer to the given stock.
     * @param name The full name of the stock.
     * @param discriminator A short series of letters to define your stock. For instance, BTC, ETH, GOLD, etc.
     * @param basePrice The base price for the stock. This will be taken into account when calculating the price of a stock.
     * @param variability A variability value in percent, this can range from 0 to 100. 0 is no variability at all, so the price stays always the same. A variability of 100 means that every price between 0 and the double of the base price can be reached.
     */
    public Stock(String id, String name, String discriminator, int basePrice, double variability) {

        this.id = id;
        this.name = name;
        this.discriminator = discriminator;
        this.basePrice = basePrice;
        this.variability = variability;

    }

    public static void initialize() {

        stocks.put("neko", new Stock("neko", "Neko Inc.", "NEKO", 420, 2.5));

    }

    public String getID() {
        return id;
    }

    public void setID(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDiscriminator() {
        return discriminator;
    }

    public void setDiscriminator(String discriminator) {
        this.discriminator = discriminator;
    }

    public int getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(int basePrice) {
        this.basePrice = basePrice;
    }

    public double getVariability() {
        return variability;
    }

    public void setVariability(double variability) {
        this.variability = variability;
    }

    public static Stock getStock(String id) {
        return stocks.getOrDefault(id, null);
    }

    public static HashMap<String, Stock> getStocks() {
        return stocks;
    }

}
