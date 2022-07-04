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

!!! Info
    Command errors are errors that are directly related to command inputs by the user.

| Code | <div style=„width:180px“>Description</div> | Help
|:-:|:-|:-
`2001` | You are already married. | You already have a partner and you can’t marry two persons at the same time.
`2002` | The specified person is already married. Sorry about that. | Your desired partner already has a partner. You can only marry people who don’t have any partner.
`2003` | You don’t have any partner. | You are single and do not have any partner at the moment.
`2004` | The specified side amount is not in the given range (1 to 999999). | You specified a number that is not in the given range, between 1 and 999999.
`2005` | 