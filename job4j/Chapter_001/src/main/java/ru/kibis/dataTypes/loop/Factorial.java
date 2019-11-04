package ru.kibis.dataTypes.loop;

public class Factorial {
    public int calc(int n) {
        int result = 1;
        for (int q = 1; q <= n; q++) {
            result *= q;
        }
        return result;
    }
}
