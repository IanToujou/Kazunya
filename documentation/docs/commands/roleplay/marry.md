# **Command:** `/marry`

## **Description**

??? Changelog Abstract
    Released in version `1.0.0`.

    No changes have been made since the first release.

The marry command is used to marry other users on the server. You can only marry one user at a time. The marriage is valid on every server, that means that you don't have different partners for different discord servers.

<p align="center"><img src="https://c.tenor.com/u7B_BCacat8AAAAC/wedding-ring-engaged.gif"></p>

!!! Accept Success
    To accept a proposal, you have to use the `/marry` command and mention the user that send you the request.

!!! Decline Failure
    To decline a proposal, you just have to ignore the request, and it will expire.

Being married does not grant any special abilities to the users at the moment. However, this might and will probably change in the future.

## **Syntax**

    /marry [@user]

`@user` *(<span color="blue">@Mention</span>)* | The user you want to marry.

## **Examples**

!!! Example
    Hildegard makes a proposal to another user, in this case, Albert.

        /marry @Albert

    Albert can accept the proposal by typing the following command.

        /marry @Hildegard

!!! Example
    Albert can decline the proposal by doing nothing in response to Hildegard's proposal.


## **Error Messages**

An overview of all error messages can be found under the <a href="/errors/">Errors</a> section.

??? 1004 Error
    The given user is a bot. You can only mention other real users.

??? 1005 Error
    You cannot do this to yourself. Please mention another user.

??? 2001 Error
    You are already married. You can only marry one user at a time.

??? 2002 Error
    The user you want to marry is already married. Sorry about that.
