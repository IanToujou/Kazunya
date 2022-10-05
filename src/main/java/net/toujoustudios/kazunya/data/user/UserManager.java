package net.toujoustudios.kazunya.data.user;

import net.toujoustudios.kazunya.data.ban.UserBan;
import net.toujoustudios.kazunya.data.ban.UserBanManager;
import net.toujoustudios.kazunya.data.money.UserMoneyManager;
import net.toujoustudios.kazunya.data.relation.UserRelation;
import net.toujoustudios.kazunya.data.relation.UserRelationManager;
import net.toujoustudios.kazunya.data.relation.UserRelationType;
import net.toujoustudios.kazunya.data.skill.SkillType;
import net.toujoustudios.kazunya.data.skill.UserSkill;
import net.toujoustudios.kazunya.data.skill.UserSkillManager;

import java.util.*;

public class UserManager {

    private static final HashMap<String, UserManager> users = new HashMap<>();
    private final String id;
    private UserBan ban;
    private double bankMoney;
    private double walletMoney;
    private ArrayList<UserRelation> relations;
    private ArrayList<UserSkill> skills;

    public UserManager(String id) {
        this.id = id;
        this.ban = UserBanManager.getBan(id);
        this.bankMoney = UserMoneyManager.getBankMoney(id);
        this.walletMoney = UserMoneyManager.getWalletMoney(id);
        this.relations = UserRelationManager.getRelations(id);
        this.skills = UserSkillManager.getSkills(id);
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
        UserMoneyManager.setBankMoney(id, bankMoney);
        UserMoneyManager.setWalletMoney(id, walletMoney);
        UserRelationManager.deleteRelations(id);
        relations.forEach(all -> {
            UserRelationManager.setRelation(id, all.getTarget(), all.getType(), all.getDate());
        });
        UserSkillManager.setSkills(id, skills);
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

    public double getBankMoney() {
        return bankMoney;
    }

    public void addBankMoney(double amount) {
        this.bankMoney += amount;
    }

    public void removeBankMoney(double amount) {
        this.bankMoney -= amount;
    }

    public void setBankMoney(double bankMoney) {
        this.bankMoney = bankMoney;
    }

    public double getWalletMoney() {
        return walletMoney;
    }

    public void setWalletMoney(double walletMoney) {
        this.walletMoney = walletMoney;
    }

    public void addWalletMoney(double amount) {
        this.walletMoney += amount;
    }

    public void removeWalletMoney(double amount) {
        this.walletMoney -= amount;
    }

    public ArrayList<UserRelation> getRelations() {
        return relations;
    }

    public UserRelation getRelation(String target) {
        for(UserRelation all : relations) {
            if(all.getTarget().equals(target)) return all;
        }
        return null;
    }

    public boolean hasRelation(String target) {
        return getRelation(target) != null;
    }

    public ArrayList<UserRelation> getRelationsOfType(UserRelationType type) {
        ArrayList<UserRelation> relationsOfType = new ArrayList<>();
        for(UserRelation all : relations) if(all.getType() == type) relationsOfType.add(all);
        return relationsOfType;
    }

    public void setRelations(ArrayList<UserRelation> relations) {
        this.relations = relations;
    }

    public void removeRelation(UserRelation relation) {
        relations.remove(relation);
    }

    public void addRelation(UserRelation relation) {
        if(!relations.contains(relation)) relations.add(relation);
    }

    public ArrayList<UserSkill> getSkills() {
        return skills;
    }

    public UserSkill getSkill(SkillType skillType) {
        for(UserSkill all : skills) if(all.getSkillType() == skillType) return all;
        UserSkill skill = new UserSkill(skillType, 0);
        skills.add(skill);
        return skill;
    }

    public int getSkillExperience(SkillType skillType) {
        return getSkill(skillType).getExperience();
    }

    public void setSkills(ArrayList<UserSkill> skills) {
        this.skills = skills;
    }

    public void setSkill(UserSkill skill) {
        if(getSkillExperience(skill.getSkillType()) > 0) skills.remove(getSkill(skill.getSkillType()));
        skills.add(skill);
    }

    public void setSkill(SkillType skillType, int experience) {
        if(getSkill(skillType) != null) skills.remove(getSkill(skillType));
        skills.add(new UserSkill(skillType, experience));
    }

}
