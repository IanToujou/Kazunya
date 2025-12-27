---
icon: lucide/heart
---

# Hug Command

Interact with another user by hugging them.

## Description

The /hug command will hug another user. Kazunya will return a matching GIF and ping the mentioned user.
GIFs are selected depending on the selected mode and personal settings.

Please note that the command can fail under certain circumstances, for example, if the other user does
not allow romantic hugs, or if they disabled hugs all together. More information, including how to manage
your preferences, can be found in the [Roleplay]() section.

## Usage

```asm
/hug <user> [<mode>]
```

Parameters:

- `user` | The user you want to hug.
- `mode` | The type of hug. Can be `casual`, `romantic`, `comforting`.

Additional settings, such as GIF appearance, whitelisting, etc. can be configured on the [Personal Dashboard]().

## Examples

```asm
# Give Kazunya a big comforting hug!
/hug @Kazunya comforting
```