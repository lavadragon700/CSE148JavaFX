package me.joey.cse148javafx.finalProject;

import java.io.Serializable;

public class Rectangle extends Geometry implements Comparable<Rectangle>, Cloneable, Serializable {
    private double width;
    private double height;

    public Rectangle(double width, double height, String color){
        super(color);
        this.width = width;
        this.height = height;
    }




    @Override
    public double getArea() {
        return getWidth() * getHeight();
    }

    @Override
    public int getShape() {
        return 4;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    @Override
    public boolean equals(Object o){
        if(o == this) return true;
        if(!(o instanceof  Rectangle)) return false;

        Rectangle rect = (Rectangle) o;

        return rect.getWidth() == getWidth() && rect.getHeight() == getHeight() && rect.getColor().equals(getColor());
    }

    @Override
    public int compareTo(Rectangle o) {
        if(this.getArea() > o.getArea()) return -1;
        if(this.getArea() < o.getArea()) return 1;
        return 0;
    }

    @Override
    public Rectangle clone() {
        try {
            Rectangle clone = (Rectangle) super.clone();
            clone.setHeight(this.getHeight());
            clone.setWidth(this.getWidth());
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

    @Override
    public String toString(){
        return "Width: " + getWidth() + ", Height: " + getHeight() + ", Area: " + getArea() + ", Shape: " + getShape() + ", Color: " + getColor();
    }
}
