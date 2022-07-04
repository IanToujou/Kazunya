# **Command:** `/admin-balance`

## **Description**

??? Changelog Abstract
    Released in version `1.0.0`.

    No changes have been made since the first release.

!!! Warning
    This command can only be used by bot administrators.
    
The admin-balance command is used to get or modify the balance of a user. It can be used to add or remove funds from a user's balance. It can also be used to set the balance of a user to a specific value.

## **Syntax**

    /admin-balance [action <set/add/remove>] [@user] [amount]
    /admin-balance [action <get>] [@user]

- `action` *(<span style="color:yellow">String</span>)* | The action to perform on the user's account balance.
- `user` *(<span style="color:cyan">@User</span>)* | The user to perform the action on.
- `amount` *(<span style="color:greenyellow">Double</span>)* | The amount to add or remove from the user's balance. (0 to 1000000)

## **Examples**

!!! Example
    Get the balance of a user, in this case, Kazunya.

        /admin-balance get @Kazunya
        
!!! Example
    Set the balance of a user, in this case, Kazunya, to 100.

        /admin-balance set @Kazunya 100
        
!!! Example
    Add 50 to the balance of a user, in this case, Kazunya.

        /admin-balance add @Kazunya 50
        
!!! Example
    Remove 50 from the balance of a user, in this case, Kazunya.
    
        /admin-balance remove @Kazunya 50

## **Error Messages**

An overview of all error messages can be found under the <a href=„/errors/„>Errors</a> section.

??? 1002 Error
    The command syntax is not correct.

??? 1007 Error
    The given number is invalid. It must be either a full or a decimal number.

??? 1008 Error
    The specified operation is invalid. You can either set, add, remove or get the account balance of someone.

??? 2007 Error
    The specified amount is not in the given range (0 to 9999999).