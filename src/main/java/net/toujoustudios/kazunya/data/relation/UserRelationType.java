package net.toujoustudios.kazunya.data.relation;

public enum UserRelationType {

    FRIENDS(20), COUPLE(1), FAMILY(10), MARRIED(1);

    private final int maxUsers;

    UserRelationType(int maxUsers) {
        this.maxUsers = maxUsers;
    }

    public int getMaxUsers() {
        return maxUsers;
    }

}
