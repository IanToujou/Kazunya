package net.toujoustudios.kazunya.util;

import java.awt.*;

public class ColorUtil {

    /**
     * Get a new color by giving a string as RGB input.
     *
     * @param rgb The RGB string. It must have a formatting like <b>"r, g, b"</b> or <b>"r,g,b"</b>.
     * @return A new color from the given RGB input.
     */
    public static Color rgb(String rgb) {
        rgb = rgb.replace(" ", "");
        String[] split = rgb.split(",");
        int r = Integer.parseInt(split[0]);
        int g = Integer.parseInt(split[1]);
        int b = Integer.parseInt(split[2]);
        return new Color(r, g, b);
    }

}
