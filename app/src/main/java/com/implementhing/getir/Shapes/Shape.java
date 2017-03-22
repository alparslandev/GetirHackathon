package com.implementhing.getir.Shapes;

import android.graphics.Color;
import android.text.TextUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by AlparslanSelçuk on 22.03.2017.
 */

public abstract class Shape {
    private int xPosition;
    private int yPosition;
    private String color;

    public Shape(int xPosition, int yPosition, String color){
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.color = "#" + color;
    }

    public abstract String draw();

    //Bir Stringin color olup olmadığını kontrol eder. Overload Method
    private int getColor(final String hex, int color) {
        int colorInt = color;
        if (hex != null && !TextUtils.isEmpty(hex) && !hex.equals("")) {
            Pattern pattern = Pattern.compile("^#([A-Fa-f0-9]{6}|[A-Fa-f0-9]{3})$");
            Matcher matcher = pattern.matcher(hex);
            if (matcher.matches())
                colorInt = Color.parseColor(hex);
        }
        return colorInt;
    }

    public int getxPosition() {
        return xPosition;
    }

    public void setxPosition(int xPosition) {
        this.xPosition = xPosition;
    }

    public int getyPosition() {
        return yPosition;
    }

    public void setyPosition(int yPosition) {
        this.yPosition = yPosition;
    }


    public int getColor() {
        return getColor(color, Color.BLACK);
    }

    public void setColor(String color) {
        this.color = color;
    }
}
