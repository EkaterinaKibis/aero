package ru.kibis.dataTypes.array;


public class SortSelected {
    public static int[] sort(int[] data) {
        for (int q = 0; q < data.length; q++) {
            int min = MinDiapason.findMin(data, q, data.length);
            int index = FindLoop.indexOf(data, min, q, data.length);
            if (data[q] > data[index]) {
                int r = data[q];
                data[q] = data[index];
                data[index] = r;
            }
        }
        return data;
    }
}
