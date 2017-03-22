package com.implementhing.getir.Shapes;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

/**
 * Created by AlparslanSelçuk on 22.03.2017.
 */

public class Circle extends Shape {

    private int radius;

    public Circle(int xPosition, int yPosition, String color, int radius) {
        super(xPosition, yPosition, color);
        this.radius = radius;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public String draw(ImageView view, AppCompatActivity context, Bitmap bitmap) {
        Canvas canvas = new Canvas(bitmap);

        Paint paint = new Paint();
        paint.setColor(getColor());
        paint.setStyle(Paint.Style.FILL);
        int x = getxPosition(), y = getyPosition(), r = getRadius();
        canvas.drawCircle(x, y, r, paint);

        return null;
    }
}
