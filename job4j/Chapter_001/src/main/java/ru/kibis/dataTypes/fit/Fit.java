package ru.kibis.dataTypes.fit;

public class Fit {
    public static double menweight(double height){
        return (height - 100)* 1.15;
    }
    public static double woumenweight(double height){
        return (height - 110)* 1.15;
    }

    public static void main(String[] args) {
        double men = menweight(180);
        System.out.println("Men 180 is " + men);
        double woumen = woumenweight(160);
        System.out.println("Woumen 160 is " + woumen);
    }
}