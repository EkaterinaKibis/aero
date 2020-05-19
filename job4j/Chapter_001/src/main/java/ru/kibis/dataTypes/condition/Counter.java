package ru.kibis.dataTypes.condition;


public class Counter {

    public int add(int start, int finish) {
        int sum = 0;
            for (int x = start; x <= finish; x++) {
            if (x % 2 == 0) {
                sum = sum + x;
            }
        }
        return sum;
   }
}
