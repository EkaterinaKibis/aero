package ru.kibis.dataTypes.loop;

public class Factorial {
    public int calc(int n) {
        int result = 1;
        int q;
        if (n == 0) {
            result = 1;
        } else
            for (q = 1; q <= n; q++) {
                result = result * q;
            }
        return result;
    }
}
