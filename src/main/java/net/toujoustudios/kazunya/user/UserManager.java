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
    private double accountMoney;
    private String partner;

    public UserManager(String userId) {

        this.userId = userId;
        this.accountMoney = UserDataManager.getAccountMoney(userId);
        this.partner = UserDataManager.getPartner(userId);

    }

    public static UserManager getUser(String userId) {

        if (users.containsKey(userId)) return users.get(userId);
        UserManager userManager = new UserManager(userId);
        users.put(userId, userManager);
        return userManager;

    }

    public static void unloadAll() {

        for (Map.Entry<String, UserManager> entry : users.entrySet()) {
            users.get(entry.getKey()).unload();
        }

    }

    public void unload() {

        save();
        destroy();

    }

    public void save() {

        UserDataManager.setAccountMoney(userId, accountMoney);
        if (hasPartner()) {
            UserDataManager.setPartner(userId, partner);
        } else {
            UserDataManager.removePartner(userId);
        }

    }

    public void destroy() {

        users.remove(this);

    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public double getAccountMoney() {
        return accountMoney;
    }

    public void setMoney(double accountMoney) {
        this.accountMoney = accountMoney;
    }

    public void addAccountMoney(double amount) {
        setMoney(getAccountMoney() + amount);
    }

    public void removeAccountMoney(double amount) {
        setMoney(getAccountMoney() - amount);
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
