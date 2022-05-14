package me.joey.cse148javafx.finalProject;

import java.io.*;
import java.util.ArrayList;

public class Question2 {

    public static void main(String[] args){
        ArrayList<Geometry> gList = new ArrayList<>();
        ArrayList<Geometry> gList2;
        try(ObjectInputStream objIn = new ObjectInputStream(new FileInputStream("C:\\CSE148JavaFX\\src\\main\\resources\\geometry.dat"))) {
            gList2 = new ArrayList<>(((ArrayList<Geometry>) objIn.readObject()));
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }


        Circle c1 = new Circle(1, "purple");
        Circle c2 = new Circle(2, "orange");
        Circle c3 = new Circle(3, "yellow");
        Rectangle r1 = new Rectangle(1, 2, "green");
        Rectangle r2 = new Rectangle(3, 4, "red");

        gList.add(c1);
        gList.add(c2);
        gList.add(c3);
        gList.add(r1);
        gList.add(r2);
        gList.add(c2.clone());
        gList.add(r1.clone());

        System.out.println(findAverageArea(gList, 1));
        System.out.println(findAverageArea(gList, 4));
        System.out.println(findAverageArea(gList, 2));

        try(ObjectOutputStream objOut = new ObjectOutputStream(new FileOutputStream("C:\\CSE148JavaFX\\src\\main\\resources\\geometry.dat"))) {
            objOut.writeObject(gList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static double findAverageArea(ArrayList<Geometry> gList, int shape){
        double sum = 0.0;


        for(Geometry g: gList){
            if(g.getShape() == shape){
                sum += g.getArea();
            }
            if(shape != 1 && shape != 4){
                sum += g.getArea();
            }

        }

        return sum / gList.size();

    }
}
