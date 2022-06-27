# **Command:** `/roll`

## **Description**

??? Changelog Abstract
    Released in version `1.0.0`.

    No changes have been made since the first release.

The roll command is used to roll a dice. It does not use the default RPG dice syntax yet, but instead it uses custom arguments to define the dice.

## **Syntax**

    /roll ([sides]) ([times]) ([offset])

- `sides` *(<span style="color:lime">Integer</span>)* | The number of sides the dice has.
- `times` *(<span style="color:lime">Integer</span>)* | How many times you want to roll the dice.
- `offset` *(<span style="color:lime">Integer</span>)* | The offset you want to add to the value that is rolled. This value can also be negative.

## **Examples**

!!! Example
    Roll a normal 6-sided dice once.

        /roll

!!! Example
    Roll a normal 10-sided dice once.

        /roll 10

!!! Example
    Roll a normal 10-sided dice twice.

        /roll 10 2

!!! Example
    Roll a normal 10-sided dice twice, with an offset of 8.

        /roll 10 2 8

## **Error Messages**

An overview of all error messages can be found under the <a href="/errors/">Errors</a> section.

??? 2004 Error
    The specified side amount is not in the given range (1 to 999999).

??? 2005 Error
    The specified offset is not in the given range (-999999 to 999999).

??? 2006 Error
    The specified times to roll the dice is not in the given range (1 to 50).