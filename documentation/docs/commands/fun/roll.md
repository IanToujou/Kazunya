# **Command:** `/roll`

## **Description**

The roll command is used to roll a die. It does not use the default RPG dice syntax yet, but instead it uses custom arguments to define the dice.

## **Syntax**

    /roll ([sides]) ([times]) ([offset])

- `sides` - The number of sides the dice has (2 to 1000).
- `times` - How many times you want to roll the dice (1 to 50).
- `offset` - The offset you want to add to the value that is rolled. This value can also be negative (-1000 to 1000).

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