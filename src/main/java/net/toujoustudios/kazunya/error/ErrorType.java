package net.toujoustudios.kazunya.error;

public enum ErrorType {

    GENERAL_UNKNOWN("0001", "An unknown error occurred."),
    GENERAL_DISABLED("0002", "This bot function has been temporarily disabled."),
    GENERAL_BANNED("0003", "You have been banned from using the bot."),
    GENERAL_UNFINISHED("0004", "This bot function is still under development."),
    GENERAL_DATABASE("0005", "An error occurred with the database."),
    GENERAL_PERMISSION("0006", "You do not have the permission to perform this action."),
    GENERAL_NSFW("0007", "This can only be performed in a NSFW channel."),

    COMMAND_INVALID_SYNTAX("1001", "The command syntax is not correct."),
    COMMAND_INVALID_SEARCH("1002", "The given search has no results."),
    COMMAND_INVALID_USER_NOT_FOUND("1003", "The given user is invalid."),
    COMMAND_INVALID_USER_BOT("1004", "The given user is a bot. I know we're superior, but please refer to another human."),
    COMMAND_INVALID_USER_SELF("1005", "You cannot do that to yourself. Please specify another user."),
    COMMAND_INVALID_NUMBER_INTEGER("1006", "The given number is invalid. It must be a full number."),
    COMMAND_INVALID_NUMBER_DOUBLE("1007", "The given number is invalid. It must be either a full or a decimal number."),
    COMMAND_INVALID_OPERATION_BALANCE("1008", "The specified operation is invalid. You can either set, add, remove or show the account balance of someone."),
    COMMAND_INVALID_STOCK("1009", "The specified stock does not exist. Please use /marketinfo to get the full list of available stocks."),
    COMMAND_INVALID_URL("1010", "The given link is invalid. Please provide a valid link."),

    ACTION_ALREADY_MARRIED_SELF("2001", "You are already married."),
    ACTION_ALREADY_MARRIED_TARGET("2002", "The specified person is already married. Sorry about that."),
    ACTION_NO_PARTNER("2003", "You don't have any partner."),
    ACTION_DICE_SIDES_NOT_IN_RANGE("2004", "The specified side amount is not in the given range (1 to 999999)."),
    ACTION_DICE_OFFSET_NOT_IN_RANGE("2005", "The specified offset is not in the given range (-999999 to 999999"),

    AUDIO_UNABLE_TO_LOAD("3001", "The audio couldn't be loaded due to an unexpected error."),
    AUDIO_USER_NOT_IN_CHANNEL("3002", "You are not in a voice channel right now."),

    PERMISSION_VIEW_CHANNELS("4001", ""),
    PERMISSION_MANAGE_CHANNELS("4002", ""),
    PERMISSION_MANAGE_ROLES("4003", ""),
    PERMISSION_MANAGE_EMOJI("4004", ""),
    PERMISSION_VIEW_AUDIT("4005", ""),
    PERMISSION_VIEW_INSIGHTS("4006", ""),
    PERMISSION_MANAGE_WEBHOOKS("4007", ""),
    PERMISSION_MANAGE_SERVER("4008", ""),
    PERMISSION_CREATE_INVITE("4009", ""),
    PERMISSION_MANAGE_NICKNAMES_SELF("4010", ""),
    PERMISSION_MANAGE_NICKNAMES_OTHER("4011", ""),
    PERMISSION_KICK_MEMBERS("4012", ""),
    PERMISSION_BAN_MEMBERS("4013", ""),
    PERMISSION_TIMEOUT_MEMBERS("4014", ""),
    PERMISSION_SEND_MESSAGES("4015", ""),
    PERMISSION_SEND_MESSAGES_THREADS("4016", ""),
    PERMISSION_CREATE_THREADS_PUBLIC("4017", ""),
    PERMISSION_CREATE_THREADS_PRIVATE("4018", ""),
    PERMISSION_EMBED_LINKS("4019", ""),
    PERMISSION_ATTACH_FILES("4020", ""),
    PERMISSION_ADD_REACTIONS("4021", ""),
    PERMISSION_USE_EMOJI("4022", ""),
    PERMISSION_USE_STICKERS("4023", ""),
    PERMISSION_MENTION_EVERYONE("4024", ""),
    PERMISSION_MANAGE_MESSAGES("4025", ""),
    PERMISSION_MANAGE_THREADS("4026", ""),
    PERMISSION_READ_HISTORY("4027", ""),
    PERMISSION_SEND_TTS("4028", ""),
    PERMISSION_USE_COMMANDS("4029", ""),
    PERMISSION_VOICE_CONNECT("4030", ""),
    PERMISSION_VOICE_SPEAK("4031", ""),
    PERMISSION_VOICE_VIDEO("4032", ""),
    PERMISSION_VOICE_ACTIVITY("4033", ""),
    PERMISSION_VOICE_PRIORITY("4034", ""),
    PERMISSION_VOICE_MUTE("4035", ""),
    PERMISSION_VOICE_DEAFEN("4036", ""),
    PERMISSION_VOICE_MOVE("4037", ""),
    PERMISSION_STAGE_REQUEST("4038", ""),
    PERMISSION_MANAGE_EVENTS("4039", ""),
    PERMISSION_ADMIN("4040", "");

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
