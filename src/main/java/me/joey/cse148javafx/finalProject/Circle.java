package me.joey.cse148javafx.finalProject;

import java.io.Serializable;

public class Circle extends Geometry implements Comparable<Circle>, Cloneable, Serializable {
    private double radius;

    public Circle(double radius, String color){
        super(color);
        this.radius = radius;
    }

    @Override
    public double getArea() {
        return Math.PI * Math.pow(radius, 2);
    }

    @Override
    public int getShape() {
        return 1;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    @Override
    public String toString(){
        return "Area: " + getArea() + ", Radius: " + getRadius() + ", Shape: " + getShape() + ", Color: " + getColor();
    }

    @Override
    public Circle clone() {
        try {
            Circle clone = (Circle) super.clone();
            clone.setRadius(this.getRadius());
            return clone;
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int compareTo(Circle o) {
        if(this.getArea() > o.getArea()) return -1;
        if(this.getArea() < o.getArea()) return 1;
        return 0;
    }
}
