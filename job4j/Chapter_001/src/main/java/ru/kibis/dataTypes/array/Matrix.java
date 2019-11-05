package ru.kibis.dataTypes.array;

public class Matrix {
    public int[][] multiple(int size) {
        int[][] table = new int[size][size];
        for (int q = 0; q < table.length; q++) {
            for (int s = 0; s < table.length; s++) {
                table[q][s] = ((q + 1) * (s + 1));
            }
        }
        return table;
    }
}
