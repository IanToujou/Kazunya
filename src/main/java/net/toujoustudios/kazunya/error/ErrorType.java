package net.toujoustudios.kazunya.error;

public enum ErrorType {

    GENERAL_UNKNOWN("0001", "An unknown error occurred."),
    GENERAL_DISABLED("0002", "This bot function has been temporarily disabled."),
    GENERAL_BANNED("0003", "You have been banned from using the bot."),
    GENERAL_UNFINISHED("0004", "This bot function is still under development."),
    GENERAL_DATABASE("0005", "An error occurred with the database."),
    GENERAL_PERMISSION("0006", "You do not have the permission to perform this action."),

    COMMAND_INVALID_SYNTAX("1001", "The command syntax is not correct."),
    COMMAND_INVALID_SEARCH("1002", "The given search has no results."),
    COMMAND_INVALID_USER_NOT_FOUND("1003", "The given user is invalid."),
    COMMAND_INVALID_USER_BOT("1004", "The given user is a bot. I know we're superior, but please refer to another human."),
    COMMAND_INVALID_USER_SELF("1005", "You cannot do that to yourself. Please specify another user."),

    ACTION_ALREADY_MARRIED_SELF("2001", "You are already married."),
    ACTION_ALREADY_MARRIED_TARGET("2002", "The specified person is already married. Sorry about that."),
    ACTION_NO_PARTNER("2003", "You don't have any partner.");

    private final String code;
    private final String description;

    ErrorType(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

}
