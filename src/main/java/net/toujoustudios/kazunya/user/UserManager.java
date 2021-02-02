package net.toujoustudios.kazunya.user;

import net.toujoustudios.kazunya.data.DataManager;

import java.util.HashMap;
import java.util.Map;

/**
 * This file was created by IanToujou.
 * Date: 02.02.2021
 * Time: 08:30
 * Project: Kazunya
 */
public class UserManager {

    private static final HashMap<String, UserManager> users = new HashMap<>();
    private String userId;
    private int money;
    private String partner;

    public UserManager(String userId) {

        this.userId = userId;
        this.money = DataManager.getMoney(userId);
        this.partner = DataManager.getPartner(userId);

    }

    public static UserManager getUser(String userId) {

        if(users.containsKey(userId)) return users.get(userId);
        UserManager userManager = new UserManager(userId);
        users.put(userId, userManager);
        return userManager;

    }

    public static void unloadAll() {

        for(Map.Entry<String, UserManager> entry : users.entrySet()) {

            users.get(entry.getKey()).unload();

        }

    }

    public void unload() {

        save();
        destroy();

    }

    public void save() {

        DataManager.setMoney(userId, money);
        if(hasPartner()) {
            DataManager.setPartner(userId, partner);
        } else {
            DataManager.removePartner(userId);
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

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public void addMoney(int amount) {
        setMoney(getMoney() + amount);
    }

    public void removeMoney(int amount) {
        setMoney(getMoney() - amount);
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
