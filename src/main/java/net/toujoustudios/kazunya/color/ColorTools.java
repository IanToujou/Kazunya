package net.toujoustudios.kazunya.color;

import java.awt.*;

/**
 * This file has been created by Ian Toujou.
 * Project: Kazunya
 * Date: 26/08/2021
 * Time: 23:10
 */
public class ColorTools {

    /**
     * Get a new color by giving an integer RGB value input.
     *
     * @param r The red RGB value, ranging between 0-255 inclusive.
     * @param g The green RGB value, ranging between 0-255 inclusive.
     * @param b The blue RGB value, ranging between 0-255 inclusive.
     * @return A new color from the given RGB input.
     */
    public static Color getFromRGB(int r, int g, int b) {
        return new Color(r, g, b);
    }

    /**
     * Get a new color by giving a string as RGB input.
     *
     * @param rgb The RGB string. It must have a formatting like <b>"r, g, b"</b> or <b>"r,g,b"</b>.
     * @return A new color from the given RGB input.
     */
    public static Color getFromRGBString(String rgb) {
        rgb = rgb.replace(" ", "");
        String[] split = rgb.split(",");
        int r = Integer.parseInt(split[0]);
        int g = Integer.parseInt(split[1]);
        int b = Integer.parseInt(split[2]);
        return new Color(r, g, b);
    }

}
