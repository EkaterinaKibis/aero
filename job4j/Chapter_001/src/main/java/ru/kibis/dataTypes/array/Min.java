package ru.kibis.dataTypes.array;

public class Min {
    public static int findMin(int[] array) {
        int min = array[0];
        for (int index = 0; index < array.length; index++) {
            if (array[index] <= min) min = array[index];
        }
        return min;
    }
}
