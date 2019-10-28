package ru.kibis.dataTypes.loop;

public class Mortgage {
    public int year(int amount, int salory, double percent) {
        int year = 0;
        double b =  amount * percent/100 + amount;
        while (b > 0 ) {
            b = b - salory;
            year++;
            Math.ceil(year);
            System.out.println(year);
        }

        return year;
    }

}
