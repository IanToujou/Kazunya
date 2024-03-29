# **Command:** `/ship`

## **Description**

The ship command is used to ship two users on a Discord server together. Kazunya will return a rating and a ship name.

The ship name will be generated by cutting the two usernames and combining the two halves.

The ship score is calculated by using the Discord ID of the users. The last two digits of both IDs will be added together to determine a score in percent. If the result is above 100, Kazunya will subtract 100 so that the resulting score will always be in the range `0-100`.

In addition, Kazunya will give a small comment depending on the resulting score.

| Score | Comment | Color
|:-:|:-|:-:
| `0-9` | The relationship would be truly horrible... | <span style="color:red">Red</span>
| `10-29` | This wouldn’t work that great... | <span style="color:orange">Orange</span>
| `30-49` | It is fine. | <span style="color:yellow">Yellow</span>
| `50-68` | This could work nya~ | <span style="color:lime">Green</span>
| `69` | 69? Nice. | <span style="color:lime">Green</span>
| `70-89` | This would be a wholesome relationship! Nya~ | <span style="color:pink">Pink</span>
| `90-99` | The two should instantly get married! Nyaaa~ | <span style="color:magenta">Magenta</span>
| `100` | I WANT THEM TO GET MARRIED! NOW! | <span style="color:magenta">Magenta</span>

## **Syntax**

    /ship [@user] [@user2]

- `@user` - The first user you want to perform the action on.
- `@user2` - The second user you want to perform the action on.

## **Examples**

!!! Example
    Ship two users together, in this case, Kazunya and IanToujou.

        /ship @Kazunya @IanToujou