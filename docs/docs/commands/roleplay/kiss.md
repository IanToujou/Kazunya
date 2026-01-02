---
icon: lucide/heart
---

# Hug Command

Interact with another user by kissing them.

## Description

The /kiss command will kiss another user. Kazunya will return a matching GIF and ping the mentioned user.
GIFs are selected depending on the selected mode and personal settings.

Please note that the command can fail under certain circumstances, for example, if the other user does
not allow kisses on the mouth, or if they disabled kisses all together. More information, including how to manage
your preferences, can be found in the [Roleplay]() section.

## Usage

```asm
/kiss <user> [<mode>]
```

Parameters:

- `user` | The user you want to cuddle.
- `mode` | The type of kiss. Can be `cheek`, `mouth`, `intense`. Default is cheek.

Additional settings, such as GIF appearance, whitelisting, etc. can be configured on the [Personal Dashboard]().

## Examples

```asm
# Give Kazunya a kiss on the cheek!
/kiss @Kazunya

# Give Kazunya a kiss on the mouth!
/kiss @Kazunya mouth
```