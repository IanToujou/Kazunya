package net.toujoustudios.kazunya.util;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * This file has been created by Ian Toujou.
 * Project: Kazunya
 * Date: 13/12/2021
 * Time: 21:16
 */
public class LinkUtil {

    public static boolean isURL(String input) {

        try {
            new URL(input);
            return true;
        } catch(MalformedURLException exception) {
            return false;
        }

    }

}
