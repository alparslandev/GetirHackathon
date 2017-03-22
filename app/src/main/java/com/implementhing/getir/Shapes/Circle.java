package com.implementhing.getir.Shapes;

/**
 * Created by AlparslanSelçuk on 22.03.2017.
 */

public class Circle extends Shape {

    private int radius;
    final double pi = Math.PI;

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

    @Override
    public String draw() {
        return null;
    }
}
