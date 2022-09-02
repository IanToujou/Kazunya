# **Errors**

## **Introduction**

Error codes are used in a variety of situations when an error comes up. You can look up any error codes here and look for solutions to fix the problem.

!!! Tip
    Most errors are self-explanatory and simple to solve, just by reading the error message that comes together with the error code. A large amount of errors are related to command syntax or invalid inputs when using command arguments.

## **Categories**

| Symbol | <div style="width:140px">Type</div> | <div style="width: 140px">Code Range</div> | Description
|:-:|:-|:-|:-
| 🌐 | **GENERAL** |  `0001 - 1000` | *General errors that are very common or do not fall into any other categories.*
| 🛰️ | **COMMAND** |  `1001 - 2000` | *Errors related directly to commands or command inputs.*
| 💥 | **ACTION** |  `2001 - 3000` | *Errors related to actions, for example marriage or dice rolls. These are in general more advanced command input errors.*
| 🔊 | **AUDIO** |  `3001 - 4000` | *Audio errors occur when an error is thrown related to audio output or input in voice channels.*
| 📄 | **PERMISSION** |  `4001 - 5000` | *Permission errors only occur when the bot lacks a permission to fulfill an action or a command.*

## **Codes**

### **General (**`0001-1000`**)**

!!! Info
    General errors are errors that are very common or do not fall into any other categories.

| Code | <div style="width:180px">Description</div> | Help
|:-:|:-|:-
`0001` | An unknown error has occurred. | Please try whatever you did again and if the error persists, contact the support.
`0002` | This bot function has been temporarily disabled. | The requested feature is temporarily disabled, mostly due to a bug that will be fixed soon.
`0003` | You have been banned from using the bot. | A staff member banned you from using Kazunya. This normally only happens in extreme cases.
`0004` | This bot function is still under development. | The requested feature is still in development. You may ask when it will be released.
`0005` | An error occurred with the database. | The bot failed to communicate correctly with the database. Please contact the support.
`0006` | You do not have the permission to perform this action. | This action requires elevated permissions, either on the Discord server, or on Kazunya.
`0007` | This can only be performed in a NSFW channel. | The requested action can only be performed in NSFW channels.

### **Command (**`1001-2000`**)**

!!! Info
    Command errors are errors that are directly related to command inputs by the user.

| Code | <div style="width:180px">Description</div> | Help
|:-:|:-|:-
`1001` | The command syntax is not correct. | The provided command syntax is not correct. This error it deprecated due to slash commands, that automatically check arguments. It can still appear on some commands though.
`1002` | The given search has no results. | The search you provided had no results.
`1003` | The given user is invalid. | You provided an invalid user. This normally occurs if the user does not exist or if the user could not be found.
`1004` | The given user is a bot. I know we're superior, but please refer to another human. | You provided a bot as a user on a command that can only be used on real humans.
`1005` | You cannot do that to yourself. Please specify another user. | You provided yourself on a command that can only be used on other people.
`1006` | The given number is invalid. It must be a full number. | You provided an invalid integer value as an argument for the command.
`1007` | The given number is invalid. It must be either a full or a decimal number. | You provided an invalid double value as an argument for the command.
`1008` | The specified operation is invalid. You can either get, set, add or remove. | You provided a wrong operation on the command. You can only use get, add, remove or set as an argument.
`1009` | The specified stock does not exist. Please use /marketinfo to get the full list of available stocks. | You provided a stock that does not exist on the normal virtual stock market.
`1010` | The given link is invalid. Please provide a valid link. | You provided an invalid URL. Don't forget the `http://` or `https://` in front of the link.
`1011` | The specified number is not in the given range. Please read the documentation to see the correct range. | You provided an invalid numeric range. You can read the command descriptions to see which range you can use.

### **Action (**`2001-3000`**)**

!!! Info
    Action errors are errors that occur while attempting to perform a certain action.

| Code | <div style="width:180px">Description</div> | Help
|:-:|:-|:-
`2001` | You are already married. | You already have a partner, and you can’t marry two persons at the same time.
`2002` | The specified person is already married. Sorry about that. | Your desired partner already has a partner. You can only marry people who don’t have any partner.
`2003` | You don’t have any partner. | You are single and do not have any partner at the moment.
`2004` | You don't have enough money in your wallet for this action. | You have insufficient funds in your wallet to perform this operation. You can use the `/withdraw` command to withdraw money from your bank account.
`2005` | You don't have enough money in your bank account. | You have insufficient funds in your bank account to perform this operation. You can use the `/deposit` command to deposit money into your bak account.

### **Audio (**`3001-4000`**)**

!!! Info
    Audio errors are errors that occur when an error is thrown related to audio output or input in voice channels.

| Code | <div style="width:180px">Description</div> | Help
|:-:|:-|:-
`3001` | The audio couldn't be loaded due to an unexpected error. | The bot encountered an error while tryint to load an audio. Please try again or contact the support.
`3002` | You are not in a voice channel right now. | You should join a voice channel before attempting this.

### **Permission (**`4001-5000`**)**

!!! Info
    Permission errors are errors that occur when the bot lacks a permission to fulfill an action or a command.

| Code | <div style="width:180px">Description</div> | Help
|:-:|:-|:-
`4001` | I don't have the permission to view channels. | The bot permissions are not configured correctly. Please grant the bot the necessary permissions.
`4002` | I don't have the permission to manage channels. | The bot permissions are not configured correctly. Please grant the bot the necessary permissions.
`4003` | I don't have the permission to manage roles. | The bot permissions are not configured correctly. Please grant the bot the necessary permissions.
`4004` | I don't have the permission to manage emojis and stickers. | The bot permissions are not configured correctly. Please grant the bot the necessary permissions.
`4005` | I don't have the permission to view the audit log. | The bot permissions are not configured correctly. Please grant the bot the necessary permissions.
`4006` | I don't have the permission to view the server insights. | The bot permissions are not configured correctly. Please grant the bot the necessary permissions.
`4007` | I don't have the permission to manage webhooks. | The bot permissions are not configured correctly. Please grant the bot the necessary permissions.
`4008` | I don't have the permission to manage the server. | The bot permissions are not configured correctly. Please grant the bot the necessary permissions.
`4009` | I don't have the permission to create invites. | The bot permissions are not configured correctly. Please grant the bot the necessary permissions.
`4010` | I don't have the permission to manage my own nickname. | The bot permissions are not configured correctly. Please grant the bot the necessary permissions.
`4011` | I don't have the permission to manage nicknames. | The bot permissions are not configured correctly. Please grant the bot the necessary permissions.
`4012` | I don't have the permission to kick members. | The bot permissions are not configured correctly. Please grant the bot the necessary permissions.
`4013` | I don't have the permission to ban members. | The bot permissions are not configured correctly. Please grant the bot the necessary permissions.
`4014` | I don't have the permission to timeout members. | The bot permissions are not configured correctly. Please grant the bot the necessary permissions.
`4015` | I don't have the permission to send messages. | The bot permissions are not configured correctly. Please grant the bot the necessary permissions.
`4016` | I don't have the permission to send messages in threads. | The bot permissions are not configured correctly. Please grant the bot the necessary permissions.
`4017` | I don't have the permission to create public threads. | The bot permissions are not configured correctly. Please grant the bot the necessary permissions.
`4018` | I don't have the permission to create private threads. | The bot permissions are not configured correctly. Please grant the bot the necessary permissions.
`4019` | I don't have the permission to embed links. | The bot permissions are not configured correctly. Please grant the bot the necessary permissions.
`4020` | I don't have the permission to attach files. | The bot permissions are not configured correctly. Please grant the bot the necessary permissions.
`4021` | I don't have the permission to add reactions. | The bot permissions are not configured correctly. Please grant the bot the necessary permissions.
`4022` | I don't have the permission to use emojis. | The bot permissions are not configured correctly. Please grant the bot the necessary permissions.
`4023` | I don't have the permission to use stickers. | The bot permissions are not configured correctly. Please grant the bot the necessary permissions.
`4024` | I don't have the permission to mention everyone. | The bot permissions are not configured correctly. Please grant the bot the necessary permissions.
`4025` | I don't have the permission to delete messages. | The bot permissions are not configured correctly. Please grant the bot the necessary permissions.
`4026` | I don't have the permission to manage threads. | The bot permissions are not configured correctly. Please grant the bot the necessary permissions.
`4027` | I don't have the permission to read the message history. | The bot permissions are not configured correctly. Please grant the bot the necessary permissions.
`4028` | I don't have the permission to send text-to-speech (TTS) messages. | The bot permissions are not configured correctly. Please grant the bot the necessary permissions.
`4029` | I don't have the permission to use commands. | The bot permissions are not configured correctly. Please grant the bot the necessary permissions.
`4030` | I don't have the permission to connect to voice channels. | The bot permissions are not configured correctly. Please grant the bot the necessary permissions.
`4031` | I don't have the permission to speak in voice channels. | The bot permissions are not configured correctly. Please grant the bot the necessary permissions.
`4032` | I don't have the permission to stream in voice channels. | The bot permissions are not configured correctly. Please grant the bot the necessary permissions.
`4033` | I don't have the permission to use voice activity. | The bot permissions are not configured correctly. Please grant the bot the necessary permissions.
`4034` | I don't have the permission to be a priority speaker. | The bot permissions are not configured correctly. Please grant the bot the necessary permissions.
`4035` | I don't have the permission to mute other in voice channels. | The bot permissions are not configured correctly. Please grant the bot the necessary permissions.
`4036` | I don't have the permission to deafen other in voice channels. | The bot permissions are not configured correctly. Please grant the bot the necessary permissions.
`4037` | I don't have the permission to move people in voice channels. | The bot permissions are not configured correctly. Please grant the bot the necessary permissions.
`4038` | I don't have the permission to request stage access. | The bot permissions are not configured correctly. Please grant the bot the necessary permissions.
`4039` | I don't have the permission to manage server events. | The bot permissions are not configured correctly. Please grant the bot the necessary permissions.
`4040` | I don't have the administrator permissions. | The bot permissions are not configured correctly. Please grant the bot the necessary permissions.