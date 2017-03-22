package com.implementhing.getir.Shapes;

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
    public String draw() {
        return null;
    }
}
