package com.implementhing.getir.Shapes;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

/**
 * Created by AlparslanSelçuk on 22.03.2017.
 */

public class Rectangle extends Shape {

    private int width, height;

    public Rectangle(int xPosition, int yPosition, String color, int width, int height) {
        super(xPosition, yPosition, color);
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    @Override
    public void draw(ImageView view, AppCompatActivity context, Bitmap bitmap) {
        Canvas canvas = new Canvas(bitmap);

        Paint paint = new Paint();
        paint.setColor(getColor());
        paint.setStyle(Paint.Style.FILL);
        canvas.drawRect(new Rect(getxPosition(),getyPosition(), getxPosition() + getWidth(), getyPosition() + getHeight() ), paint);
    }
}
