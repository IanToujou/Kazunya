package net.toujoustudios.kazunya.error;

/**
 * Error types that are used in this software. The errors are divided into multiple categories, with
 * error codes and descriptions to build readable error messages.
 *
 * @author Ian Toujou
 * @version 1.2.0
 * @since 1.0.0
 */
@SuppressWarnings("unused")
public enum ErrorType {

    // General errors that don't fall into any other category.
    GENERAL_UNKNOWN("0001", "An unknown error occurred."),
    GENERAL_DISABLED("0002", "This bot function has been temporarily disabled."),
    GENERAL_BANNED("0003", "You have been banned from using the bot."),
    GENERAL_UNFINISHED("0004", "This bot function is still under development."),
    GENERAL_DATABASE("0005", "An error occurred with the database."),
    GENERAL_PERMISSION("0006", "You do not have the permission to perform this action."),
    GENERAL_NSFW("0007", "This can only be performed in a NSFW channel."),

    // Command related or syntax related errors.
    COMMAND_INVALID_SYNTAX("1001", "The command syntax is not correct."),
    COMMAND_INVALID_SEARCH("1002", "The given search has no results."),
    COMMAND_INVALID_USER_NOT_FOUND("1003", "The given user is invalid."),
    COMMAND_INVALID_USER_BOT("1004", "The given user is a bot. I know we're superior, but please refer to another human."),
    COMMAND_INVALID_USER_SELF("1005", "You cannot do that to yourself. Please specify another user."),
    COMMAND_INVALID_NUMBER_INTEGER("1006", "The given number is invalid. It must be a full number."),
    COMMAND_INVALID_NUMBER_DOUBLE("1007", "The given number is invalid. It must be either a full or a decimal number."),
    COMMAND_INVALID_OPERATION_GET_SET_ADD_REMOVE("1008", "The specified operation is invalid. You can either get, set, add or remove."),
    COMMAND_INVALID_STOCK("1009", "The specified stock does not exist. Please use /marketinfo to get the full list of available stocks."),
    COMMAND_INVALID_URL("1010", "The given link is invalid. Please provide a valid link."),
    COMMAND_INVALID_RANGE("1011", "The specified number is not in the given range. Please read the documentation to see the correct range."),

    // More advanced command errors when trying to perform a specific action.
    ACTION_INVALID_USER("2001", "That action is not meant to be performed by you."),
    ACTION_NOT_ENOUGH_WALLET_MONEY("2002", "You don't have enough money in your wallet for this action."),
    ACTION_NOT_ENOUGH_BANK_MONEY("2003", "You don't have enough money in your bank account for this action."),
    ACTION_ALREADY_FRIENDS("2004", "You are already friends with that person."),
    ACTION_ALREADY_MARRIED("2005", "You are already married to that person."),
    ACTION_NOT_FRIENDS("2006", "You are not friends with that person."),
    ACTION_NOT_MARRIED("2007", "You are not married to anyone."),
    ACTION_INVALID_FRIEND_REQUEST("2008", "The friend request is invalid. Please send a new one."),
    ACTION_INVALID_MARRY_REQUEST("2009", "The proposal is invalid. Please send a new one."),
    ACTION_MAX_MARRIED("2010", "You already have the maximum number of married partners."),
    ACTION_MAX_FRIENDS("2011", "You already have the maximum number of friends."),
    ACTION_RELATION_TOO_HIGH("2012", "Your relationship with the other person is too strong."),

    // Audio errors that only occur when failing to send an audio stream.
    AUDIO_UNABLE_TO_LOAD("3001", "The audio couldn't be loaded due to an unexpected error."),
    AUDIO_USER_NOT_IN_CHANNEL("3002", "You are not in a voice channel right now."),

    // Discord permission based errors if the permissions are not configured properly.
    PERMISSION_VIEW_CHANNELS("4001", "I don't have the permission to view channels."),
    PERMISSION_MANAGE_CHANNELS("4002", "I don't have the permission to manage channels."),
    PERMISSION_MANAGE_ROLES("4003", "I don't have the permission to manage roles."),
    PERMISSION_MANAGE_EMOJI("4004", "I don't have the permission to manage emojis and stickers."),
    PERMISSION_VIEW_AUDIT("4005", "I don't have the permission to view the audit log."),
    PERMISSION_VIEW_INSIGHTS("4006", "I don't have the permission to view the server insights."),
    PERMISSION_MANAGE_WEBHOOKS("4007", "I don't have the permission to manage webhooks."),
    PERMISSION_MANAGE_SERVER("4008", "I don't have the permission to manage the server."),
    PERMISSION_CREATE_INVITE("4009", "I don't have the permission to create invites."),
    PERMISSION_MANAGE_NICKNAMES_SELF("4010", "I don't have the permission to manage my own nickname."),
    PERMISSION_MANAGE_NICKNAMES_OTHER("4011", "I don't have the permission to manage nicknames."),
    PERMISSION_KICK_MEMBERS("4012", "I don't have the permission to kick members."),
    PERMISSION_BAN_MEMBERS("4013", "I don't have the permission to ban members."),
    PERMISSION_TIMEOUT_MEMBERS("4014", "I don't have the permission to timeout members."),
    PERMISSION_SEND_MESSAGES("4015", "I don't have the permission to send messages."),
    PERMISSION_SEND_MESSAGES_THREADS("4016", "I don't have the permission to send messages in threads."),
    PERMISSION_CREATE_THREADS_PUBLIC("4017", "I don't have the permission to create public threads."),
    PERMISSION_CREATE_THREADS_PRIVATE("4018", "I don't have the permission to create private threads."),
    PERMISSION_EMBED_LINKS("4019", "I don't have the permission to embed links."),
    PERMISSION_ATTACH_FILES("4020", "I don't have the permission to attach files."),
    PERMISSION_ADD_REACTIONS("4021", "I don't have the permission to add reactions."),
    PERMISSION_USE_EMOJI("4022", "I don't have the permission to use emojis."),
    PERMISSION_USE_STICKERS("4023", "I don't have the permission to use stickers."),
    PERMISSION_MENTION_EVERYONE("4024", "I don't have the permission to mention everyone."),
    PERMISSION_MANAGE_MESSAGES("4025", "I don't have the permission to delete messages."),
    PERMISSION_MANAGE_THREADS("4026", "I don't have the permission to manage threads."),
    PERMISSION_READ_HISTORY("4027", "I don't have the permission to read the message history."),
    PERMISSION_SEND_TTS("4028", "I don't have the permission to send text-to-speech (TTS) messages."),
    PERMISSION_USE_COMMANDS("4029", "I don't have the permission to use commands."),
    PERMISSION_VOICE_CONNECT("4030", "I don't have the permission to connect to voice channels."),
    PERMISSION_VOICE_SPEAK("4031", "I don't have the permission to speak in voice channels."),
    PERMISSION_VOICE_VIDEO("4032", "I don't have the permission to stream in voice channels."),
    PERMISSION_VOICE_ACTIVITY("4033", "I don't have the permission to use voice activity."),
    PERMISSION_VOICE_PRIORITY("4034", "I don't have the permission to be a priority speaker."),
    PERMISSION_VOICE_MUTE("4035", "I don't have the permission to mute other in voice channels."),
    PERMISSION_VOICE_DEAFEN("4036", "I don't have the permission to deafen other in voice channels."),
    PERMISSION_VOICE_MOVE("4037", "I don't have the permission to move people in voice channels."),
    PERMISSION_STAGE_REQUEST("4038", "I don't have the permission to request stage access."),
    PERMISSION_MANAGE_EVENTS("4039", "I don't have the permission to manage server events."),
    PERMISSION_ADMIN("4040", "I don't have the administrator permissions.");

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