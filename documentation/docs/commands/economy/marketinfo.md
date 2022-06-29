# **Command:** `/marketinfo`

## **Description**

??? Changelog Abstract
    Released in version `1.0.0`.

    No changes have been made since the first release.

The marketinfo command is used to get information from the virtual stock market. It displays the different stocks and their current value.

If the user provides a specific stock as an argument, Kazunya will return more information on that stock, including, but not limited to, current value, full name, history, etc.

## **Syntax**

    /marketinfo ([stock])

- `stock` *(<span style=„color:yellow“>String</span>)* | The stock you want to get more detailed information on.

## **Examples**

!!! Example
    See all the stocks that are available on the virtual market and their current price.

        /marketinfo
        
!!! Example
    Get detailed information on the NEKO stock.

        /marketinfo NEKO

## **Error Messages**

An overview of all error messages can be found under the <a href=„/errors/„>Errors</a> section.

??? 1009 Error
    The specified stock does not exist. Please use /marketinfo to get the full list of available stocks.