package ru.kibis.dataTypes.loop;

public class PrimeNumber {
    public int calc(int finish) {
        int count = 0;
        CheckPrimeNumber checker = new CheckPrimeNumber();
        for (int num = 2; num <= finish; num++) {
            if (checker.check(num)) {
                count++;
            }
        }
        return count;
    }
}