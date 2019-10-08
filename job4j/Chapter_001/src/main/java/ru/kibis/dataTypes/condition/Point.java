package ru.kibis.dataTypes.condition;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

public class Point {
    public static double distance (int x1, int y1, int x2, int y2){
        return sqrt(pow(x2 - x1, 2) + pow(y2 - y1, 2));
    }

    public static void main(String[] args) {
        double dist = distance(1,2,2,2);
        System.out.println("result (0, 0) to (2, 0)"  +  dist);
    }
}
