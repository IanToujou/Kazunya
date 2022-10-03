package net.toujoustudios.kazunya.user;

import net.toujoustudios.kazunya.data.ban.UserBan;
import net.toujoustudios.kazunya.data.ban.UserBanManager;
import net.toujoustudios.kazunya.log.LogLevel;
import net.toujoustudios.kazunya.log.Logger;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class UserManager {

    private static final HashMap<String, UserManager> users = new HashMap<>();
    private final String id;
    private UserBan ban;

    public UserManager(String id) {
        this.id = id;
        this.ban = UserBanManager.getBan(id);
        checkBan();
    }

    public static UserManager getUser(String id) {
        if(users.containsKey(id)) return users.get(id);
        UserManager userManager = new UserManager(id);
        users.put(id, userManager);
        return userManager;
    }

    public static void saveAll() {
        if(users == null || users.size() == 0) return;
        for(Map.Entry<String, UserManager> entry : users.entrySet()) {
            users.get(entry.getKey()).save();
        }
    }

    public static void unloadAll() {
        saveAll();
        users.clear();
    }

    public void save() {
        checkBan();
        if(isBanned()) UserBanManager.ban(id, ban.getReason(), ban.getUntil(), ban.getDate());
        else UserBanManager.unban(id);
    }

    public boolean isBanned() {
        return (ban != null);
    }

    public void ban(String reason, Date until, Date date) {
        ban = new UserBan(reason, until, date);
    }

    public void ban(String reason, Date until) {
        ban(reason, until, new Date());
    }

    public void ban(String reason) {
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, 30);
        ban(reason, calendar.getTime());
    }

    public void ban() {
        ban("No Reason");
    }

    public void unban() {
        ban = null;
    }

    public void checkBan() {
        if(!isBanned()) return;
        if(new Date().after(ban.getUntil())) ban = null;
    }

}
