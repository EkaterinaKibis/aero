package ru.kibis.dataTypes.loop;

public class CheckPrimeNumber {
    public boolean check(int finish) {
        boolean prime = true;
        int num;

        for (num = 2; num < finish ; num++) {

            if ( (finish % num) == 0) {
                prime = false;
            }
        }
        return prime;
    }
}

