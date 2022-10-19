# **Command:** `/pp`

## **Description**

??? Changelog Abstract
    Released in version `1.0.0`.

    No changes have been made since the first release.

The pp command is a fun command that will display a person's pp in terms of length. The bot will return the pp of the person you provided. If you don't provide any user, it will return your own pp. The pp is calculated by using the user's Discord ID. The last two digits of the ID will be divided by `3` and rounded to the nearest whole number. The result will be used to detemine how long the user's pp is.

For instance, if the user's ID ends with `58`, the pp will be `58 / 3 = 19` equal signs long, resulting in `8===================D`.

Kazunya will also give a comment depending on the length of the pp.

| Size | Comment
|:-:|:-
| `0-1` | 🔪 Oh no, did someone chop it off?! I can't see it.
| `2-5` | 🔬 Hold up. I need an electron microscope for this one.
| `6-10` | ⏬ Below average but still fine.
| `11-20` | ✨ Pretty average, very nice tip though. A solid 7/10. Now onto the taste test.
| `21-29` | 💪 That's a nice one bro! A solid 9/10.
| `30-32` | 😳 This one is huuuuge...
| `33` | 🗿 That one is max-size. What a gigachad.

!!! Info
    There are also some hardcoded IDs that will return a certain pp.

## **Syntax**

    /pp ([@user])

- `@user` *(<span style="color:aqua">@Mention</span>)* | The user you want to get the pp of.

## **Examples**

!!! Example
    See your own pp size.

        /pp

!!! Example
    See the pp of another user you provide, in this case, Kazunya.

        /pp @Kazunya