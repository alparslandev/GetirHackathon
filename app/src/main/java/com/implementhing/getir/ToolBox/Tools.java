package com.implementhing.getir.ToolBox;

import android.graphics.Color;
import android.text.TextUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by dh on 22/03/2017.
 */

public class Tools {

    //Bir Stringin color olup olmadığını kontrol eder. Overload Method
    public static int getColor(final String hex, int color) {
        int colorInt = color;
        if (hex != null && !TextUtils.isEmpty(hex) && !hex.equals("")) {
            Pattern pattern = Pattern.compile("^#([A-Fa-f0-9]{6}|[A-Fa-f0-9]{3})$");
            Matcher matcher = pattern.matcher(hex);
            if (matcher.matches())
                colorInt = Color.parseColor(hex);
        }
        return colorInt;
    }
}
