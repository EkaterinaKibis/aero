package ru.kibis.dataTypes.loop;

public class PrimeNumber {
    public int calc(int finish) {
        int count = 0;
        for (int num = 2; num <= finish; num++) {
            boolean prime = true;
            for (int i = 2; i < num; i++) {
                if (num % i == 0) {
                    prime = false;
                }
            }
            if (prime) {
                count++;
            }
        }
        return count;
    }
}

