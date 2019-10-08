package ru.kibis.dataTypes.converter;

public class Converter {
    public static int rubletodollar(int value) {

        return value / 60;

    }
    public static int rubletoevro (int value) {
        return value/60;
    }
    public static int evrotoruble (int value){
        return value * 70;
    }
    public static int dollartoruble (int value){
        return value * 60;
    }

    public static void main(String[] args) {
        int euro = rubletoevro(50);
        System.out.println("50 rubles are " + euro + " euro.");
        int dollar = rubletodollar(65);
        System.out.println("65 rubles are " + dollar + " dollar");
        int rublefromdollar = dollartoruble(30);
        System.out.println("30 dollars are " + rublefromdollar + " rubles");
        int rublefromevro = evrotoruble(40);
        System.out.println("40 dollars are " + rublefromevro + " rubles");
    }
}