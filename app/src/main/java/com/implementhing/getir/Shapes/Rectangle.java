package com.implementhing.getir.Shapes;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
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

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public String draw(ImageView view, AppCompatActivity context) {
        Bitmap bitmap = Bitmap.createBitmap((int) context.getWindowManager()
                .getDefaultDisplay().getWidth(), (int) context.getWindowManager()
                .getDefaultDisplay().getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        view.setImageBitmap(bitmap);

        // Circle

        Paint paint = new Paint();
        paint.setColor(getColor());
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawRect(getHeight(), getWidth(),getHeight(), getWidth(), paint);

        return null;
    }
}
