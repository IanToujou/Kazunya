# **Command:** `/friend`

## **Description**

??? Changelog Abstract
    Released in version `1.2.0`.

    No changes have been made since the first release.

The friend command is used to manage your friend list on Kazunya. The friend system will be used in the future to send game invites and to use certain interactions.

<p align="center"><img src="https://media.tenor.com/OQJmvVwLZ5YAAAAi/tkthao219-bubududu.gif"></p>

## **Syntax**

    /friend add [@user]
    /friend remove [@user]
    /friend list

`@user` *(<span style="color:aqua">@Mention</span>)* | The user you want to perform the action on.

## **Examples**

!!! Example
    Send a friend request to another user.

        /friend add @Kazunya
        
!!! Example
    Remove Kazunya from your friend list.

        /friend remove @Kazunya
        
!!! Example
    List all your current friends.

        /friend list

## **Error Messages**

An overview of all error messages can be found under the <a href="/errors/">Errors</a> section.

??? 1004 Error
    The given user is a bot. You can only mention other real users.
    
??? 1005 Error
    You cannot do that to yourself. Please specify another user.
    
??? 2004 Error
    You are already friends with that person.
    
??? 2006 Error
    You are not friends with that person.
    
??? 2012 Error
    Your relationship with the other person is too strong.