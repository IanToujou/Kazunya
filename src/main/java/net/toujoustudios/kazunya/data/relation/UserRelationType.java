package net.toujoustudios.kazunya.data.relation;

public enum UserRelationType {

    FRIENDS(20, 1), COUPLE(5, 2), FAMILY(10, 3), MARRIED(3, 4);

    private final int maxUsers;
    private final int value;

    UserRelationType(int maxUsers, int value) {
        this.maxUsers = maxUsers;
        this.value = value;
    }

    public int getMaxUsers() {
        return maxUsers;
    }

    public int getValue() {
        return value;
    }

}
