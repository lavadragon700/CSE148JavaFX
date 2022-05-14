package me.joey.cse148javafx.finalProject;

import java.io.Serializable;

public abstract class Geometry implements IShape, Serializable {
    private String color;

    public Geometry(String color){
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public abstract double getArea();

    @Override
    public String toString(){
        return this.getShape() + ", " + this.getColor() + ", " + this.getArea();
    }
}
