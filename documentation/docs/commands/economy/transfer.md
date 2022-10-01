# **Command:** `/transfer`

## **Description**

??? Changelog Abstract
    Released in version `1.1.0`.

    No changes have been made since the first release.

The transfer command is used to transfer money from your bank account to another bank account by specifying another user.

## **Syntax**

    /transfer [amount] [@user]
    
- `amount` *(<span style="color:lime">Double</span>)* | The amount of money you want to transfer.
- `@user` *(<span style="color:aqua">@Mention</span>)* | The user you want to give the money.

## **Examples**

!!! Example
    Transfer 500€ to Kazunya's bank account.

        /transfer 500 @Kazunya

## **Error Messages**

An overview of all error messages can be found under the <a href=„/errors/„>Errors</a> section.

??? 1011 Error
    The specified number is not in the given range. Please read the documentation to see the correct range.
    
??? 2005 Error
    You don't have enough money in your bank account for this action.