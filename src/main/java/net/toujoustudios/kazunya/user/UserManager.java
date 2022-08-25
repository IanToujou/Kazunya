package net.toujoustudios.kazunya.user;

import net.toujoustudios.kazunya.data.UserDataManager;

import java.util.HashMap;
import java.util.Map;

/**
 * This file has been created by Ian Toujou.
 * Project: Kazunya
 * Date: 26/08/2021
 * Time: 23:33
 */
public class UserManager {

    private static final HashMap<String, UserManager> users = new HashMap<>();
    private String userId;
    private boolean usageBanned;
    private double accountMoney;
    private double walletMoney;
    private String partner;

    public UserManager(String userId) {

        this.userId = userId;
        this.usageBanned = UserDataManager.isUsageBanned(userId);
        this.accountMoney = UserDataManager.getAccountMoney(userId);
        this.walletMoney = UserDataManager.getWalletMoney(userId);
        this.partner = UserDataManager.getPartner(userId);

    }

    public static UserManager getUser(String userId) {

        if (users.containsKey(userId)) return users.get(userId);
        UserManager userManager = new UserManager(userId);
        users.put(userId, userManager);
        return userManager;

    }

    public static void saveAll() {
        if (users == null || users.size() == 0) return;
        for (Map.Entry<String, UserManager> entry : users.entrySet()) {
            users.get(entry.getKey()).save();
        }
    }

    public static void unloadAll() {
        saveAll();
        users.clear();
    }

    public void save() {
        UserDataManager.setUsageBanned(userId, usageBanned);
        UserDataManager.setAccountMoney(userId, accountMoney);
        UserDataManager.setWalletMoney(userId, walletMoney);
        if (hasPartner()) {
            UserDataManager.setPartner(userId, partner);
        } else {
            UserDataManager.removePartner(userId);
        }
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public boolean isUsageBanned() {
        return usageBanned;
    }

    public void setBanned(boolean usageBanned) {
        this.usageBanned = usageBanned;
    }

    public double getAccountMoney() {
        return accountMoney;
    }

    public void setAccountMoney(double accountMoney) {
        this.accountMoney = accountMoney;
    }

    public void addAccountMoney(double amount) {
        setAccountMoney(getAccountMoney() + amount);
    }

    public void removeAccountMoney(double amount) {
        setAccountMoney(getAccountMoney() - amount);
    }

    public double getWalletMoney() {
        return walletMoney;
    }

    public void setWalletMoney(double walletMoney) {
        this.walletMoney = walletMoney;
    }

    public void addWalletMoney(double amount) {
        setWalletMoney(getWalletMoney() + amount);
    }

    public void removeWalletMoney(double amount) {
        setWalletMoney(getWalletMoney() - amount);
    }

    public String getPartner() {
        return partner;
    }

    public void setPartner(String partnerId) {
        this.partner = partnerId;
    }

    public boolean hasPartner() {
        return partner != null;
    }

    public void removePartner() {
        this.partner = null;
    }

}
