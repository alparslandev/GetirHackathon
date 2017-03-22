package com.implementhing.getir.Shapes;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.implementhing.getir.ToolBox.Tools;

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

    public abstract void draw(ImageView view, AppCompatActivity activity, Bitmap bitmap);

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
        return Tools.getColor(color, Color.BLACK);
    }

    public void setColor(String color) {
        this.color = color;
    }
}
