package ru.kibis.dataTypes.condition;

import static java.lang.Math.pow;

public class SqArea {
    public static double square(int p, int k) {

        return pow(p / 2 * (k + 1), 2) * k;
    }

    public static void main(String[] args) {
        double result1 = square(4, 1);
        System.out.println(" p = 4, k = 1, s = 1, real = " + result1);
    }
}

